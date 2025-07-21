package de.umg.minai.cqlonomop.repl;

import de.umg.minai.cqlonomop.engine.CQLonOMOPEngine;
import de.umg.minai.cqlonomop.engine.Configuration;
import org.hl7.elm.r1.FunctionDef;
import org.hl7.elm.r1.VersionedIdentifier;
import org.opencds.cqf.cql.engine.debug.DebugResult;
import org.opencds.cqf.cql.engine.exception.CqlException;
import org.opencds.cqf.cql.engine.execution.EvaluationResult;
import org.opencds.cqf.cql.engine.execution.ExpressionResult;
import org.opencds.cqf.cql.engine.execution.Profile;
import org.opencds.cqf.cql.engine.runtime.Tuple;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Evaluator {

    public static class State {
        public int expressionCount = 1;
        public PseudoLibrary pseudoLibrary;
        public Set<Object> contextObjects = new HashSet<>();
        public Map<String, Object> parameterBindings = new HashMap<>();

        public State(final PseudoLibrary pseudoLibrary) {
            this.pseudoLibrary = pseudoLibrary;
        }

        public State(final String omopVersion) {
            this(new PseudoLibrary(omopVersion));
        }

        public State klone() {
            final var result = new State(this.pseudoLibrary.klone());
            result.expressionCount = this.expressionCount;
            result.contextObjects = new HashSet<>(this.contextObjects);
            result.parameterBindings = new HashMap<>(this.parameterBindings);
            return result;
        }

    }

    private final Configuration configuration;

    private static final Set<String> statementKeywords = Set.of("code",
            "codesystem",
            "context",
            "define",
            "include",
            "using");

    private final REPLSourceProvider sourceProvider;

    private final CQLonOMOPEngine engine;

    private State state;

    private boolean isProfiling = false;

    public Evaluator(final Configuration configuration) {
        this.configuration = configuration;
        this.sourceProvider = new REPLSourceProvider();
        this.engine = new CQLonOMOPEngine(configuration, List.of(this.sourceProvider));
        this.state = new State(configuration.getOmopVersion());
        this.state.contextObjects.add("DummyContextObject"); // so that "context Patient" does not fail
    }

    public CQLonOMOPEngine getEngine() {
        return this.engine;
    }

    public void clearLibraryCache() {
        this.engine.getLibraryCache().clear();
    }

    public PseudoLibrary getPseudoLibrary() {
        return this.state.pseudoLibrary;
    }

    public void load(final Path filename) throws IOException {
        this.state.pseudoLibrary.addStatement(Files.readString(filename));
    }

    public void setParameter(final String name, final Object value) {
        if (value == null) {
            this.state.parameterBindings.remove(name);
        } else {
            this.state.parameterBindings.put(name, value);
        }
    }

    public void setParameterToEvaluationResult(final String name, final String valueExpression) {
        final var emptyState = new State(this.state.pseudoLibrary.omopVersion);
        final Object value = internalEvaluate(emptyState, valueExpression);
        withStateUpdateOnSuccess(state -> {
            if (value == null) {
                state.parameterBindings.remove(name);
            } else {
                state.parameterBindings.put(name, value);
            }
            internalEvaluate(state, null);
        });
    }

    public EvaluationResult evaluate(final String command) {
        final var trimmed = command.trim();
        if (statementKeywords.stream().anyMatch(trimmed::startsWith)) {
            if (trimmed.startsWith("include")) {
                return executeCQLIncludeStatement(trimmed);
            } else if (trimmed.startsWith("code") || trimmed.startsWith("codesystem")) {
                return executeCQLPreludeStatement(trimmed);
            } else {
                return executeCQLStatement(trimmed);
            }
        } else {
            return executeCQLExpression(trimmed);
        }
    }

    private EvaluationResult executeCQLIncludeStatement(final String statement) {
        return withStateUpdateOnSuccess(state -> {
            state.pseudoLibrary.addInclude(statement);
            return internalEvaluate(state,null);
        });
    }

    private EvaluationResult executeCQLPreludeStatement(final String statement) {
        return withStateUpdateOnSuccess(state -> {
            state.pseudoLibrary.addPrelude(statement);
            return internalEvaluate(state, null);
        });
    }

    private EvaluationResult executeCQLStatement(final String statement) {
        return withStateUpdateOnSuccess(state -> {
            state.pseudoLibrary.addStatement(statement);
            return internalEvaluate(state, null);
        });
    }

    public EvaluationResult executeCQLExpression(final String expression) {
        return withStateUpdateOnSuccess(state -> {
            final var definitionName = String.format("E%d", state.expressionCount);
            state.expressionCount++;
            final var statement = String.format("define %s: %s", definitionName, expression);
            state.pseudoLibrary.addStatement(statement);
            return internalEvaluate(state, definitionName);
        });
    }

    public Object evaluateExpression(final String expression, final String context, final Set<Object> contextObjects) {
        final var temporaryState = this.state.klone();
        final var definitionName = String.format("Temporary%d", temporaryState.pseudoLibrary.statements.size());
        final var statementBuilder = new StringBuilder();
        if (context != null) {
            statementBuilder.append(String.format("context %s\n", context));
        }
        statementBuilder.append(String.format("define %s: %s\n", definitionName, expression));
        final var statement = statementBuilder.toString();
        temporaryState.pseudoLibrary.addStatement(statement);
        if (contextObjects != null) {
            temporaryState.contextObjects = contextObjects;
        }
        final EvaluationResult result = internalEvaluate(temporaryState, definitionName);
        return result.forExpression(definitionName).value();
    }

    public Object evaluateExpression(final String expression) {
        return evaluateExpression(expression, null, null);
    }

    public Object withProfiling(final Supplier<Object> continuation) {
        final var oldValue = this.isProfiling;
        this.isProfiling = true;
        try {
            return continuation.get();
        } finally {
            this.isProfiling = oldValue;
        }
    }

    public <R> R withStateUpdateOnSuccess(Function<State, R> continuation) {
        final var newState = this.state.klone();
        final R result = continuation.apply(newState);
        this.state = newState;
        return result;
    }

    public void withStateUpdateOnSuccess(Consumer<State> continuation) {
        final var newState = this.state.klone();
        continuation.accept(newState);
        this.state = newState;
    }

    private EvaluationResult internalEvaluate(final State state, final String definitionName) {
        this.engine.setProfiling(this.isProfiling);
        this.sourceProvider.setContent(state.pseudoLibrary.getCode());
        final var libraryName = this.sourceProvider.libraryName();
        // Compile library. The compilation results will be
        // cached. This with parallel evaluation since compilation
        // would happen at the start of each task otherwise.
        //
        // Also, if the compilation fails, an exception is thrown here
        // and evaluation is aborted.
        final var libraries = engine.prepareLibrary(new VersionedIdentifier().withId(libraryName));

        // TODO(jmoringe): describe
        final HashMap<VersionedIdentifier, Set<String>> unfilteredExpressions = new HashMap<>();
        libraries.forEach(library -> {
            if (library.getStatements() != null) {
                library.getStatements().getDef().forEach(definition -> {
                    if (!(definition instanceof FunctionDef)  && definition.getContext().equals("Unfiltered")) {
//                        System.out.printf("%-48s.%-48s %s\n",
//                                library.getIdentifier().getId(),
//                                definition.getName(),
//                                definition.getContext());
                        unfilteredExpressions.compute(
                                library.getIdentifier(),
                                (_library, expressions) -> {
                                    if (expressions == null) {
                                        expressions = new HashSet<>();
                                    }
                                    expressions.add(definition.getName());
                                    return expressions;
                                });
                    }
                });
            }
        });

        final var unfilteredEngine = this.engine.evaluateExpressionsIntoCache(unfilteredExpressions); // TODO(jmoringe): parameters
        final var unfilteredCache = unfilteredEngine.getCache();
        final var unfilteredDebugResult = unfilteredEngine.getState().getDebugResult();
        final var unfilteredProfile = unfilteredDebugResult != null
                ? unfilteredDebugResult.getProfile()
                : null;
        final List<CqlException> unfilteredMessages = unfilteredDebugResult != null
                ? unfilteredDebugResult.getMessages()
                : List.of();

        libraries.forEach(library -> {
            if (library.getStatements() != null) {
                library.getStatements().getDef().forEach(definition -> {
                    if (!(definition instanceof FunctionDef) && definition.getContext().equals("Unfiltered")) {
//                        System.out.printf("%-48s.%-48s %s -> %s\n",
//                                library.getIdentifier().getId(),
//                                definition.getName(),
//                                definition.getContext(),
//                                cache.getCachedExpression(library.getIdentifier(), definition.getName()));
                    }
                });
            }
        });

        // Choose evaluation semantics based on the context object(s):
        // for no object or a single object, evaluate in the current
        // thread right away. For a list of objects, evaluate in
        // parallel using a thread pool and tasks.
        final var contextObjects = state.contextObjects;
        final EvaluationResult result;
        if (contextObjects.isEmpty()) {
            result = engine.evaluateLibrary(libraryName, null, state.parameterBindings, unfilteredCache);
        } else if (contextObjects.size() == 1) {
            result = engine.evaluateLibrary(libraryName,
                    contextObjects.iterator().next(),
                    state.parameterBindings,
                    unfilteredCache);
        } else {
            result = new EvaluationResult();
            if (definitionName != null) { // TODO(jmoringe): when is this null?
                final var allResults = new ConcurrentHashMap<String, Object>();
                final boolean[] anyFailed = {false};
                final int[] count = {0};
                final var pool = this.configuration.getThreadCount() != null
                        ? Executors.newWorkStealingPool(this.configuration.getThreadCount())
                        : Executors.newWorkStealingPool();
                final Profile profile;
                if (this.isProfiling) {
                    final var debugResult = new DebugResult();
                    result.setDebugResult(debugResult);
                    profile = debugResult.ensureProfile();
                } else {
                    profile = null;
                }
                try {
                    contextObjects.forEach(object -> {
                        pool.submit(() -> {
                            final var objectKey = object.toString(); // TODO(jmoringe): ensure uniqueness
                            try {
                                final var results = engine.evaluateLibrary(libraryName,
                                        object,
                                        state.parameterBindings,
                                        unfilteredCache);
                                final var objectResult = results.forExpression(definitionName);
                                // TODO(jmoringe): make this concurrent or just do it after the parallel part
                                if (profile != null) {
                                    long start = System.nanoTime();
                                    synchronized (profile) {
                                        profile.merge(results.getDebugResult().getProfile());
                                        long elapsed = System.nanoTime() - start;
                                        /*if (results.getDebugResult().getProfile().getDuration() > 3_000_000_000L) {
                                            System.out.printf("%s, evaluation %,3d ms, profile merge %,3d ms, overhead %d ms\n",
                                                    object,
                                                    results.getDebugResult().getProfile().getDuration() / 1_000_000,
                                                    elapsed / 1_000_000,
                                                    0/results.getDebugResult().getProfile().getOverhead() / 1_000_000/);
                                        }*/
                                    }
                                }
                                allResults.put(objectKey, objectResult.value());
                            } catch (Exception e) {
                                allResults.put(objectKey, e.toString());
                                synchronized (this) {
                                    anyFailed[0] = true;
                                }
                            }
                            // TODO(jmoringe): wrong
                            var currentCount = 0;
                            synchronized (this) {
                                currentCount = ++count[0];
                            }
                            if (currentCount % 100 == 0) {
                                System.out.printf("%d/%d\n", currentCount, contextObjects.size());
                            }
                        });
                    });
                } finally {
                    try {
                        pool.shutdown();
                        if (!pool.awaitTermination(1000000, TimeUnit.SECONDS)) {
                            System.err.println("Failed to shutdown within allotted time");
                        }
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                }
                final var tuple = new Tuple();
                tuple.setElements(new LinkedHashMap<>(allResults));
                result.expressionResults.put(definitionName, new ExpressionResult(tuple, Set.of()));
            }
        }
        if (!unfilteredMessages.isEmpty()) {
            var debugResult = result.getDebugResult();
            if (debugResult == null) {
                debugResult = new DebugResult();
                result.setDebugResult(debugResult);
            }
            debugResult.getMessages().addAll(unfilteredMessages);
        }
        if (this.isProfiling) {
            result.getDebugResult().getProfile().merge(unfilteredProfile);
        }
        return result;
    }

}

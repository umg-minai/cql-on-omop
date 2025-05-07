package de.umg.minai.cqlonomop.repl;

import OMOP.MappingInfo;
import org.cqframework.cql.cql2elm.DefaultLibrarySourceProvider;
import org.cqframework.cql.cql2elm.LibrarySourceProvider;
import de.umg.minai.cqlonomop.engine.CQLonOMOPEngine;
import de.umg.minai.cqlonomop.engine.Configuration;
import de.umg.minai.cqlonomop.engine.ConnectionFactory;
import org.hl7.elm.r1.FunctionDef;
import org.hl7.elm.r1.VersionedIdentifier;
import org.opencds.cqf.cql.engine.debug.DebugResult;
import org.opencds.cqf.cql.engine.execution.EvaluationResult;
import org.opencds.cqf.cql.engine.execution.ExpressionResult;
import org.opencds.cqf.cql.engine.runtime.Tuple;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Evaluator {

    public static class State {
        public int expressionCount = 1;
        public List<String> include = new LinkedList<>();
        public List<String> prelude = new LinkedList<>();
        public List<String> statements = new LinkedList<>();
        public Set<Object> contextObjects = new HashSet<>();
        public Map<String, Object> parameterBindings = new HashMap<>();
        public EvaluationResult previousResult = null;
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

    public Evaluator(final Configuration configuration) {
        this.configuration = configuration;
        this.sourceProvider = new REPLSourceProvider();
        final List<LibrarySourceProvider> sourceProviders = new LinkedList<>();
        sourceProviders.add(this.sourceProvider);
        configuration.getLibrarySearchPath().forEach(path ->
                sourceProviders.add(new DefaultLibrarySourceProvider(path)));

        final var mappingInfo = MappingInfo.ensureVersion("v5.4"); // TODO(jmoringe): don't hard-code
        final var sessionFactory = ConnectionFactory.createSessionFactory(configuration, mappingInfo);
        this.engine = new CQLonOMOPEngine(sessionFactory, mappingInfo, sourceProviders);
        this.state = new State();
        this.state.contextObjects.add("DummyContextObject"); // so that "context Patient" does not fail
    }

    public CQLonOMOPEngine getEngine() {
        return this.engine;
    }

    public void load(final Path filename) throws IOException {
        this.state.statements.add(Files.readString(filename));
    }

    public void setParameter(final String name, final Object value) {
        if (value == null) {
            this.state.parameterBindings.remove(name);
        } else {
            this.state.parameterBindings.put(name, value);
        }
    }

    public void setParameter(final String name, final String value) {
        // TODO(jmoringe): make evaluateStandaloneExpression or similar
        final var oldState = this.state;
        this.state = new State();
        try {
            final Object result = evaluateExpression(value);
            this.setParameter(name, result);
        } finally {
            this.state = oldState;
        }
    }

    public EvaluationResult evaluate(final String command) {
        final var trimmed = command.trim();
        if (statementKeywords.stream().anyMatch(trimmed::startsWith)) {
            if (trimmed.startsWith("include")) {
                return executeCQLIncludeStatement(trimmed);
            } else if (trimmed.startsWith("code") || trimmed.startsWith("codesystem")) {
                return executeCQLPreludeStatement(trimmed);
            } else {
                return executeCQLStatement(trimmed, null);
            }
        } else {
            return executeCQLExpression(trimmed);
        }
    }

    private EvaluationResult executeCQLIncludeStatement(final String statement) {
        this.state.include.add(statement);
        try {
            return internalEvaluate(null);
        } catch (Exception e) {
            this.state.include.remove(this.state.include.size() - 1);
            throw e;
        }
    }

    private EvaluationResult executeCQLPreludeStatement(final String statement) {
        this.state.prelude.add(statement);
        try {
            return internalEvaluate(null);
        } catch (Exception e) {
            this.state.prelude.remove(this.state.prelude.size() - 1);
            throw e;
        }
    }

    private EvaluationResult executeCQLStatement(final String statement, final String definitionName) {
        this.state.statements.add(statement);
        try {
            this.state.previousResult = internalEvaluate(definitionName);
        } catch (Exception e) {
            this.state.statements.remove(this.state.statements.size() - 1);
            throw e;
        }
        return this.state.previousResult;
    }

    public EvaluationResult executeCQLExpression(final String expression) {
        final var definitionName = String.format("E%d", state.expressionCount);
        this.state.expressionCount++;
        final var statement = String.format("define %s: %s", definitionName, expression);
        return executeCQLStatement(statement, definitionName);
    }

    private String pseudoLibrary(final State state) {
        final var input = new StringBuilder();
        input.append("""
            using "OMOP" version 'v5.4'

            include "OMOPHelpers"
            include "OMOPFunctions"
            """);
        state.include.forEach(statement -> {
            input.append(statement);
            input.append("\n");
        });
        input.append("""
            codesystem LOINC: 'http://loinc.org'

            codesystem OMOPSV: 'https://fhir-terminology.ohdsi.org' // SV = Standardized Vocabulary

            codesystem SNOMED: 'http://snomed.info/sct'

            """);
        state.prelude.forEach(statement -> {
            input.append(statement);
            input.append("\n");
        });
        state.statements.forEach(statement -> {
            input.append(statement);
            input.append("\n");
        });
        return input.toString();
    }

    private String pseudoLibrary() {
        return pseudoLibrary(this.state);
    }

    private EvaluationResult internalEvaluate(final String definitionName) {
        this.sourceProvider.setContent(pseudoLibrary());
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
                        System.out.printf("%-48s.%-48s %s\n",
                                library.getIdentifier().getId(),
                                definition.getName(),
                                definition.getContext());
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
        final var cache = this.engine.evaluateExpressions(unfilteredExpressions); // TODO(jmoringe): parameters

        libraries.forEach(library -> {
            if (library.getStatements() != null) {
                library.getStatements().getDef().forEach(definition -> {
                    if (!(definition instanceof FunctionDef) && definition.getContext().equals("Unfiltered")) {
                        System.out.printf("%-48s.%-48s %s -> %s\n",
                                library.getIdentifier().getId(),
                                definition.getName(),
                                definition.getContext(),
                                cache.getCachedExpression(library.getIdentifier(), definition.getName()));
                    }
                });
            }
        });


        // Choose evaluation semantics based on the context object(s):
        // for no object or a single object, evaluate in the current
        // thread right away. For a list of objects, evaluate in
        // parallel using a thread pool and tasks.
        final var contextObjects = this.state.contextObjects;
        final EvaluationResult result;
        if (contextObjects.isEmpty()) {
            result = engine.evaluateLibrary(libraryName, null, this.state.parameterBindings);
        } else if (contextObjects.size() == 1) {
            result = engine.evaluateLibrary(libraryName,
                    contextObjects.iterator().next(),
                    this.state.parameterBindings);
        } else {
            result = new EvaluationResult();
            if (state.previousResult != null) {
                result.expressionResults.putAll(state.previousResult.expressionResults);
            }
            if (definitionName != null) { // TODO(jmoringe): when is this null?
                final var allResults = new ConcurrentHashMap<String, Object>();
                final boolean[] anyFailed = {false};
                final int[] count = {0};
                final var pool = this.configuration.getThreadCount() != null
                        ? Executors.newWorkStealingPool(this.configuration.getThreadCount())
                        : Executors.newWorkStealingPool();
                if (result.getDebugResult() == null) {
                    result.setDebugResult(new DebugResult());
                }
                final var profile = result.getDebugResult().getProfile();
                try {
                    contextObjects.forEach(object -> {
                        pool.submit(() -> {
                            final var objectKey = object.toString(); // TODO(jmoringe): ensure uniqueness
                            try {
                                final var results = engine.evaluateLibrary(libraryName,
                                    object,
                                    this.state.parameterBindings);
                                final var objectResult = results.forExpression(definitionName);
                                // TODO(jmoringe): make this concurrent
                                synchronized (profile) {
                                    profile.merge(results.getDebugResult().getProfile());
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
        return result;
    }

    public Object evaluateExpression(final String expression, final String context, final Set<Object> contextObjects) {
        final var definitionName = String.format("Temporary%d", this.state.statements.size());
        final var statementBuilder = new StringBuilder();
        if (context != null) {
            statementBuilder.append(String.format("context %s\n", context));
        }
        statementBuilder.append(String.format("define %s: %s\n",
                definitionName, expression));
        final var statement = statementBuilder.toString();
        final var oldContextObjects = this.state.contextObjects;
        if (contextObjects != null) {
            this.state.contextObjects = contextObjects;
        }
        this.state.statements.add(statement);
        final EvaluationResult result;
        try {
            result = internalEvaluate(definitionName);
        } finally {
            this.state.statements.remove(this.state.statements.size() - 1);
            this.state.contextObjects = oldContextObjects;
        }
        return result.forExpression(definitionName).value();
    }

    public Object evaluateExpression(final String expression) {
        return evaluateExpression(expression, null, null);
    }

    public Object withoutState(final Function<State, Object> continuation) {
        final var oldState = this.state;
        this.state = new State();
        try {
            return continuation.apply(oldState);
        } finally {
            this.state = oldState;
        }
    }

}

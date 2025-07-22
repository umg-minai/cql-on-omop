package de.umg.minai.cqlonomop.repl;

import de.umg.minai.cqlonomop.engine.CQLonOMOPEngine;
import de.umg.minai.cqlonomop.engine.Configuration;
import de.umg.minai.cqlonomop.engine.MapReduceEngine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.BiFunction;
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

    private static final Set<String> statementKeywords = Set.of("code",
            "codesystem",
            "context",
            "define",
            "include",
            "using");

    private final REPLSourceProvider sourceProvider;

    private final MapReduceEngine engine;

    private State state;

    private boolean isProfiling = false;

    public Evaluator(final Configuration configuration) {
        this.sourceProvider = new REPLSourceProvider();
        this.engine = new MapReduceEngine(configuration, List.of(this.sourceProvider));
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
        final Object value = evaluateExpression(valueExpression);
        withStateUpdateOnSuccess(state -> {
            if (value == null) {
                state.parameterBindings.remove(name);
            } else {
                state.parameterBindings.put(name, value);
            }
            internalEvaluate(state,
                    (ignored1, ignored2) -> 0,
                    intermediate -> 0);
        });
    }

    public <R, I> R evaluate(final String command,
                             final BiFunction<Object, MapReduceEngine.Outcome, I> mapper,
                             final Function<Map<Object, I>, R> reducer) {
        final var trimmed = command.trim();
        if (statementKeywords.stream().anyMatch(trimmed::startsWith)) {
            if (trimmed.startsWith("include")) {
                return executeCqlIncludeStatement(trimmed, mapper, reducer);
            } else if (trimmed.startsWith("code") || trimmed.startsWith("codesystem")) {
                return executeCqlPreludeStatement(trimmed, mapper, reducer);
            } else {
                return executeCqlStatement(trimmed, mapper, reducer);
            }
        } else {
            return executeCqlExpression(trimmed, mapper, reducer);
        }
    }

    private <R, I> R executeCqlIncludeStatement(final String statement,
                                                final BiFunction<Object, MapReduceEngine.Outcome, I> mapper,
                                                final Function<Map<Object, I>, R> reducer) {
        return withStateUpdateOnSuccess(state -> {
            state.pseudoLibrary.addInclude(statement);
            return internalEvaluate(state, mapper, reducer);
        });
    }

    private <R, I> R executeCqlPreludeStatement(final String statement,
                                                final BiFunction<Object, MapReduceEngine.Outcome, I> mapper,
                                                final Function<Map<Object, I>, R> reducer) {
        return withStateUpdateOnSuccess(state -> {
            state.pseudoLibrary.addPrelude(statement);
            return internalEvaluate(state, mapper, reducer);
        });
    }

    private <R, I> R executeCqlStatement(final String statement,
                                         final BiFunction<Object, MapReduceEngine.Outcome, I> mapper,
                                         final Function<Map<Object, I>, R> reducer) {
        return withStateUpdateOnSuccess(state -> {
            state.pseudoLibrary.addStatement(statement);
            return internalEvaluate(state, mapper, reducer);
        });
    }

    private <R, I> R executeCqlExpression(final String expression,
                                          final BiFunction<Object, MapReduceEngine.Outcome, I> mapper,
                                          final Function<Map<Object, I>, R> reducer) {
        return withStateUpdateOnSuccess(state -> {
            final var definitionName = String.format("E%d", state.expressionCount);
            state.expressionCount++;
            final var statement = String.format("define %s: %s", definitionName, expression);
            state.pseudoLibrary.addStatement(statement);
            final BiFunction<Object, MapReduceEngine.Outcome, I> filteringMapper = (contextObject, outcome) -> {
                if (outcome instanceof MapReduceEngine.Outcome.Success success) {
                    final var results = success.result().expressionResults;
                    final var resultObject = results.get(definitionName);
                    results.clear();
                    if (contextObject != null) {
                        results.put(definitionName, resultObject);
                    }
                }
                return mapper.apply(contextObject, outcome);
            };
            return internalEvaluate(state, filteringMapper, reducer);
        });
    }

    public Object evaluateExpression(final String expression, final String context, final Set<Object> contextObjects) {
        // Set up a state with a pseudo-library based on expression, context and contextObjects.
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
        // Evaluate the pseudo-library with the specified context, then extract and return the evaluation result.
        return internalEvaluate(temporaryState,
                (ignored, outcome) -> outcome,
                intermediate -> {
                    final var values = intermediate.values();
                    assert values.size() == 1;
                    final var theOutcome = values.iterator().next();
                    if (theOutcome instanceof MapReduceEngine.Outcome.Success success) {
                        final var expressionResults = success.result().expressionResults;
                        final var resultKey = String.format("%s.%s", this.sourceProvider.libraryName(), definitionName);
                        return expressionResults.get(resultKey).value();
                    } else if (theOutcome instanceof MapReduceEngine.Outcome.Failure failure) {
                        throw (RuntimeException) failure.error();
                    }
                    throw new RuntimeException("unreachable");
                });
    }

    public Object evaluateExpression(final String expression) {
        return evaluateExpression(expression, null, null);
    }

    public <R> R withProfiling(final Supplier<R> continuation) {
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

    private <R, I> R internalEvaluate(final State state,
                                      final BiFunction<Object, MapReduceEngine.Outcome, I> mapper,
                                      final Function<Map<Object, I>, R> reducer) {
        this.engine.setProfiling(this.isProfiling);
        this.sourceProvider.setContent(state.pseudoLibrary.getCode());
        final var libraryName = this.sourceProvider.libraryName();

        return this.engine.prepareAndEvaluateLibraryMapReduce(libraryName,
                state.contextObjects,
                state.parameterBindings,
                mapper,
                reducer);
    }

}

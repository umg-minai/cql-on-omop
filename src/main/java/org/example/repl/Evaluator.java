package org.example.repl;

import OMOP.MappingInfo;
import org.cqframework.cql.cql2elm.DefaultLibrarySourceProvider;
import org.cqframework.cql.cql2elm.LibrarySourceProvider;
import org.cqframework.cql.cql2elm.PriorityLibrarySourceLoader;
import org.cqframework.cql.cql2elm.StringLibrarySourceProvider;
import org.example.engine.CQLonOMOPEngine;
import org.example.engine.Configuration;
import org.example.engine.ConnectionFactory;
import org.opencds.cqf.cql.engine.execution.EvaluationResult;
import org.opencds.cqf.cql.engine.execution.ExpressionResult;
import org.opencds.cqf.cql.engine.runtime.Date;
import org.opencds.cqf.cql.engine.runtime.Tuple;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Evaluator {

    private Configuration configuration;

    private static class State {
        public int expressionCount = 1;
        public List<String> include = new LinkedList<>();
        public List<String> prelude = new LinkedList<>();
        public List<String> statements = new LinkedList<>();
        public Set<Object> contextObjects = new HashSet<>();
        public Map<String, Object> parameterBindings = new HashMap<>();
        public EvaluationResult previousResult = null;
    }

    private static final Set<String> statementKeywords = Set.of("code",
            "codesystem",
            "context",
            "define",
            "include",
            "using");

    private final REPLSourceProvider sourceProvider;

    // private final OMOPDataProvider dataProvider;

    private final CQLonOMOPEngine engine;

    private State state;

    public Evaluator(final Configuration configuration) {
        this.configuration = configuration;
        this.sourceProvider = new REPLSourceProvider();
        final List<LibrarySourceProvider> sourceProviders = new LinkedList<>();
        sourceProviders.add(this.sourceProvider);
        configuration.getLibrarySearchPath().forEach(path ->
                sourceProviders.add(new DefaultLibrarySourceProvider(path)));

        final var mappingInfo = MappingInfo.ensureVersion("v5.4");
        final var sessionFactory = ConnectionFactory.createSessionFactory(configuration, mappingInfo);
        //this.dataProvider = OMOPDataProvider.fromSessionFactory(sessionFactory, mappingInfo);
        this.engine = new CQLonOMOPEngine(sessionFactory, mappingInfo, sourceProviders);
        this.state = new State();
        this.state.contextObjects.add("DummyContextObject"); // so that "context Patient" does not fail
    }

    public void load(final Path filename) throws IOException {
        this.state.statements.add(Files.readString(filename));
    }

    public void setParameter(final String name, final Object value) {
        this.state.parameterBindings.put(name, value);
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

    public EvaluationResult evaluate(final String command) throws Exception {
        final var trimmed = command.trim();
        if (trimmed.startsWith(",")) {
            executeMetaCommand(trimmed);
            return null;
        } else if (statementKeywords.stream().anyMatch(trimmed::startsWith)) {
            if (trimmed.startsWith("include")) {
                executeCQLIncludeStatement(trimmed);
            } else if (trimmed.startsWith("code") || trimmed.startsWith("codesystem")) {
                executeCQLPreludeStatement(trimmed);
            } else {
                executeCQLStatement(trimmed, null);
            }
            return null;
        } else {
            return executeCQLExpression(trimmed);
        }
    }

    private void executeMetaCommand(final String string) throws Exception {
        final var commandAndArguments = string.substring(1).split("[ \t]+", 2);
        final var command = commandAndArguments[0];
        //final var argument = Arrays.copyOfRange(commandAndArguments, 1, commandAndArguments.length);
        final var argument = commandAndArguments.length == 2 ? commandAndArguments[1] : null;
        switch (command) {
            case "quit" -> {
                //assert arguments.length == 0;
                // TODO: not implemented
            }
            case "set" -> {
                final var parameterAndExpression = argument.split(" ", 2);
                commandSet(parameterAndExpression[0], parameterAndExpression[1]);
            }
            case "unset" -> {
                commandUnset(argument);
            }
            case "focus" -> {
                //assert arguments.length == 1;
                commandFocus(argument);
            }
            case "graph" -> {
                final var filenameAndExpression = argument.split(" ", 2);
                commandGraph(filenameAndExpression[1], filenameAndExpression[0]);
            }
            default -> throw new RuntimeException(String.format("Unknown command %s", command));
        }
    }

    private void executeCQLIncludeStatement(final String statement) {
        this.state.include.add(statement);
        try {
            internalEvaluate(null);
        } catch (Exception e) {
            this.state.include.remove(this.state.include.size() - 1);
            throw e;
        }
    }

    private void executeCQLPreludeStatement(final String statement) {
        this.state.prelude.add(statement);
        try {
            internalEvaluate(null);
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

    private EvaluationResult executeCQLExpression(final String expression) {
        final var definitionName = String.format("E%d", state.expressionCount);
        this.state.expressionCount++;
        final var statement = String.format("define %s: %s", definitionName, expression);
        return executeCQLStatement(statement, definitionName);
    }

    private EvaluationResult internalEvaluate(final String definitionName) {
        final var input = new StringBuilder();
        input.append("""
            using "OMOP" version 'v5.4'
            
            include "OMOPHelpers"
            """);
        this.state.include.forEach(statement -> {
            input.append(statement);
            input.append("\n");
        });
        input.append("""
            codesystem LOINC: 'http://loinc.org'
            
            codesystem OMOPSV: 'https://fhir-terminology.ohdsi.org' // SV = Standardized Vocabulary 
            
            codesystem SNOMED: 'http://snomed.info/sct'
                                    
            """);
        this.state.prelude.forEach(statement -> {
            input.append(statement);
            input.append("\n");
        });
        this.state.statements.forEach(statement -> {
            input.append(statement);
            input.append("\n");
        });
        this.sourceProvider.setContent(input.toString());
        final var libraryName = this.sourceProvider.libraryName();
        final var contextObjects = this.state.contextObjects;
        final EvaluationResult result;
        if (contextObjects.isEmpty()) {
            result = engine.evaluateLibrary(libraryName, this.state.parameterBindings);
        } else if (contextObjects.size() == 1) {
            result = engine.evaluateLibrary(libraryName,
                    contextObjects.iterator().next(),
                    this.state.parameterBindings);
        } else {
            result = new EvaluationResult();
            if (state.previousResult != null) {
                result.expressionResults.putAll(state.previousResult.expressionResults);
            }
            if (definitionName != null) {
                final var allResults = new ConcurrentHashMap<String, Object>();
                final int[] count = {0};
                final var pool = this.configuration.getThreadCount() != null
                        ? Executors.newWorkStealingPool(this.configuration.getThreadCount())
                        : Executors.newWorkStealingPool();
                try {
                    contextObjects.forEach(object -> {
                        pool.submit(() -> {
                            synchronized (this) {
                                System.out.printf("[%s] + %d/%d (%s)\n",
                                        Thread.currentThread(),
                                        ++count[0],
                                        contextObjects.size(),
                                        object);
                            }
                            final var objectKey = object.toString(); // TODO(jmoringe): ensure uniqueness
                            try {
                                final var results = engine.evaluateLibrary(libraryName,
                                    object,
                                    this.state.parameterBindings);
                                final var objectResult = results.forExpression(definitionName);
                                allResults.put(objectKey, objectResult.value());
                            } catch (Exception e) {
                                allResults.put(objectKey, e.toString());
                            }
                            // TODO(jmoringe): wrong
                            synchronized (this) {
                                System.out.printf("[%s] - %d/%d (%s)\n",
                                        Thread.currentThread(),
                                        --count[0],
                                        contextObjects.size(),
                                        object);
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

    private void commandSet(final String parameter, final String expression) {
        Object object;
        final var oldState = this.state;
        this.state = new State();
        try {
            object = evaluateExpression(expression, "Unfiltered", new HashSet<>());
            System.out.printf("Assigning %s <- %s\n", parameter, object);
        } finally {
            this.state = oldState;
        }
        this.state.parameterBindings.put(parameter, object);
    }

    private void commandUnset(final String parameter) {
        System.out.printf("Removing binding for %s\n", parameter);
        this.state.parameterBindings.remove(parameter);
    }

    private void commandFocus(final String expression) {
        final var oldState = this.state;
        this.state = new State();
        try {
            final var object = evaluateExpression(expression, "Unfiltered", new HashSet<>());
            System.out.print("Focussing on ");
            if (object instanceof Iterable<?> iterable) {
                oldState.contextObjects = new HashSet<>();
                iterable.forEach(element -> {
                    System.out.printf("%s, ", element);
                    oldState.contextObjects.add(element);
                });
                System.out.println();
            } else {
                System.out.printf("%s\n", object);
                oldState.contextObjects = Set.of(object);
            }
        } finally {
            this.state = oldState;
        }
    }

    private void commandGraph(final String expression, final String filename) throws IOException {
        System.out.printf("Attempting to produce graph for %s%n", filename);
        final var object = evaluateExpression(expression);
        if (object instanceof Tuple tuple) {
            final var counts = new HashMap<java.util.Date, Integer>();
            for (Object dates : tuple.getElements().values()) {
                if (dates instanceof List<?> list) {
                    for (Object date : list) {
                        if (date instanceof Date date1) {
                            // CQL engine Dates don't have a hashCode method
                            final var javaDate = date1.toJavaDate();
                            counts.compute(javaDate, (_date, count) -> (count != null ? count : 0) + 1);
                        }
                    }
                }
            }
            System.out.printf("Attempting to write graph to %s%n", filename);
            try (var file = Files.newBufferedWriter(Path.of(filename))) {
                final var dates = counts.keySet().stream().sorted().toList();
                java.util.Date previous = null;
                for (java.util.Date date : dates) {
                    if (previous != null && (date.getTime() - previous.getTime()) > 1000 * 24 * 60 * 60) {
                        file.write("\n");
                    }
                    file.write(String.format("%s-%s-%s %d\n",
                            1900 + date.getYear(),
                            1 + date.getMonth(),
                            date.getDate(),
                            counts.get(date)));
                    previous = date;
                }
            }
        }
    }

    private Object evaluateExpression(final String expression, final String context, final Set<Object> contextObjects) {
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

    private Object evaluateExpression(final String expression) {
        return evaluateExpression(expression, null, null);
    }

}

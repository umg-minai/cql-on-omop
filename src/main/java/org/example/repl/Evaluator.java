package org.example.repl;

import OMOP.MappingInfo;
import org.example.engine.CQLonOMOPEngine;
import org.opencds.cqf.cql.engine.execution.EvaluationResult;

import java.util.Arrays;
import java.util.Set;

public class Evaluator {

    private static class State {
        public int iteration = 1;
        public Object contextObject = null;
    }

    private static final Set<String> statementKeywords = Set.of("code",
            "codesystem",
            "context",
            "define",
            "include",
            "using");

    private final CQLonOMOPEngine engine;

    private final State state;

    public Evaluator() {
        final var modelInfo = MappingInfo.ensureVersion("v5.4");
        this.engine = new CQLonOMOPEngine(modelInfo, new REPLSourceProvider());
        this.state = new State();
    }

    public EvaluationResult evaluate(final String command) {
        final var trimmed = command.trim();
        if (trimmed.startsWith(",")) {
            executeMetaCommand(trimmed);
            return null;
        } else if (statementKeywords.stream().anyMatch(trimmed::startsWith)) {
            executeCQLStatement(trimmed);
            return null;
        } else {
            return executeCQLExpression(trimmed);
        }
    }

    private void executeMetaCommand(final String string) {
        final var commandAndArguments = string.substring(1).split("[ \t]+");
        final var command = commandAndArguments[0];
        final var arguments = Arrays.copyOfRange(commandAndArguments, 1, commandAndArguments.length);
        switch (command) {
            case "quit" -> {
                assert arguments.length == 0;
            }
            case "focus" -> {
                assert arguments.length == 1;
                System.out.printf("Focussing on %s%n", arguments[0]);
                state.contextObject = arguments[0];
            }
        }
    }

    private void executeCQLStatement(final String statement) {

    }

    private EvaluationResult executeCQLExpression(final String expression) {
        final var input = String.format("""
                        library REPL%s version '1.0.0'
                        using "OMOP" version 'v5.4'
                        
                        codesystem "LOINC": 'http://loinc.org'
                        context Patient
                        define E: %s
                        """, state.iteration, expression);
        EvaluationResult result = null;
        result = engine.evaluateLibrary(input);
        return result;
    }

}

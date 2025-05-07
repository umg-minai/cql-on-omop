package org.example.repl.command;

import org.example.repl.Evaluator;
import org.opencds.cqf.cql.engine.execution.EvaluationResult;

import java.util.Collection;
import java.util.List;

public class CommandUnset extends AbstractCommand{

    private final Evaluator evaluator;

    public CommandUnset(final Evaluator evaluator) {
        super("unset");
        this.evaluator = evaluator;
    }

    @Override
    public String getDescription() {
        return "Make a CQL prameter have no value";
    }

    @Override
    public Collection<String> getArguments() {
        return List.of("PARAMETER");
    }

    @Override
    public EvaluationResult run(final String arguments) throws Exception {
        final var parameter = arguments;
        System.out.printf("Removing binding for %s\n", parameter);
        this.evaluator.setParameter(parameter, null);
        return null;
    }

}

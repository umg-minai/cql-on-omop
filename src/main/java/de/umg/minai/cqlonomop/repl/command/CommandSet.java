package de.umg.minai.cqlonomop.repl.command;

import de.umg.minai.cqlonomop.repl.Evaluator;
import org.opencds.cqf.cql.engine.execution.EvaluationResult;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class CommandSet extends AbstractCommand {

    private final Evaluator evaluator;

    public CommandSet(final Evaluator evaluator) {
        super("set");
        this.evaluator = evaluator;
    }

    @Override
    public String getDescription() {
        return "Evaluate expression and set CQL parameter to result";
    }

    @Override
    public Collection<String> getArguments() {
        return List.of("PARAMETER", "EXPRESSION");
    }

    @Override
    public void run(final String arguments) throws Exception {
        final var parameterAndExpression = arguments.split(" ", 2);
        final var parameter = parameterAndExpression[0];
        final var expression = parameterAndExpression[1];
        final var object = this.evaluator.evaluateExpression(expression, "Unfiltered", new HashSet<>());
        System.out.printf("Assigning %s <- %s\n", parameter, object);
        this.evaluator.setParameter(parameter, object);
    }

}

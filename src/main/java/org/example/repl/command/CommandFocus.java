package org.example.repl.command;

import org.example.repl.Evaluator;
import org.opencds.cqf.cql.engine.execution.EvaluationResult;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommandFocus extends AbstractCommand {

    private final Evaluator evaluator;

    public CommandFocus(final Evaluator evaluator) {
        super("focus");
        this.evaluator = evaluator;
    }

    @Override
    public String getDescription() {
        return "Set CQL context value";
    }

    @Override
    public Collection<String> getArguments() {
        return List.of("EXPRESSION");
    }

    @Override
    public EvaluationResult run(String arguments) throws Exception {
        final var expression = arguments;
        this.evaluator.withoutState((oldState) -> {
            final var object = this.evaluator.evaluateExpression(expression, "Unfiltered", new HashSet<>());
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
            return object;
        });
        return null;
    }

}

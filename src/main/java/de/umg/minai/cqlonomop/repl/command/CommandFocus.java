package de.umg.minai.cqlonomop.repl.command;

import de.umg.minai.cqlonomop.repl.Evaluator;
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
    public void run(String arguments) throws Exception {
        final var expression = arguments;
        final var object = this.evaluator.evaluateExpression(expression, "Unfiltered", new HashSet<>());
        System.out.print("Focussing on ");
        this.evaluator.withStateUpdateOnSuccess(state -> {
            if (object instanceof Iterable<?> iterable) {
                state.contextObjects = new HashSet<>();
                iterable.forEach(element -> {
                    System.out.printf("%s, ", element);
                    state.contextObjects.add(element);
                });
                System.out.println();
            } else {
                System.out.printf("%s\n", object);
                state.contextObjects = Set.of(object);
            }
        });
    }

}

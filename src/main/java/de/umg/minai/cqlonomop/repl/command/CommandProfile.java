package de.umg.minai.cqlonomop.repl.command;

import de.umg.minai.cqlonomop.repl.Evaluator;
import org.opencds.cqf.cql.engine.execution.EvaluationResult;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

public class CommandProfile extends AbstractCommand {

    private final Evaluator evaluator;

    public CommandProfile(final Evaluator evaluator) {
        super("profile");
        this.evaluator = evaluator;
    }

    @Override
    public String getDescription() {
        return "Collect profile while evaluating expression and write profile to given file";
    }

    @Override
    public Collection<String> getArguments() {
        return List.of("FILENAME", "EXPRESSION");
    }

    @Override
    public EvaluationResult run(final String arguments) throws IOException {
        final var filenameAndExpression = arguments.split(" ", 2);
        final var filename = filenameAndExpression[0];
        final var expression = filenameAndExpression[1];
        final var result = evaluator.evaluate(expression);
        result.getDebugResult().getProfile().render(Path.of(filename));
        return result;
    }

}

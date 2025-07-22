package de.umg.minai.cqlonomop.repl.command;

import de.umg.minai.cqlonomop.engine.MapReduceEngine;
import de.umg.minai.cqlonomop.repl.Evaluator;
import de.umg.minai.cqlonomop.terminal.OutcomePresenter;
import org.opencds.cqf.cql.engine.debug.DebugResult;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

public class CommandProfile extends AbstractCommand {

    private final Evaluator evaluator;

    private final OutcomePresenter outcomePresenter;

    public CommandProfile(final Evaluator evaluator, final OutcomePresenter outcomePresenter) {
        super("profile");
        this.evaluator = evaluator;
        this.outcomePresenter = outcomePresenter;
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
    public void run(final String arguments) throws IOException {
        final var filenameAndExpression = arguments.split(" ", 2);
        final var filename = filenameAndExpression[0];
        final var expression = filenameAndExpression[1];
        final var debugResult = evaluator.withProfiling(() ->
            evaluator.evaluate(expression,
                    (contextObject, outcome) -> {
                        synchronized (this) {
                            this.outcomePresenter.presentOutcome(contextObject, outcome);
                        }
                        return outcome;
                    },
                    intermediate -> {
                        final var result = new DebugResult();
                        intermediate.forEach((contextObject, outcome) -> {
                            if (outcome instanceof MapReduceEngine.Outcome.Success success) {
                                final var oneDebugResult = success.result().getDebugResult();
                                if (oneDebugResult != null) {
                                    final var profile = oneDebugResult.getProfile();
                                    if (profile != null) {
                                        result.ensureProfile().merge(profile);
                                    }
                                }
                            }
                        });
                        return result;
                    }));
        System.out.printf("Writing profile to %s\n", filename);
        debugResult.getProfile().render(Path.of(filename));
    }

}

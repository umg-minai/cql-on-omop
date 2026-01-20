package de.umg.minai.cqlonomop;

import de.umg.minai.cqlonomop.batch.Batch;
import de.umg.minai.cqlonomop.batch.ResultSinkCommandAdapter;
import de.umg.minai.cqlonomop.repl.Repl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.logging.Level;

@CommandLine.Command(
        name = "cql-on-omop",
        version = "cql-on-omop 0.1",
        subcommands = { Batch.class, Repl.class },
        mixinStandardHelpOptions = true
)
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    private static class ExecutionStrategy implements CommandLine.IExecutionStrategy {

        private final CommandLine.IExecutionStrategy runLast = new CommandLine.RunLast();

        @Override
        public int execute(CommandLine.ParseResult parseResult) {
            final var subParseResult = parseResult.subcommand();
            // If no sub-command has been selected, let the default execution strategy take care of displaying the
            // appropriate usage text.
            if (subParseResult == null) {
                return this.runLast.execute(parseResult);
            } else {
                // Get the CommandLine of the first-level sub-command for execution.
                final var commandLine = subParseResult.commandSpec().commandLine();
                final var command = commandLine.getCommand();
                final var subSubParseResult = subParseResult.subcommand();
                try {
                    if (command instanceof Callable<?>) {
                        assert subSubParseResult == null;
                        return ((Callable<Integer>) commandLine.getCommand()).call();
                    } else {
                        final var subSubCommand = subSubParseResult != null
                                ? (ResultSinkCommandAdapter) subSubParseResult.commandSpec().commandLine().getCommand()
                                : null;
                        return ((Function<ResultSinkCommandAdapter, Integer>) commandLine.getCommand()).apply(subSubCommand);
                    }
                } catch (Exception e) {
                    throw new CommandLine.ExecutionException(commandLine, "Error while running command (" + commandLine.getCommandName() + "): " + e, e);
                }
            }
        }
    }

    public static void main(String[] args) {
        final var logManager = java.util.logging.LogManager.getLogManager();
        logManager.getLogger("").setLevel(Level.WARNING);
        // Make a CommandLine instance with sub-commands that processes sub-commands on the first level so that deeper
        // sub-commands can be used for options without confusing the overall execution.
        final var exitCode = new CommandLine(new Main())
                .setExecutionStrategy(new ExecutionStrategy())
                .execute(args);
        System.exit(exitCode);
    }

}

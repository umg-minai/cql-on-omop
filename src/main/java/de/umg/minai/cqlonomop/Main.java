package de.umg.minai.cqlonomop;

import de.umg.minai.cqlonomop.batch.Batch;
import de.umg.minai.cqlonomop.repl.Repl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Level;

@CommandLine.Command(
        name = "cql-on-omop",
        version = "cql-on-omop 0.1",
        subcommands = {
                Batch.class,
                Repl.class
        },
        mixinStandardHelpOptions = true
)
public class Main implements Callable<Integer> {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        final var logManager = java.util.logging.LogManager.getLogManager();
        logManager.getLogger("").setLevel(Level.WARNING);
        final var exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        System.out.printf("Available sub-commands: %s%n", List.of("repl, main")); // TODO(jmoringe): obtain this programatically
        return CommandLine.ExitCode.USAGE;
    }

}

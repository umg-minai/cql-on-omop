package de.umg.minai.cqlonomop;

import de.umg.minai.cqlonomop.batch.Batch;
import de.umg.minai.cqlonomop.repl.Repl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.util.logging.Level;

@CommandLine.Command(
        name = "cql-on-omop",
        version = "cql-on-omop 0.1",
        subcommands = { Batch.class, Repl.class },
        mixinStandardHelpOptions = true
)
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        final var logManager = java.util.logging.LogManager.getLogManager();
        logManager.getLogger("").setLevel(Level.WARNING);
        final var exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }

}

package de.umg.minai.cqlonomop;

import de.umg.minai.cqlonomop.batch.Batch;
import de.umg.minai.cqlonomop.repl.Repl;
import picocli.CommandLine;

import java.util.List;
import java.util.concurrent.Callable;

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

    public static void main(String[] args) {
        final var exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        System.out.printf("Available sub-commands: %s%n", List.of("repl, main")); // TODO(jmoringe): obtain this programatically
        return CommandLine.ExitCode.USAGE;
    }

}

package de.umg.minai.cqlonomop.commandline;

import de.umg.minai.cqlonomop.engine.Configuration;
import picocli.CommandLine;

public class ExecutionOptions {

    @CommandLine.Option(
            names = {"-n", "--threads"},
            paramLabel = "<thread-count>",
            description = "Use the specified number of threads when evaluating CQL expressions for multiple context values (typically patients) in parallel. If this option is not supplied, try to determine a suitable number of threads automatically. Default: ${DEFAULT-VALUE}"
    )
    public Integer threadCount;

    public Configuration applyToConfiguration(final Configuration configuration) {
        return configuration.withThreadCount(this.threadCount);
    }

}
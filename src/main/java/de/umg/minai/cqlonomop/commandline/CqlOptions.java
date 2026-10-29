package de.umg.minai.cqlonomop.commandline;

import de.umg.minai.cqlonomop.engine.Configuration;
import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CqlOptions {

    public enum OmopVersion {
        OMOP_CDM_V53("v5.3"),
        OMOP_CDM_V54("v5.4"),
        OMOP_CDM_V54_MIMIC("v5.4.MIMIC");

        public final String versionString;

        OmopVersion(final String versionString) {
            this.versionString = versionString;
        }

    }

    private static class OmopVersionConverter implements CommandLine.ITypeConverter<OmopVersion> {

        @Override
        public OmopVersion convert(final String value) throws Exception {
            return Arrays.stream(OmopVersion.values())
                    .filter(element -> element.versionString.equals(value))
                    .findFirst()
                    .orElseThrow(() -> {
                        final var message = new StringBuilder(String.format("Invalid OMOP Version: '%s'; valid version are ", value));
                        final boolean[] first = {true};
                        Arrays.stream(OmopVersion.values())
                                .forEach(v -> {
                                    if (first[0]) {
                                        first[0] = false;
                                    } else {
                                        message.append(", ");
                                    }
                                    message.append(v.versionString);
                                });
                        return new RuntimeException(message.toString());
                    });
        }

    }

    @Option(
            names = {"--omop-version"},
            paramLabel = "<omop-version>",
            description = """
                          The version of the OMOP CDM that should be selected as the CQL data model.
                          CQL library code should say `using OMOP version 'v5.4'` regardless of the version selected \
                          here.
                          Default: ${DEFAULT-VALUE}
                          """,
            converter = OmopVersionConverter.class
    )
    public OmopVersion omopVersion;

    @Option(
            names = {"-I"},
            paramLabel = "<directory>",
            description = """
                          Search the specified directory for CQL libraries when processing CQL include statements.
                          This option can be supplied multiple times.
                          """
    )
    public List<Path> librarySearchPath = List.of();

    @Option(
            names = {"-l", "--load"},
            paramLabel = "<library-file>",
            description = "Load the specified file as CQL code."
    )
    public List<Path> load = List.of();

    @Option(
            names = {"-D"},
            description = """
                          Bind parameters of the CQL library to specific values.
                          A binding is of the form NAME=EXPRESSION where EXPRESSION is a CQL expression that is \
                          evaluated to produce the CQL value that is assigned to the parameter named NAME.
                          """
    )
    public Map<String, String> parameterBindings = Map.of();

    @Option(names = {"--retrieve-max-retry-count"},
            paramLabel = "attempt-count",
            description = """
                          The maximum number of times a failed retrieve operation should be retried.
                          A non-zero number of retries may be useful for unreliable database connections.
                          Defaults to 0 which means retrieve operations are not retried.
                          """)
    public int retrieveMaxRetryCount = 0;

    @Option(names = {"--retrieve-retry-delay"},
            paramLabel = "time-in-milliseconds",
            description = """
                          Time in milliseconds to wait before retrying a failed retrieve operation.
                          Waiting before retrying may be used to try and take load off a heavily loaded and \
                          intermittently failing database.
                          This option has no effect unless --retrieve-max-retry-count is non-zero.
                          Note: There is no additional backoff for multiple retries at the moment.
                          """)
    public int retrieveRetryDelayMS = 0;

    @Option(names = {"--context-object-max-retry-count"},
            paramLabel = "attempt-count",
            description = """
                          The maximum number of times the evaluation for a given context object should be retried.
                          A non-zero number of retries may be useful for unreliable database connections that cannot \
                          recover by retrying individual queries. Instead, this option causes the whole evaluation \
                          for the context object in question to be retried, including a new database connection.
                          Defaults to 0 which means that context object evaluations are not retried.
                          """)
    public int contextObjectMaxRetryCount = 0;

    // TODO(jmoringe): context and focus

    public Configuration applyToConfiguration(final Configuration configuration) {
        return configuration
                .withOmopVersion(this.omopVersion.versionString)
                .withLibrarySearchPath(this.librarySearchPath)
                .withRetrieveMaxRetryCount(this.retrieveMaxRetryCount)
                .withRetrieveRetryDelayMS(this.retrieveRetryDelayMS)
                .withContextObjectMaxRetryCount(this.contextObjectMaxRetryCount);
    }

}

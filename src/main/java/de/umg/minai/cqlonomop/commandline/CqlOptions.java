package de.umg.minai.cqlonomop.commandline;

import de.umg.minai.cqlonomop.engine.Configuration;
import picocli.CommandLine.Option;

import java.nio.file.Path;
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

    @Option(
            names = {"--omop-version"},
            paramLabel = "<omop-version>",
            description = "TODO"
    )
    public OmopVersion omopVersion;

    @Option(
            names = {"-I"},
            paramLabel = "<directory>",
            description = "Search the specified directory for CQL libraries when processing CQL include statements. This option can be supplied multiple times."
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
            description = "Bind parameters of the CQL library to specific values. A binding is of the form NAME=EXPRESSION where EXPRESSION is a CQL expression that is evaluated to produce the CQL value that is assigned to the parameter named NAME. "
    )
    public Map<String, String> parameterBindings = Map.of();

    // TODO(jmoringe): context and focus

    public Configuration applyToConfiguration(final Configuration configuration) {
        return configuration
                .withOmopVersion(this.omopVersion.versionString)
                .withLibrarySearchPath(this.librarySearchPath);
    }

}

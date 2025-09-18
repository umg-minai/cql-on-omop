package de.umg.minai.cqlonomop.commandline;

import de.umg.minai.cqlonomop.engine.Configuration;
import picocli.CommandLine;

public class DefaultValueProvider implements CommandLine.IDefaultValueProvider {

    @Override
    public String defaultValue(final CommandLine.Model.ArgSpec argSpec) {
        if (argSpec instanceof CommandLine.Model.OptionSpec optionSpec && optionSpec.longestName().equals("--threads")) {
            return String.valueOf(Configuration.defaultThreadCount());
        } else if (argSpec instanceof CommandLine.Model.OptionSpec optionSpec && optionSpec.longestName().equals("--omop-version")) {
            return Configuration.DEFAULT_OMOP_VERSION;
        } else {
            return null;
        }
    }

}

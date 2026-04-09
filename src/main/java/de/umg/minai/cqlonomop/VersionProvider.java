package de.umg.minai.cqlonomop;

import picocli.CommandLine;

public class VersionProvider implements CommandLine.IVersionProvider {

    @Override
    public String[] getVersion() throws Exception {
        final var mainPackage = Main.class.getPackage();
        final var name = mainPackage.getImplementationTitle();
        final var version = mainPackage.getImplementationVersion();

        return new String[]{
            String.format("%s %s", name, version)
        };
    }

}

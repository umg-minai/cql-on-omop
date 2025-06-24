package de.umg.minai.cqlonomop.engine;

import org.cqframework.cql.cql2elm.LibrarySourceProvider;
import org.hl7.elm.r1.VersionedIdentifier;

import java.io.InputStream;

/**
 * This class provides the built-in OMOPHelpers and OMOPFunctions libraries as a library source.
 */
public class BuiltinLibrariesSourceProvider implements LibrarySourceProvider {

    private final String omopVersion;

    public BuiltinLibrariesSourceProvider(final String omopVersion) {
        this.omopVersion = omopVersion;
    }

    @Override
    public InputStream getLibrarySource(final VersionedIdentifier versionedIdentifier) {
        // TODO: check version
        final var id = versionedIdentifier.getId();
        if (id.equals("OMOPHelpers") || id.equals("OMOPFunctions")) {
            return tryResource(id + omopVersion);
        }
        else {
            return null;
        }
    }

    private InputStream tryResource(final String basename) {
        final var name = String.format("/de/umg/minai/cqlonomop/%s.cql", basename);
        return BuiltinLibrariesSourceProvider.class.getResourceAsStream(name);
    }

}

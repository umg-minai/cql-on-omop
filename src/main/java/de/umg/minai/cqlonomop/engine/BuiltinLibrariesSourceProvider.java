package de.umg.minai.cqlonomop.engine;

import org.cqframework.cql.cql2elm.LibrarySourceProvider;
import org.hl7.elm.r1.VersionedIdentifier;

import java.io.InputStream;

/**
 * This class provides the built-in OMOPHelpers library as a library source.
 */
public class BuiltinLibrariesSourceProvider implements LibrarySourceProvider {

    @Override
    public InputStream getLibrarySource(final VersionedIdentifier versionedIdentifier) {
        // TODO: check version
        if (versionedIdentifier.getId().equals("OMOPHelpers")) {
            return BuiltinLibrariesSourceProvider.class.getResourceAsStream("/de/umg/minai/cqlonomop/OMOPHelpers.cql");
        } else if (versionedIdentifier.getId().equals("OMOPFunctions")) {
            return BuiltinLibrariesSourceProvider.class.getResourceAsStream("/de/umg/minai/cqlonomop/OMOPFunctions.cql");
        }
        else {
            return null;
        }
    }

}

package org.example.engine;

import org.cqframework.cql.cql2elm.LibrarySourceProvider;
import org.hl7.elm.r1.VersionedIdentifier;

import java.io.InputStream;

public class BuiltinLibrariesSourceProvider implements LibrarySourceProvider {

    @Override
    public InputStream getLibrarySource(final VersionedIdentifier versionedIdentifier) {
        // TODO: check version
        if (versionedIdentifier.getId().equals("OMOPHelpers")) {
            return BuiltinLibrariesSourceProvider.class.getResourceAsStream("/org/example/OMOPHelpers.cql");
        } else {
            return null;
        }
    }

}

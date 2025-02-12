package org.example.engine;

import org.cqframework.cql.cql2elm.LibrarySourceProvider;
import org.hl7.elm.r1.VersionedIdentifier;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class InMemoryLibrarySourceProvider implements LibrarySourceProvider {

    private final Map<VersionedIdentifier, String> libraries = new HashMap<>();

    public void registerLibrary(final VersionedIdentifier identifier, final String content) {
        libraries.put(identifier, content);
    }

    @Override
    public InputStream getLibrarySource(final VersionedIdentifier versionedIdentifier) {
        final String content = libraries.get(versionedIdentifier);
        if (content != null) {
            return new ByteArrayInputStream(content.getBytes());
        } else {
            return null;
        }
    }

}

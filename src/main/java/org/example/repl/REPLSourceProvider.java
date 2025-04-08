package org.example.repl;

import org.cqframework.cql.cql2elm.LibrarySourceProvider;
import org.hl7.elm.r1.VersionedIdentifier;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class REPLSourceProvider implements LibrarySourceProvider {

    private String replContent = null;

    private int replCount = 0;

    public String libraryName() {
        return String.format("REPL%s", this.replCount);
    }

    public void setContent(final String replContent) {
        this.replContent = replContent;
        this.replCount++;
    }

    @Override
    public InputStream getLibrarySource(final VersionedIdentifier libraryIdentifier) {
        if (libraryIdentifier.getId().equals(libraryName())) {
            final var replLibrary = String.format("library REPL%s version '1.0.0'\n%s", replCount, replContent);
            return new ByteArrayInputStream(replLibrary.getBytes(StandardCharsets.UTF_8));
        } else {
            return null;
        }
    }

}

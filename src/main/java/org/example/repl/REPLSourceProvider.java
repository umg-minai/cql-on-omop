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

    public int setReplContent(String replContent) {
        this.replContent = replContent;
        return ++this.replCount;
    }

    @Override
    public InputStream getLibrarySource(final VersionedIdentifier libraryIdentifier) {
        if (libraryIdentifier.getId().equals(String.format("REPL%s", this.replCount))) {
            return new ByteArrayInputStream(replContent.getBytes(StandardCharsets.UTF_8));
        }
        String path =
                switch (libraryIdentifier.getId()) {
                    case "Test" -> "/home/jan/code/cql/cql-on-omop/cql/test.cql";
                    default ->
                            throw new IllegalStateException("Unexpected value: " + libraryIdentifier.getId());
                };
            /*switch (libraryIdentifier.getId()) {
                case "MINAICDS" -> "/home/jan/code/cql/cql-example/input/cql/MINAICDS.cql";
                case "FHIRHelpers" -> "/home/jan/code/cql/clinical_quality_language/Src/java/quick/src/main/resources/org/hl7/fhir/FHIRHelpers-4.0.1.cql";
                default -> throw new IllegalStateException("Unexpected value: " + libraryIdentifier.getId());
            };*/
        try {
            return new FileInputStream(Path.of(path).toFile());
        } catch (FileNotFoundException e) {
            return null;
        }
    }

}

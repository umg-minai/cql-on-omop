package org.example;

import org.apache.commons.lang3.tuple.Pair;
import org.cqframework.cql.cql2elm.LibraryManager;
import org.cqframework.cql.cql2elm.ModelManager;
import org.hl7.elm.r1.VersionedIdentifier;
import org.opencds.cqf.cql.engine.data.DataProvider;
import org.opencds.cqf.cql.engine.execution.CqlEngine;
import org.opencds.cqf.cql.engine.execution.Environment;
import org.opencds.cqf.cql.engine.execution.EvaluationResult;
import org.opencds.cqf.cql.engine.terminology.TerminologyProvider;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Map;

public class CQLonOMOPEngine {

    private final ModelManager modelManager = new ModelManager(Path.of("/home/jan/code/cql/cql-example/input/cql/"));

    private final LibrarySourceProvider librarySourceProvider = new LibrarySourceProvider();
    private final LibraryManager libraryManager;

    private final Map<String, DataProvider> dataProviders;
    private final TerminologyProvider terminologyProvider;
    private final Environment environment;
    private final CqlEngine engine;

    private class LibrarySourceProvider implements org.cqframework.cql.cql2elm.LibrarySourceProvider {

        private String replContent = null;

        private int replCount = 0;

        public int setReplContent(String replContent) {
            this.replContent = replContent;
            return ++this.replCount;
        }

        @Override
        public InputStream getLibrarySource(VersionedIdentifier libraryIdentifier) {
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

    public CQLonOMOPEngine() {
        modelManager.getModelInfoLoader().registerModelInfoProvider(new OMOPModelInfoProvider(), true);
        this.libraryManager = new LibraryManager(modelManager);
        this.libraryManager.getLibrarySourceLoader().registerProvider(librarySourceProvider);
        final var modelResolver = new OMOPModelResolver();
        this.dataProviders = Map.of("urn:ohdsi:omop-types:r5.4",
                new OMOPDataProvider(modelResolver));
        this.terminologyProvider = new OMOPTerminologyProvider();
        this.environment = new Environment(libraryManager, dataProviders, terminologyProvider);
        this.engine = new CqlEngine(environment);
    }

    public EvaluationResult evaluateLibrary(final String library, final Object patient) {
        engine.getState().getDebugResult().getMessages().forEach(System.err::println);
        return engine.evaluate("Test",
                Pair.of("Patient", patient));
    }

    public EvaluationResult evaluateLibrary(final String library) {
        return evaluateLibrary(library, null);
    }

    public EvaluationResult evaluateExpression(final String expression) {
        final var i = this.librarySourceProvider.setReplContent(expression);
        final var result = engine.evaluate(String.format("REPL%s", i)/*,
                Pair.of("Patient", null)*/);
        engine.getState().getDebugResult().getMessages().forEach(System.err::println);
        return result;
    }

}
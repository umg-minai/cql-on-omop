package org.example;

import org.cqframework.cql.cql2elm.LibraryManager;
import org.cqframework.cql.cql2elm.LibrarySourceProvider;
import org.cqframework.cql.cql2elm.ModelManager;
import org.hl7.cql.model.ModelIdentifier;
import org.hl7.cql.model.ModelInfoProvider;
import org.hl7.elm.r1.VersionedIdentifier;
import org.hl7.elm_modelinfo.r1.ModelInfo;
import org.opencds.cqf.cql.engine.data.DataProvider;
import org.opencds.cqf.cql.engine.execution.CqlEngine;
import org.opencds.cqf.cql.engine.execution.Environment;
import org.opencds.cqf.cql.engine.runtime.Code;
import org.opencds.cqf.cql.engine.runtime.Interval;
import org.opencds.cqf.cql.engine.terminology.CodeSystemInfo;
import org.opencds.cqf.cql.engine.terminology.TerminologyProvider;
import org.opencds.cqf.cql.engine.terminology.ValueSetInfo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Map;

public class Main {

    static private DataProvider createOMOPDataProvider() {
        return new DataProvider() {
            @Override
            public String getPackageName() {
                return "";
            }

            @Override
            public void setPackageName(String s) {

            }

            @Override
            public Object resolvePath(Object o, String s) {
                return null;
            }

            @Override
            public Object getContextPath(String s, String s1) {
                return null;
            }

            @Override
            public Class<?> resolveType(String s) {
                return null;
            }

            @Override
            public Class<?> resolveType(Object o) {
                return null;
            }

            @Override
            public Boolean is(Object o, Class<?> aClass) {
                return null;
            }

            @Override
            public Object as(Object o, Class<?> aClass, boolean b) {
                return null;
            }

            @Override
            public Object createInstance(String s) {
                return null;
            }

            @Override
            public void setValue(Object o, String s, Object o1) {

            }

            @Override
            public Boolean objectEqual(Object o, Object o1) {
                return null;
            }

            @Override
            public Boolean objectEquivalent(Object o, Object o1) {
                return null;
            }

            @Override
            public String resolveId(Object o) {
                return "";
            }

            @Override
            public Iterable<Object> retrieve(String s, String s1, Object o, String s2, String s3, String s4, Iterable<Code> iterable, String s5, String s6, String s7, String s8, Interval interval) {
                return null;
            }
        };
    }

    private static TerminologyProvider createOMOPTerminologyProvider() {
        return new TerminologyProvider() {
            @Override
            public boolean in(Code code, ValueSetInfo valueSetInfo) {
                return false;
            }

            @Override
            public Iterable<Code> expand(ValueSetInfo valueSetInfo) {
                return null;
            }

            @Override
            public Code lookup(Code code, CodeSystemInfo codeSystemInfo) {
                return null;
            }
        };
    }

    public static void main(String[] args) {
        final var modelManager = new ModelManager(Path.of("/home/jan/code/cql/cql-example/input/cql/"));
        // modelManager.getModelInfoLoader().registerModelInfoProvider(new SystemModelInfoProvider());
        modelManager.getModelInfoLoader().registerModelInfoProvider(new ModelInfoProvider() {
            @Override
            public ModelInfo load(ModelIdentifier modelIdentifier) {
                return null;
            }
        }, true);

        final var libraryManager = new LibraryManager(modelManager);
        libraryManager.getLibrarySourceLoader().registerProvider(new LibrarySourceProvider() {
            @Override
            public InputStream getLibrarySource(VersionedIdentifier libraryIdentifier) {
                String path =
                switch (libraryIdentifier.getId()) {
                    case "Test" -> "/home/jan/code/cql/cql-on-omop/cql/test.cql";
                    default -> throw new IllegalStateException("Unexpected value: " + libraryIdentifier.getId());
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
        });

        final var dataProviders = Map.of("omop", createOMOPDataProvider());
        final var terminologyProvider = createOMOPTerminologyProvider();
        final var environment = new Environment(libraryManager, dataProviders, terminologyProvider);
        final var engine = new CqlEngine(environment);
        final var result = engine.evaluate("Test");
        result.expressionResults.forEach((expressionName, expressionResult) ->
                System.out.printf("%s => %s\n%n", expressionName, expressionResult.value()));
        System.out.print(result);
    }

}
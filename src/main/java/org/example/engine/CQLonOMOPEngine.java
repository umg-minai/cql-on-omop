package org.example.engine;

import jakarta.persistence.EntityManager;
import org.apache.commons.lang3.tuple.Pair;
import org.cqframework.cql.cql2elm.LibraryManager;
import org.cqframework.cql.cql2elm.LibrarySourceProvider;
import org.cqframework.cql.cql2elm.ModelManager;
import org.opencds.cqf.cql.engine.data.DataProvider;
import org.opencds.cqf.cql.engine.execution.CqlEngine;
import org.opencds.cqf.cql.engine.execution.Environment;
import org.opencds.cqf.cql.engine.execution.EvaluationResult;
import org.opencds.cqf.cql.engine.terminology.TerminologyProvider;

import java.nio.file.Path;
import java.util.Map;

public class CQLonOMOPEngine {

    private final ModelManager modelManager = new ModelManager(Path.of("/home/jan/code/cql/cql-example/input/cql/"));

    private final LibraryManager libraryManager;

    private final Map<String, DataProvider> dataProviders;
    private final TerminologyProvider terminologyProvider;
    private final Environment environment;
    private final CqlEngine engine;

    public CQLonOMOPEngine(final OMOPDataProvider dataProvider, final LibrarySourceProvider librarySourceProvider) {
        modelManager.getModelInfoLoader().registerModelInfoProvider(new OMOPModelInfoProvider(), true);

        this.libraryManager = new LibraryManager(modelManager);
        final var loader = this.libraryManager.getLibrarySourceLoader();
        loader.registerProvider(new BuiltinLibrariesSourceProvider());
        loader.registerProvider(librarySourceProvider);

        this.dataProviders = Map.of("urn:ohdsi:omop-types:r5.4", dataProvider);

        this.terminologyProvider = new OMOPTerminologyProvider();

        this.environment = new Environment(libraryManager, dataProviders, terminologyProvider);
        this.engine = new CqlEngine(environment);
    }

    public CQLonOMOPEngine(final EntityManager entityManager, final LibrarySourceProvider librarySourceProvider) {
        this(OMOPDataProvider.fromEntityManager(entityManager), librarySourceProvider);
    }

    public CQLonOMOPEngine(final LibrarySourceProvider librarySourceProvider) {
        this(new OMOPDataProvider(), librarySourceProvider);
    }

    public EvaluationResult evaluateLibrary(final String library, final Object patient) {
        //engine.getState().getDebugResult().getMessages().forEach(System.err::println);
        return engine.evaluate(library, Pair.of("Patient", patient));
    }

    public EvaluationResult evaluateLibrary(final String library) {
        return evaluateLibrary(library, null);
    }

    public EvaluationResult evaluateExpression(final String expression) {
        /*final var i = this.librarySourceProvider.setReplContent(expression);
        final var result = engine.evaluate(String.format("REPL%s", i),
                Pair.of("Patient", null));
        engine.getState().getDebugResult().getMessages().forEach(System.err::println);
        return result;*/
        return null;
    }

}
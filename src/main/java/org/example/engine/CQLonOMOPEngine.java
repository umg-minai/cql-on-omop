package org.example.engine;

import OMOP.MappingInfo;
import org.apache.commons.lang3.tuple.Pair;
import org.cqframework.cql.cql2elm.LibraryManager;
import org.cqframework.cql.cql2elm.LibrarySourceProvider;
import org.cqframework.cql.cql2elm.ModelManager;
import org.hibernate.SessionFactory;
import org.opencds.cqf.cql.engine.data.DataProvider;
import org.opencds.cqf.cql.engine.execution.CqlEngine;
import org.opencds.cqf.cql.engine.execution.Environment;
import org.opencds.cqf.cql.engine.execution.EvaluationResult;
import org.opencds.cqf.cql.engine.terminology.TerminologyProvider;

import java.nio.file.Path;
import java.util.Map;

public class CQLonOMOPEngine {

    // TODO(jmoringe): do something better
    private final ModelManager modelManager = new ModelManager(Path.of("."));

    private final LibraryManager libraryManager;

    private final TerminologyProvider terminologyProvider;
    //private final Environment environment;

    private SessionFactory sessionFactory;

    private MappingInfo mappingInfo;

    // TODO(jmoringe): remove
    private Map<String, DataProvider> dataProviders;

    public CQLonOMOPEngine(final OMOPDataProvider dataProvider,
                           final LibrarySourceProvider librarySourceProvider) {
        modelManager.getModelInfoLoader().registerModelInfoProvider(new OMOPModelInfoProvider(), true);

        this.libraryManager = new LibraryManager(modelManager);
        final var loader = this.libraryManager.getLibrarySourceLoader();
        loader.registerProvider(new BuiltinLibrariesSourceProvider());
        loader.registerProvider(librarySourceProvider);

        /*this.dataProviders = Map.of(
                // TODO String.format("urn:ohdsi:omop-types:r5.4", dataProvider.getModelInfo().getVersion()),
                "urn:ohdsi:omop-types:v5.4",
                dataProvider);*/

        this.terminologyProvider = new OMOPTerminologyProvider();

        //this.environment = new Environment(libraryManager, dataProviders, terminologyProvider);
        //this.engine = new CqlEngine(environment);
    }

    public CQLonOMOPEngine(final SessionFactory sessionFactory,
                           final MappingInfo mappingInfo,
                           final LibrarySourceProvider librarySourceProvider) {
        //this(OMOPDataProvider.fromEntityManager(entityManager, mappingInfo), librarySourceProvider);
        this(null /*OMOPDataProvider.fromSessionFactory(sessionFactory, mappingInfo)*/, librarySourceProvider);
        this.sessionFactory = sessionFactory;
        this.mappingInfo = mappingInfo;
    }

    /*public CQLonOMOPEngine(final Configuration configuration,
                           final MappingInfo mappingInfo,
                           final LibrarySourceProvider librarySourceProvider) {
        this(new OMOPDataProvider(configuration, mappingInfo), librarySourceProvider);
    }*/

    public EvaluationResult evaluateLibrary(final String library,
                                            final Object contextObject,
                                            final Map<String, Object> parameterBindings) {
        //final var dataProvider = OMOPDataProvider.fromSessionFactory(this.sessionFactory, this.mappingInfo);
        try (var session = this.sessionFactory.openSession();
             var entityManager = session.getEntityManagerFactory().createEntityManager()) {
            // final var dataProvider = OMOPDataProvider.fromSessionFactory(this.sessionFactory, this.mappingInfo);
            final var dataProvider = OMOPDataProvider.fromEntityManager(entityManager, this.mappingInfo);
            final var dataProviders = Map.<String, DataProvider>of(
                    // TODO String.format("urn:ohdsi:omop-types:r5.4", dataProvider.getModelInfo().getVersion()),
                    "urn:ohdsi:omop-types:v5.4",
                    dataProvider);
            final var environment = new Environment(this.libraryManager, dataProviders, this.terminologyProvider);
            //engine.getState().getDebugResult().getMessages().forEach(System.err::println);
            final var engine = new CqlEngine(environment);
            if (contextObject == null) {
                return engine.evaluate(library, parameterBindings);
            } else {
                // TODO(jmoringe): can we know the name of the context?
                return engine.evaluate(library,
                        Pair.of("Patient", contextObject),
                        parameterBindings);
            }
        }
    }

    public EvaluationResult evaluateLibrary(final String library, final Object contextObject) {
        return evaluateLibrary(library, contextObject, Map.of());
    }

    public EvaluationResult evaluateLibrary(final String library) {
        return evaluateLibrary(library, null, Map.of());
    }

}
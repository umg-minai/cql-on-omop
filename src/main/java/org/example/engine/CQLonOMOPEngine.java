package org.example.engine;

import OMOP.MappingInfo;
import org.apache.commons.lang3.tuple.Pair;
import org.cqframework.cql.cql2elm.*;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.proxy.HibernateProxy;
import org.opencds.cqf.cql.engine.data.DataProvider;
import org.opencds.cqf.cql.engine.execution.CqlEngine;
import org.opencds.cqf.cql.engine.execution.Environment;
import org.opencds.cqf.cql.engine.execution.EvaluationResult;
import org.opencds.cqf.cql.engine.terminology.TerminologyProvider;

import java.beans.BeanDescriptor;
import java.beans.PropertyDescriptor;
import java.nio.file.Path;
import java.util.*;

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

        CqlCompilerOptions options = CqlCompilerOptions.defaultOptions()
                .withSignatureLevel(LibraryBuilder.SignatureLevel.All);
        this.libraryManager = new LibraryManager(modelManager, options);
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
            final var result = contextObject == null
                ? engine.evaluate(library, parameterBindings)
                : engine.evaluate(library,
                        Pair.of("Patient", contextObject), // TODO(jmoringe): can we know the name of the context?
                        parameterBindings);
            result.expressionResults.forEach((name, expressionResult) -> {
                initializeProxies(expressionResult.value(), new HashSet<>());
            });
            return result;
        }
    }

    private void initializeProxies(final Object object, final HashSet<Object> seen) {
        object.toString(); // HACK(jmoringe): force some initialization

        /*if (!seen.contains(object)) {
            System.out.printf("Checking %s\n", object);
            seen.add(object);
            if (object instanceof HibernateProxy proxy) {
                System.out.printf("Is a proxy %s\n", object);
                if (!Hibernate.isInitialized(proxy)) {
                    System.out.printf("Is not initialized %s\n", object);
                    Hibernate.initialize(proxy);
                } else {
                    System.out.printf("Is initialized %s\n", object);
                }
                /final var d = new BeanDescriptor(proxy.getClass());
                if (proxy instanceof Collection<?> collection) {
                    collection.forEach(element -> initializeProxies(element, seen));
                }/
            } else if (object instanceof Iterable<?> iterable) {
                for (var element : iterable) {
                    initializeProxies(element, seen);
                }
            } else if (object instanceof Optional<?> optional) {
                optional.ifPresent(value -> initializeProxies(value, seen));
            } else {
                System.out.printf("Not a proxy %s\n", object);
            }
            if (object.getClass().getPackageName().contains("OMOP")) {
                // TODO(jmoringe): copied from ResultPresenter
                final var clazz = object.getClass();
                Arrays.stream(clazz.getMethods())
                        .filter(method -> method.getName().startsWith("get")
                                && !method.getName().equals("getClass"))
                        .forEach(method -> {
                            final var fieldName = method.getName().substring(3, 4).toLowerCase(Locale.ROOT)
                                    + method.getName().substring(4);
                            Object fieldValue;
                            try {
                                fieldValue = method.invoke(object);
                            } catch (Exception e) {
                                fieldValue = String.format("error accessing field: %s", e);
                            }
                            initializeProxies(fieldValue, seen);
                        });
            }
        }*/
    }

    public EvaluationResult evaluateLibrary(final String library, final Object contextObject) {
        return evaluateLibrary(library, contextObject, Map.of());
    }

    public EvaluationResult evaluateLibrary(final String library) {
        return evaluateLibrary(library, null, Map.of());
    }

}
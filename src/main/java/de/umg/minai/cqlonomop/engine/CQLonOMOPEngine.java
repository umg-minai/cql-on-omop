package de.umg.minai.cqlonomop.engine;

import OMOP.MappingInfo;
import jakarta.persistence.EntityManager;
import org.apache.commons.lang3.tuple.Pair;
import org.cqframework.cql.cql2elm.*;
import org.cqframework.cql.cql2elm.model.CompiledLibrary;
import org.cqframework.cql.cql2elm.model.Model;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hl7.cql.model.ModelIdentifier;
import org.hl7.cql.model.NamespaceManager;
import org.hl7.elm.r1.Library;
import org.hl7.elm.r1.VersionedIdentifier;
import org.opencds.cqf.cql.engine.data.DataProvider;
import org.opencds.cqf.cql.engine.execution.Cache;
import org.opencds.cqf.cql.engine.execution.CqlEngine;
import org.opencds.cqf.cql.engine.execution.Environment;
import org.opencds.cqf.cql.engine.execution.EvaluationResult;
import org.opencds.cqf.cql.engine.terminology.TerminologyProvider;

import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;

public class CQLonOMOPEngine {

    public record EngineSession(CqlEngine cqlEngine, Session session, EntityManager entityManager) {}

    private final ModelIdentifier modelIdentifier;

    // TODO(jmoringe): do something better
    private final ModelManager modelManager = new ModelManager(Path.of("."));

    private final LibraryManager libraryManager;

    private final Map<VersionedIdentifier, CompiledLibrary> libraryCache = new HashMap<>();

    private final TerminologyProvider terminologyProvider;

    private SessionFactory sessionFactory;

    private MappingInfo mappingInfo;

    private boolean isProfiling = false;

    public CQLonOMOPEngine(final String version, final List<LibrarySourceProvider> librarySourceProviders) {
        this.modelIdentifier = new ModelIdentifier().withId("OMOP").withVersion(version);
        this.modelManager.getModelInfoLoader().registerModelInfoProvider(new OMOPModelInfoProvider(), true);

        final var options = CqlCompilerOptions.defaultOptions()
                .withSignatureLevel(LibraryBuilder.SignatureLevel.All);
                //.withOptions(CqlCompilerOptions.Options.EnableDetailedErrors);
        this.libraryManager = new LibraryManager(modelManager, options, libraryCache);
        final var loader = this.libraryManager.getLibrarySourceLoader();
        loader.registerProvider(new BuiltinLibrariesSourceProvider(version));
        librarySourceProviders.forEach(loader::registerProvider);
        this.terminologyProvider = new OMOPTerminologyProvider();
    }

    public CQLonOMOPEngine(final String version,
                           final SessionFactory sessionFactory,
                           final MappingInfo mappingInfo,
                           final List<LibrarySourceProvider> librarySourceProviders) {
        this(version, librarySourceProviders);
        this.sessionFactory = sessionFactory;
        this.mappingInfo = mappingInfo;
    }

    private static List<LibrarySourceProvider> computeSourceProvider(final Configuration configuration,
                                                                     final List<LibrarySourceProvider> additionalLibrarySourceProviders) {
        final var sourceProviders = new LinkedList<LibrarySourceProvider>();
        configuration.getLibrarySearchPath().forEach(path ->
                sourceProviders.add(new DefaultLibrarySourceProvider(path)));
        sourceProviders.addAll(additionalLibrarySourceProviders);
        return sourceProviders;
    }

    public CQLonOMOPEngine(final Configuration configuration,
                           final List<LibrarySourceProvider> additionalLibrarySourceProviders) {
        this(configuration.getOmopVersion(), computeSourceProvider(configuration, additionalLibrarySourceProviders));
        this.mappingInfo = MappingInfo.ensureVersion(configuration.getOmopVersion());
        this.sessionFactory = ConnectionFactory.createSessionFactory(configuration, mappingInfo);
    }

    public CQLonOMOPEngine(final Configuration configuration) {
        this(configuration, List.of());
    }

    public Model getModel(final ModelIdentifier modelIdentifier) {
        return this.modelManager.resolveModel(modelIdentifier);
    }

    public Model getModel(final String id) {
        return getModel(new ModelIdentifier().withId(id));
    }

    public Model getModel() {
        return getModel(this.modelIdentifier);
    }

    public LibraryManager getLibraryManager() {
        return this.libraryManager;
    }

    public Map<VersionedIdentifier, CompiledLibrary> getLibraryCache() { return this.libraryCache; }

    public SessionFactory getSessionFactory() { // TODO(jmoringe): maybe remove later
        return this.sessionFactory;
    }

    public boolean isProfiling() {
        return this.isProfiling;
    }

    public void setProfiling(boolean isProfiling) {
        this.isProfiling = isProfiling;
    }

    public List<Library> prepareLibrary(final VersionedIdentifier libraryIdentifier) {
        // "Compilation" proceeds despite errors, collect errors in a list.
        final var errors = new ArrayList<CqlCompilerException>();
        final var result = new LinkedList<Library>();
        prepareOneLibrary(libraryIdentifier, errors, result);
        // Compilation failures are not indicated directly (as far as
        // I can tell) but via a non-empty list of errors.
        if (!errors.isEmpty()) {
            throw new CompilationFailedException(errors);
        }
        return result;
    }

    // TODO forgetLibrary

    private void prepareOneLibrary(final VersionedIdentifier libraryIdentifier,
                                   final List<CqlCompilerException> errors,
                                   final List<Library> result) {
        if (!errors.isEmpty()) {
            return;
        }
        final var library = this.libraryManager
                .resolveLibrary(libraryIdentifier, errors)
                .getLibrary();
        final var includes = library.getIncludes();
        final var includesDef = includes == null ? null : includes.getDef();
        if (includesDef != null) {
            includesDef.forEach(included -> {
                    final var includedIdentifier = new VersionedIdentifier()
                            .withSystem(NamespaceManager.getUriPart(included.getPath()))
                            .withId(NamespaceManager.getNamePart(included.getPath()))
                            .withVersion(included.getVersion());
                    prepareOneLibrary(includedIdentifier, errors, result);
            });
        }
        result.add(library);
    }

    public <R> R withEngineSession(final Function<EngineSession, R> continuation) {
        try (var session = this.sessionFactory.openSession();
             var entityManager = session.getEntityManagerFactory().createEntityManager()) {
            final var dataProvider = OMOPDataProvider.fromEntityManager(entityManager, this.mappingInfo);
            final var dataProviders = Map.<String, DataProvider>of(
                    String.format("urn:ohdsi:omop-types:%s", this.modelIdentifier.getVersion()),
                    dataProvider);
            final var environment = new Environment(this.libraryManager, dataProviders, this.terminologyProvider);
            final var options = this.isProfiling
                    ? Set.of(CqlEngine.Options.EnableProfiling)
                    : Set.<CqlEngine.Options>of();
            final var engine = new CqlEngine(environment, options);
            return continuation.apply(new EngineSession(engine, session, entityManager));
        }
    }

    private void prefillCache(final Cache cache, final Cache initialContents) {
        initialContents.getExpressions().forEach((library, expressions) ->
                expressions.forEach((name, result) ->
                        cache.cacheExpression(library, name, result)));
        initialContents.getFunctionCache().forEach(cache::cacheFunctionDef);
    }

    public <R> R withPreparedEngineSession(final Cache initialCache,
                                           final Function<EngineSession, R> continuation) {
        return withEngineSession(session -> {
            final var engine = session.cqlEngine();
            final var cache = engine.getCache();
            cache.setExpressionCaching(true);
            if (initialCache != null) {
                prefillCache(cache, initialCache);
            }
            return continuation.apply(session);
        });
    }

    public <R> R withEvaluationResult(final String library,
                                      final Object contextObject,
                                      final Map<String, Object> parameterBindings,
                                      final Cache initialCache,
                                      final Function<EvaluationResult, R> continuation) {
        return withPreparedEngineSession(initialCache, session -> {
            final var engine = session.cqlEngine();
            final var result = contextObject == null
                    ? engine.evaluate(library, parameterBindings)
                    : engine.evaluate(library,
                    Pair.of("Patient", contextObject), // TODO(jmoringe): can we know the name of the context?
                    parameterBindings);
            return continuation.apply(result);
        });
    }

    public EvaluationResult evaluateLibrary(final String library,
                                            final Object contextObject,
                                            final Map<String, Object> parameterBindings,
                                            final Cache initialCache) {
        return withEvaluationResult(library,
                contextObject,
                parameterBindings,
                initialCache,
                result -> result);
    }

    public EvaluationResult evaluateLibrary(final String library,
                                            final Object contextObject,
                                            final Map<String, Object> parameterBindings) {
        return evaluateLibrary(library, contextObject, parameterBindings, null);
    }

    public EvaluationResult evaluateLibrary(final String library, final Object contextObject) {
        return evaluateLibrary(library, contextObject, Map.of());
    }

    public EvaluationResult evaluateLibrary(final String library) {
        return evaluateLibrary(library, null, Map.of());
    }

}

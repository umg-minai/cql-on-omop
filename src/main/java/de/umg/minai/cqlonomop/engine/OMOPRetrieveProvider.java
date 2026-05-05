package de.umg.minai.cqlonomop.engine;

import OMOP.MappingInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.hibernate.query.sqm.PathElementException;
import org.opencds.cqf.cql.engine.retrieve.RetrieveProvider;
import org.opencds.cqf.cql.engine.runtime.Code;
import org.opencds.cqf.cql.engine.runtime.Interval;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class OMOPRetrieveProvider implements RetrieveProvider {

    private record Pair<L, R> (L left, R right) {}

    private final EntityManager entityManager;

    private final MappingInfo mappingInfo;

    private final Class<Object> conceptClass;
    private final Class<Object> conceptAncestorClass;

    public OMOPRetrieveProvider(final OMOPModelResolver modelResolver,
                                final EntityManager entityManager,
                                final MappingInfo mappingInfo) {
        this.entityManager = entityManager;
        this.mappingInfo = mappingInfo;
        //noinspection unchecked
        this.conceptClass = (Class<Object>) mappingInfo.getDataTypeInfo("Concept").getClazz();
        //noinspection unchecked
        this.conceptAncestorClass = (Class<Object>) mappingInfo.getDataTypeInfo("ConceptAncestor").getClazz();
    }

    static class RetrieveResult implements Iterable<Object> {

        private final TypedQuery<?> query;

        private final Predicate<Object> codeFilter;

        private List<Object> cache = null;

        public RetrieveResult(final TypedQuery<?> query,
                              final Predicate<Object> codeFilter) {
            this.query = query;
            this.codeFilter = codeFilter;
        }

        public RetrieveResult(TypedQuery<?> query) { this(query, null); }

        @Override
        public Iterator<Object> iterator() {
            if (cache == null) {
                Stream<?> stream = query.getResultStream();
                if (this.codeFilter != null) {
                    stream = stream.filter(this.codeFilter);
                }
                cache = stream.collect(Collectors.toList());
            }
            return cache.iterator();
        }
    }

    @Override
    public Iterable<Object> retrieve(String context,
                                     String contextPath,
                                     Object contextValue,
                                     String dataType,
                                     String templateId,
                                     String codePath,
                                     Iterable<Code> codes,
                                     String valueSet,
                                     String datePath,
                                     String dateLowPath,
                                     String dateHighPath,
                                     Interval dateRange) {
        if (this.entityManager == null) {
            throw new RuntimeException("Cannot evaluate retrieve expression because database connection is not initialized.");
        }
        // A "context Patient" statement is translated to something like "define Patient: [Person]" which then chokes
        // on the returned list because it expects a single element. Work around that issue. The replacement value
        // we provide here does not really matter since subsequent statements use the context value that we supply
        // directly.
        if (Objects.equals(contextPath, "person") && Objects.equals(contextValue, "DummyContextObject")) {
            return List.of(contextValue);
        }
        final var entityManager = this.entityManager;
        // Create a base query that selects from the OMOP table for dataType.
        final var criteriaBuilder = entityManager.getCriteriaBuilder();
        final var queryAndRoot = dataTypeCriteria(criteriaBuilder, dataType);
        final var criteriaQuery = queryAndRoot.left;
        final var root = queryAndRoot.right;
        // Add context criteria, if possible.
        if (context != null && contextPath != null && contextValue != null) {
            maybeAddContextCriteria(criteriaBuilder, criteriaQuery, dataType, root, contextPath, contextValue);
        }
        // Add code criteria, if possible.
        if (codes != null) {
            maybeAddCodeCriteria(criteriaBuilder, criteriaQuery, dataType, root, codePath, codes);
        }
        // Add date criteria, if possible
        if (dateRange != null) {
            throw new RuntimeException("Filtering by date range not implemented"); // TODO: implement
        }
        // Create query and return the (lazy) retrieve result.
        final var query = entityManager.createQuery(criteriaQuery);
        return new RetrieveResult(query);
    }

    /**
     * Create the base query for the given data type.
     *
     * @param criteriaBuilder The criteria builder to be used for creating the query.
     * @param dataType The name of the data type in the CQL model info.
     * @return The constructed query.
     */
    private Pair<CriteriaQuery<?>, Root<?>> dataTypeCriteria(final CriteriaBuilder criteriaBuilder,
                                                             final String dataType) {
        //noinspection unchecked
        final Class<Object> clazz = (Class<Object>) this.mappingInfo.getDataTypeInfo(dataType).getClazz();
        if (clazz == null) {
            throw new RuntimeException(String.format("Class for %s not found", dataType));
        }
        final var query = criteriaBuilder.createQuery(clazz);
        final var root = query.from(clazz);
        return new Pair<>(query.select(root), root);
    }

    /**
     * Add predicates to @param{baseQuery} to restrict it the current context and return the modified query.
     *
     * @param baseQuery   The partially built query that should be restricted to the context
     * @param dataType    The type of the objects that will be retrieved
     * @param root        The table from which objects are being retrieved
     * @param contextPath the path within objects from the @param{root} table that should match the context
     */
    private void maybeAddContextCriteria(final CriteriaBuilder criteriaBuilder,
                                         final AbstractQuery<?> baseQuery,
                                         final String dataType,
                                         final Root<?> root,
                                         final String contextPath,
                                         final Object contextValue) {
        final var info = this.mappingInfo.getDataTypeInfo(dataType);
        final var contextInfo = info.infoForContext(contextPath, contextValue);
        if (contextInfo != null) {
            final var columnName = contextInfo.columnName();
            Path<?> column;
            try {
                column = root.get(columnName);
            } catch (PathElementException e) {
                column = root.get("compoundId").get(columnName);
            }
            addRestriction(baseQuery, criteriaBuilder.equal(column, contextInfo.value()));
        }
    }

    private void maybeAddCodeCriteria(final CriteriaBuilder criteriaBuilder,
                                      final CriteriaQuery<?> baseQuery,
                                      final String dataType,
                                      final Root<?> root,
                                      final String codePath,
                                      final Iterable<Code> codes) {
        final var info = this.mappingInfo.getDataTypeInfo(dataType);
        if (!info.isJoinableCodePath(codePath)) {
            throw new RuntimeException(String.format("Retrieve for data type %s cannot filter by '%s'.",
                    dataType, codePath));
        }
        // Separate the supplied codes regarding whether they should be considered with or without concept hierarchy.
        // When considered with hierarchy, the code designates a concept and all its ancestors.
        final var codesWithoutHierarchy = new ArrayList<Code>();
        final var codesWithHierarchy = new ArrayList<Code>();
        for (var code : codes) {
            final var URLAndClassification = analyzeCodeSystem(code.getSystem());
            if (URLAndClassification.right) {
                // Add the code to the list of code that should be considered with hierarchy. Replace the
                // codesystem URL to contain just the actual codesystem URL.
                final var normalizedCode = new Code()
                        .withCode(code.getCode())
                        .withSystem(URLAndClassification.left);
                codesWithHierarchy.add(normalizedCode);
            } else {
                codesWithoutHierarchy.add(code);
            }
        }
        // For concepts that should be considered with hierarchy, we generate a sub-query which finds all ancestor
        // concepts and restrict the returned rows to those ancestor concepts.
        if (!codesWithHierarchy.isEmpty()) {
            addCodeAncestorsJoinCriteria(criteriaBuilder, baseQuery, root, codePath, codesWithHierarchy);
        }
        // For concepts that should be considered without hierarchy, we restrict the returned rows to the supplied
        // concepts either based on the concept id or via a join that handles the vocabulary lookup.
        if (!codesWithoutHierarchy.isEmpty()) {
            addCodeJoinCriteria(criteriaBuilder, baseQuery, root, codePath, codesWithoutHierarchy);
        }
    }

    /*
     * Check whether the code system designator indicates hierarchical processing via the URL query part being
     * "hierarchical".
     */
    private Pair<String, Boolean> analyzeCodeSystem(final String codeSystem) {
        // Parse the code system designator as a URL.
        final URL codeSystemURL;
        try {
            codeSystemURL= new URL(codeSystem);
        } catch (MalformedURLException exception) {
            throw new RuntimeException(String.format("Code system URL '%s' is not valid: %s",
                    codeSystem,
                    exception));
        }
        // If the query part of the URL is "hierarchical", return true and a URL with the query part stripped,
        // otherwise return false the unmodified URL.
        final var query = codeSystemURL.getQuery();
        if (query == null) {
            return new Pair<>(null, false);
        }
        if (query.equals("hierarchical")) {
            try {
                final var baseURL = new URL(codeSystemURL.getProtocol(),
                        codeSystemURL.getHost(),
                        codeSystemURL.getPort(),
                        codeSystemURL.getPath());
                return new Pair<>(baseURL.toString(), true);
            } catch (MalformedURLException exception) {
                throw new RuntimeException("should not happen");
            }
        } else {
            throw new RuntimeException(String.format("Code system URL '%s' contains invalid query part: %s",
                    codeSystemURL,
                    codeSystemURL.getQuery()));
        }
    }

    private <T> void addCodeAncestorsJoinCriteria(final CriteriaBuilder criteriaBuilder,
                                                  final CriteriaQuery<T> baseQuery,
                                                  final Root<?> root,
                                                  final String conceptRelation,
                                                  final Iterable<Code> codes) {
        // TODO(jmoringe): pass entityManager
        final var subQuery = baseQuery.subquery(this.conceptClass);
        final var subRoot = subQuery.from(this.conceptAncestorClass);
        subQuery.select(subRoot.get("descendantConcept"));
        addCodeJoinCriteria(criteriaBuilder, subQuery, subRoot, "ancestorConcept", codes);

        //final var root = baseQuery.getRoots().stream().findFirst().orElseThrow();
        baseQuery.where(criteriaBuilder.in(root.get(conceptRelation)).value(subQuery.getSelection()));
    }

    /*
     * Add restrictions for the supplied concept relation and codes to the query. Either by adding where clauses is
     * the query is against the concept table or by adding a join with the concept table if the query.
     */
    private <T, Q extends AbstractQuery<T>> void addCodeJoinCriteria(final CriteriaBuilder criteriaBuilder,
                                                                     final Q baseQuery,
                                                                     final Root<?> root,
                                                                     final String conceptRelation,
                                                                     final Iterable<Code> codes) {
        jakarta.persistence.criteria.Predicate predicates;
        if (root.getModel().getName().equals("Concept")
                && conceptRelation.equals("concept")) {
            predicates = conceptPredicateForCodes(criteriaBuilder, root, codes);
        } else {
            final Join<T, ?> join = root.join(conceptRelation);
            predicates = conceptPredicateForCodes(criteriaBuilder, join, codes);
        }
        addRestriction(baseQuery, predicates);
    }

    private jakarta.persistence.criteria.Predicate conceptPredicateForCodes(final CriteriaBuilder criteriaBuilder,
                                                                            final Path<?> conceptPath,
                                                                            final Iterable<Code> codes) {
        final var predicates = StreamSupport
                .stream(codes.spliterator(), false)
                .map(code -> conceptPredicateForCode(criteriaBuilder, conceptPath, code))
                .toArray(jakarta.persistence.criteria.Predicate[]::new);
        return criteriaBuilder.or(predicates);
    }

    private jakarta.persistence.criteria.Predicate conceptPredicateForCode(final CriteriaBuilder criteriaBuilder,
                                                                           final Path<?> conceptPath,
                                                                           final Code code) {
        if (code.getSystem().equals(Constants.OMOP_CODESYSTEM_URI)) {
            final var conceptId = Integer.parseInt(code.getCode()); // TODO: handle errors
            return criteriaBuilder.equal(conceptPath.get("conceptId"), conceptId);
        } else {
            // TODO(jmoringe): we could look up the code system url in the OMOP vocabulary table to obtain the vocabulary id
            final var system = code.getSystem();
            final var vocabularyId = Constants.OMOP_CODESYSTEM_URI_TO_VOCABULARY_ID.getOrDefault(system, system);
            assert vocabularyId != null;
            return criteriaBuilder.and(
                    criteriaBuilder.equal(conceptPath.get("conceptCode"), code.getCode()),
                    criteriaBuilder.equal(conceptPath.get("vocabularyId"), vocabularyId));
        }
    }

    private <T> void addRestriction(final AbstractQuery<T> query,
                                    final jakarta.persistence.criteria.Predicate predicate) {
        final var oldRestriction = query.getRestriction();
        if (oldRestriction == null) {
            query.where(predicate);
        } else {
            query.where(oldRestriction, predicate);
        }
    }

}

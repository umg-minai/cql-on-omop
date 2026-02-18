package de.umg.minai.cqlonomop.engine;

import OMOP.MappingInfo;
import OMOP.v54.Concept;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.hibernate.query.sqm.PathElementException;
import org.opencds.cqf.cql.engine.retrieve.RetrieveProvider;
import org.opencds.cqf.cql.engine.runtime.Code;
import org.opencds.cqf.cql.engine.runtime.Interval;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class OMOPRetrieveProvider implements RetrieveProvider {

    private final EntityManager entityManager;

    private final MappingInfo mappingInfo;

    public OMOPRetrieveProvider(final OMOPModelResolver modelResolver,
                                final EntityManager entityManager,
                                final MappingInfo mappingInfo) {
        this.entityManager = entityManager;
        this.mappingInfo = mappingInfo;
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
        // A "context Patient" statement is translated to something like "define Patient: [Person]" which then chokes
        // on the returned list because it expects a single element. Work around that issue. The replacement value
        // we provide here does not really matter since subsequent statements use the context value that we supply
        // directly.
        if (Objects.equals(contextPath, "person") && Objects.equals(contextValue, "DummyContextObject")) {
            return List.of(contextValue);
        }
        final var entityManager = this.entityManager;
        // Create a base query that selects from the OMOP table for dataType.
        var criteriaQuery = dataTypeCriteria(dataType);
        final var criteriaBuilder = entityManager.getCriteriaBuilder();
        final var root = criteriaQuery.getRoots().stream().findFirst().orElseThrow();

        // Add context criteria, if possible.
        if (context != null && contextPath != null && contextValue != null) {
            criteriaQuery = maybeAddContextCriteria(criteriaBuilder, criteriaQuery, dataType, root, contextPath, contextValue);
        }

        // Add code criteria, if possible.
        boolean mustFilterCodes = false;
        if (codes != null) {
            criteriaQuery = maybeAddCodeCriteria(criteriaBuilder, criteriaQuery, dataType, root, codePath, codes);
        }

        // Add date criteria, if possible
        if (dateRange != null) {
            System.out.printf("Adding date range %s%n", dateRange);
            throw new RuntimeException("not implemented");

            // TODO
        }

        final var query = entityManager.createQuery(criteriaQuery);
        return new RetrieveResult(query);
    }

    private CriteriaQuery<?> dataTypeCriteria(final String dataType) {
        final Class<Object> clazz = (Class<Object>) this.mappingInfo.getDataTypeInfo(dataType).getClazz();
        if (clazz == null) {
            System.err.println("Class for " + dataType + " not found");
            return null;
        }
        // TODO(jmoringe): pass entityManager
        final var criteria = this.entityManager.getCriteriaBuilder().createQuery(clazz);
        return criteria.select(criteria.from(clazz));
    }

    /**
     * Add predicates to @param{baseQuery} to restrict it the current context and return the modified query.
     *
     * @param baseQuery The partially built query that should be restricted to the context
     * @param dataType The type of the objects that will be retrieved
     * @param root The table from which objects are being retrieved
     * @param contextPath the path within objects from the @param{root} table that should match the context
     */
    private CriteriaQuery<?> maybeAddContextCriteria(final CriteriaBuilder criteriaBuilder,
                                                     final CriteriaQuery<?> baseQuery,
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
            return addRestriction(baseQuery, criteriaBuilder.equal(column, contextInfo.value()));
        } else {
            return baseQuery;
        }
    }

    private CriteriaQuery<?> maybeAddCodeCriteria(final CriteriaBuilder criteriaBuilder,
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
        return addCodeJoinCriteria(criteriaBuilder, baseQuery, codePath, codes);
    }

    /*
     *
     */
    private <T> CriteriaQuery<T> addCodeJoinCriteria(final CriteriaBuilder criteriaBuilder,
                                                     final CriteriaQuery<T> baseQuery,
                                                     final String conceptRelation,
                                                     final Iterable<Code> codes) {
        final var root = baseQuery.getRoots().stream().findFirst().orElseThrow();
        jakarta.persistence.criteria.Predicate predicates;
        if (root.getModel().getName().equals("Concept")
                && conceptRelation.equals("concept")) {
            predicates = conceptPredicateForCodes(criteriaBuilder, (Path<Concept>) root, codes);
        } else {
            final Join<T, Concept> join = root.join(conceptRelation);
            predicates = conceptPredicateForCodes(criteriaBuilder, join, codes);
        }
        return addRestriction(baseQuery, predicates);
    }

    private jakarta.persistence.criteria.Predicate conceptPredicateForCodes(final CriteriaBuilder criteriaBuilder,
                                                                            final Path<Concept> conceptPath,
                                                                            final Iterable<Code> codes) {
        // TODO(jmoringe): can we use criteriaBuilder.in(expression).in(collection)?
        final var predicates = StreamSupport
                .stream(codes.spliterator(), false)
                .map(code -> conceptPredicateForCode(criteriaBuilder, conceptPath, code))
                .toArray(jakarta.persistence.criteria.Predicate[]::new);
        return criteriaBuilder.or(predicates);
    }

    private jakarta.persistence.criteria.Predicate conceptPredicateForCode(final CriteriaBuilder criteriaBuilder,
                                                                           final Path<Concept> conceptPath,
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

    private <T> CriteriaQuery<T> addRestriction(final CriteriaQuery<T> baseQuery,
                                                final jakarta.persistence.criteria.Predicate predicate) {
        final var oldRestriction = baseQuery.getRestriction();
        if (oldRestriction == null) {
            return baseQuery.where(predicate);
        } else {
            return baseQuery.where(oldRestriction, predicate);
        }
    }

}

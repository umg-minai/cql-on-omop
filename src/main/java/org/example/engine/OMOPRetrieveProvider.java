package org.example.engine;

import OMOP.Concept;
import OMOP.ModelInfo;
import OMOP.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.opencds.cqf.cql.engine.model.ModelResolver;
import org.opencds.cqf.cql.engine.retrieve.RetrieveProvider;
import org.opencds.cqf.cql.engine.runtime.Code;
import org.opencds.cqf.cql.engine.runtime.Interval;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class OMOPRetrieveProvider implements RetrieveProvider {

    private final ModelResolver modelResolver;

    private final EntityManager entityManager;

    public OMOPRetrieveProvider(final ModelResolver modelResolver, final EntityManager entityManager) {
        this.modelResolver = modelResolver;
        this.entityManager = entityManager;
    }

    public OMOPRetrieveProvider(final ModelResolver modelResolver) {
        this(modelResolver, ConnectionFactory.createEntityManager());
    }

    class RetrieveResult implements Iterable<Object> {

        private final TypedQuery<?> query;

        private final Predicate<Object> codeFilter;

        private List<Object> cache = null;

        public RetrieveResult(TypedQuery<?> query,
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
        final var query = entityManager.createQuery(criteriaQuery);
        if (mustFilterCodes) {
            final Predicate<Object> filter = object -> {
                final var objectCode = modelResolver.resolvePath(object, codePath);
                for (final Object code : codes) {
                    if (code == objectCode) {
                        return true;
                    }
                }
                return false;
            };
            return new RetrieveResult(query, filter);
        } else {
            return new RetrieveResult(query);
        }

    }

    private CriteriaQuery<?> dataTypeCriteria(final String dataType) {
        // TODO(jmoringe): keep a map of name -> class
        final Class<Object> clazz = (Class<Object>) ModelInfo.getClass(String.format("OMOP.%s", dataType));
        if (clazz == null) {
            System.err.println("Class for " + dataType + " not found");
            return null;
        }
        final var criteria = entityManager.getCriteriaBuilder().createQuery(clazz);
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
        /* TODO(jmoringe): This is what should happen here
        final DataTypeInfo info;
        final var column = info.columnForContext(contextPath, contextValue);*/

        if (contextValue instanceof Person person) {
            final var column = switch (dataType) {
                case "ConditionOccurrence", "Observation", "Measurement" -> root.get("personId");
                case "Concept" -> null;
                default -> root.get(contextPath);
            };
            if (column != null) {
                final var id = person.getPersonId().orElseThrow(); // TODO: don't throw
                return addRestriction(baseQuery, criteriaBuilder.equal(column, id));
            } else {
                return baseQuery;
            }
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
        /* TODO(jmoringe): This is what should happen here
        final DataTypeInfo info;
         if (info.isJoinableCodePath(codePath)) {
            return addCodeJoinCriteria(criteriaBuilder, baseQuery, codePath, codes);
        }*/

        // TODO: try to add where clause for codes
        if (dataType.equals("Concept") && codePath.equals("conceptId")) {
            final var conceptRoot = (Root<Concept>) root;
            final var criteria = conceptPredicateForCodes(criteriaBuilder, conceptRoot, codes);
            return addRestriction(baseQuery, criteria);
        } else if (dataType.equals("ConditionOccurrence") && codePath.equals("conditionConcept")) {
            return addCodeJoinCriteria(criteriaBuilder, baseQuery, codePath, codes);
        } else if (dataType.equals("Observation") && (codePath.equals("observationConcept") || codePath.equals("observationTypeConcept"))) {
            return addCodeJoinCriteria(criteriaBuilder, baseQuery, codePath, codes);
        } else {
            return null;
        }
    }

    /*
     *
     */
    private <T> CriteriaQuery<T> addCodeJoinCriteria(final CriteriaBuilder criteriaBuilder,
                                                     final CriteriaQuery<T> baseQuery,
                                                     final String conceptRelation,
                                                     final Iterable<Code> codes) {
        final var root = baseQuery.getRoots().stream().findFirst().orElseThrow();
        final Join<T, Concept> join = root.join(conceptRelation);
        return addRestriction(baseQuery, conceptPredicateForCodes(criteriaBuilder, join, codes));
    }

    private jakarta.persistence.criteria.Predicate conceptPredicateForCodes(final CriteriaBuilder criteriaBuilder,
                                                                            final Path<OMOP.Concept> conceptPath,
                                                                            final Iterable<Code> codes) {
        final var predicates = StreamSupport
                .stream(codes.spliterator(), false)
                .map(code -> conceptPredicateForCode(criteriaBuilder, conceptPath, code))
                .toArray(jakarta.persistence.criteria.Predicate[]::new);
        return criteriaBuilder.or(predicates);
    }

    private jakarta.persistence.criteria.Predicate conceptPredicateForCode(final CriteriaBuilder criteriaBuilder,
                                                                           final Path<OMOP.Concept> conceptPath,
                                                                           final Code code) {
        if (code.getSystem().equals(Constants.OMOP_CODESYSTEM_URI)) {
            final var conceptId = Integer.parseInt(code.getCode()); // TODO: handle errors
            return criteriaBuilder.equal(conceptPath.get("conceptId"), conceptId);
        } else {
            // TODO(jmoringe): we could look up the code system url in the OMOP vocabulary table to obtain the vocabulary id
            final var vocabularyId = Constants.OMOP_CODESYSTEM_URI_TO_VOCABULARY_ID.get(code.getSystem());
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

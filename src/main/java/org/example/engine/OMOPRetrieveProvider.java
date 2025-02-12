package org.example.engine;

import OMOP.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import org.opencds.cqf.cql.engine.model.ModelResolver;
import org.opencds.cqf.cql.engine.retrieve.RetrieveProvider;
import org.opencds.cqf.cql.engine.runtime.Code;
import org.opencds.cqf.cql.engine.runtime.Interval;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        private final TypedQuery<Object> query;

        private final Predicate<Object> codeFilter;

        private List<Object> cache = null;

        public RetrieveResult(TypedQuery<Object> query,
                              final Predicate<Object> codeFilter) {
            this.query = query;
            this.codeFilter = codeFilter;
        }

        public RetrieveResult(TypedQuery<Object> query) { this(query, null); }

        @Override
        public Iterator<Object> iterator() {
            if (cache == null) {
                Stream<Object> stream = query.getResultStream();
                if (this.codeFilter != null) {
                    stream = stream.filter(this.codeFilter);
                }
                cache = stream.collect(Collectors.toList());
            }
            return cache.iterator();
        }
    }

    private List<Object> getContextObject() {
        return null;
    }

    private CriteriaQuery<Object> prepareCriteria (final String dataType) {
        final Class<Object> clazz;
        try {
            // TODO: keep a map of name -> class
            clazz = (Class<Object>) Class.forName(String.format("OMOP.%s", dataType));
            System.err.println("Class " + dataType + " => " + clazz);
        } catch (ClassNotFoundException e) {
            System.err.println("Class " + dataType + " not found: " + e);
            return null;
        }
        // TODO: add code filtering here
        final var criteria = entityManager.getCriteriaBuilder()
                .createQuery(clazz);
        return criteria.select(criteria.from(clazz));
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

        var criteria = prepareCriteria(dataType);
        if (contextValue instanceof Person person) {
            final var root = criteria.getRoots().stream().findFirst().orElseThrow();
            final var id = person.getPersonId().orElseThrow(); // TODO: don't throw
            final var column = switch (dataType) {
                case "ConditionOccurrence", "Observation" -> root.get("personId");
                default -> root.get(contextPath);
            };
            criteria =criteria.where(entityManager.getCriteriaBuilder().equal(column, id));
        }
        final var query = entityManager.createQuery(criteria);
        if (codes != null) {
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

}

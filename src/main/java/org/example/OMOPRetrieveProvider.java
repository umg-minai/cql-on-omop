package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
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

    private final SessionFactory sessionFactory;

    private final Session session;

    private final EntityManager entityManager;

    private String contextObjectId = null;

    public OMOPRetrieveProvider(final ModelResolver modelResolver) {
        this.modelResolver = modelResolver;
        // Configure Hibernate
        final var config = new Configuration();
        config.configure("org/example/hibernate.cfg.xml");
        OMOP.Meta.allClasses().forEach(clazz -> {
            if (!clazz.getName().equals("OMOP.FactRelationship")
                    && !clazz.getName().equals("OMOP.ConceptRelationship")
                    && !clazz.getName().equals("OMOP.ConceptSynonym")
                    && !clazz.getName().equals("OMOP.CdmSource")
                    && !clazz.getName().equals("OMOP.CohortDefinition")
                    && !clazz.getName().equals("OMOP.Death")
                    && !clazz.getName().equals("OMOP.ConceptAncestor")
                    && !clazz.getName().equals("OMOP.EpisodeEvent")
                    && !clazz.getName().equals("OMOP.SourceToConceptMap")
                    && !clazz.getName().equals("OMOP.DrugStrength")
                    && !clazz.getName().equals("OMOP.Cohort")
            ) {
                System.out.printf("Registering class %s%n", clazz.getName());
                config.addAnnotatedClass(clazz);
            } else {
                System.out.printf("Not registering class %s%n", clazz.getName());
            }
        });
        this.sessionFactory = config.buildSessionFactory();
        this.session = this.sessionFactory.openSession();
        this.entityManager = this.session.getEntityManagerFactory().createEntityManager();
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
        this.contextObjectId = "1";
        if (context.equals("Patient") && contextObjectId != null) {
            final var root = criteria.getRoots().stream().findFirst().orElseThrow();
            criteria = criteria.where(entityManager.getCriteriaBuilder().equal(root.get("personId"), contextObjectId));
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

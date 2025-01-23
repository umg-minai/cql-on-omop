package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.apache.commons.lang3.stream.Streams;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;
import org.opencds.cqf.cql.engine.data.DataProvider;
import org.opencds.cqf.cql.engine.runtime.Code;
import org.opencds.cqf.cql.engine.runtime.Interval;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OMOPDataProvider implements DataProvider {

    private final SessionFactory sessionFactory;

    private final Session session;

    private final EntityManager entityManager;

    public OMOPDataProvider() {
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

    @Override
    public String getPackageName() {
        return "OMOP";
    }

    @Override
    public void setPackageName(String s) {

    }

    @Override
    public Object resolvePath(Object o, String s) {
        Class<?> clazz = o.getClass();
        final var name = s.substring(0, 1).toUpperCase() + s.substring(1);
        final Method method;
        Object result;
        try {
            method = clazz.getMethod(String.format("get%s", name), new Class[]{});
            result = method.invoke(o);
        } catch (NoSuchMethodException e) {
            System.err.printf("%s [get%s] not found in class %s%n",
                    s, name, clazz.getCanonicalName());
            return null;
        } catch (InvocationTargetException | IllegalAccessException e) {
            System.err.printf("failed to get %s of %s: %s", s, o, e);
            return null;
        }
        if (result instanceof Optional<?> optional) {
            return optional.orElse(null);
        } else {
            return result;
        }
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

        @NotNull
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
        final Class<Object> clazz;
        try {
            // TODO: keep a map of name -> class
            clazz = (Class<Object>) Class.forName(String.format("OMOP.%s", dataType));
            System.err.println("Class " + dataType + " => " + clazz);
        } catch (ClassNotFoundException e) {
            System.err.println("Class " + dataType + " not found: " + e);
            return null;
        }
        final var criteria = entityManager.getCriteriaBuilder()
                .createQuery(clazz);
        criteria.select(criteria.from(clazz));
        final var query = entityManager.createQuery(criteria);
        Predicate<Object> filter = null;
        if (codes != null) {
            filter = object -> {
                final var objectCode = resolvePath(object, codePath);
                for (final Object code : codes) {
                    if (code == objectCode) {
                        return true;
                    }
                }
                return false;
            };
        }
        return new RetrieveResult(query, filter);
    }
}
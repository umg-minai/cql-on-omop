package de.umg.minai.cqlonomop.batch;

import de.umg.minai.cqlonomop.engine.MapReduceEngine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.opencds.cqf.cql.engine.execution.ExpressionResult;

import java.util.List;
import java.util.Map;

public class DatabaseWriterSink extends BasicResultSink {

    private final SessionFactory sessionFactory;

    private Session session;

    public DatabaseWriterSink(final MapReduceEngine engine, final List<String> resultNames) {
        super(resultNames);
        this.sessionFactory = engine.getSessionFactory();
    }

    @Override
    public ResultInfo processResults(final Map<Object, MapReduceEngine.Outcome> intermediateResults) {
        try (var session = this.sessionFactory.openSession()) {
            this.session = session;
            final var transaction = session.beginTransaction();
            final var result = super.processResults(intermediateResults);
            transaction.commit();
            return result;
        }
    }

    @Override
    protected void processExpressionResult(final Object contextObject,
                                           final String name,
                                           final ExpressionResult expressionResult) {
        final var object = expressionResult.value();
        if (object instanceof Iterable<?> iterable) {
            int i = 0;
            for (Object element : iterable) {
                notePersistedObject(contextObject, name, i++, element);
                this.session.persist(element);
            }
        } else {
            notePersistedObject(contextObject, name, null, object);
            this.session.persist(object);
        }
    }

    void notePersistedObject(final Object contextObject, final String name, final Integer index, final Object object) {
        final var writer = System.out;
        writer.print("Persisting");
        if (contextObject != null) {
            writer.printf(" %s ->", contextObject);
        }
        writer.printf(" %s", name);
        if (index != null) {
            writer.printf("[%d]", index);
        }
        writer.printf(" -> %s\n", object);
    }

}

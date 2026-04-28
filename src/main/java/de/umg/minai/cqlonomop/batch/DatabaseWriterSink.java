package de.umg.minai.cqlonomop.batch;

import de.umg.minai.cqlonomop.database.ConnectionFactory;
import de.umg.minai.cqlonomop.database.DatabaseConfiguration;
import de.umg.minai.cqlonomop.engine.MapReduceEngine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.opencds.cqf.cql.engine.execution.ExpressionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DatabaseWriterSink extends BasicResultSink {

    private static final Logger log = LoggerFactory.getLogger(DatabaseWriterSink.class);

    private final boolean printNotes;

    private final DatabaseConfiguration databaseConfiguration;

    private final SessionFactory sessionFactory;

    private Session session;

    public DatabaseWriterSink(final MapReduceEngine engine,
                              final List<String> resultNames,
                              final DatabaseConfiguration databaseConfiguration,
                              boolean printNotes) {
        super(resultNames);
        this.databaseConfiguration = databaseConfiguration;
        this.sessionFactory = (databaseConfiguration != null)
                ? ConnectionFactory.createSessionFactory(databaseConfiguration, engine.getMappingInfo())
                : engine.getSessionFactory();
        this.printNotes = printNotes;
    }

    public DatabaseWriterSink(final MapReduceEngine engine, final List<String> resultNames) {
        this(engine, resultNames, null, true);
    }

    @Override
    public ResultInfo processResults(final Map<Object, MapReduceEngine.Outcome> intermediateResults) {
        try (var session = this.sessionFactory.openSession()) {
            this.session = session;
            final var transaction = session.beginTransaction();
            final var result = super.processResults(intermediateResults);
            transaction.commit();
            return result;
        } catch (Exception exception) {
            // TODO: make a debug option for this
            log.error(String.format("""
                                    Error persisting results
                                      %s
                                    with database configuration
                                      %s
                                    session factory
                                      %s
                                      %s
                                    session
                                      %s
                                    """,
                            intermediateResults,
                            this.databaseConfiguration,
                            this.sessionFactory,
                            this.sessionFactory.getProperties(),
                            this.session),
                    exception);
            throw exception;
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

    private void notePersistedObject(final Object contextObject,
                                     final String name,
                                     final Integer index,
                                     final Object object) {
        if (this.printNotes) {
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

}

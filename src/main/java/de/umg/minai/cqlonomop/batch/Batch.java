package de.umg.minai.cqlonomop.batch;

import OMOP.MappingInfo;
import OMOP.v54.Person;
import de.umg.minai.cqlonomop.commandline.CqlOptions;
import de.umg.minai.cqlonomop.commandline.DatabaseOptions;
import de.umg.minai.cqlonomop.commandline.ExecutionOptions;
import de.umg.minai.cqlonomop.engine.CQLonOMOPEngine;
import de.umg.minai.cqlonomop.engine.Configuration;
import de.umg.minai.cqlonomop.engine.ConnectionFactory;
import de.umg.minai.cqlonomop.terminal.*;
import org.cqframework.cql.cql2elm.DefaultLibrarySourceProvider;
import org.cqframework.cql.cql2elm.LibrarySourceProvider;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Command(
        name = "batch",
        description = "Evaluate CQL code and print the results"
)
public class Batch implements Runnable {

    @ArgGroup(validate = false, heading = "Database Options%n")
    DatabaseOptions databaseOptions = new DatabaseOptions();

    @ArgGroup(validate = false, heading = "CQL Options%n")
    CqlOptions cqlOptions;

    @ArgGroup(validate = false, heading = "Other Options%n")
    ExecutionOptions executionOptions;

    @Override
    public void run() {
        var configuration = databaseOptions.applyToConfiguration(new Configuration());
        if (cqlOptions != null) {
            configuration = cqlOptions.applyToConfiguration(configuration);
        }
        if (executionOptions != null) {
            configuration = executionOptions.applyToConfiguration(configuration);
        }
        //
        final var omopVersion = configuration.getOmopVersion();
        final var mappingInfo = MappingInfo.ensureVersion(omopVersion);
        final var sessionFactory = ConnectionFactory.createSessionFactory(configuration, mappingInfo);
        final List<LibrarySourceProvider> sourceProviders = new LinkedList<>();
        configuration.getLibrarySearchPath().forEach(path ->
                sourceProviders.add(new DefaultLibrarySourceProvider(path)));
        final var engine = new CQLonOMOPEngine(omopVersion, sessionFactory, mappingInfo, sourceProviders);

        final Terminal terminal;
        final Theme theme;
        try {
            terminal = TerminalBuilder.builder().build();
            theme = new DefaultTheme();
        } catch (IOException e) {
            throw new RuntimeException(String.format("Error initializing terminal: %s%n", e));
        }
        final var sourcePresenter = new SourcePresenter(terminal, theme, engine.getLibraryManager());
        final var valuePresenter = new ValuePresenter(terminal, theme);
        final var resultPresenter = new ResultPresenter(terminal, theme, sourcePresenter, valuePresenter);
        final var errorPresenter = new ErrorPresenter(terminal, theme, sourcePresenter, valuePresenter);

        try {
            final var entityManager = sessionFactory.createEntityManager();
            final var clazz = Person.class;
            final var criteria = entityManager.getCriteriaBuilder()
                    .createQuery(clazz);
            final var query = criteria.select(criteria.from(clazz));
            final var persons = entityManager.createQuery(query).getResultStream().toList();
            persons.forEach(person -> {
                System.out.printf("Context Object %s%n", person);
                final var result = engine.evaluateLibrary("15ProphylacticAnticoagulation", person);
                resultPresenter.reset();
                resultPresenter.presentResult(result);
                terminal.flush();
            });
        } catch (Exception e) {
            errorPresenter.presentError(e);
        }
        terminal.flush();
    }

}

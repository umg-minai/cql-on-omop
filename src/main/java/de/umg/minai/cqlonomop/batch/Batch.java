package de.umg.minai.cqlonomop.batch;

import OMOP.v54.Person;
import de.umg.minai.cqlonomop.commandline.CqlOptions;
import de.umg.minai.cqlonomop.commandline.DatabaseOptions;
import de.umg.minai.cqlonomop.commandline.DefaultValueProvider;
import de.umg.minai.cqlonomop.commandline.ExecutionOptions;
import de.umg.minai.cqlonomop.engine.Configuration;
import de.umg.minai.cqlonomop.engine.MapReduceEngine;
import de.umg.minai.cqlonomop.terminal.*;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.opencds.cqf.cql.engine.debug.DebugResult;
import picocli.CommandLine;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.Callable;

@Command(
        name = "batch",
        description = "Evaluate CQL code and print the results",
        defaultValueProvider = DefaultValueProvider.class
)
public class Batch implements Callable<Integer> {

    @ArgGroup(validate = false, heading = "Database Options%n")
    private DatabaseOptions databaseOptions = new DatabaseOptions();

    @ArgGroup(validate = false, heading = "CQL Options%n")
    private CqlOptions cqlOptions;

    @ArgGroup(validate = false, heading = "Other Options%n")
    private ExecutionOptions executionOptions;

    @CommandLine.Parameters(
            arity = "1",
            description = "The id (without version) of a CQL library that should be evaluated. For a library named " +
                    "NAME, a file named NAME.cql has to present in one of the directories specified via the -I option."
    )
    private String libraryToEvaluate;

    @CommandLine.Option(
            names = {"--profile"},
            paramLabel = "<svg-output-file>",
            description = "Profile CQL evaluation and write a flamegraph representation of the captured profile into " +
                    "the specified SVG file."
    )
    private Path profilePath;

    private static final int SUCCESS = 0;
    private static final int FAILURE = 1;

    private record ResultInfo(DebugResult debugResult, int[] outcomeCounts) {};

    @Override
    public Integer call() {
        // Prepare a configuration and instantiate the engine.
        var configuration = databaseOptions.applyToConfiguration(new Configuration());
        if (cqlOptions != null) {
            configuration = cqlOptions.applyToConfiguration(configuration);
        }
        if (executionOptions != null) {
            configuration = executionOptions.applyToConfiguration(configuration);
        }
        final var engine = new MapReduceEngine(configuration);
        // Initialize the terminal and result, error,
        // etc. presentation components.
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
        final var outcomePresenter = new OutcomePresenter(terminal, theme, resultPresenter, errorPresenter);
        // Evaluate the specified CQL code in an engine session. Apply
        // the outcomePresenter to all outcomes as they are
        // reported. For long-running, parallel evaluations, this
        // means that outcomes are reported incrementally instead of
        // at the end of the whole evaluation. Any failure outcomes as
        // well as uncaught exceptions change the overall result and
        // thus the exit code to failure. If profiling has been
        // requested, combine partial profiles (from concurrent
        // evaluations) into a complete profile after the evaluation
        // finishes.
        boolean overallSuccess = false;
        try {
            final var sessionFactory = engine.getSessionFactory();
            final var entityManager = sessionFactory.createEntityManager();
            final var clazz = Person.class;
            final var criteria = entityManager.getCriteriaBuilder()
                    .createQuery(clazz);
            final var query = criteria.select(criteria.from(clazz));
            final var persons = entityManager.createQuery(query).getResultStream().toList();
            engine.setProfiling(profilePath != null);
            outcomePresenter.beginPresentation();
            final var resultInfo = engine.prepareAndEvaluateLibraryMapReduce(libraryToEvaluate,
                    persons,
                    Map.of(),
                    (contextObject, outcome) -> {
                        synchronized (this) {
                            outcomePresenter.presentOutcome(contextObject, outcome);
                        }
                        return outcome;
                    },
                    intermediate -> {
                        final int[] outcomeCounts = {0, 0}; // success, failure
                        final var debugResult = new DebugResult();
                        intermediate.forEach((contextObject, outcome) -> {
                            if (outcome instanceof MapReduceEngine.Outcome.Success success) {
                                if (profilePath != null) {
                                    final var oneDebugResult = success.result().getDebugResult();
                                    if (oneDebugResult != null) {
                                        final var profile = oneDebugResult.getProfile();
                                        if (profile != null) {
                                            debugResult.ensureProfile().merge(profile);
                                        }
                                    }
                                }
                                outcomeCounts[SUCCESS]++;
                            } else {
                                outcomeCounts[FAILURE]++;
                            }
                        });
                        return new ResultInfo(debugResult, outcomeCounts);
                    });
            outcomePresenter.endPresentation();
            if (profilePath != null) {
                resultInfo.debugResult().getProfile().render(profilePath);
            }
            overallSuccess = resultInfo.outcomeCounts()[FAILURE] == 0; // no failures
        } catch (Exception e) {
            errorPresenter.presentError(e);
            // Note: overallSuccess remains false
        }
        terminal.flush();
        return overallSuccess ? CommandLine.ExitCode.OK : CommandLine.ExitCode.SOFTWARE;
    }

}

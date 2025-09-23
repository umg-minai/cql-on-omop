package de.umg.minai.cqlonomop.batch;

import de.umg.minai.cqlonomop.commandline.CqlOptions;
import de.umg.minai.cqlonomop.commandline.DatabaseOptions;
import de.umg.minai.cqlonomop.commandline.DefaultValueProvider;
import de.umg.minai.cqlonomop.commandline.ExecutionOptions;
import de.umg.minai.cqlonomop.engine.CQLonOMOPEngine;
import de.umg.minai.cqlonomop.engine.Configuration;
import de.umg.minai.cqlonomop.engine.InMemoryLibrarySourceProvider;
import de.umg.minai.cqlonomop.engine.MapReduceEngine;
import de.umg.minai.cqlonomop.terminal.*;
import org.hl7.elm.r1.VersionedIdentifier;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import picocli.CommandLine;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

@Command(
        name = "batch",
        description = "Evaluate CQL code and print or process the results",
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
            names = { "-c", "--context-value"},
            paramLabel = "<cql-expression>",
            description = "A CQL expression that is evaluated in the \"Unfiltered\" context to produce one or more " +
                    "objects. The \"main\" evaluation, that is the evaluation of the CQL library specified as the " +
                    "main argument of the batch operation, will be performed once for each context object. The " +
                    "distinct evaluation results will be aggregated according by the specified result sink " +
                    "(see --result-sink). The default is ${DEFAULT-VALUE} which performs the CQL evaluation for each " +
                    "Person entry in the database."
    )
    private String contextExpression;

    @CommandLine.Option(
            names = { "--result-name" },
            paramLabel = "<expression-definition-name>",
            description = "Name of CQL expression definitions which should be processed. The processing is performed " +
                    "by the \"result sink\" chose via the --sink option. If this option is supplied multiple times, " +
                    "all specified results are selected for processing."
    )
    private List<String> resultNames;

    @CommandLine.Option(
            names = { "--sink" },
            paramLabel = "<sink>",
            description = """
                    Action that should be applied to evaluation results, either all results or the ones selected via \
                    the --result-name option(s). Possible values are
                    "noop" No operation.
                    "dbwrite" Write result objects to OMOP database. The result for each expression selected via the \
                    --result-name option(s) is inserted into the OMOP table which corresponds to the type of the \
                    result. Result objects are translated into database rows according to the following rules \
                      - The results for different context values (such as Person instances) are inserted independently
                      - The elements of a list are inserted as individual rows
                      - Instances of OMOP datatypes are inserted into the corresponding table
                      - Other datatypes are not supported
                      In other words, each selected definition has to evaluate to an instance of an OMOP datatype such \
                    as Person or to a list of such like List<Person>.
                    "histogram" Write a temporal histogram to one or more files. The output is human-readable and also \
                    suitable for plotting with gnuplot. Each expression selected by --result-name is written into a \
                    file EXPRESSION_NAME.txt.
                    "csv" Write the selected result objects into CSV files. Each expression selected by --result-name \
                    is written into a file EXPRESSION_NAME.csv. A --result-name option that selects multiple \
                    expressions leads to multiple CSV files. Result objects are translated into rows and columns of \
                    the CSV files according to the following rules:
                      - The elements of a list turn into rows of the CSV file
                      - The elements of a tuple turn into columns of a row
                      - A "scalar" value turns into a single column of a row
                      - The results for different context values (such a Person instance) are turned into sequences \
                    of rows and concatenated into an overall sequence of rows
                    The default is "noop".
                    """,
            converter = SinkConverter.class
    )
    private Class<? extends ResultSink> resultSinkClass = NoopSink.class;

    @CommandLine.Option(
            names = {"--profile"},
            paramLabel = "<svg-output-file>",
            description = "Profile CQL evaluation and write a flamegraph representation of the captured profile into " +
                    "the specified SVG file."
    )
    private Path profilePath;

    // A temporary source provider that is used for evaluating stand-alone CQL expressions. We use this to compute
    // the values of the CQL context object(s) and CQL parameters.
    final InMemoryLibrarySourceProvider temporarySourceProvider = new InMemoryLibrarySourceProvider();

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
        // Add a source provider for evaluating stand-alone CQL
        // expressions. We use this for computing the values of the
        // CQL context object(s) and CQL parameters.
        engine.getLibraryManager().getLibrarySourceLoader().registerProvider(temporarySourceProvider); // HACK

        // Initialize the terminal and result, error,
        // etc. presentation components.
        final Terminal terminal;
        final Theme theme;
        try {
            terminal = TerminalBuilder.builder().build();
            theme = new DefaultTheme();
        } catch (final IOException e) {
            throw new RuntimeException(String.format("Internal error initializing terminal: %s%n", e));
        }
        final var sourcePresenter = new SourcePresenter(terminal, theme, engine.getLibraryManager());
        final var valuePresenter = new de.umg.minai.cqlonomop.terminal.ValuePresenter(terminal, theme);
        final var errorPresenter = new ErrorPresenter(terminal, theme, sourcePresenter, valuePresenter);
        final var resultPresenter = new ResultPresenter(terminal, theme, sourcePresenter, valuePresenter);
        final var outcomePresenter = new de.umg.minai.cqlonomop.terminal.OutcomePresenter(terminal,
                theme,
                resultPresenter,
                errorPresenter);

        // Prepare the requested result sink. The result sink will receive all evaluation results in the reduce step
        // of the MapReduceEngine and extract the expressions for resultNames for processing. It will also compute
        // the overall success of the batch processing.
        final ResultSink resultSink;
        try {
            resultSink = resultSinkClass
                    .getConstructor(MapReduceEngine.class, List.class)
                    .newInstance(engine, resultNames);
        } catch (final Exception e) {
            throw new RuntimeException(String.format("Internal error while creating result sink: %s%n", e), e);
        }

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
            // Compute context object(s) from CQL expression provided via the commandline option.
            Object contextObjects = null;
            if (contextExpression != null) {
                contextObjects = evaluateWithoutContext(engine, configuration, contextExpression);
            }
            // Compute CQL parameters from expression provided via the commandline option.
            final var parameters = new HashMap<String, Object>();
            for (var entry : cqlOptions.parameterBindings.entrySet()) {
                final var name = entry.getKey();
                final var expression = entry.getValue();
                System.out.printf("Computing value for %s from %s\n", name, expression);
                final var value = evaluateWithoutContext(engine, configuration, expression);
                System.out.printf("Assigning %s <- %s\n", name, value);
                parameters.put(name, value);
            }
            // Pass into everything to the engine: the library, the
            // context information and the profiling flag.
            //
            // Receive individual outcomes via the outcomePresenter
            // and the overall result as well as debug info possibly
            // including a profile via resultSink.
            engine.setProfiling(profilePath != null);
            final ResultSink.ResultInfo resultInfo;
            try (var adapter = new AutoClosableOutcomePresenter(outcomePresenter)) {
                resultInfo = engine.prepareAndEvaluateLibraryMapReduce(libraryToEvaluate,
                        contextObjects,
                        parameters,
                        (contextObject, outcome) -> {
                            synchronized (this) {
                                adapter.processOutcome(contextObject, outcome);
                            }
                            return outcome;
                        },
                        resultSink::processResults);
            }
            if (profilePath != null) {
                resultInfo.debugResult().getProfile().render(profilePath);
            }
            overallSuccess = resultInfo.isSuccess();
        } catch (final Exception e) {
            errorPresenter.presentError(e);
            // Note: overallSuccess remains false
        }
        terminal.flush();
        return overallSuccess ? CommandLine.ExitCode.OK : CommandLine.ExitCode.SOFTWARE;
    }

    // For context object and parameters
    private Object evaluateWithoutContext(final CQLonOMOPEngine engine,
                                          final Configuration configuration,
                                          final String expression) {
        // TODO: The engine should provide this
        engine.getLibraryCache().clear();
        temporarySourceProvider.registerLibrary(new VersionedIdentifier().withId("Temporary"),
                String.format("""
                                library Temporary

                                using "OMOP" version '%s'

                                include OMOPHelpers
                                include OMOPFunctions

                                define Temporary: %s""",
                        configuration.getOmopVersion(),
                        expression));
        final var result = engine.evaluateLibrary("Temporary");
        return result.forExpression("Temporary").value();
    }

}

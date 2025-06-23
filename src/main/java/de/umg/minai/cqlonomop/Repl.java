package de.umg.minai.cqlonomop;

import de.umg.minai.cqlonomop.engine.Configuration;
import de.umg.minai.cqlonomop.repl.CommandProcessor;
import de.umg.minai.cqlonomop.repl.Completer;
import de.umg.minai.cqlonomop.repl.Evaluator;
import de.umg.minai.cqlonomop.terminal.DefaultTheme;
import de.umg.minai.cqlonomop.terminal.ErrorPresenter;
import de.umg.minai.cqlonomop.terminal.ResultPresenter;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@CommandLine.Command(
        name = "REPL",
        version = "REPL 0.1",
        mixinStandardHelpOptions = true
)
public class Repl implements Runnable {

    @CommandLine.ArgGroup(validate = false, heading = "Database Options%n")
    DatabaseOptions databaseOptions = new DatabaseOptions();

    static class DatabaseOptions {
        @CommandLine.Option(
                names = {"-h", "--host"},
                defaultValue = "localhost",
                description = "The hostname of the database server from which to retrieve the OMOP data."
        )
        private String host = "localhost";

        @CommandLine.Option(
                names = {"-p", "--port"},
                defaultValue = "5434",
                description = "The post on which the database server from which to retrieve the OMOP data listens."
        )
        private int port = 5434;

        @CommandLine.Option(
                names = {"-u", "--user"},
                description = "The username of the database server account that should be used to retrieve the OMOP data."
        )
        private String user = "postgres";

        @CommandLine.Option(
                names = {"--password"},
                defaultValue = "${env:CQL_ON_OMOP_DATABASE_PASSWORD}",
                description = "The password for the database server account."
        )
        private String password;

        @CommandLine.Option(
                names = {"-d", "--database"},
                required = true,
                description = "The name of the database from which OMOP data should be retrieved."
        )
        private String database;

        @CommandLine.Option(
                names = {"-s", "--show-sql"},
                defaultValue = "false",
                description = "Show the generated SQL statements as they are sent to the database server."
        )
        private boolean showSQL = false;
    }

    @CommandLine.ArgGroup(validate = false, heading = "CQL Options%n")
    CQLOptions cqlOptions;

    static class CQLOptions {

        @CommandLine.Option(
                names = {"-I"},
                paramLabel = "<directory>",
                description = "Search the specified directory for CQL libraries when processing CQL include statements. This option can be supplied multiple times."
        )
        private List<Path> librarySearchPath = List.of();

        @CommandLine.Option(
                names = {"-l", "--load"},
                paramLabel = "<library-file>",
                description = "Load the specified file as CQL code."
        )
        private List<Path> load = List.of();

        @CommandLine.Option(
                names = {"-D"},
                description = "Bind parameters of the CQL library to specific values. A binding is of the form NAME=EXPRESSION where EXPRESSION is a CQL expression that is evaluated to produce the CQL value that is assigned to the parameter named NAME. "
        )
        private Map<String, String> parameterBindings = Map.of();

        // TODO(jmoringe): context and focus

    }

    @CommandLine.ArgGroup(validate = false, heading = "Other Options%n")
    OtherOptions otherOptions;

    static class OtherOptions {

        @CommandLine.Option(
                names = {"-n", "--threads"},
                description = "Use the specified number of threads when evaluating CQL expressions for multiple context values (typically patients) in parallel. If this option is not supplied, try to determine a suitable number of threads automatically."
        )
        private Integer threadCount;
    }

    private Terminal terminal;
    private LineReader reader;

    private Evaluator evaluator;
    private CommandProcessor commandProcessor;
    private ResultPresenter resultPresenter;
    private ErrorPresenter errorPresenter;

    public Repl() {}

    private void repl() {
        for (int i = 0;; ++i) {
            final String input;
            try {
                final var prompt = String.format("[%s] ", i);
                input = reader.readLine(prompt);
                final var result = commandProcessor.process(input);
                if (result != null) { // TODO(jmoringe): can this be null?
                    this.resultPresenter.presentResult(result);
                }
            } catch (EndOfFileException _e) {
                // User probably pressed C-d.
                break;
            } catch (Exception exception) {
                this.errorPresenter.presentError(exception);
            }

        }
    }

    @Override
    public void run() {
        // Configure and create evaluator.
        var configuration = new Configuration()
                .withDatabaseHost(databaseOptions.host)
                .withDatabasePort(databaseOptions.port)
                .withDatabaseUser(databaseOptions.user)
                .withDatabasePassword(databaseOptions.password)
                .withDatabaseName(databaseOptions.database)
                .withShowSQL(databaseOptions.showSQL);
        if (cqlOptions != null) {
            configuration = configuration
                    .withLibrarySearchPath(cqlOptions.librarySearchPath);
        }
        if (otherOptions != null) {
            configuration = configuration
                    .withThreadCount(otherOptions.threadCount);
        }
        this.evaluator = new Evaluator(configuration);
        if (cqlOptions != null) {
            // Load files
            for (var filename : cqlOptions.load) {
                try {
                    this.evaluator.load(filename);
                } catch (IOException e) {
                    throw new RuntimeException(String.format("Error loading file '%s'", filename), e);
                }
            }
            // Install parameter bindings
            for (var entry : cqlOptions.parameterBindings.entrySet()) {
                final var name = entry.getKey();
                final var expression = entry.getValue();
                try {
                    this.evaluator.setParameter(name, expression);
                } catch (Exception e) {
                    throw new RuntimeException(String.format("Error setting parameter '%s' to '%s'", name, expression), e);
                }
            }
        }

        // Initialize user interface.
        final var systemModelInfo = this.evaluator.getEngine().getModel("System").getModelInfo();
        final var domainModelInfo = this.evaluator.getEngine().getModel().getModelInfo();
        this.commandProcessor = new CommandProcessor(evaluator);
        try {
            this.terminal = TerminalBuilder.builder().build();
            // TODO(jmoringe): fallback directory if XDG variable is not set
            this.reader = LineReaderBuilder.builder()
                    .variable(LineReader.HISTORY_FILE,
                            String.format("%s/cql-on-omop/repl-history", System.getenv("XDG_STATE_HOME")))
                    .completer(new Completer(systemModelInfo, domainModelInfo, this.commandProcessor))
                    .terminal(this.terminal).build();
        } catch (IOException e) {
            throw new RuntimeException("Error initializing terminal", e);
        }

        final var libraryManager = this.evaluator.getEngine().getLibraryManager();
        final var theme = new DefaultTheme();
        this.resultPresenter = new ResultPresenter(libraryManager, terminal, theme);
        this.errorPresenter = new ErrorPresenter(libraryManager, terminal, theme);
        try {
            repl();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                this.reader.getHistory().save();
            } catch (IOException e) {
                System.err.printf("Error saving REPL history %s\n", e);
            }
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Repl()).execute(args);
        System.exit(exitCode);
    }

}

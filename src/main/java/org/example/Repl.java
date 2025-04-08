package org.example;

import org.example.engine.Configuration;
import org.example.repl.Completer;
import org.example.repl.Evaluator;
import org.example.repl.ResultPresenter;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.opencds.cqf.cql.engine.execution.EvaluationResult;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@CommandLine.Command(name = "REPL", version = "REPL 0.1", mixinStandardHelpOptions = true)
public class Repl implements Runnable {

    @CommandLine.Option(
            names       = { "-h", "--host" },
            description = "The hostname of the database server from which to retrieve the OMOP data."
    )
    private String databaseHost = "localhost";

    @CommandLine.Option(
            names       = { "-p", "--port"},
            description = "The post on which the database server from which to retrieve the OMOP data listens."
    )
    private int databasePort = 5434;

    @CommandLine.Option(
            names       = { "-u", "--user" },
            description = "The username of the database server account that should be used to retrieve the OMOP data."
    )
    private String databaseUser = "postgres";

    @CommandLine.Option(
            names        = { "--password" },
            defaultValue = "${env:CQL_ON_OMOP_DATABASE_PASSWORD}",
            description  = "The password for the database server account."
    )
    private String databasePassword;

    @CommandLine.Option(
            names       = { "-d", "--database" },
            required    = true,
            description = "The name of the database from which OMOP data should be retrieved.")
    private String databaseName;

    @CommandLine.Option(names = { "-s", "--show-sql"})
    private boolean showSQL = false;

    @CommandLine.Option(
            names       = { "-n", "--threads"},
            description = "Use the specified number of threads when evaluating CQL expressions for multiple context values in parallel."
    )
    private Integer threadCount;

    @CommandLine.Option(names = { "-I" })
    private List<Path> librarySearchPath = List.of();

    @CommandLine.Option(
            names       = { "-l", "--load" },
            description = "Load the specified file as CQL code."
    )
    private List<Path> load = List.of();

    @CommandLine.Option(
            names       = {"-D"},
            description = "Bind parameters of the CQL library to specific values. A binding is of the form NAME=EXPRESSION where EXPRESSION is a CQL expression that is evaluated to produce the CQL value that is assigned to the parameter named NAME. "
    )
    private Map<String, String> parameterBindings = Map.of();

    private Terminal terminal;
    private LineReader reader;

    private Evaluator evaluator;
    private ResultPresenter resultPresenter;

    public Repl() {}

    private void repl() {
        for (int i = 0;; ++i) {
            final String input;
            try {
                input = reader.readLine(String.format("[%s] ", i));
            } catch (EndOfFileException _e) {
                // User probably pressed C-d.
                break;
            }
            if (input.equals("quit")) {
                break;
            }
            EvaluationResult result = null;
            try {
                result = evaluator.evaluate(input);
            } catch (Exception exception) {
                new AttributedStringBuilder()
                        .style(new AttributedStyle().foregroundRgb(0xff0000))
                        .append(exception.toString())
                        .println(terminal);
            }
            if (result != null) {
                resultPresenter.presentResult(result, terminal);
            }
        }
    }

    @Override
    public void run() {
        try {
            // Initialize user interface.
            this.terminal = TerminalBuilder.builder().build();
            this.reader = LineReaderBuilder.builder()
                    .completer(new Completer())
                    .terminal(this.terminal).build();
        } catch (IOException e) {
            throw new RuntimeException("Error initializing terminal", e);
        }
        // Configure and create evaluator.
        final var configuration = new Configuration()
                .withDatabaseHost(databaseHost)
                .withDatabasePort(databasePort)
                .withDatabaseUser(databaseUser)
                .withDatabasePassword(databasePassword)
                .withDatabaseName(databaseName)
                .withThreadCount(threadCount)
                .withShowSQL(showSQL)
                .withLibrarySearchPath(librarySearchPath);
        this.evaluator = new Evaluator(configuration);
        // Load files
        for (Path filename : load) {
            try {
                this.evaluator.load(filename);
            } catch (IOException e) {
                throw new RuntimeException(String.format("Error loading file '%s'", filename), e);
            }
        }
        // Install parameter bindings
        for (Map.Entry<String, String> entry : parameterBindings.entrySet()) {
            final var name = entry.getKey();
            final var expression = entry.getValue();
            try {
                this.evaluator.setParameter(name, expression);
            } catch (Exception e) {
                throw new RuntimeException(String.format("Error setting parameter '%s' to '%s'", name, expression), e);
            }
        }

        this.resultPresenter = new ResultPresenter();
        try {
            repl();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        int exitCode = new CommandLine(new Repl()).execute(args);
        System.exit(exitCode);
    }

    public Map<String, String> getParameterBindings() {
        return parameterBindings;
    }
}

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

@CommandLine.Command(name = "REPL", version = "REPL 0.1", mixinStandardHelpOptions = true)
public class Repl implements Runnable {

    @CommandLine.Option(names = { "-h", "--host" })
    private String databaseHost = "localhost";

    @CommandLine.Option(names = { "-p", "--port" })
    private int databasePort = 5434;

    @CommandLine.Option(names = { "-u", "--user" })
    private String databaseUser = "postgres";

    @CommandLine.Option(
            names        = { "--password" },
            defaultValue = "${env:CQL_ON_OMOP_DATABASE_PASSWORD}"
    )
    private String databasePassword;

    @CommandLine.Option(names = { "-d", "--database" }, required = true)
    private String databaseName;

    @CommandLine.Option(names = { "-s", "--show-sql"})
    private boolean showSQL = false;

    @CommandLine.Option(names = { "-l", "--load" })
    private List<Path> load = List.of();

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
            /*final var input = String.format("""
                        library REPL%s version '1.0.0'
                        using "OMOP" version 'v5.4'

                        codesystem "LOINC": 'http://loinc.org'
                        context Patient
                        define E: %s
                        """, i, expression);*/
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
            // Configure and create evaluator.
            final var configuration = new Configuration()
                    .withDatabaseHost(databaseHost)
                    .withDatabasePort(databasePort)
                    .withDatabaseUser(databaseUser)
                    .withDatabasePassword(databasePassword)
                    .withDatabaseName(databaseName)
                    .withShowSQL(showSQL);
            this.evaluator = new Evaluator(configuration);
            Evaluator evaluator1 = this.evaluator;
            for (Path filename : load) {
                evaluator1.load(filename);
            }

            this.resultPresenter = new ResultPresenter();

            repl();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        int exitCode = new CommandLine(new Repl()).execute(args);
        System.exit(exitCode);
    }

}

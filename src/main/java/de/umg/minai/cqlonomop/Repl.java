package de.umg.minai.cqlonomop;

import de.umg.minai.cqlonomop.commandline.CqlOptions;
import de.umg.minai.cqlonomop.commandline.DatabaseOptions;
import de.umg.minai.cqlonomop.commandline.ExecutionOptions;
import de.umg.minai.cqlonomop.engine.Configuration;
import de.umg.minai.cqlonomop.repl.CommandProcessor;
import de.umg.minai.cqlonomop.repl.Completer;
import de.umg.minai.cqlonomop.repl.Evaluator;
import de.umg.minai.cqlonomop.terminal.*;
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

    @CommandLine.ArgGroup(validate = false, heading = "CQL Options%n")
    CqlOptions cqlOptions;

    @CommandLine.ArgGroup(validate = false, heading = "Other Options%n")
    ExecutionOptions otherOptions;

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
        var configuration = databaseOptions.applyToConfiguration(new Configuration());
        if (cqlOptions != null) {
            configuration = cqlOptions.applyToConfiguration(configuration);
        }
        if (otherOptions != null) {
            configuration = otherOptions.applyToConfiguration(configuration);
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
        final var sourcePresenter = new SourcePresenter(terminal, theme, libraryManager);
        final var valuePresenter = new ValuePresenter(terminal, theme);
        this.resultPresenter = new ResultPresenter(terminal, theme, sourcePresenter, valuePresenter);
        this.errorPresenter = new ErrorPresenter(terminal, theme, sourcePresenter, valuePresenter);
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

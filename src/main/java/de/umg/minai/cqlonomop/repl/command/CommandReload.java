package de.umg.minai.cqlonomop.repl.command;

import de.umg.minai.cqlonomop.repl.Evaluator;
import org.opencds.cqf.cql.engine.execution.EvaluationResult;

import java.util.Collection;
import java.util.List;

public class CommandReload extends AbstractCommand {

    final Evaluator evaluator;

    public CommandReload(final Evaluator evaluator) {
        super("reload");
        this.evaluator = evaluator;
    }

    @Override
    public String getDescription() {
        return "Force reloading of currently loaded libraries.";
    }

    @Override
    public Collection<String> getArguments() {
        return List.of();
    }

    @Override
    public void run(final String arguments) throws Exception {
        this.evaluator.clearLibraryCache();
    }

}

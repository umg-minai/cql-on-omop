package de.umg.minai.cqlonomop.repl.command;

import org.jline.reader.EndOfFileException;
import org.opencds.cqf.cql.engine.execution.EvaluationResult;

import java.util.Collection;
import java.util.List;

public class CommandQuit extends AbstractCommand {

    public CommandQuit() {
        super("quit");
    }

    @Override
    public String getDescription() {
        return "Leave the REPL";
    }

    @Override
    public Collection<String> getArguments() {
        return List.of();
    }

    @Override
    public EvaluationResult run(final String arguments) throws Exception {
        throw new EndOfFileException();
    }

}

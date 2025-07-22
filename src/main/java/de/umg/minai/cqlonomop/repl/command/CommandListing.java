package de.umg.minai.cqlonomop.repl.command;

import de.umg.minai.cqlonomop.repl.Evaluator;
import org.opencds.cqf.cql.engine.execution.EvaluationResult;

import java.util.Collection;
import java.util.List;

public class CommandListing extends AbstractCommand {

    private final Evaluator evaluator;

    public CommandListing(final Evaluator evaluator) {
        super("listing");
        this.evaluator = evaluator;
    }

    @Override
    public String getDescription() {
        return "Print out the CQL \"library\" accumulated from REPL inputs.";
    }

    @Override
    public Collection<String> getArguments() {
        return List.of();
    }

    @Override
    public void run(String arguments) throws Exception {
        final var code = this.evaluator.getPseudoLibrary().getCode();
        System.out.println(code);
    }

}

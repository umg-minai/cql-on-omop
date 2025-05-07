package de.umg.minai.cqlonomop.repl.command;

import org.opencds.cqf.cql.engine.execution.EvaluationResult;

import java.util.Collection;

public interface Command {

    String getName();

    String getDescription();

    Collection<String> getArguments();

    String getSyntaxDescription();

    EvaluationResult run(final String arguments) throws Exception;

}

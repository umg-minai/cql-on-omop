package de.umg.minai.cqlonomop.repl;

import org.jline.reader.ParsedLine;
import org.opencds.cqf.cql.engine.execution.EvaluationResult;

public class Processor {

    final CommandProcessor commandProcessor;

    final Evaluator evaluator;

    public Processor(final CommandProcessor commandProcessor, final Evaluator evaluator) {
        this.commandProcessor = commandProcessor;
        this.evaluator = evaluator;
    }

    public EvaluationResult process(final ParsedLine input) throws Exception {
        return process(input.line());
    }

    public EvaluationResult process(final String input) throws Exception {
        final var trimmed = input.trim();
        if (trimmed.startsWith(",")) {
            return this.commandProcessor.process(trimmed);
        } else {
            return evaluator.evaluate(trimmed);
        }
    }

}

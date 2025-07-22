package de.umg.minai.cqlonomop.repl;

import de.umg.minai.cqlonomop.terminal.OutcomePresenter;
import org.jline.reader.ParsedLine;

public class Processor {

    private final CommandProcessor commandProcessor;

    private final Evaluator evaluator;

    private final OutcomePresenter outcomePresenter;

    public Processor(final CommandProcessor commandProcessor,
                     final Evaluator evaluator,
                     final OutcomePresenter outcomePresenter) {
        this.commandProcessor = commandProcessor;
        this.evaluator = evaluator;
        this.outcomePresenter = outcomePresenter;
    }

    public void process(final ParsedLine input) throws Exception {
        process(input.line());
    }

    public void process(final String input) throws Exception {
        final var trimmed = input.trim();
        this.outcomePresenter.beginPresentation();
        if (trimmed.startsWith(",")) {
            this.commandProcessor.process(trimmed);
        } else {
            this.evaluator.evaluate(trimmed,
                    (object, outcome) -> {
                        synchronized (this) {
                            this.outcomePresenter.presentOutcome(object, outcome);
                        }
                        return 0;
                    },
                    intermediaResults -> 0);
        }
        this.outcomePresenter.endPresentation(); // TODO: if failed
    }

}

package de.umg.minai.cqlonomop.batch;

import de.umg.minai.cqlonomop.engine.MapReduceEngine;

public class AutoClosableOutcomePresenter implements AutoCloseable {

    private final de.umg.minai.cqlonomop.terminal.OutcomePresenter outcomePresenter;

    public AutoClosableOutcomePresenter(final de.umg.minai.cqlonomop.terminal.OutcomePresenter outcomePresenter) {
        this.outcomePresenter = outcomePresenter;
        this.outcomePresenter.beginPresentation();
    }

    public void processOutcome(final Object contextObject, final MapReduceEngine.Outcome outcome) {
        this.outcomePresenter.presentOutcome(contextObject, outcome);
    }

    @Override
    public void close() {
        this.outcomePresenter.endPresentation();
    }

}

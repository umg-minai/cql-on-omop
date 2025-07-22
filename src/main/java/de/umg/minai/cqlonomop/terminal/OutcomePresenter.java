package de.umg.minai.cqlonomop.terminal;

import de.umg.minai.cqlonomop.engine.MapReduceEngine;
import org.jline.terminal.Terminal;

import java.util.Set;

/**
 * Present an {@link de.umg.minai.cqlonomop.engine.MapReduceEngine.Outcome} to a terminal. Both success and failure
 * outcomes are supported. The results contained in a success outcome are presented using a {@link ResultPresenter}
 * and the errors contained in a failure outcome are presented using a {@link ErrorPresenter}.
 * <br/>
 * When {@link #presentOutcome} is repeatedly called with success outcomes, results for expressions are only presented
 * if they have not already been presented in a preceding call of the method.
 * <br/>
 * To allow presenting multiple expression results for the same expression name, for example when presenting
 * corresponding outcomes for multiple different context objects, all individual {@link #presentOutcome} calls should
 * be enclosed in a pair of {@link #beginPresentation} and {@link #endPresentation} so that the set of already
 * presented results is updated for the result set as a whole, not during the presentation of the first element.
 */
public class OutcomePresenter extends AbstractPresenter {

    private final ResultPresenter resultPresenter;

    private final ErrorPresenter errorPresenter;

    private Set<String> oldState = Set.of();

    private Set<String> newState = null;

    private static final int SUCCESS = 0;
    private static final int FAILURE = 1;
    private final int[] counts = {0, 0};

    private long startTime = 0;

    public OutcomePresenter(final Terminal terminal,
                            final Theme theme,
                            final ResultPresenter resultPresenter,
                            final ErrorPresenter errorPresenter) {
        super(terminal, theme);
        this.resultPresenter = resultPresenter;
        this.errorPresenter = errorPresenter;
    }

    public void beginPresentation() {
        this.newState = null;
        this.counts[SUCCESS] = 0;
        this.counts[FAILURE] = 0;
        this.startTime = System.nanoTime();
    }

    public void endPresentation() {
        if (this.newState != null) {
            this.oldState = this.newState;
        }
        final var endTime = System.nanoTime();
        final var elapsed = endTime - this.startTime;
        if (counts[FAILURE] > 0 || (elapsed) > 2_000_000_000) {
            final var builder = new ThemeAwareStringBuilder(this.theme)
                    .style(this.theme.styleForElement(Theme.Element.INACTIVE))
                    .append(String.format("%d ms", elapsed / 1_000_000))
                    .append(", ")
                    .append(String.valueOf(counts[SUCCESS]))
                    .append(" ")
                    .append(counts[SUCCESS] == 1 ? "success" : "successes")
                    .append(", ");
            if (counts[FAILURE] > 0) {
                builder.style(this.theme.styleForElement(Theme.Element.ERROR));
            }
            builder.append(String.valueOf(counts[FAILURE]))
                    .append(" ")
                    .append(counts[FAILURE] == 1 ? "failure" : "failures")
                    .println(this.terminal);
        }
    }

    public void presentOutcome(final Object contextObject, final MapReduceEngine.Outcome outcome) {
        final var builder = new ThemeAwareStringBuilder(this.theme);
        if (outcome instanceof MapReduceEngine.Outcome.Success success) {
            if (!success.result().expressionResults.isEmpty()) {
                printContextObject(builder, contextObject);
                this.resultPresenter.reset(this.oldState);
                this.resultPresenter.presentResult(builder, success.result());
                if (this.newState == null) {
                    this.newState = this.resultPresenter.getSeenResults();
                }
            }
            this.counts[SUCCESS]++;
        } else if (outcome instanceof MapReduceEngine.Outcome.Failure failure) {
            printContextObject(builder, contextObject);
            this.errorPresenter.presentError(builder, failure.error());
            this.counts[FAILURE]++;
        }
        builder.print(this.terminal);
        this.terminal.flush();
    }

    private void printContextObject(final ThemeAwareStringBuilder builder, final Object contextObject) {
        builder.withStyle(Theme.Element.HEADING,
                        contextObject != null ? contextObject.toString() : "Unfiltered Context")
                .append("\n");
    }

}

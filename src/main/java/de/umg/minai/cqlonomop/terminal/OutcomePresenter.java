package de.umg.minai.cqlonomop.terminal;

import de.umg.minai.cqlonomop.engine.MapReduceEngine;
import org.jline.terminal.Terminal;
import org.opencds.cqf.cql.engine.exception.CqlException;
import org.opencds.cqf.cql.engine.exception.Severity;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

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

    public enum Option {
        PRESENT_MESSAGES,
        PRESENT_ERRORS,
        PRESENT_EVALUATION_SUMMARY,
        PRESENT_MESSAGE_SUMMARY,
        PRESENT_MESSAGE_SUMMARY_PROTOTYPES
    }

    public static EnumSet<Option> DEFAULT_OPTIONS = EnumSet.of(Option.PRESENT_MESSAGES,
            Option.PRESENT_ERRORS,
            Option.PRESENT_EVALUATION_SUMMARY);

    private final EnumSet<Option> options;

    private final ResultPresenter resultPresenter;

    private final ErrorPresenter errorPresenter;

    // State tracking

    private Set<String> oldState = Set.of();

    private Set<String> newState = null;

    private static final int SUCCESS = 0;
    private static final int FAILURE = 1;
    private static final int WARNING = 2;
    private final long[] counts = {0, 0, 0};

    private record MessageKey(Severity severity, String message) {}

    private record MessageInfo(Exception prototype, long count) {}

    private Map<MessageKey, MessageInfo> clusters;

    private long startTime = 0;

    public OutcomePresenter(final Terminal terminal,
                            final Theme theme,
                            final ResultPresenter resultPresenter,
                            final ErrorPresenter errorPresenter,
                            final EnumSet<Option> options) {
        super(terminal, theme);
        this.resultPresenter = resultPresenter;
        this.errorPresenter = errorPresenter;
        this.options = options;
        if (options.contains(Option.PRESENT_MESSAGE_SUMMARY)) {
            this.clusters = new HashMap<>();
        }
    }
    public OutcomePresenter(final Terminal terminal,
                            final Theme theme,
                            final ResultPresenter resultPresenter,
                            final ErrorPresenter errorPresenter) {
        this(terminal, theme, resultPresenter, errorPresenter, DEFAULT_OPTIONS);
    }

    public void beginPresentation() {
        this.newState = null;
        this.counts[SUCCESS] = 0;
        this.counts[FAILURE] = 0;
        this.counts[WARNING] = 0;
        if (this.clusters != null) {
            this.clusters.clear();
        }
        this.startTime = System.nanoTime();
    }

    public void endPresentation() {
        if (this.newState != null) {
            this.oldState = this.newState;
        }
        final var builder = new ThemeAwareStringBuilder(this.theme);
        if (this.options.contains(Option.PRESENT_EVALUATION_SUMMARY)) {
            final var endTime = System.nanoTime();
            final var elapsed = endTime - this.startTime;
            if (counts[FAILURE] > 0 || counts[WARNING] > 0 || elapsed > 2_000_000_000) {
                builder.withStyle(this.theme.styleForElement(Theme.Element.INACTIVE),
                        (builder2) -> {
                            builder2.append(String.format("%.3f s", elapsed / 1_000_000_000.0d))
                                    .append(", ")
                                    .append(String.valueOf(counts[SUCCESS]))
                                    .append(" ")
                                    .append(counts[SUCCESS] == 1 ? "success" : "successes")
                                    .append(", ");
                            if (counts[FAILURE] > 0) {
                                builder2.style(this.theme.styleForElement(Theme.Element.ERROR));
                            }
                            builder2.append(String.valueOf(counts[FAILURE]))
                                    .append(" ")
                                    .append(counts[FAILURE] == 1 ? "failure" : "failures");
                            return builder2;
                        });
                if (counts[WARNING] > 0) {
                    builder.append(", ");
                    builder.withStyle(this.theme.styleForElement(Theme.Element.WARNING),
                            (builder2) ->
                                    builder2.append(String.valueOf(counts[WARNING]))
                                            .append(" ")
                                            .append(counts[WARNING] == 1 ? "warning" : "warnings"));
                }
                builder.append("\n");
            }
        }
        // Summary of warning and message clusters.
        if (this.clusters != null && !this.clusters.isEmpty()) {
            for (var entry : this.clusters.entrySet()) {
                final var severity = entry.getKey().severity;
                final var prototype = entry.getValue().prototype;
                final var count = entry.getValue().count;
                var message = prototype.getMessage();
                // For error messages, the CQL engine append '\n<value>' to the message where <value> is the first
                // argument to the Message function. Strip that extra line.
                if (severity == Severity.ERROR && message.indexOf('\n') != -1) {
                    message = message.substring(0, message.lastIndexOf('\n'));
                }
                builder.append(String.format("%5d time(s): %s '%s'\n", count, severity.toString(), message));
            }
            // If requested, detailed presentation of one prototype for each cluster.
            if (this.options.contains(Option.PRESENT_MESSAGE_SUMMARY_PROTOTYPES)) {
                for (var messageInfo : this.clusters.values()) {
                    final var prototype = messageInfo.prototype;
                    builder.append("\n");
                    if (prototype instanceof CqlException cqlException) {
                        if (cqlException.getSeverity() == Severity.ERROR) {
                            this.errorPresenter.presentError(builder, cqlException);
                        } else {
                            this.resultPresenter.presentMessage(builder, cqlException);
                        }
                    } else {
                        this.errorPresenter.presentError(builder, prototype);
                    }
                }
            }
        }
        builder.print(this.terminal);
    }

    public void presentOutcome(final Object contextObject, final MapReduceEngine.Outcome outcome) {
        final var builder = new ThemeAwareStringBuilder(this.theme);
        if (outcome instanceof MapReduceEngine.Outcome.Success success) {
            // Present message and results.
            if (this.resultPresenter.willPresent(success.result())) {
                printContextObject(builder, contextObject);
                this.resultPresenter.reset(this.oldState);
                this.resultPresenter.presentResult(builder, success.result());
                if (this.newState == null) {
                    this.newState = this.resultPresenter.getSeenResults();
                }
            }
            // Count messages.
            var debugResult = success.result().getDebugResult();
            if (debugResult != null) {
                 debugResult.getMessages().stream()
                         .filter(message -> message.getSeverity() == Severity.WARNING)
                         .forEach(warning -> {
                             this.counts[WARNING] += 1;
                             registerMessage(warning);
                         });
            }
            this.counts[SUCCESS]++;
        } else if (outcome instanceof MapReduceEngine.Outcome.Failure failure) {
            if (this.options.contains(Option.PRESENT_ERRORS)) {
                printContextObject(builder, contextObject);
                this.errorPresenter.presentError(builder, failure.error());
            }
            this.counts[FAILURE]++;
            this.registerMessage(failure.error());
        }
        builder.print(this.terminal);
        this.terminal.flush();
    }

    private void printContextObject(final ThemeAwareStringBuilder builder, final Object contextObject) {
        builder.withStyle(Theme.Element.HEADING,
                        contextObject != null ? contextObject.toString() : "Unfiltered Context")
                .append("\n");
    }

    private static final Pattern DATETIME_PATTERN
            = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}(:?T[0-9]{2}:[0-9]{2}:[0-9]{2}(:?.[0-9]{3}(:?[+-][0-9]{2}:[0-9]{2})?)?)?");

    private static final Pattern FLOATING_POINT_VALUE
            = Pattern.compile("[+-]?[0-9]*[.][0-9]*");

    private void registerMessage(final Exception exception) {
        if (this.clusters != null) {
            final Severity keySeverity;
            String keyMessage;
            if (exception instanceof CqlException cqlException) {
                keySeverity = cqlException.getSeverity();
                keyMessage = cqlException.getMessage();
            } else {
                keySeverity = Severity.ERROR;
                keyMessage = exception.getMessage();
            }
            keyMessage = DATETIME_PATTERN.matcher(keyMessage).replaceAll("«DateTime»");
            keyMessage = FLOATING_POINT_VALUE.matcher(keyMessage).replaceAll("«Number»");
            final MessageKey key = new MessageKey(keySeverity, keyMessage);
            this.clusters.compute(key, (_key, value) ->
                    new MessageInfo(value != null ? value.prototype : exception,
                            value != null ? value.count + 1 : 1));
        }
    }

}

package de.umg.minai.cqlonomop.terminal;

import org.hl7.elm.r1.VersionedIdentifier;
import org.jline.terminal.Terminal;
import org.opencds.cqf.cql.engine.exception.CqlException;
import org.opencds.cqf.cql.engine.execution.EvaluationResult;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class ResultPresenter extends AbstractPresenter {

    private final Set<String> seenResults = new HashSet<>();

    private final SourcePresenter sourcePresenter;

    private final ValuePresenter valuePresenter;

    private final Pattern filter;

    public ResultPresenter(final Terminal terminal,
                           final Theme theme,
                           final SourcePresenter sourcePresenter,
                           final ValuePresenter valuePresenter,
                           final String filter) {
        super(terminal, theme);
        this.sourcePresenter = sourcePresenter;
        this.valuePresenter = valuePresenter;
        this.filter = filter != null ? Pattern.compile(filter) : null;
    }

    public ResultPresenter(final Terminal terminal,
                           final Theme theme,
                           final SourcePresenter sourcePresenter,
                           final ValuePresenter valuePresenter) {
        this(terminal, theme, sourcePresenter, valuePresenter, null);
    }

    public void presentResult(final EvaluationResult result) {
        present(builder -> presentResult(builder, result));
    }

    public void presentResult(final ThemeAwareStringBuilder builder, final EvaluationResult result) {
        // Messages
        final var debugResult = result.getDebugResult();
        if (debugResult != null) {
            presentMessages(builder, debugResult.getMessages());
        }
        // Result values
        result.expressionResults.forEach((expressionName, expressionResult) -> {
            if (!this.seenResults.contains(expressionName)
                    && (this.filter == null || this.filter.matcher(expressionName).matches())) {
                this.seenResults.add(expressionName);
                final var value = expressionResult.value();
                builder.withStyle(Theme.Element.IDENTIFIER, expressionName).append(" => ");
                this.valuePresenter.presentValue(builder, value);
            }
        });
    }

    public void presentMessages(final ThemeAwareStringBuilder builder, final List<CqlException> messages) {
        messages.forEach(message -> {
            builder.withStyle(switch (message.getSeverity()) {
                        case WARNING -> Theme.Element.MESSAGE_WARNING;
                        case MESSAGE -> Theme.Element.MESSAGE_INFO;
                        default -> Theme.Element.MESSAGE_OTHER;
                    }, message.getSeverity().toString() + ": " + message.getMessage())
                    .append("\n");
            final var locator = message.getSourceLocator();
            if (locator != null) {
                builder.append("  ");
                builder.withStyle(DefaultTheme.STYLE_HEADING, locator.getLibraryName())
                        .append("\n");
                final var libraryId = new VersionedIdentifier()
                        .withSystem(locator.getLibrarySystemId())
                        .withId(locator.getLibraryName())
                        .withVersion(locator.getLibraryVersion());
                final var sourceLines = this.sourcePresenter.fetchLibrarySource(libraryId);
                this.sourcePresenter.presentSource(builder, sourceLines, locator.getSourceLocation());
            }
        });
    }

    public Set<String> getSeenResults() {
        return Set.copyOf(this.seenResults);
    }

    public void reset() {
        this.seenResults.clear();
    }

    public void reset(final Set<String> state) {
        reset();
        this.seenResults.addAll(state);
    }

}

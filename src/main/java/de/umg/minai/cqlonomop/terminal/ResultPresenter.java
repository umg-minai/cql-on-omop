package de.umg.minai.cqlonomop.terminal;

import org.cqframework.cql.cql2elm.LibraryManager;
import org.hl7.elm.r1.VersionedIdentifier;
import org.jline.terminal.Terminal;
import org.opencds.cqf.cql.engine.execution.EvaluationResult;

import java.util.HashSet;
import java.util.Set;

public class ResultPresenter {

    private final Set<String> seenResults = new HashSet<>();

    private final Terminal terminal;

    private final SourcePresenter sourcePresenter;

    private final ValuePresenter valuePresenter;

    private final Theme theme;

    public ResultPresenter(final Terminal terminal,
                           final Theme theme,
                           final SourcePresenter sourcePresenter,
                           final ValuePresenter valuePresenter) {
        this.terminal = terminal;
        this.sourcePresenter = sourcePresenter;
        this.valuePresenter = valuePresenter;
        this.theme = theme;
    }

    public ResultPresenter(final Terminal terminal, final Theme theme, final LibraryManager libraryManager) {
        this(terminal, theme, new SourcePresenter(terminal, theme, libraryManager), new ValuePresenter(terminal, theme));
    }

    public void presentResult(final EvaluationResult result) {
        final var builder = new ThemeAwareStringBuilder(this.theme);
        // Messages
        final var debugResult = result.getDebugResult();
        if (debugResult != null) {
            final var messages = debugResult.getMessages();
            if (!messages.isEmpty()) {
                messages.forEach(message -> {
                    builder.withStyle(switch (message.getSeverity()) {
                                case WARNING -> Theme.Element.MESSAGE_WARNING;
                                case MESSAGE -> Theme.Element.MESSAGE_INFO;
                                default -> Theme.Element.MESSAGE_OTHER;
                            }, message.getMessage())
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
                        this.sourcePresenter.presentSource(sourceLines, locator.getSourceLocation(), builder);
                    }
                });
            }
        }
        // Result values
        result.expressionResults.forEach((expressionName, expressionResult) -> {
            if (!this.seenResults.contains(expressionName)) {
                this.seenResults.add(expressionName);
                final var value = expressionResult.value();
                builder.withStyle(Theme.Element.IDENTIFIER, expressionName).append(" => ");
                this.valuePresenter.presentValue(value, builder);
            }
        });
        builder.print(this.terminal);
    }

}

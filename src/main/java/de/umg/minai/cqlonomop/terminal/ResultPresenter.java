package de.umg.minai.cqlonomop.terminal;

import org.cqframework.cql.cql2elm.LibraryManager;
import org.hl7.elm.r1.VersionedIdentifier;
import org.jline.terminal.Terminal;
import org.opencds.cqf.cql.engine.execution.EvaluationResult;
import org.opencds.cqf.cql.engine.runtime.Interval;
import org.opencds.cqf.cql.engine.runtime.Quantity;
import org.opencds.cqf.cql.engine.runtime.Ratio;
import org.opencds.cqf.cql.engine.runtime.Tuple;

import java.math.BigDecimal;
import java.util.*;

public class ResultPresenter {

    private final Set<String> seenResults = new HashSet<>();

    private final Terminal terminal;

    private final SourcePresenter sourcePresenter;

    private final Theme theme;

    public ResultPresenter(final Terminal terminal, final SourcePresenter sourcePresenter, final Theme theme) {
        this.terminal = terminal;
        this.sourcePresenter = sourcePresenter;
        this.theme = theme;
    }

    public ResultPresenter(final LibraryManager libraryManager, final Terminal terminal, final Theme theme) {
        this(terminal, new SourcePresenter(libraryManager, terminal, theme), theme);
    }

    public ResultPresenter(final Terminal terminal, final SourcePresenter sourcePresenter) {
        this(terminal, sourcePresenter, new DefaultTheme()); // TODO(jmoringe): singleton
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
                presentResultValue(value, builder);
            }
        });
        builder.print(this.terminal);
    }

    private void presentResultValue(final Object value, final ThemeAwareStringBuilder builder) {
        boolean isOMOP = false;
        // Present type
        final String type;
        if (value == null) {
            type = "Null (unknown)";
        } else if (value instanceof Iterable<?> iterable) {
            final var it = iterable.iterator();
            final var firstElement = it.hasNext() ? it.next() : null;
            final var elementType = firstElement != null ? firstElement.getClass() : Object.class;
            type = String.format("List<%s>", elementType.getSimpleName());
        } else if (value instanceof Interval interval) {
            type = String.format("Interval<%s>", interval.getPointType().getSimpleName());
        } else if (value.getClass().getPackageName().contains("OMOP")) {
            isOMOP = true;
            type = value.getClass().getCanonicalName();
        } else {
            type = value.getClass().getSimpleName();
        }
        builder.withStyle(Theme.Element.TYPE_SPECIFIER, type);
        // Present value
        builder.append(" ");
        if (value instanceof Iterable<?> iterable) {
            iterable.forEach(v -> builder.append(String.format("%n  %s", v)));
        } else if (value instanceof Tuple tuple) {
            builder.append("{");
            tuple.getElements().forEach((name, value2) -> {
                builder.append("\n  ");
                builder.withStyle(Theme.Element.IDENTIFIER, name);
                builder.append(": ");
                presentValue(value2, builder);
            });
            builder.append("\n}");
        } else if (isOMOP) {
            final var clazz = value.getClass();
            builder.append("{");
            Arrays.stream(clazz.getMethods())
                    .filter(method -> method.getName().startsWith("get")
                            && !method.getName().equals("getClass"))
                    .forEach(method -> {
                        final var fieldName = method.getName().substring(3, 4).toLowerCase(Locale.ROOT)
                                + method.getName().substring(4);
                        builder.append("\n");
                        builder.withStyle(Theme.Element.IDENTIFIER, String.format("  %s: ", fieldName));
                        try {
                            final var fieldValue = method.invoke(value);
                            presentFieldValue(fieldValue, builder);
                        } catch (Exception e) {
                            builder.withStyle(Theme.Element.ERROR, String.format("error accessing field: %s", e));
                        }
                    });
            builder.append("\n}");
        } else {
            presentValue(value, builder);
        }
        builder.append("\n");
    }

    public void presentValue(final Object value, final ThemeAwareStringBuilder builder, int limit) {
        final Theme.Element element;
        String string;
        if (value == null) {
            element = Theme.Element.GENERIC_LITERAL;
            string = "null";
        } else if (value instanceof Long) {
            element = Theme.Element.NUMBER_LITERAL;
            string = value + "L";
        } else if (value instanceof Integer
                   || value instanceof BigDecimal || value instanceof Quantity || value instanceof Ratio) {
            element = Theme.Element.NUMBER_LITERAL;
            string = value.toString();
        } else if (value instanceof String) {
            element = Theme.Element.STRING_LITERAL;
            string = String.format("'%s'", value);
        } else {
            element = Theme.Element.GENERIC_LITERAL;
            string = value.toString();
        }
        if (limit > 0 && string.length() > limit) {
            string = string.substring(0, limit - 1) + "…";
        }
        builder.withStyle(element, string);
    }

    public void presentValue(final Object value, final ThemeAwareStringBuilder builder) {
        presentValue(value, builder, -1);
    }

    private void presentFieldValue(final Object fieldValue, final ThemeAwareStringBuilder builder) {
        if (fieldValue instanceof Optional<?> optional) {
            optional.ifPresentOrElse(
                    value -> presentValue(value, builder),
                    () -> builder.withStyle(Theme.Element.INACTIVE, "<no value>"));
        } else {
            presentValue(fieldValue, builder);
        }
    }

}

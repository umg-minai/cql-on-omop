package de.umg.minai.cqlonomop.terminal;

import org.cqframework.cql.cql2elm.LibraryManager;
import org.hl7.elm.r1.VersionedIdentifier;
import org.jline.terminal.Terminal;
import org.opencds.cqf.cql.engine.execution.EvaluationResult;
import org.opencds.cqf.cql.engine.runtime.*;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;

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
        // Present type
        presentTypeOf(value, builder);
        // Present value
        builder.append(" ");
        if (value instanceof Iterable<?> iterable) {
            builder.append("\n");
            iterable.forEach(element -> {
                builder.append("  ");
                presentResultValue(element, builder);
            });
        } else if (value instanceof Tuple tuple) {
            final var elements = tuple.getElements();
            presentFields(fieldPrinter -> elements.forEach((name, value2) -> {
                fieldPrinter.accept(name);
                presentValue(value2, builder);
            }), builder, elements.size() > 4);
        } else if (value.getClass().getPackageName().contains("OMOP")
                   || value.getClass().getPackageName().equals("org.opencds.cqf.cql.engine.runtime")) {
            final var clazz = value.getClass();
            final var getters = Arrays.stream(clazz.getMethods())
                    .filter(method -> method.getName().startsWith("get")
                            && !method.getName().equals("getClass")
                            && !method.getName().equals("getHibernateLazyInitializer"))
                    .sorted(Comparator.comparing(Method::getName))
                    .toList();
            presentFields(fieldPrinter -> getters.forEach(method -> {
                final var methodName = method.getName();
                final var fieldName = methodName.substring(3, 4).toLowerCase(Locale.ROOT)
                        + methodName.substring(4);
                fieldPrinter.accept(fieldName);
                try {
                    final var fieldValue = method.invoke(value);
                    presentFieldValue(fieldValue, builder);
                } catch (Exception e) {
                    builder.withStyle(Theme.Element.ERROR, String.format("error accessing field: %s", e));
                }
            }), builder, getters.size() > 4);
        } else {
            presentValue(value, builder);
        }
        builder.append("\n");
    }

    public void presentTypeOf(final Object value, final ThemeAwareStringBuilder builder) {
        builder.withStyle(Theme.Element.TYPE_SPECIFIER, typeStringOf(value));
    }

    private String typeStringOf(final Object value) {
        if (value == null) {
            return "Null (unknown)";
        } else if (value instanceof Iterable<?> iterable) {
            final var it = iterable.iterator();
            final var firstElement = it.hasNext() ? it.next() : null;
            final var elementType = firstElement != null ? firstElement.getClass() : Object.class;
            return String.format("List<%s>", typeString(elementType));
        } else if (value instanceof Interval interval) {
            return String.format("Interval<%s>", typeString(interval.getPointType()));
        } else {
            return typeString(value.getClass());
        }
    }

    private String typeString(final Class<?> clazz) {
        final var packageName = clazz.getPackageName();
        if (packageName.contains("OMOP")) {
            return clazz.getCanonicalName();
        } else if (packageName.equals("org.opencds.cqf.cql.engine.runtime")) {
            return String.format("System.%s", clazz.getSimpleName());
        } else {
            return clazz.getSimpleName();
        }
    }

    public void presentFields(final Consumer<Consumer<String>> continuation,
                              final ThemeAwareStringBuilder builder,
                              boolean multipleLines) {
        builder.append("{");
        if (multipleLines) {
            continuation.accept(name -> {
                builder.append("\n  ");
                builder.withStyle(Theme.Element.IDENTIFIER, name);
                builder.append(": ");
            });
            builder.append("\n}");
        } else {
            boolean []first = {true};
            continuation.accept(name -> {
                if (first[0]) {
                    first[0] = false;
                } else {
                    builder.append(", ");
                }
                builder.withStyle(Theme.Element.IDENTIFIER, name);
                builder.append(": ");
            });
            builder.append("}");
        }
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

}

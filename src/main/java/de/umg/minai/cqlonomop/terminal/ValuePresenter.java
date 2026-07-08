package de.umg.minai.cqlonomop.terminal;

import org.jline.terminal.Terminal;
import org.jline.utils.AttributedStyle;
import org.opencds.cqf.cql.engine.runtime.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ValuePresenter extends AbstractPresenter {

    private final String ATHENA_UI_URL = "https://athena.ohdsi.org";

    public ValuePresenter(final Terminal terminal, final Theme theme) {
        super(terminal, theme);
    }

    public void presentValue(final Object value) {
        present(builder -> presentValue(builder, value));
    }

    public void presentValue(final ThemeAwareStringBuilder builder, final Object value) {
        // Present type
        presentTypeOf(builder, value);
        // Present value
        builder.append(" ");
        if (value instanceof Iterable<?> iterable) {
            builder.append("\n");
            iterable.forEach(element -> {
                builder.append("  ");
                presentValue(builder, element);
            });
        } else if (value instanceof Tuple tuple) {
            final var elements = tuple.getElements();
            presentFields(builder,
                    fieldPrinter -> elements.forEach((name, value2) -> {
                        fieldPrinter.accept(name);
                        presentValueSimple(builder, value2);
                    }),
                    elements.size() > 4);
        } else if (value instanceof Interval interval) {
            builder.append(interval.getLowClosed() ? "[" : "(");
            presentValueSimple(builder, interval.getLow());
            builder.append(", ");
            presentValueSimple(builder, interval.getHigh());
            builder.append(interval.getHighClosed() ? "]" : ")");
        } else if (value != null
                && (value.getClass().getPackageName().contains("OMOP")
                || value instanceof Code || value instanceof Concept || value instanceof ValueSet)) {
            final var clazz = value.getClass();
            final var getters = Arrays.stream(clazz.getMethods())
                    .filter(method -> method.getName().startsWith("get")
                            && !method.getName().equals("getClass")
                            && !method.getName().equals("getHibernateLazyInitializer"))
                    .collect(Collectors.toMap(Method::getName, method -> method));
            // Omit getter methods that would lead to some relation being presented twice (once for the foreign key and
            // a second time for the related object). Concretely, omit getter methods for which
            // 1. The name ends with "Id"
            // 2. Another getter method with the same name except for the "Id" suffix exists
            var iterator = getters.entrySet().iterator();
            while (iterator.hasNext()) {
                var entry = iterator.next();
                var name = entry.getKey();
                if (name.endsWith("Id") && getters.get(name.substring(0, name.length() - 2)) != null) {
                    iterator.remove();
                }
            }
            // Sort and present the values of the remaining getter methods.
            final var sortedGetters = getters.entrySet().stream()
                    .sorted(Comparator.comparing(Map.Entry::getKey))
                    .toList();
            presentFields(builder,
                    fieldPrinter -> sortedGetters.forEach(entry -> {
                        final var method = entry.getValue();
                        final var methodName = entry.getKey();
                        final var fieldName = methodName.substring(3, 4).toLowerCase(Locale.ROOT)
                                + methodName.substring(4);
                        fieldPrinter.accept(fieldName);
                        try {
                            final var fieldValue = method.invoke(value);
                            presentFieldValue(builder, fieldValue);
                        } catch (Exception e) {
                            builder.withStyle(Theme.Element.ERROR, String.format("error accessing field: %s", e));
                        }
                    }),
                    sortedGetters.size() > 4);
        } else {
            presentValueSimple(builder, value);
        }
        builder.append("\n");
    }

    public void presentTypeOf(final ThemeAwareStringBuilder builder, final Object value) {
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

    public void presentFields(final ThemeAwareStringBuilder builder,
                              final Consumer<Consumer<String>> continuation,
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

    private void presentFieldValue(final ThemeAwareStringBuilder builder, final Object fieldValue) {
        if (fieldValue instanceof Optional<?> optional) {
            optional.ifPresentOrElse(
                    value -> presentValueSimple(builder, value),
                    () -> builder.withStyle(Theme.Element.INACTIVE, "<no value>"));
        } else {
            presentValueSimple(builder, fieldValue);
        }
    }

    public void presentValueSimple(final ThemeAwareStringBuilder builder, final Object value, int limit) {
        final Theme.Element element;
        String string;
        if (value == null) {
            element = Theme.Element.GENERIC_LITERAL;
            string = "null";
        } else if (!terminal.getType().equals(Terminal.TYPE_DUMB)
                && (value.getClass().getSimpleName().equals("Concept")
                || value.getClass().getSuperclass().getSimpleName().equals("Concept"))) {
            var id = 0L;
            try {
                final var raw = value.getClass().getMethod("getConceptId").invoke(value);
                if (raw instanceof Integer i) {
                    id = i.longValue();
                }
            } catch (final NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {}
            final var linkStart = String.format("\033]8;;%s/search-terms/terms/%d\033\\", ATHENA_UI_URL, id);
            final var linkEnd = "\033]8;;\033\\";
            builder.styled(AttributedStyle.HIDDEN, linkStart);
            builder.withStyle(Theme.Element.GENERIC_LITERAL, value.toString());
            builder.styled(AttributedStyle.HIDDEN, linkEnd);
            return;
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

    public void presentValueSimple(final ThemeAwareStringBuilder builder, final Object value) {
        presentValueSimple(builder, value, -1);
    }

}

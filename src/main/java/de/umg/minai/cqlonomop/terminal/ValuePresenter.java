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

    public int presentValueSimple(final ThemeAwareStringBuilder builder, final Object value, int limit) {
        final Theme.Element elementStyle;
        String string;
        if (value == null) {
            return printWithStyleMaybeTruncate(builder, Theme.Element.GENERIC_LITERAL, "null", limit);
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
            final var valueString = value.toString();
            builder.styled(AttributedStyle.HIDDEN, linkStart);
            final var length = printWithStyleMaybeTruncate(builder, Theme.Element.GENERIC_LITERAL, valueString, limit);
            builder.styled(AttributedStyle.HIDDEN, linkEnd);
            return length;
        } else if (value instanceof Long) {
            return printWithStyleMaybeTruncate(builder, Theme.Element.NUMBER_LITERAL, value + "L", limit);
        } else if (value instanceof Integer
                   || value instanceof BigDecimal || value instanceof Quantity || value instanceof Ratio) {
            return printWithStyleMaybeTruncate(builder, Theme.Element.NUMBER_LITERAL, value.toString(), limit);
        } else if (value instanceof String) {
            return printWithStyleMaybeTruncate(builder, Theme.Element.STRING_LITERAL, String.format("'%s'", value), limit);
        } else if (value instanceof Iterable<?> iterable) {
            final var listBuilder = new StringBuilder();
            builder.append("[");
            var length = 0;
            var isTruncated = false;
            var isFirst = true;
            for (var element : iterable) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    if (!(length + 4 < limit)) {
                        isTruncated = true;
                        break;
                    }
                    builder.append(", ");
                }
                length += presentValueSimple(builder, element, limit - length);
                if (length == limit) {
                    isTruncated = true;
                    break;
                }
            }
            if (isTruncated) {
                builder.append("…");
            }
            builder.append("]");
            return length;
        } else if (value instanceof DateTime dateTime) {
            elementStyle = Theme.Element.GENERIC_LITERAL;
            final var offsetDateTime = dateTime.getNormalized(Precision.MILLISECOND);
            final var offsetSeconds = dateTime.getZoneOffset().getTotalSeconds();
            final var precision = dateTime.getPrecision();
            final int[] length = {0};
            builder.withStyle(Theme.Element.GENERIC_LITERAL,
                    (var builder1) -> {
                        length[0] += addDateTimeComponent(builder1, 0, offsetDateTime.getYear(), precision);
                        length[0] += addDateTimeComponent(builder1, 1, offsetDateTime.getMonthValue(), precision);
                        length[0] += addDateTimeComponent(builder1, 2, offsetDateTime.getDayOfMonth(), precision);
                        length[0] += addDateTimeComponent(builder1, 3, offsetDateTime.getHour(), precision);
                        length[0] += addDateTimeComponent(builder1, 4, offsetDateTime.getMinute(), precision);
                        length[0] += addDateTimeComponent(builder1, 5, offsetDateTime.getSecond(), precision);
                        length[0] += addDateTimeComponent(builder1, 6, offsetDateTime.get(precision.toChronoField()), precision);
                        length[0] += addDateTimeComponent(builder1, 7, (offsetSeconds >= 0) ? "+" : "-", precision);
                        length[0] += addDateTimeComponent(builder1, 8, Math.abs(offsetSeconds) / 3600, precision);
                        length[0] += addDateTimeComponent(builder1, 9, (Math.abs(offsetSeconds) % 3600) / 60, precision);
                        return builder1;
                    });
            return length[0];
        } else if (value instanceof Interval interval) {
            var length = 0;
            builder.append("Interval");
            length += "Interval".length();
            builder.append(interval.getLowClosed() ? "[" : "(");
            length += 1;
            length += presentValueSimple(builder, interval.getLow(), limit - length);
            builder.append(", ");
            length += 2;
            length += presentValueSimple(builder, interval.getHigh(), limit - length);
            builder.append(interval.getHighClosed() ? "]" : ")");
            length += 1;
            return length;
        }
        return printWithStyleMaybeTruncate(builder, Theme.Element.GENERIC_LITERAL, value.toString(), limit);
    }

    public void presentValueSimple(final ThemeAwareStringBuilder builder, final Object value) {
        presentValueSimple(builder, value, -1);
    }

    private int printWithStyleMaybeTruncate(final ThemeAwareStringBuilder builder,
                                            final Theme.Element style,
                                            final String string,
                                            int limit) {
        final var maybeTruncated = (limit > 0 && string.length() > limit)
            ? string.substring(0, limit - 1) + "…"
            : string;
        builder.withStyle(style, maybeTruncated);
        return maybeTruncated.length();
    }

    private <T> int addDateTimeComponent(final ThemeAwareStringBuilder builder,
                                         final int index,
                                         final T value,
                                         final Precision precision) {
        final var precisionIndex = precision.toDateTimeIndex();
        final String[] components = { "%04d", "-%02d", "-%02d", "T%02d", ":%02d", ":%02d", ".%03d", "%s", "%02d", ":%02d" };
        final var string = String.format(components[index], value);
        var length = 0;
        if (!terminal.getType().equals(Terminal.TYPE_DUMB)) {
            if (index <= precisionIndex) {
                builder.append(string);
            } else {
                builder.withStyle(Theme.Element.INACTIVE, string);
            }
            length += string.length();
        } else {
            if (index == precisionIndex && precisionIndex < components.length) {
                builder.append("«");
                length += 1;
            }
            builder.append(string);
            length += string.length();
            if (index == components.length - 1 && precisionIndex < components.length - 1) {
                builder.append("»");
                length += 1;
            }
        }
        return length;
    }

}

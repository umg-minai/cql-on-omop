package de.umg.minai.cqlonomop.terminal;

import org.jline.terminal.Terminal;
import org.opencds.cqf.cql.engine.runtime.*;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Consumer;

public class ValuePresenter extends AbstractPresenter {

    public ValuePresenter(final Terminal terminal, final Theme theme) {
        super(terminal, theme);
    }

    public void presentValue(final Object value) {
        present(builder -> presentValue(value, builder));
    }

    public ThemeAwareStringBuilder presentValue(final Object value, final ThemeAwareStringBuilder builder) {
        // Present type
        presentTypeOf(value, builder);
        // Present value
        builder.append(" ");
        if (value instanceof Iterable<?> iterable) {
            builder.append("\n");
            iterable.forEach(element -> {
                builder.append("  ");
                presentValue(element, builder);
            });
        } else if (value instanceof Tuple tuple) {
            final var elements = tuple.getElements();
            presentFields(fieldPrinter -> elements.forEach((name, value2) -> {
                fieldPrinter.accept(name);
                presentValueSimple(value2, builder);
            }), builder, elements.size() > 4);
        } else if (value instanceof Interval interval) {
            builder.append(interval.getLowClosed() ? "[" : "(");
            presentValueSimple(interval.getLow(), builder);
            builder.append(", ");
            presentValueSimple(interval.getHigh(), builder);
            builder.append(interval.getHighClosed() ? "]" : ")");
        } else if (value != null
                && (value.getClass().getPackageName().contains("OMOP")
                || value instanceof Code || value instanceof Concept || value instanceof ValueSet)) {
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
            presentValueSimple(value, builder);
        }
        builder.append("\n");
        return builder;
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
                    value -> presentValueSimple(value, builder),
                    () -> builder.withStyle(Theme.Element.INACTIVE, "<no value>"));
        } else {
            presentValueSimple(fieldValue, builder);
        }
    }

    public void presentValueSimple(final Object value, final ThemeAwareStringBuilder builder, int limit) {
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

    public void presentValueSimple(final Object value, final ThemeAwareStringBuilder builder) {
        presentValueSimple(value, builder, -1);
    }

}

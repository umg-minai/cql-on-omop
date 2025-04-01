package org.example.repl;

import org.jline.terminal.Terminal;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.opencds.cqf.cql.engine.execution.EvaluationResult;

import java.util.*;

public class ResultPresenter {

    private final Set<String> seenResults = new HashSet<>();

    public void presentResult(final EvaluationResult result, final Terminal terminal) {
        final var string = new AttributedStringBuilder();
        result.expressionResults.forEach((expressionName, expressionResult) -> {
            if (!this.seenResults.contains(expressionName)) {
                this.seenResults.add(expressionName);
                final var value = expressionResult.value();
                string.style(new AttributedStyle().foregroundRgb(0x808080))
                        .append(String.format("%s => ", expressionName));
                presentValue(value, string);
            }
        });
        string.print(terminal);
    }

    private void presentValue(final Object value, final AttributedStringBuilder string) {
        string.style(new AttributedStyle().foregroundRgb(0xa0f0a0));
        // Present type
        final String type;
        if (value == null) {
            type = "Null (unknown)";
        } else if (value instanceof Iterable<?> iterable) {
            final var it = iterable.iterator();
            final var elementType = it.hasNext() ? it.next().getClass() : Object.class;
            type = String.format("List<%s>", elementType.getSimpleName());
        } else {
            type = value.getClass().getSimpleName();
        }
        string.append(type);
        // Present value
        string.style(new AttributedStyle().foregroundRgb(0xa0a0f0));
        if (value == null) {
            string.append(" null\n");
        } else if (value instanceof Iterable<?> iterable) {
            string.append("\n");
            iterable.forEach(v -> string.append(String.format("  %s%n", v)));
        } else if (value.getClass().getPackageName().contains("OMOP")) {
            string.append("\n");
            final var clazz = value.getClass();
            string.append(String.format("%s {\n", clazz.getCanonicalName()));
            Arrays.stream(clazz.getMethods())
                    .filter(method -> method.getName().startsWith("get")
                            && !method.getName().equals("getClass"))
                    .forEach(method -> {
                        final var fieldName = method.getName().substring(3, 4).toLowerCase(Locale.ROOT)
                                + method.getName().substring(4);
                        Object fieldValue;
                        try {
                            fieldValue = method.invoke(value);
                        } catch (Exception e) {
                            fieldValue = String.format("error accessing field: %s", e);
                        }
                        string.append(String.format("  %s: ", fieldName));
                        presentFieldValue(fieldValue, string);
                        string.append("\n");
                    });
            string.append("}\n");
        } else {
            string.append(String.format(" %s%n", value));
        }
    }

    private void presentFieldValue(final Object fieldValue, final AttributedStringBuilder string) {
        if (fieldValue instanceof Optional<?> optional) {
            optional.ifPresentOrElse(
                    value -> string.append(value.toString()),
                    () -> {
                        string.style(new AttributedStyle().foregroundRgb(0x808080));
                        string.append("<no value>");
                        string.style(new AttributedStyle().foregroundRgb(0xa0a0f0));
                    });
        } else {
            string.append(fieldValue.toString());
        }
    }

}

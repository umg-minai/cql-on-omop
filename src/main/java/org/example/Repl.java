package org.example;

import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Attributes;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.opencds.cqf.cql.engine.exception.CqlException;
import org.opencds.cqf.cql.engine.execution.EvaluationResult;

public class Repl {

    private static void presentResult(final EvaluationResult result, final Terminal terminal) {
        final var string = new AttributedStringBuilder();
        result.expressionResults.forEach((expressionName, expressionResult) -> {
            final var value = expressionResult.value();
            string.style(new AttributedStyle().foregroundRgb(0x808080))
                  .append(String.format("%s => ", expressionName));
            string.style(new AttributedStyle().foregroundRgb(0xa0f0a0));
            final String type;
            if (value instanceof Iterable<?> iterable) {
                final var it = iterable.iterator();
                final var elementType = it.hasNext() ? it.next().getClass() : Object.class;
                type = String.format("List<%s>", elementType.getSimpleName());
            } else {
                type = value.getClass().getSimpleName();
            }
            string.append(type);
            string.style(new AttributedStyle().foregroundRgb(0xa0a0f0));
            if (value instanceof Iterable<?> iterable) {
                string.append("\n");
                iterable.forEach(v -> string.append(String.format("  %s%n", v)));
            } else {
                string.append(String.format(" %s%n", value));
            }
        });
        string.print(terminal);
    }

    public static void main(String[] args) throws Exception {
        final var engine = new CQLonOMOPEngine();

        final var terminal = TerminalBuilder.builder().build();
        final var reader = LineReaderBuilder.builder().terminal(terminal).build();
        for (int i = 0;; ++i) {
            final String expression;
            try {
                expression = reader.readLine(String.format("[%s] ", i));
            } catch (EndOfFileException _e) {
                // User probably pressed C-d.
                break;
            }
            if (expression.equals("quit")) {
                break;
            }
            final var input = String.format("""
                        library REPL%s version '1.0.0'
                        using "OMOP" version 'v5.4'
                        //context Patient
                        codesystem "LOINC": 'http://loinc.org'
                        define E: %s
                        """, i, expression);
            EvaluationResult result = null;
            try {
                result = engine.evaluateExpression(input);
            } catch (CqlException exception) {
                new AttributedStringBuilder()
                        .style(new AttributedStyle().foregroundRgb(0xff0000))
                        .append(exception.toString())
                        .println(terminal);
            }
            if (result != null) {
                presentResult(result, terminal);
            }


        }
    }
}

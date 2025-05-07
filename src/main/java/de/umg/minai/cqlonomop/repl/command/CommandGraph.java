package de.umg.minai.cqlonomop.repl.command;

import de.umg.minai.cqlonomop.repl.Evaluator;
import org.opencds.cqf.cql.engine.execution.EvaluationResult;
import org.opencds.cqf.cql.engine.runtime.Date;
import org.opencds.cqf.cql.engine.runtime.Tuple;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CommandGraph extends AbstractCommand{

    private final Evaluator evaluator;

    public CommandGraph(final Evaluator evaluator) {
        super("graph");
        this.evaluator = evaluator;
    }

    @Override
    public String getDescription() {
        return "Evaluate expression and write date for a temporal histogram into given file";
    }

    @Override
    public Collection<String> getArguments() {
        return List.of("FILENAME", "EXPRESSION");
    }

    @Override
    public EvaluationResult run(final String arguments) throws Exception {
        final var filenameAndExpression = arguments.split(" ", 2);
        final var expression = filenameAndExpression[1];
        final var filename = filenameAndExpression[0];
        System.out.printf("Attempting to produce graph for %s%n", filename);
        final var object = this.evaluator.evaluateExpression(expression);
        if (object instanceof Tuple tuple) {
            final var counts = new HashMap<java.util.Date, Integer>();
            for (Object dates : tuple.getElements().values()) {
                if (dates instanceof List<?> list) {
                    for (Object date : list) {
                        if (date instanceof Date date1) {
                            // CQL engine Dates don't have a hashCode method
                            final var javaDate = date1.toJavaDate();
                            counts.compute(javaDate, (_date, count) -> (count != null ? count : 0) + 1);
                        }
                    }
                }
            }
            System.out.printf("Attempting to write graph to %s%n", filename);
            try (var file = Files.newBufferedWriter(Path.of(filename))) {
                final var dates = counts.keySet().stream().sorted().toList();
                java.util.Date previous = null;
                for (java.util.Date date : dates) {
                    if (previous != null && (date.getTime() - previous.getTime()) > 1000 * 24 * 60 * 60) {
                        file.write("\n");
                    }
                    file.write(String.format("%s-%s-%s %d\n",
                            1900 + date.getYear(),
                            1 + date.getMonth(),
                            date.getDate(),
                            counts.get(date)));
                    previous = date;
                }
            }
        } else {
            throw new RuntimeException(String.format("The expression evaluated to %s, not a tuple of shape Tuple{patient1 List<Interval<Date>> patient2 List<Interval<Date>> ...}", object));
        }
        return null;
    }

}

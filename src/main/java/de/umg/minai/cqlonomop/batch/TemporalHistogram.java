package de.umg.minai.cqlonomop.batch;

import de.umg.minai.cqlonomop.engine.MapReduceEngine;
import org.opencds.cqf.cql.engine.execution.ExpressionResult;
import org.opencds.cqf.cql.engine.runtime.Date;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemporalHistogram extends ExpressionResultToFileWriterSink {

    private final Map<String,Map<java.util.Date, Integer>> counts = new HashMap<>();

    public TemporalHistogram(final MapReduceEngine ignoredEngine, final List<String> resultNames) {
        super(resultNames);
    }

    @Override
    protected void innerProcessResults(final Map<Object, MapReduceEngine.Outcome> intermediateResults) {
        this.counts.forEach((name, counts) -> {
            try (var stream = new PrintWriter(ensureOutputFile(name, "txt").writer())) {
                final var dates = counts.keySet().stream().sorted().toList();
                java.util.Date previous = null;
                for (java.util.Date date : dates) {
                    if (previous != null && (date.getTime() - previous.getTime()) > 1000 * 24 * 60 * 60) {
                        stream.print("\n");
                    }
                    stream.printf("%s-%s-%s %d\n",
                            1900 + date.getYear(),
                            1 + date.getMonth(),
                            date.getDate(),
                            counts.get(date));
                    previous = date;
                }
            }
        });
    }

    @Override
    protected void processExpressionResult(final Object contextObject,
                                           final String name,
                                           final ExpressionResult expressionResult) {
        super.processExpressionResult(contextObject, name, expressionResult);
        final var countsForExpression = this.counts.compute(name, (ignored, counts) ->
                (counts != null ? counts : new HashMap<>()));
        final var dates = expressionResult.value();
        if (dates instanceof Iterable<?> iterable) {
            for (Object element : iterable) {
                if (element instanceof Date date) {
                    // CQL engine Dates don't have a hashCode method
                    final var javaDate = date.toJavaDate();
                    countsForExpression.compute(javaDate, (ignored, count) -> (count != null ? count : 0) + 1);
                } else {
                    System.err.printf("Ignoring non-Date object %s of type %s\n",
                            element, element.getClass().getName());
                }
            }
        } else {
            throw new RuntimeException("The expression evaluated to %s, not a list of dates.");
        }
    }

}

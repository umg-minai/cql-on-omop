package de.umg.minai.cqlonomop.batch;

import de.umg.minai.cqlonomop.engine.MapReduceEngine;
import org.opencds.cqf.cql.engine.execution.ExpressionResult;
import org.opencds.cqf.cql.engine.runtime.Tuple;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CSVWriterSink extends ExpressionResultToFileWriterSink {

    // TODO(jmoringe): make this configurable
    private final boolean contextColumn = false;

    private final List<List<String>> slice = new ArrayList<>();

    public CSVWriterSink(final MapReduceEngine ignoredEngine, final List<String> resultNames) {
        super(resultNames);
    }

    @Override
    protected void processExpressionResult(final Object contextObject,
                                           final String name,
                                           final ExpressionResult expressionResult) {
        final var outputFile = ensureOutputFile(name, "csv");
        super.processExpressionResult(contextObject, name, expressionResult);
        final var value = expressionResult.value();
        int rowIndex = 0;
        // If the value is iterable, generate one row for each element.
        if (value instanceof Iterable<?> iterable) {
            for (var element : iterable) {
                final var row = ensureRow(rowIndex);
                maybeAddContext(row, contextObject);
                addToRow(row, element);
                rowIndex++;
            }
        } else if (value instanceof Tuple) {
            final var row = ensureRow(rowIndex);
            maybeAddContext(row, contextObject);
            addToRow(row, value);
        } else {
            throw new RuntimeException(String.format("Unexpected result for definition name '%s': %s (type %s, expected List or Tuple)",
                    name,
                    value,
                    value.getClass().getName()));
        }
        printSlice(this.slice, new PrintWriter(outputFile.writer()));
        this.slice.clear();
    }

    private List<String> ensureRow(int index) {
        final List<String> row;
        if (index < this.slice.size()) {
            row = this.slice.get(index);
        } else {
            row = new ArrayList<>();
            this.slice.add(row);
        }
        return row;
    }

    private void maybeAddContext(final List<String> row, final Object contextObject) {
        if (this.contextColumn && contextObject != null) {
            row.add(contextObject.toString());
        }
    }

    private void addToRow(final List<String> row, final Object object) {
        if (object instanceof Iterable<?> iterable) {
            addIterableElements(row, iterable);
        } else if (object instanceof Tuple tuple) {
            addTupleElements(row, tuple);
        } else {
            throw new RuntimeException(String.format("Unexpected result element %s (type %s)",
                    object,
                    object.getClass().getName()));
        }
    }

    private void addIterableElements(final List<String> row, final Iterable<?> iterable) {
        iterable.forEach(element -> row.add(element != null ? element.toString() : "null"));
    }

    private void addTupleElements(final List<String> row, final Tuple tuple) {
        final var namedElements = tuple.getElements();
        namedElements.keySet().forEach(key -> {
            final var value = namedElements.get(key);
            row.add(value != null ? value.toString() : "null");
        });
    }

    private void printSlice(final List<List<String>> slice, final PrintWriter stream) {
        slice.forEach(row -> printRow(row, stream));
    }

    private void printRow(final List<String> row, final PrintWriter stream) {
        if (!row.isEmpty()) {
            var isFirst = true;
            for (final var cell : row) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    stream.print(",");
                }
                stream.print(cell);
            }
            stream.println();
        }
    }

}

package de.umg.minai.cqlonomop.batch;

import de.umg.minai.cqlonomop.engine.MapReduceEngine;
import org.opencds.cqf.cql.engine.execution.ExpressionResult;
import org.opencds.cqf.cql.engine.runtime.Tuple;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class CSVWriterSink extends ExpressionResultToFileWriterSink {

    private final boolean includeContextAsColumn;

    private final List<List<String>> slice = new ArrayList<>();

    public CSVWriterSink(final MapReduceEngine ignoredEngine,
                         final List<String> resultNames,
                         boolean includeContextAsColumn) {
        super(resultNames);
        this.includeContextAsColumn = includeContextAsColumn;
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
        } else if (value instanceof Tuple || value.getClass().getPackageName().startsWith("OMOP")) {
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
        if (this.includeContextAsColumn && contextObject != null) {
            row.add(contextObject.toString());
        }
    }

    private void addToRow(final List<String> row, final Object object) {
        if (object instanceof Iterable<?> iterable) {
            addIterableElements(row, iterable);
        } else if (object instanceof Tuple tuple) {
            addTupleElements(row, tuple);
        } else if (object.getClass().getPackageName().startsWith("OMOP")) {
            addObjectFields(row, object);
        } else {
            throw new RuntimeException(String.format("Unexpected result element %s (type %s, expected List or Tuple)",
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

    private void addObjectFields(final List<String> row, final Object object) {
        final var clazz = object.getClass();
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
            if (getters.get(name + "Id") != null) {
                iterator.remove();
            }
        }
        // Sort and present the values of the remaining getter methods.
        getters.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    final var method = entry.getValue();
                    try {
                        final var fieldValue = method.invoke(object);
                        if (fieldValue == null) {
                          row.add("");
                        } else if (fieldValue instanceof Optional<?> optional) {
                            row.add(optional.isPresent() ? optional.get().toString() : "");
                        } else {
                            row.add(fieldValue.toString());
                        }
                    } catch (Exception e) {
                        row.add(String.format("«Error: %s»", e));
                    }
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

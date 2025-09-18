package de.umg.minai.cqlonomop.batch;

import de.umg.minai.cqlonomop.engine.MapReduceEngine;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ExpressionResultToFileWriterSink extends BasicResultSink {

    protected record OutputFile(Path path, FileWriter writer) {};

    protected final Map<String, OutputFile> outputFiles = new HashMap<>();

    public ExpressionResultToFileWriterSink(final List<String> resultNames) {
        super(resultNames);
    }

    @Override
    public ResultInfo processResults(final Map<Object, MapReduceEngine.Outcome> intermediateResults) {
        try {
            final var result = super.processResults(intermediateResults);
            innerProcessResults(intermediateResults);
            return result;
        } finally {
            for (var entry : this.outputFiles.entrySet()) {
                final var stream = entry.getValue().writer;
                try {
                    stream.close();
                } catch (final IOException e) {
                    // TODO: failure result or rethrow?
                    System.err.printf("Failed to close stream for '%s': %s\n", entry.getKey(), e);
                }
            }
        }
    }

    protected void innerProcessResults(final Map<Object, MapReduceEngine.Outcome> intermediateResults) {
    }

    protected OutputFile ensureOutputFile(final String name, final String suffix) {
        final var existing = this.outputFiles.get(name);
        if (existing != null) {
            return existing;
        } else {
            final var filename = Path.of(name + "." + suffix);
            System.out.printf("'%s' -> %s\n", name, filename);
            try {
                final var output = new OutputFile(filename, new FileWriter(filename.toFile()));
                outputFiles.put(name, output);
                return output;
            } catch (final IOException e) {
                final var message = String.format("Could not open file '%s' for expression '%s': %s",
                        filename,
                        name,
                        e);
                throw new RuntimeException(message, e);
            }
        }
    }

}

package de.umg.minai.cqlonomop.batch;

import de.umg.minai.cqlonomop.engine.MapReduceEngine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;

public class GnuplotHistogramSink extends TemporalHistogram {

    private record Graph(Path dataFile, String title) {}

    private record Plot(Path imageFile, List<Graph> graphs) {}

    private Pattern groupBy = Pattern.compile("(.*)-([^-]+)");

    public GnuplotHistogramSink(MapReduceEngine ignoredEngine, List<String> resultNames) {
        super(ignoredEngine, resultNames);
    }

    @Override
    public ResultInfo processResults(Map<Object, MapReduceEngine.Outcome> intermediateResults) {
        final var result = super.processResults(intermediateResults);
        final var plots = new HashMap<String, Plot>();
        // Group output files into plots.
        for (final var entry: outputFiles.entrySet()) {
            final var name = entry.getKey();
            final var outputFile = entry.getValue();
            final var matcher = this.groupBy.matcher(name);
            if (!matcher.matches()) {
                throw new RuntimeException(String.format("Expression name %s does not match %s",
                        name, this.groupBy));
            }
            final var group = matcher.group(1);
            final var title = matcher.group(2);
            final var plot = plots.computeIfAbsent(group,
                    (_ignored) -> new Plot(Path.of(group + ".png"), new LinkedList<>()));
            plot.graphs.add(new Graph(outputFile.path(), title));
        }
        // Render plots.
        for (final var plot : plots.values()) {
            try {
                gnuplot(plot.graphs(), plot.imageFile());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    private void gnuplot(final List<Graph> graphs, final Path outputFile) throws IOException, InterruptedException {
        final var format = "png";
        //final var plotType = "smooth sbezier";
        final var plotType = "with boxes";
        final var scriptFile = Path.of("script.gplt");
        System.out.printf("%s -> '%s'\n", graphs.stream().map(Graph::dataFile).toList(), outputFile);

        final var builder = new StringBuilder(String.format("""
                set terminal %s size 1200,600
                set output "%s"
                
                set xdata time
                set timefmt "%%Y-%%m-%%d"
                
                set style fill solid 1.0
                set boxwidth 0.02 relative
                
                set yrange [0:]
                
                """,
                format, outputFile));
        var isFirst = true;
        for (final var graph : graphs.stream().sorted(Comparator.comparing(Graph::title)).toList()) {
            if (isFirst) {
                builder.append(String.format("plot \"%s\" using 1:2 %s title \"%s\"",
                        graph.dataFile(), plotType, graph.title()));
                isFirst = false;
            } else {
                builder.append(String.format(",\\\n     \"%s\" using 1:2 %s title \"%s\"",
                        graph.dataFile(), plotType, graph.title()));
            }
        }
        Files.writeString(scriptFile, builder.toString());

        final var process = new ProcessBuilder("gnuplot", scriptFile.toString()).start();
        // TODO(jmoringe): This should probably be asynchronous
        final var output = Arrays.toString(process.getInputStream().readAllBytes());
        final var error = Arrays.toString(process.getErrorStream().readAllBytes());
        final var code = process.waitFor();
        if (code != 0) {
            throw new RuntimeException(String.format("Gnuplot failed with code %d.\n%s%s", code, output, error));
        }
        // Only delete files if everything worked. Otherwise, leave the temporary files around for debugging.
        Files.delete(scriptFile);
        for (final var graph : graphs) {
            Files.delete(graph.dataFile());
        }
    }
}

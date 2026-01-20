package de.umg.minai.cqlonomop.batch;

import de.umg.minai.cqlonomop.engine.MapReduceEngine;
import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(
        name = "histogram",
        description = """
                      Write a temporal histogram to one or more files. The output is human-readable and also suitable \
                      for plotting with gnuplot. Each expression selected by --result-name is written into a file \
                      EXPRESSION_NAME.txt.
                      """,
        usageHelpAutoWidth = true
)
public class GnuplotHistogramSinkCommandAdapter implements ResultSinkCommandAdapter {

    public ResultSink createConfigured(final MapReduceEngine engine, final List<String> resultNames) {
        return new GnuplotHistogramSink(engine, resultNames);
    }

}

package de.umg.minai.cqlonomop.batch;

import de.umg.minai.cqlonomop.engine.MapReduceEngine;
import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(
        name = "histogram",
        description = """
                      Write a temporal histogram to one or more files. The output is human-readable and also suitable \
                      for plotting with gnuplot. Each expression selected by --result-name is written into a file \
                      EXPRESSION_NAME.txt.""",
        usageHelpAutoWidth = true
)
public class GnuplotHistogramSinkCommandAdapter implements ResultSinkCommandAdapter {

    @CommandLine.Option(
            names = "--group-by",
            defaultValue = GnuplotHistogramSink.GROUP_BY_DEFAULT,
            description = """
                          A regular expression that is applied to result names in order to split them into a plot name \
                          and a within-plot series name. Therefore, the supplied regular expression has to contain two \
                          capture groups.
                          Default value: ${DEFAULT-VALUE}"""
    )
    private String groupBy = GnuplotHistogramSink.GROUP_BY_DEFAULT;

    @CommandLine.Option(
            names = "--plot-type",
            description = """
                          The plot type to use.
                          The following plot types are supported at the moment: ${COMPLETION-CANDIDATES}
                          Default value: ${DEFAULT-VALUE}"""
    )
    private GnuplotHistogramSink.PlotType plotType = GnuplotHistogramSink.PlotType.BOXES;

    @CommandLine.Option(
            names = "--keep-temporary-files",
            negatable = true,
            defaultValue = "false",
            description = """
                          Control whether temporary files should be kept around after producing the plot.
                          Default value: ${DEFAULT-VALUE}"""
    )
    private Boolean keepTemporaryFiles = false;

    public ResultSink createConfigured(final MapReduceEngine engine, final List<String> resultNames) {
        return new GnuplotHistogramSink(engine, resultNames, this.groupBy, this.plotType, this.keepTemporaryFiles);
    }

}

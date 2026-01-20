package de.umg.minai.cqlonomop.batch;

import de.umg.minai.cqlonomop.engine.MapReduceEngine;
import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(
        name = "csv",
        description = """
                      Write the selected result objects into CSV files.
                      Each expression selected by --result-name is written into a file EXPRESSION_NAME.csv. A \
                      --result-name option that selects multiple expressions leads to multiple CSV files. Result \
                      objects are translated into rows and columns of the CSV files according to the following rules:
                      - The elements of a list turn into rows of the CSV file
                      - The elements of a tuple turn into columns of a row
                      - A "scalar" value turns into a single column of a row
                      - The results for different context values (such a Person instance) are turned into sequences \
                      of rows and concatenated into an overall sequence of rows
                      """,
        usageHelpAutoWidth = true
)
public class CSVWriterSinkCommandAdapter implements ResultSinkCommandAdapter {

    public ResultSink createConfigured(final MapReduceEngine engine, final List<String> resultNames) {
        return new CSVWriterSink(engine, resultNames);
    }

}

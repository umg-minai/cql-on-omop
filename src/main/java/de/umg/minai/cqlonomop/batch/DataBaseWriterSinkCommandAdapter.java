package de.umg.minai.cqlonomop.batch;

import de.umg.minai.cqlonomop.commandline.DatabaseOptions;
import de.umg.minai.cqlonomop.database.DatabaseConfiguration;
import de.umg.minai.cqlonomop.engine.MapReduceEngine;
import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(
        name = "dbwrite",
        description = """
                      Write result objects to OMOP database. The result for each expression selected via the \
                      --result-name option(s) is inserted into the OMOP table which corresponds to the type of the \
                      result.
                      Result objects are translated into database rows according to the following rules
                      - The results for different context values (such as Person instances) are inserted independently
                      - The elements of a list are inserted as individual rows
                      - Instances of OMOP datatypes are inserted into the corresponding table
                      - Other datatypes are not supported
                      In other words, each selected definition has to evaluate to an instance of an OMOP datatype \
                      such as Person or to a list of such like List<Person>.
                      """,
        usageHelpAutoWidth = true
)
public class DataBaseWriterSinkCommandAdapter implements ResultSinkCommandAdapter {

    public ResultSink createConfigured(final MapReduceEngine engine, final List<String> resultNames) {
        return new DatabaseWriterSink(engine, resultNames);
    }

}

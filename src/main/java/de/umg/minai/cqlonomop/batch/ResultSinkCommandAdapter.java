package de.umg.minai.cqlonomop.batch;

import de.umg.minai.cqlonomop.engine.MapReduceEngine;

import java.util.List;

public interface ResultSinkCommandAdapter {

    ResultSink createConfigured(MapReduceEngine engine, List<String> resultNames);

}

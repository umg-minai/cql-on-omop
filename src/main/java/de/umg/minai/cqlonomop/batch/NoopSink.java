package de.umg.minai.cqlonomop.batch;

import de.umg.minai.cqlonomop.engine.MapReduceEngine;

import java.util.List;

public class NoopSink extends BasicResultSink {

    public NoopSink(final MapReduceEngine engine, final List<String> resultNames) {
        super(resultNames);
    }

}

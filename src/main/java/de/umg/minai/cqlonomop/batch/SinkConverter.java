package de.umg.minai.cqlonomop.batch;

import picocli.CommandLine;

public class SinkConverter implements CommandLine.ITypeConverter<Class<? extends ResultSink>> {

    @Override
    public Class<? extends ResultSink> convert(final String string) {
        return switch (string) {
            case "none" -> NoopSink.class;
            case "dbwrite" -> DatabaseWriterSink.class;
            case "histogram" -> TemporalHistogram.class;
            default -> throw new RuntimeException(String.format("'%s' is not a valid result sink.", string));
        };
    }

}

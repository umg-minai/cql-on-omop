package de.umg.minai.cqlonomop.engine;

import de.umg.minai.cqlonomop.database.DatabaseConfiguration;

import java.nio.file.Path;
import java.util.List;

public class Configuration extends DatabaseConfiguration {

    public final static String DEFAULT_OMOP_VERSION = "v5.4";

    private Integer threadCount = defaultThreadCount();

    private String omopVersion = DEFAULT_OMOP_VERSION;

    private List<Path> librarySearchPath = List.of();

    public static int defaultThreadCount() {
        return Runtime.getRuntime().availableProcessors();
    }

    public Integer getThreadCount() {
        return this.threadCount;
    }

    public Configuration withThreadCount(Integer threadCount) {
        this.threadCount = threadCount;
        return this;
    }

    public List<Path> getLibrarySearchPath() {
        return this.librarySearchPath;
    }

    public Configuration withLibrarySearchPath(final List<Path> librarySearchPath) {
        this.librarySearchPath = librarySearchPath;
        return this;
    }

    public String getOmopVersion() {
        return this.omopVersion;
    }

    public Configuration withOmopVersion(final String omopVersion) {
        this.omopVersion = omopVersion;
        return this;
    }

}

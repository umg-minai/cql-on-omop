package de.umg.minai.cqlonomop.engine;

import de.umg.minai.cqlonomop.database.DatabaseConfiguration;

import java.nio.file.Path;
import java.util.List;

public class Configuration extends DatabaseConfiguration {

    public final static String DEFAULT_OMOP_VERSION = "v5.4";

    private boolean debug = false;

    private Integer threadCount = defaultThreadCount();

    private String omopVersion = DEFAULT_OMOP_VERSION;

    private List<Path> librarySearchPath = List.of();

    private int retrieveMaxRetryCount = 0;

    private int retrieveRetryDelayMS = 0;

    private int contextObjectMaxRetryCount = 0;

    public boolean getDebug() {
        return this.debug;
    }

    public Configuration withDebug(boolean debug) {
        this.debug= debug;
        return this;
    }

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

    public int getRetrieveMaxRetryCount() {
        return this.retrieveMaxRetryCount;
    }

    public Configuration withRetrieveMaxRetryCount(int retrieveRetryCount) {
        this.retrieveMaxRetryCount = retrieveRetryCount;
        return this;
    }

    public int getRetrieveRetryDelayMS() {
        return this.retrieveRetryDelayMS;
    }

    public Configuration withRetrieveRetryDelayMS(int retrieveRetryDelayMS) {
        this.retrieveRetryDelayMS = retrieveRetryDelayMS;
        return this;
    }

    public int getContextObjectMaxRetryCount() {
        return this.contextObjectMaxRetryCount;
    }

    public Configuration withContextObjectMaxRetryCount(int contextObjectMaxRetryCount) {
        this.contextObjectMaxRetryCount = contextObjectMaxRetryCount;
        return this;
    }

}

package de.umg.minai.cqlonomop.engine;

import java.nio.file.Path;
import java.util.List;

public class Configuration {

    public final static String DEFAULT_DATABASE_DRIVER = "postgresql";

    public final static String DEFAULT_OMOP_VERSION = "v5.4";

    private String databaseDriver = DEFAULT_DATABASE_DRIVER;

    private String databaseHost;

    private int databasePort;

    private String databaseUser;

    private String databasePassword;

    private String databaseName;

    private String databaseSchema;

    private String databaseConnectionString = null;

    private Integer threadCount = defaultThreadCount();

    private Boolean showSQL = false;

    private String omopVersion = DEFAULT_OMOP_VERSION;

    private List<Path> librarySearchPath = List.of();

    public static int defaultThreadCount() {
        return Runtime.getRuntime().availableProcessors();
    }

    public String getDatabaseDriver() {
        return this.databaseDriver;
    }

    public Configuration withDatabaseDriver(final String driver) {
        this.databaseDriver = driver;
        return this;
    }

    public String getDatabaseHost() {
        return this.databaseHost;
    }

    public Configuration withDatabaseHost(final String databaseHost) {
        this.databaseHost = databaseHost;
        return this;
    }

    public int getDatabasePort() {
        return this.databasePort;
    }

    public Configuration withDatabasePort(final int databasePort) {
        this.databasePort = databasePort;
        return this;
    }

    public String getDatabaseUser() {
        return this.databaseUser;
    }

    public Configuration withDatabaseUser(final String databaseUser) {
        this.databaseUser = databaseUser;
        return this;
    }

    public String getDatabasePassword() {
        return this.databasePassword;
    }

    public Configuration withDatabasePassword(final String databasePassword) {
        this.databasePassword = databasePassword;
        return this;
    }

    public String getDatabaseName() {
        return this.databaseName;
    }

    public Configuration withDatabaseName(final String databaseName) {
        this.databaseName = databaseName;
        return this;
    }

    public String getDatabaseSchema() {
        return this.databaseSchema;
    }

    public Configuration withDatabaseSchema(final String databaseSchema) {
        this.databaseSchema = databaseSchema;
        return this;
    }

    public String getDatabaseConnectionString() {
        return this.databaseConnectionString;
    }

    public Configuration withDatabaseConnectionString(final String databaseConnectionString) {
        this.databaseConnectionString = databaseConnectionString;
        return this;
    }

    public Integer getThreadCount() {
        return this.threadCount;
    }

    public Configuration withThreadCount(Integer threadCount) {
        this.threadCount = threadCount;
        return this;
    }

    public boolean getShowSQL() {
        return this.showSQL;
    }

    public Configuration withShowSQL(boolean showSQL) {
        this.showSQL = showSQL;
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

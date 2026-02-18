package de.umg.minai.cqlonomop.database;

import java.nio.file.Path;
import java.util.List;

public class DatabaseConfiguration {

    public final static String DEFAULT_DRIVER = "postgresql";

    public static final String DEFAULT_HOST = "localhost";

    public static final int DEFAULT_PORT = 5432;

    public static final String DEFAULT_PORT_AS_STRING = "5432";

    public final static String DEFAULT_USER = "postgres";

    private String databaseDriver = DEFAULT_DRIVER;

    private String databaseHost = DEFAULT_HOST;

    private int databasePort = DEFAULT_PORT;

    private String databaseUser = DEFAULT_USER;

    private String databasePassword;

    private String databaseName;

    private String databaseSchema;

    private String databaseConnectionString = null;

    private List<Path> loadJDBCDrivers = List.of();

    private Boolean showSQL = false;

    public String getDatabaseDriver() {
        return this.databaseDriver;
    }

    public DatabaseConfiguration withDatabaseDriver(final String driver) {
        this.databaseDriver = driver;
        return this;
    }

    public String getDatabaseHost() {
        return this.databaseHost;
    }

    public DatabaseConfiguration withDatabaseHost(final String databaseHost) {
        this.databaseHost = databaseHost;
        return this;
    }

    public int getDatabasePort() {
        return this.databasePort;
    }

    public DatabaseConfiguration withDatabasePort(final int databasePort) {
        this.databasePort = databasePort;
        return this;
    }

    public String getDatabaseUser() {
        return this.databaseUser;
    }

    public DatabaseConfiguration withDatabaseUser(final String databaseUser) {
        this.databaseUser = databaseUser;
        return this;
    }

    public String getDatabasePassword() {
        return this.databasePassword;
    }

    public DatabaseConfiguration withDatabasePassword(final String databasePassword) {
        this.databasePassword = databasePassword;
        return this;
    }

    public String getDatabaseName() {
        return this.databaseName;
    }

    public DatabaseConfiguration withDatabaseName(final String databaseName) {
        this.databaseName = databaseName;
        return this;
    }

    public String getDatabaseSchema() {
        return this.databaseSchema;
    }

    public DatabaseConfiguration withDatabaseSchema(final String databaseSchema) {
        this.databaseSchema = databaseSchema;
        return this;
    }

    public String getDatabaseConnectionString() {
        return this.databaseConnectionString;
    }

    public DatabaseConfiguration withDatabaseConnectionString(final String databaseConnectionString) {
        this.databaseConnectionString = databaseConnectionString;
        return this;
    }

    public List<Path> getLoadJDBCDrivers() {
        return this.loadJDBCDrivers;
    }

    public DatabaseConfiguration withLoadJDBCDrivers(final List<Path> loadJDBCDrivers) {
        this.loadJDBCDrivers = loadJDBCDrivers;
        return this;
    }

    public boolean getShowSQL() {
        return this.showSQL;
    }

    public DatabaseConfiguration withShowSQL(boolean showSQL) {
        this.showSQL = showSQL;
        return this;
    }

}

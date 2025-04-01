package org.example.engine;

public class Configuration {

    private String databaseHost;

    private int databasePort;

    private String databaseUser;

    private String databasePassword;

    private String databaseName;

    private Integer threadCount;

    private Boolean showSQL = false;

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


}

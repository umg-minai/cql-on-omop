package de.umg.minai.cqlonomop.commandline;

import de.umg.minai.cqlonomop.engine.Configuration;
import picocli.CommandLine.Option;

public class DatabaseOptions {

    @Option(
            names = {"--driver"},
            defaultValue = Configuration.DEFAULT_DATABASE_DRIVER,
            description = "The driver that should be used to connect to the OMOP database server."
    )
    public String driver = Configuration.DEFAULT_DATABASE_DRIVER;

    @Option(
            names = {"-h", "--host"},
            defaultValue = "localhost",
            description = "The hostname of the database server from which to retrieve the OMOP data."
    )
    public String host = "localhost";

    @Option(
            names = {"-p", "--port"},
            defaultValue = "5434",
            description = "The post on which the database server from which to retrieve the OMOP data listens."
    )
    public int port = 5434;

    @Option(
            names = {"-u", "--user"},
            description = "The username of the database server account that should be used to retrieve the OMOP data."
    )
    public String user = "postgres";

    @Option(
            names = {"--password"},
            defaultValue = "${env:CQL_ON_OMOP_DATABASE_PASSWORD}",
            description = "The password for the database server account."
    )
    public String password;

    @Option(
            names = {"-d", "--database"},
            required = true,
            description = "The name of the database from which OMOP data should be retrieved."
    )
    public String database;

    @Option(
            names = {"-s", "--schema"},
            defaultValue = "cds_cdm",
            description = "The schema in which the OMOP tables reside within the specified database."
    )
    public String schema;

    @Option(
            names = "--connection-string",
            description = "JDBC database connection string. Has priority over and replaces --driver, --host, --port and --database options."
    )
    public String connectionString;

    @Option(
            names = {"--show-sql"},
            defaultValue = "false",
            description = "Show the generated SQL statements as they are sent to the database server."
    )
    public boolean showSQL = false;

    public Configuration applyToConfiguration(final Configuration configuration) {
        return configuration
                .withDatabaseDriver(this.driver)
                .withDatabaseHost(this.host)
                .withDatabasePort(this.port)
                .withDatabaseUser(this.user)
                .withDatabasePassword(this.password)
                .withDatabaseName(this.database)
                .withDatabaseSchema(this.schema)
                .withDatabaseConnectionString(this.connectionString)
                .withShowSQL(this.showSQL);
    }

}

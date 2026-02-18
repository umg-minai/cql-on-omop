package de.umg.minai.cqlonomop.commandline;

import de.umg.minai.cqlonomop.database.DatabaseConfiguration;
import de.umg.minai.cqlonomop.engine.Configuration;
import picocli.CommandLine.Option;

import java.nio.file.Path;
import java.util.List;

public class DatabaseOptions {

    @Option(
            names = {"--driver"},
            defaultValue = DatabaseConfiguration.DEFAULT_DRIVER,
            description = "The driver that should be used to connect to the OMOP database server."
    )
    public String driver = DatabaseConfiguration.DEFAULT_DRIVER;

    @Option(
            names = {"-h", "--host"},
            defaultValue = DatabaseConfiguration.DEFAULT_HOST,
            description = "The hostname of the database server from which to retrieve the OMOP data."
    )
    public String host = DatabaseConfiguration.DEFAULT_HOST;

    @Option(
            names = {"-p", "--port"},
            defaultValue = DatabaseConfiguration.DEFAULT_PORT_AS_STRING,
            description = "The post on which the database server from which to retrieve the OMOP data listens."
    )
    public int port = DatabaseConfiguration.DEFAULT_PORT;

    @Option(
            names = {"-u", "--user"},
            defaultValue = DatabaseConfiguration.DEFAULT_USER,
            description = "The username of the database server account that should be used to retrieve the OMOP data."
    )
    public String user = DatabaseConfiguration.DEFAULT_USER;

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
            description = """
                          JDBC database connection string. Has priority over and replaces --driver, --host, --port \
                          and --database options.
                          """
    )
    public String connectionString;

    @Option(
            names = "--load-jdbc-driver",
            paramLabel = "<jar-filename>",
            description = """
                          Filename of a JAR file that should be loaded and searched for additional JDBC drivers. \
                          This option can be supplied multiple times.
                          """
    )
    public List<Path> loadJDBCDrivers;

    @Option(
            names = {"--show-sql"},
            defaultValue = "false",
            description = "Show the generated SQL statements as they are sent to the database server."
    )
    public boolean showSQL = false;

    public <T extends DatabaseConfiguration> T applyToConfiguration(final T configuration) {
        //noinspection unchecked
        return (T) configuration
                .withDatabaseDriver(this.driver)
                .withDatabaseHost(this.host)
                .withDatabasePort(this.port)
                .withDatabaseUser(this.user)
                .withDatabasePassword(this.password)
                .withDatabaseName(this.database)
                .withDatabaseSchema(this.schema)
                .withDatabaseConnectionString(this.connectionString)
                .withLoadJDBCDrivers(this.loadJDBCDrivers)
                .withShowSQL(this.showSQL);
    }

}

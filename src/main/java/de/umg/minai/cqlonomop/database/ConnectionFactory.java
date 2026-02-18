package de.umg.minai.cqlonomop.database;

import OMOP.MappingInfo;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.ServiceLoader;

import static org.hibernate.cfg.JdbcSettings.*;
import static org.hibernate.cfg.MappingSettings.DEFAULT_SCHEMA;

public class ConnectionFactory {

    private final static Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);

    private static ClassLoader classloader = null;

    private static ClassLoader ensureClassLoader(final List<Path> driverLibraries) throws MalformedURLException {
        if (classloader == null) {
            final java.net.URL[] urls = new java.net.URL[driverLibraries.size()];
            for (var i = 0; i < driverLibraries.size(); ++i) {
                urls[i] = driverLibraries.get(i).toUri().toURL();
            }
            classloader = new URLClassLoader(urls);
        }
        return classloader;
    }

    public static void loadJDBCDrivers(final List<Path> driverLibraries) {
        if (!driverLibraries.isEmpty()) {
            try {
                final var classLoader = ensureClassLoader(driverLibraries);
                final var serviceLoader = ServiceLoader.load(Driver.class, classLoader);
                for (var driver : serviceLoader) {
                    if (DriverManager.drivers()
                            .anyMatch(existingDriver -> existingDriver.getClass() == driver.getClass())) {
                        logger.info("Already registered {}", driver);
                    } else {
                        logger.info("Registering driver {} (with wrapper)", driver);
                        DriverManager.registerDriver(new JDBCDriverWrapper(driver));
                    }
                }
            } catch (IOException | SQLException e) {
                throw new RuntimeException(String.format("Failed to load and register JDBC driver library %s", "library"),
                        e);
            }
        }
    }

    public static SessionFactory createSessionFactory(final DatabaseConfiguration configuration,
                                                      final MappingInfo mappingInfo) {
        // Configure Hibernate
        final var config = new org.hibernate.cfg.Configuration();
        config.configure("de/umg/minai/cqlonomop/hibernate.cfg.xml");
        final var connectionURL = configuration.getDatabaseConnectionString() != null
                ? configuration.getDatabaseConnectionString()
                : String.format("jdbc:%s://%s:%d/%s",
                configuration.getDatabaseDriver(),
                configuration.getDatabaseHost(),
                configuration.getDatabasePort(),
                configuration.getDatabaseName());

        config.setProperty(JAKARTA_JDBC_URL, connectionURL);
        config.setProperty(JAKARTA_JDBC_USER, configuration.getDatabaseUser());
        final var password = configuration.getDatabasePassword();
        if (password != null) {
            config.setProperty(JAKARTA_JDBC_PASSWORD, password);
        }
        config.setProperty(DEFAULT_SCHEMA, configuration.getDatabaseSchema());
        config.setProperty(SHOW_SQL, String.valueOf(configuration.getShowSQL()));
        // Register classes of the data model.
        mappingInfo.getDataTypeInfos().forEach(
                (name, info) -> config.addAnnotatedClass(info.getClazz()));
        return config.buildSessionFactory();
    }

}

package de.umg.minai.cqlonomop.engine;

import OMOP.MappingInfo;
import org.hibernate.SessionFactory;

import static org.hibernate.cfg.JdbcSettings.*;
import static org.hibernate.cfg.MappingSettings.DEFAULT_SCHEMA;

public class ConnectionFactory {

    public static SessionFactory createSessionFactory(final Configuration configuration,
                                                      final MappingInfo mappingInfo) {
        // Configure Hibernate
        final var config = new org.hibernate.cfg.Configuration();
        config.configure("de/umg/minai/cqlonomop/hibernate.cfg.xml");
        final var connectionURL = String.format("jdbc:%s://%s:%d/%s",
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
        // Register class of the data model.
        mappingInfo.getDataTypeInfos().forEach(
                (name, info) -> config.addAnnotatedClass(info.getClazz()));
        return config.buildSessionFactory();
    }

}

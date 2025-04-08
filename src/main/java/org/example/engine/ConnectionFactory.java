package org.example.engine;

import OMOP.MappingInfo;
import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;

public class ConnectionFactory {

    public static SessionFactory createSessionFactory(final Configuration configuration,
                                                      final MappingInfo mappingInfo) {
        // Configure Hibernate
        final var config = new org.hibernate.cfg.Configuration();
        config.configure("org/example/hibernate.cfg.xml");
        final var connectionURL = String.format("jdbc:postgresql://%s:%d/%s",
                configuration.getDatabaseHost(),
                configuration.getDatabasePort(),
                configuration.getDatabaseName());
        config.setProperty("hibernate.connection.url", connectionURL);
        config.setProperty("hibernate.connection.username", configuration.getDatabaseUser());
        final var password = configuration.getDatabasePassword();
        if (password != null) {
            config.setProperty("hibernate.connection.password", password);
        }
        config.setProperty("hibernate.show_sql", String.valueOf(configuration.getShowSQL()));
        // Register class of the data model.
        mappingInfo.getDataTypeInfos().forEach(
                (name, info) -> config.addAnnotatedClass(info.getClazz()));
        return config.buildSessionFactory();
    }

    public static EntityManager createEntityManager(final Configuration configuration,
                                                    final MappingInfo mappingInfo) {
        final var sessionFactory = createSessionFactory(configuration, mappingInfo);
        final var session = sessionFactory.openSession();
        return session.getEntityManagerFactory().createEntityManager();
    }

}

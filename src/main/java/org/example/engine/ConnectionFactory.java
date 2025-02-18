package org.example.engine;

import OMOP.MappingInfo;
import jakarta.persistence.EntityManager;
import org.hibernate.cfg.Configuration;

public class ConnectionFactory {

    public static EntityManager createEntityManager(final MappingInfo mappingInfo) {
        // Configure Hibernate
        final var config = new Configuration();
        config.configure("org/example/hibernate.cfg.xml");
        // TODO: config.setProperty("hibernate.connection.url", connectionUrl)
        mappingInfo.getDataTypeInfos().forEach((name, info) -> {
            System.out.printf("Registering class %s%n", info.getClazz().getName());
            config.addAnnotatedClass(info.getClazz());
        });
        final var sessionFactory = config.buildSessionFactory();
        final var session = sessionFactory.openSession();
        return session.getEntityManagerFactory().createEntityManager();
    }

}

package org.example.engine;

import OMOP.ModelInfo;
import jakarta.persistence.EntityManager;
import org.hibernate.cfg.Configuration;

public class ConnectionFactory {

    public static EntityManager createEntityManager() {
        // Configure Hibernate
        final var config = new Configuration();
        config.configure("org/example/hibernate.cfg.xml");
        // TODO: config.setProperty("hibernate.connection.url", connectionUrl)
        ModelInfo.allClasses().forEach(clazz -> {
            if (!clazz.getName().equals("OMOP.FactRelationship")
                    && !clazz.getName().equals("OMOP.ConceptRelationship")
                    && !clazz.getName().equals("OMOP.ConceptSynonym")
                    && !clazz.getName().equals("OMOP.CdmSource")
                    && !clazz.getName().equals("OMOP.CohortDefinition")
                    && !clazz.getName().equals("OMOP.Death")
                    && !clazz.getName().equals("OMOP.ConceptAncestor")
                    && !clazz.getName().equals("OMOP.EpisodeEvent")
                    && !clazz.getName().equals("OMOP.SourceToConceptMap")
                    && !clazz.getName().equals("OMOP.DrugStrength")
                    && !clazz.getName().equals("OMOP.Cohort")
            ) {
                System.out.printf("Registering class %s%n", clazz.getName());
                config.addAnnotatedClass(clazz);
            } else {
                System.out.printf("Not registering class %s%n", clazz.getName());
            }
        });
        final var sessionFactory = config.buildSessionFactory();
        final var session = sessionFactory.openSession();
        return session.getEntityManagerFactory().createEntityManager();
    }

}

package org.example.engine;

import jakarta.persistence.EntityManager;
import org.opencds.cqf.cql.engine.data.CompositeDataProvider;

public class OMOPDataProvider extends CompositeDataProvider {

    public OMOPDataProvider(final OMOPModelResolver modelResolver, OMOPRetrieveProvider retrieveProvider) {
        super(modelResolver, retrieveProvider);
    }

    public OMOPDataProvider(final OMOPModelResolver modelResolver) {
        this(modelResolver, new OMOPRetrieveProvider(modelResolver));
    }

    public OMOPDataProvider() {
        this(new OMOPModelResolver());
    }

    public static OMOPDataProvider fromEntityManager(final EntityManager entityManager) {
        final var modelResolver = new OMOPModelResolver();
        final var retrieveProvider = new OMOPRetrieveProvider(modelResolver, entityManager);
        return new OMOPDataProvider(modelResolver, retrieveProvider);
    }

}
package org.example.engine;

import OMOP.MappingInfo;
import jakarta.persistence.EntityManager;
import org.opencds.cqf.cql.engine.data.CompositeDataProvider;

public class OMOPDataProvider extends CompositeDataProvider {

    private final MappingInfo mappingInfo;

    public OMOPDataProvider(final OMOPModelResolver modelResolver,
                            final OMOPRetrieveProvider retrieveProvider) {
        super(modelResolver, retrieveProvider);
        this.mappingInfo = modelResolver.mappingInfo;
    }

    public OMOPDataProvider(final OMOPModelResolver modelResolver) {
        this(modelResolver, new OMOPRetrieveProvider(modelResolver));
    }

    public OMOPDataProvider(final MappingInfo mappingInfo) {
        this(new OMOPModelResolver(mappingInfo));
    }

    public static OMOPDataProvider fromEntityManager(final EntityManager entityManager,
                                                     final MappingInfo mappingInfo) {
        final var modelResolver = new OMOPModelResolver(mappingInfo);
        final var retrieveProvider = new OMOPRetrieveProvider(modelResolver, entityManager);
        return new OMOPDataProvider(modelResolver, retrieveProvider);
    }

    public MappingInfo getModelInfo() {
        return this.mappingInfo;
    }

}
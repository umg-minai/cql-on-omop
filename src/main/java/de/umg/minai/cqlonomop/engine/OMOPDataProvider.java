package de.umg.minai.cqlonomop.engine;

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

    public static OMOPDataProvider fromEntityManager(final EntityManager entityManager,
                                                     final MappingInfo mappingInfo) {
        final var modelResolver = new OMOPModelResolver(mappingInfo);
        final var retrieveProvider = new OMOPRetrieveProvider(modelResolver, entityManager, mappingInfo);
        return new OMOPDataProvider(modelResolver, retrieveProvider);
    }

    public MappingInfo getModelInfo() {
        return this.mappingInfo;
    }

}

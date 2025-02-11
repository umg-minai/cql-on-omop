package org.example;

import org.opencds.cqf.cql.engine.data.CompositeDataProvider;
import org.opencds.cqf.cql.engine.model.ModelResolver;

public class OMOPDataProvider extends CompositeDataProvider {

    public OMOPDataProvider(ModelResolver modelResolver) {
        super(modelResolver, new OMOPRetrieveProvider(modelResolver));
    }

    public OMOPDataProvider() {
        this(new OMOPModelResolver());
    }

}
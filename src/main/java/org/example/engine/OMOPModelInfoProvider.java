package org.example.engine;

import org.hl7.cql.model.ModelIdentifier;
import org.hl7.cql.model.ModelInfoProvider;
import org.hl7.cql.model.NamespaceManager;
import org.hl7.elm_modelinfo.r1.ModelInfo;
import org.hl7.elm_modelinfo.r1.serializing.ModelInfoReaderFactory;

import java.io.IOException;

public class OMOPModelInfoProvider implements ModelInfoProvider {

    private NamespaceManager namespaceManager;

    public void setNamespaceManager(final NamespaceManager namespaceManager) {
        this.namespaceManager = namespaceManager;
    }

    private boolean isOMOPModelIdentifier(final ModelIdentifier modelIdentifier) {
        final var idMatches = modelIdentifier.getId().equals("OMOP");
        if (namespaceManager != null && namespaceManager.hasNamespaces()) {
            return idMatches
                    && (modelIdentifier.getSystem() == null
                    || modelIdentifier.getSystem().equals("urn:hl7-org:elm-types:r1"));
        } else {
            return idMatches;
        }
    }

    @Override
    public ModelInfo load(final ModelIdentifier modelIdentifier) {
        if (isOMOPModelIdentifier(modelIdentifier)) {
            return loadModelInfo(modelIdentifier.getVersion());
        } else {
            return null;
        }
    }

    private ModelInfo loadModelInfo(final String version) {
        try {
            final var resourceName = String.format("/org/example/OMOP%s.xml", version);
            return ModelInfoReaderFactory.getReader("application/xml")
                    .read(OMOPModelInfoProvider.class.getResourceAsStream(resourceName));
        } catch (IOException e) {
            return null;
        }
    }

}

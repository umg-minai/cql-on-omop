// This file has been generated from a description of the OMOP CDM v5.3 - do
// not edit
package OMOP.v53;

import OMOP.DataTypeInfo;

public class FactRelationshipInfo implements DataTypeInfo {

    public Class<?> getClazz() {
        return FactRelationship.class;
    }

    public String contextPath(final String contextName) {
        return null;
    }

    public ContextInfo infoForContext(final String contextPath, final Object contextValue) {
        return null;
    }

    public boolean isJoinableCodePath(final String codePath) {
        return codePath.equals("relationshipConcept");
    }


}

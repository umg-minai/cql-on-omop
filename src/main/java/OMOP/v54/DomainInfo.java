// This file has been generated from a description of the OMOP CDM v5.4 - do
// not edit
package OMOP.v54;

import OMOP.DataTypeInfo;

public class DomainInfo implements DataTypeInfo {

    public Class<?> getClazz() {
        return Domain.class;
    }

    public String contextPath(final String contextName) {
        return null;
    }

    public ContextInfo infoForContext(final String contextPath, final Object contextValue) {
        return null;
    }

    public boolean isJoinableCodePath(final String codePath) {
        return codePath.equals("domainConcept");
    }


}

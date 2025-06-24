package OMOP.v53;

import OMOP.DataTypeInfo;

public class ConceptAncestorInfo implements DataTypeInfo {

    public Class<?> getClazz() {
        return ConceptAncestor.class;
    }

    public String contextPath(final String contextName) {
        return null;
    }

    public ContextInfo infoForContext(final String contextPath, final Object contextValue) {
        return null;
    }

    public boolean isJoinableCodePath(final String codePath) {
        return codePath.equals("descendantConcept")
               || codePath.equals("ancestorConcept");
    }


}

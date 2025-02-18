package OMOP.v54;

import OMOP.DataTypeInfo;

public class ConceptAncestorInfo implements DataTypeInfo {
    public Class<?> getClazz() {
        return ConceptAncestor.class;
    }
    public String contextPath(final String contextName) {
        return null;
    }
    public String columnForContext(final String contextPath, final Object contextValue) {
        return null;
    }
    public boolean isJoinableCodePath(final String codePath) {
        return codePath.equals("descendantConcept")
               || codePath.equals("ancestorConcept");
    }
    
}

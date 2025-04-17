package OMOP.v54;

import OMOP.DataTypeInfo;

public class ConceptRelationshipInfo implements DataTypeInfo {
    @Override
    public Class<?> getClazz() {
        return ConceptRelationship.class;
    }

    @Override
    public String contextPath(String contextName) {
        return null;
    }

    @Override
    public String columnForContext(String contextPath, Object contextValue) {
        return null;
    }

    @Override
    public boolean isJoinableCodePath(String codePath) {
        return false;
    }
}

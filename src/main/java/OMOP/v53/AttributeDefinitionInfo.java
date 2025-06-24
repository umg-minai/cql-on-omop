package OMOP.v53;

import OMOP.DataTypeInfo;

public class AttributeDefinitionInfo implements DataTypeInfo {

    public Class<?> getClazz() {
        return AttributeDefinition.class;
    }

    public String contextPath(final String contextName) {
        return null;
    }

    public ContextInfo infoForContext(final String contextPath, final Object contextValue) {
        return null;
    }

    public boolean isJoinableCodePath(final String codePath) {
        return codePath.equals("attributeTypeConcept");
    }


}

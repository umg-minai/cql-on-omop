package OMOP.v54MIMIC;

import OMOP.DataTypeInfo;

public class SourceToConceptMapInfo implements DataTypeInfo {

    public Class<?> getClazz() {
        return SourceToConceptMap.class;
    }

    public String contextPath(final String contextName) {
        return null;
    }

    public ContextInfo infoForContext(final String contextPath, final Object contextValue) {
        return null;
    }

    public boolean isJoinableCodePath(final String codePath) {
        return ((codePath.equals("targetConcept"))
                || (codePath.equals("sourceConcept")));
    }


}

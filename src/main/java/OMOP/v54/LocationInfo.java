package OMOP.v54;

import OMOP.DataTypeInfo;

public class LocationInfo implements DataTypeInfo {

    public Class<?> getClazz() {
        return Location.class;
    }

    public String contextPath(final String contextName) {
        return null;
    }

    public String columnForContext(final String contextPath, final Object contextValue) {
        return null;
    }

    public ContextInfo infoForContext(final String contextPath, final Object contextValue) {
        return null;
    }

    public boolean isJoinableCodePath(final String codePath) {
        return codePath.equals("countryConcept");
    }


}

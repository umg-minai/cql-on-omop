package OMOP.v54MIMIC;

import OMOP.DataTypeInfo;

public class ProviderInfo implements DataTypeInfo {

    public Class<?> getClazz() {
        return Provider.class;
    }

    public String contextPath(final String contextName) {
        return null;
    }

    public ContextInfo infoForContext(final String contextPath, final Object contextValue) {
        return null;
    }

    public boolean isJoinableCodePath(final String codePath) {
        return codePath.equals("genderSourceConcept")
               || codePath.equals("specialtySourceConcept")
               || codePath.equals("genderConcept")
               || codePath.equals("specialtyConcept");
    }


}

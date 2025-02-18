package OMOP.v54;

import OMOP.DataTypeInfo;

public class DeathInfo implements DataTypeInfo {
    public Class<?> getClazz() {
        return Death.class;
    }
    public String contextPath(final String contextName) {
        if (contextName.equals("Patient")) {
            return "person";
        } else {
            return null;
        }
        
    }
    public String columnForContext(final String contextPath, final Object contextValue) {
        if (contextPath.equals("person") && (contextValue instanceof Person)) {
            return "personId";
        } else {
            return null;
        }
        
    }
    public boolean isJoinableCodePath(final String codePath) {
        return codePath.equals("causeSourceConcept")
               || codePath.equals("causeConcept")
               || codePath.equals("deathTypeConcept");
    }
    
}

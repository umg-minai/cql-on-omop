package OMOP.v54;

import OMOP.DataTypeInfo;

public class SpecimenInfo implements DataTypeInfo {
    public Class<?> getClazz() {
        return Specimen.class;
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
        return codePath.equals("diseaseStatusConcept")
               || codePath.equals("anatomicSiteConcept")
               || codePath.equals("unitConcept")
               || codePath.equals("specimenTypeConcept")
               || codePath.equals("specimenConcept");
    }
    
}

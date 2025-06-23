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

    public ContextInfo infoForContext(final String contextPath, final Object contextValue) {
        if (contextPath.equals("person") && (contextValue instanceof Person person)) {
            return new ContextInfo("personId", person.getPersonId());
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

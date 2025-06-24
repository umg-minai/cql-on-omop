package OMOP.v53;

import OMOP.DataTypeInfo;

public class ObservationInfo implements DataTypeInfo {

    public Class<?> getClazz() {
        return Observation.class;
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
        return codePath.equals("observationSourceConcept")
               || codePath.equals("unitConcept")
               || codePath.equals("qualifierConcept")
               || codePath.equals("valueAsConcept")
               || codePath.equals("observationTypeConcept")
               || codePath.equals("observationConcept");
    }


}

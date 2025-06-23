package OMOP.v54;

import OMOP.DataTypeInfo;

public class ProcedureOccurrenceInfo implements DataTypeInfo {

    public Class<?> getClazz() {
        return ProcedureOccurrence.class;
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
        return codePath.equals("procedureSourceConcept")
               || codePath.equals("modifierConcept")
               || codePath.equals("procedureTypeConcept")
               || codePath.equals("procedureConcept");
    }


}

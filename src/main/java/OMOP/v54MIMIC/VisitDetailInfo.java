package OMOP.v54MIMIC;

import OMOP.DataTypeInfo;

public class VisitDetailInfo implements DataTypeInfo {

    public Class<?> getClazz() {
        return VisitDetail.class;
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
        return codePath.equals("dischargedToConcept")
               || codePath.equals("admittedFromConcept")
               || codePath.equals("visitDetailSourceConcept")
               || codePath.equals("visitDetailTypeConcept")
               || codePath.equals("visitDetailConcept");
    }


}

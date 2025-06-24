package OMOP.v54MIMIC;

import OMOP.DataTypeInfo;

public class PayerPlanPeriodInfo implements DataTypeInfo {

    public Class<?> getClazz() {
        return PayerPlanPeriod.class;
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
        return codePath.equals("stopReasonSourceConcept")
               || codePath.equals("stopReasonConcept")
               || codePath.equals("sponsorSourceConcept")
               || codePath.equals("sponsorConcept")
               || codePath.equals("planSourceConcept")
               || codePath.equals("planConcept")
               || codePath.equals("payerSourceConcept")
               || codePath.equals("payerConcept");
    }


}

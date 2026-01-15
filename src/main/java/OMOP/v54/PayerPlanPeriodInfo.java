// This file has been generated from a description of the OMOP CDM v5.4 - do
// not edit
package OMOP.v54;

import OMOP.DataTypeInfo;

public class PayerPlanPeriodInfo implements DataTypeInfo {

    public Class<?> getClazz() {
        return PayerPlanPeriod.class;
    }

    public String contextPath(final String contextName) {
        // contextName can be "Person" when a "related context "retrieve" is
        // used as in
        //   define p: First([Person])
        //   define v: First([p -> PayerPlanPeriod])
        // .
        if (((contextName.equals("Patient"))
             || (contextName.equals("Person")))) {
            return "person";
        } else {
            return null;
        }
    }

    public ContextInfo infoForContext(final String contextPath, final Object contextValue) {
        if (!contextPath.equals("person")) {
            return null;
        } else {
            if (contextValue instanceof Person person) {
                return new ContextInfo("personId", person.getPersonId());
            } else {
                if (contextValue instanceof String string) {
                    // contextValue can be a string when "related context
                    // retrieves" are used as in
                    //   define p: First([Person])
                    //   define v: First([p -> PayerPlanPeriod])
                    // .
                    return new ContextInfo("personId", string);
                } else {
                    return null;
                }
            }
        }
    }

    public boolean isJoinableCodePath(final String codePath) {
        return ((codePath.equals("stopReasonSourceConcept"))
                || (codePath.equals("stopReasonConcept"))
                || (codePath.equals("sponsorSourceConcept"))
                || (codePath.equals("sponsorConcept"))
                || (codePath.equals("planSourceConcept"))
                || (codePath.equals("planConcept"))
                || (codePath.equals("payerSourceConcept"))
                || (codePath.equals("payerConcept")));
    }


}

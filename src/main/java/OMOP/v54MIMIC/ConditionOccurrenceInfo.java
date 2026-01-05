package OMOP.v54MIMIC;

import OMOP.DataTypeInfo;

public class ConditionOccurrenceInfo implements DataTypeInfo {

    public Class<?> getClazz() {
        return ConditionOccurrence.class;
    }

    public String contextPath(final String contextName) {
        // contextName can be "Person" when a "related context "retrieve" is
        // used as in
        //   define p: First([Person])
        //   define v: First([p -> ConditionOccurrence])
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
                    //   define v: First([p -> ConditionOccurrence])
                    // .
                    return new ContextInfo("personId", string);
                } else {
                    return null;
                }
            }
        }
    }

    public boolean isJoinableCodePath(final String codePath) {
        return ((codePath.equals("conditionSourceConcept"))
                || (codePath.equals("conditionStatusConcept"))
                || (codePath.equals("conditionTypeConcept"))
                || (codePath.equals("conditionConcept")));
    }


}

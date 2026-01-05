package OMOP.v54;

import OMOP.DataTypeInfo;

public class MeasurementInfo implements DataTypeInfo {

    public Class<?> getClazz() {
        return Measurement.class;
    }

    public String contextPath(final String contextName) {
        // contextName can be "Person" when a "related context "retrieve" is
        // used as in
        //   define p: First([Person])
        //   define v: First([p -> Measurement])
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
                    //   define v: First([p -> Measurement])
                    // .
                    return new ContextInfo("personId", string);
                } else {
                    return null;
                }
            }
        }
    }

    public boolean isJoinableCodePath(final String codePath) {
        return ((codePath.equals("measEventFieldConcept"))
                || (codePath.equals("unitSourceConcept"))
                || (codePath.equals("measurementSourceConcept"))
                || (codePath.equals("unitConcept"))
                || (codePath.equals("valueAsConcept"))
                || (codePath.equals("operatorConcept"))
                || (codePath.equals("measurementTypeConcept"))
                || (codePath.equals("measurementConcept")));
    }


}

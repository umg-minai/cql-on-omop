// This file has been generated from a description of the OMOP CDM v5.3 - do
// not edit
package OMOP.v53;

import OMOP.DataTypeInfo;

public class SpecimenInfo implements DataTypeInfo {

    public Class<?> getClazz() {
        return Specimen.class;
    }

    public String contextPath(final String contextName) {
        // contextName can be "Person" when a "related context "retrieve" is
        // used as in
        //   define p: First([Person])
        //   define v: First([p -> Specimen])
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
                    //   define v: First([p -> Specimen])
                    // .
                    return new ContextInfo("personId", string);
                } else {
                    return null;
                }
            }
        }
    }

    public boolean isJoinableCodePath(final String codePath) {
        return ((codePath.equals("diseaseStatusConcept"))
                || (codePath.equals("anatomicSiteConcept"))
                || (codePath.equals("unitConcept"))
                || (codePath.equals("specimenTypeConcept"))
                || (codePath.equals("specimenConcept")));
    }


}

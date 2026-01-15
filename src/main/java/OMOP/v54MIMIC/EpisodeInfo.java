// This file has been generated from a description of the OMOP CDM v5.4.MIMIC -
// do not edit
package OMOP.v54MIMIC;

import OMOP.DataTypeInfo;

public class EpisodeInfo implements DataTypeInfo {

    public Class<?> getClazz() {
        return Episode.class;
    }

    public String contextPath(final String contextName) {
        // contextName can be "Person" when a "related context "retrieve" is
        // used as in
        //   define p: First([Person])
        //   define v: First([p -> Episode])
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
                    //   define v: First([p -> Episode])
                    // .
                    return new ContextInfo("personId", string);
                } else {
                    return null;
                }
            }
        }
    }

    public boolean isJoinableCodePath(final String codePath) {
        return ((codePath.equals("episodeSourceConcept"))
                || (codePath.equals("episodeTypeConcept"))
                || (codePath.equals("episodeObjectConcept"))
                || (codePath.equals("episodeConcept")));
    }


}

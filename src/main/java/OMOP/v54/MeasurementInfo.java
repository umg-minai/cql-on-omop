package OMOP.v54;

import OMOP.DataTypeInfo;

public class MeasurementInfo implements DataTypeInfo {

    public Class<?> getClazz() {
        return Measurement.class;
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
        return codePath.equals("measEventFieldConcept")
               || codePath.equals("unitSourceConcept")
               || codePath.equals("measurementSourceConcept")
               || codePath.equals("unitConcept")
               || codePath.equals("valueAsConcept")
               || codePath.equals("operatorConcept")
               || codePath.equals("measurementTypeConcept")
               || codePath.equals("measurementConcept");
    }


}

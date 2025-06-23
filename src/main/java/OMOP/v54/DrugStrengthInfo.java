package OMOP.v54;

import OMOP.DataTypeInfo;

public class DrugStrengthInfo implements DataTypeInfo {

    public Class<?> getClazz() {
        return DrugStrength.class;
    }

    public String contextPath(final String contextName) {
        return null;
    }

    public ContextInfo infoForContext(final String contextPath, final Object contextValue) {
        return null;
    }

    public boolean isJoinableCodePath(final String codePath) {
        return codePath.equals("denominatorUnitConcept")
               || codePath.equals("numeratorUnitConcept")
               || codePath.equals("amountUnitConcept")
               || codePath.equals("ingredientConcept")
               || codePath.equals("drugConcept");
    }


}

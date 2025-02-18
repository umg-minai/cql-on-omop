package OMOP.v54;

import OMOP.DataTypeInfo;

public class CostInfo implements DataTypeInfo {
    public Class<?> getClazz() {
        return Cost.class;
    }
    public String contextPath(final String contextName) {
        return null;
    }
    public String columnForContext(final String contextPath, final Object contextValue) {
        return null;
    }
    public boolean isJoinableCodePath(final String codePath) {
        return codePath.equals("drgConcept")
               || codePath.equals("revenueCodeConcept")
               || codePath.equals("currencyConcept")
               || codePath.equals("costTypeConcept");
    }
    
}

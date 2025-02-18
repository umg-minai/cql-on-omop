package OMOP;

public interface DataTypeInfo {

    Class<?> getClazz();

    String contextPath(String contextName);

    String columnForContext(String contextPath, Object contextValue);

    boolean isJoinableCodePath(String codePath);

}

package OMOP;

public interface DataTypeInfo {

    /**
     * Return the Java class which implements the CQL/OMOP Type.
     *
     * @return The Java class.
     */
    Class<?> getClazz();

    /**
     * Return the path (which roughly corresponds to an OMOP column) within an instance of this data type that should
     * be compared to the context value for a context of kind contextName.
     *
     * @param contextName The name of the context for which the path should be returned.
     * @return The path (which roughly corresponds to an OMOP column) as a String.
     */
    String contextPath(String contextName);

    /**
     * Return the OMOP column within the OMOP table represented by this data type that should be compared to
     * contextValue for contextPath.
     *
     * @param contextPath The CQL path within an instance of the data type.
     * @param contextValue The value of the CQL context that should be compared to the value of the OMOP column.
     * @return The name of an OMOP column.
     */
    String columnForContext(String contextPath, Object contextValue);

    /**
     * Indicate whether a CQL query condition with the given codePath can be translated into an SQL join of OMOP tables.
     *
     * @param codePath The CQL path of the property within an instance of this data type that should be compared to
     *                 CQL code values.
     * @return True if CQL query condition can be represented as an SQL join of OMOP tables, false otherwise.
     */
    boolean isJoinableCodePath(String codePath);

}

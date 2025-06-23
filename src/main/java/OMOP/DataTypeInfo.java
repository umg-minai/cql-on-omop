package OMOP;

public interface DataTypeInfo {

    record ContextInfo(String columnName, Object value) {};

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
     * Return an OMOP column name and a value that should be used for add the context restriction to a query.
     * <br>
     * The OMOP column name refers to a column of the OMOP table represented by this data type. The value is obtained
     * from contextValue according to contextPath.
     *
     * @param contextPath The CQL path within an instance of the data type.
     * @param contextValue The value of the CQL context that should be compared to the value of the OMOP column.
     * @return A {@link ContextInfo} instance with the column name and value.
     */
    default ContextInfo infoForContext(String contextPath, Object contextValue) {
        return null;
    }

    /**
     * Indicate whether a CQL query condition with the given codePath can be translated into an SQL join of OMOP tables.
     *
     * @param codePath The CQL path of the property within an instance of this data type that should be compared to
     *                 CQL code values.
     * @return True if CQL query condition can be represented as an SQL join of OMOP tables, false otherwise.
     */
    boolean isJoinableCodePath(String codePath);

}

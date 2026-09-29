package de.umg.minai.cqlonomop.engine;

import OMOP.MappingInfo;
import org.opencds.cqf.cql.engine.exception.CqlException;
import org.opencds.cqf.cql.engine.model.ModelResolver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

public class OMOPModelResolver implements ModelResolver {

    final MappingInfo mappingInfo;

    public OMOPModelResolver(final MappingInfo mappingInfo) {
        this.mappingInfo = mappingInfo;
    }

    @Override
    public String getPackageName() {
        return String.format("OMOP.%s", this.mappingInfo.getVersion().replace(".", ""));
    }

    @Override
    public void setPackageName(String s) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Object resolvePath(final Object object, final String path) {
        Class<?> clazz = object.getClass();
        final var name = path.substring(0, 1).toUpperCase() + path.substring(1);
        final Method method;
        Object result;
        try {
            method = clazz.getMethod(String.format("get%s", name));
            result = method.invoke(object);
        } catch (NoSuchMethodException e) {
            System.err.printf("property '%s' [via get%s] not found in class %s%n",
                    path, name, clazz.getCanonicalName());
            throw new CqlException(String.format("Property '%s' [via get%s] not found in class %s",
                    path,
                    name,
                    clazz.getCanonicalName()));
        } catch (InvocationTargetException | IllegalAccessException e) {
            // toString() may trigger other method calls (such as those related to Hibernate) that could in turn fail.
            // Make sure that the original is still reported if that problem occurs.
            String objectString;
            try {
                objectString = object.toString();
            } catch (Exception ignored) {
                objectString = String.format("<error during %s.toString>", clazz.getCanonicalName());
            }
            throw new CqlException(String.format("Failed to get property '%s' [via get%s] of object %s: %s: %s",
                    path,
                    name,
                    objectString,
                    e,
                    e.getMessage()));
        }
        if (result instanceof Optional<?> optional) {
            return optional.orElse(null);
        } else {
            return result;
        }
    }

    @Override
    public Object getContextPath(final String contextName, final String targetType) {
       return mappingInfo.getDataTypeInfo(targetType).contextPath(contextName);
    }

    @Override
    public Class<?> resolveType(final String typeName) {
        return this.mappingInfo.getDataTypeInfo(typeName).getClazz();
    }

    @Override
    public Class<?> resolveType(final Object value) {
        return value.getClass();
    }

    @Override
    public Boolean is(final Object value, final Class<?> type) {
        return type.isAssignableFrom(value.getClass());
    }

    @Override
    public Object as(Object value, Class<?> type, boolean isStrict) {
        throw new RuntimeException(String.format("as(%s, %s, %s) not implemented", value, type, isStrict));
    }

    @Override
    public Object createInstance(final String typeName) {
        final var clazz = mappingInfo.getDataTypeInfo(typeName).getClazz();
        final Constructor<?> constructor;
        try {
            constructor = clazz.getConstructor((Class<?>[]) null);
        } catch (NoSuchMethodException e) {
            return null;
        }
        try {
            return constructor.newInstance((Object[]) null);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }

    @Override
    public void setValue(final Object object, final String field, final Object value) {
        final var clazz = object.getClass();
        final var methodName = "set" + field.substring(0, 1).toUpperCase() + field.substring(1);
        final var method = Arrays.stream(clazz.getMethods())
                .filter(m -> m.getName().equals(methodName))
                .findFirst().orElseThrow(() -> new RuntimeException(String.format("No setter method found for '%s' in object of type %s",
                        field, object.getClass().getName())));
        try {
            method.invoke(object, value);
        } catch (final Exception e) {
            throw new RuntimeException(String.format("Could not set '%s' in object of type %s to %s using %s: %s",
                    field, object.getClass().getName(), value, method, e), e);
        }
    }

    @Override
    public boolean canUseEquals(final Class<?> clazz) {
        return mappingInfo.getDataTypeInfo(clazz) != null;
    }

    @Override
    public Boolean objectEqual(final Object left, final Object right) {
        return left.equals(right);
    }

    @Override
    public Boolean objectEquivalent(Object left, Object right) {
        return left.equals(right);
    }

    @Override
    public String resolveId(Object target) {
        final var dataTypeInfo = mappingInfo.getDataTypeInfo(target.getClass());
        if (dataTypeInfo != null) {
            final var contextInfo = dataTypeInfo.infoForContext("person", target);
            if (contextInfo != null) {
                return String.valueOf(contextInfo.value());
            }
        }
        throw new RuntimeException(String.format("resolveId(%s) not implemented", target));
    }

}

package org.example.engine;

import OMOP.MappingInfo;
import org.opencds.cqf.cql.engine.model.ModelResolver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    public Object resolvePath(Object o, String s) {
        Class<?> clazz = o.getClass();
        final var name = s.substring(0, 1).toUpperCase() + s.substring(1);
        final Method method;
        Object result;
        try {
            method = clazz.getMethod(String.format("get%s", name), new Class[]{});
            result = method.invoke(o);
        } catch (NoSuchMethodException e) {
            System.err.printf("%s [get%s] not found in class %s%n",
                    s, name, clazz.getCanonicalName());
            return null;
        } catch (InvocationTargetException | IllegalAccessException e) {
            System.err.printf("failed to get %s of %s: %s", s, o, e);
            return null;
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
        throw new RuntimeException("not implemented");
    }

    @Override
    public Object createInstance(String typeName) {
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
    public void setValue(Object o, String s, Object o1) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Boolean objectEqual(final Object left, final Object right) {
        return left.equals(right);
    }

    @Override
    public Boolean objectEquivalent(Object o, Object o1) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public String resolveId(Object o) {
        throw new RuntimeException("Not implemented");
    }

}

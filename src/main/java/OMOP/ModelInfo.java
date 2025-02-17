package OMOP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

// Based on https://www.baeldung.com/java-find-all-classes-in-package
public class ModelInfo {

    public static Set<Class<?>> allClasses() {
        final var stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream("OMOP");
        return new BufferedReader(new InputStreamReader(stream))
                .lines()
                .filter(line -> line.endsWith(".class"))
                .map(ModelInfo::getClass)
                .collect(Collectors.toSet());
    }

    public static Class<?> getClass(final String className) {
        try {
            return Class.forName("OMOP" + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    private final String version;

    private final Map<String, DataTypeInfo> infos = new HashMap<>();

    public ModelInfo(String version) {
        this.version = version;
    }

    public String getVersion() {
        return this.version;
    }

    public void registerDataTypeInfo(final String name, final DataTypeInfo info) {
        infos.put(name, info);
    }

    public DataTypeInfo getDataTypeInfo(final String name) {
        return infos.get(name);
    }

    private static final Map<String, ModelInfo> versions = new HashMap<>();

    public static void ensureVersion(final String version) {
        versions.computeIfAbsent(version, ModelInfo::new);
    }

}

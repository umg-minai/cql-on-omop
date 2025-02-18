package OMOP;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class MappingInfo {

    private final String version;

    private final Map<String, DataTypeInfo> infos = new HashMap<>();

    public MappingInfo(final String version) {
        this.version = version;
        final var registerClassName = String.format("OMOP.%s.Register", version.replace(".", ""));
        try {
            final var registerClass = Class.forName(registerClassName);
            registerClass.getMethod("register", MappingInfo.class).invoke(null, this);
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public String getVersion() {
        return this.version;
    }

    public Map<String, DataTypeInfo> getDataTypeInfos() {
        return this.infos;
    }

    public void registerDataTypeInfo(final String name, final DataTypeInfo info) {
        infos.put(name, info);
    }

    public DataTypeInfo getDataTypeInfo(final String name) {
        return infos.get(name);
    }

    private static final Map<String, MappingInfo> versions = new HashMap<>();

    public static MappingInfo ensureVersion(final String version) {
        return versions.computeIfAbsent(version, MappingInfo::new);
    }

}

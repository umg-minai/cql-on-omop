package OMOP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.stream.Collectors;

// Based on https://www.baeldung.com/java-find-all-classes-in-package
public class Meta {

    public static Set<Class<?>> allClasses() {
        final var stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream("OMOP");
        return new BufferedReader(new InputStreamReader(stream))
                .lines()
                .filter(line -> line.endsWith(".class"))
                .map(Meta::getClass)
                .collect(Collectors.toSet());
    }

    private static Class<?> getClass(final String className) {
        try {
            return Class.forName("OMOP" + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

}

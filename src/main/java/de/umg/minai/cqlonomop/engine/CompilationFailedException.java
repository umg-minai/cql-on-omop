package de.umg.minai.cqlonomop.engine;

import org.cqframework.cql.cql2elm.CqlCompilerException;

import java.util.Collection;
import java.util.Set;

public class CompilationFailedException extends RuntimeException {

    final private Set<CqlCompilerException> errors;

    public CompilationFailedException(final Collection<CqlCompilerException> compilerErrors) {
        this.errors = Set.copyOf(compilerErrors);
    }

    public Set<CqlCompilerException> getErrors() {
        return this.errors;
    }

}

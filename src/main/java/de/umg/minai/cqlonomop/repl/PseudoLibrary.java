package de.umg.minai.cqlonomop.repl;

import de.umg.minai.cqlonomop.engine.Constants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// TODO: rewrite as record?
public class PseudoLibrary {

    public final List<String> include;
    public final List<String> prelude;
    public final List<String> statements;

    public PseudoLibrary() {
        this.include = new LinkedList<>();
        this.prelude = new LinkedList<>();
        this.statements = new LinkedList<>();
    }

    public PseudoLibrary(List<String> include, List<String> prelude, List<String> statements) {
        this.include = include;
        this.prelude = prelude;
        this.statements = statements;
    }

    public void addInclude(final String include) {
        this.include.add(include);
    }

    public PseudoLibrary withAddedInclude(final String include) {
        final var result = klone();
        result.addInclude(include);
        return result;
    }

    public void addPrelude(final String preludeStatement) {
        this.prelude.add(preludeStatement);
    }

    public PseudoLibrary withAddedPrelude(final String preludeStatement) {
        final var result = klone();
        result.addPrelude(preludeStatement);
        return result;
    }

    public void addStatement(final String statement) {
        this.statements.add(statement);
    }

    public PseudoLibrary withAddedStatement(final String statement) {
        final var result = klone();
        result.addStatement(statement);
        return result;
    }

    public String getCode() {
        final var input = new StringBuilder();
        input.append("""
        using "OMOP" version 'v5.4'

        include "OMOPHelpers"
        include "OMOPFunctions"
        """);
        this.include.forEach(statement -> input.append(statement).append("\n"));
        input.append(String.format("codesystem OMOPSV: '%s' // SV = Standardized Vocabulary\n", Constants.OMOP_CODESYSTEM_URI));
        Constants.OMOP_CODESYSTEM_URI_TO_VOCABULARY_ID.forEach((url, name) ->
                input.append(String.format("codesystem %s: '%s'\n", name, url)));
        this.prelude.forEach(statement -> input.append(statement).append("\n"));
        this.statements.forEach(statement -> input.append(statement).append("\n"));
        return input.toString();
    }

    public PseudoLibrary klone() {
        return new PseudoLibrary(new ArrayList<>(this.include),
                new ArrayList<>(this.prelude),
                new ArrayList<>(this.statements));
    }

}

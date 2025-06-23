package de.umg.minai.cqlonomop.repl;

import de.umg.minai.cqlonomop.engine.Constants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// TODO: rewrite as record?
public class PseudoLibrary {

    public String omopVersion;
    public final List<String> include;
    public final List<String> prelude;
    public final List<String> statements;

    public PseudoLibrary(final String omopVersion,
                         final List<String> include,
                         final List<String> prelude,
                         final List<String> statements) {
        this.omopVersion = omopVersion;
        this.include = include;
        this.prelude = prelude;
        this.statements = statements;
    }

    public PseudoLibrary(final String omopVersion) {
        this(omopVersion, new LinkedList<>(), new LinkedList<>(), new LinkedList<>());
    }

    public void setOmopVersion(final String omopVersion) {
        this.omopVersion = omopVersion;
    }

    public PseudoLibrary withOmopVersion(final String omopVersion) {
        final var result = klone();
        result.setOmopVersion(omopVersion);
        return result;
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
        input.append(String.format("""
        using "OMOP" version '%s'

        include "OMOPHelpers%s" called "OMOPHelpers"
        include "OMOPFunctions" called "OMOPFunctions"
        """, this.omopVersion, this.omopVersion));
        this.include.forEach(statement -> input.append(statement).append("\n"));
        input.append(String.format("codesystem OMOPSV: '%s' // SV = Standardized Vocabulary\n", Constants.OMOP_CODESYSTEM_URI));
        Constants.OMOP_CODESYSTEM_URI_TO_VOCABULARY_ID.forEach((url, name) ->
                input.append(String.format("codesystem %s: '%s'\n", name, url)));
        this.prelude.forEach(statement -> input.append(statement).append("\n"));
        this.statements.forEach(statement -> input.append(statement).append("\n"));
        return input.toString();
    }

    public PseudoLibrary klone() {
        return new PseudoLibrary(this.omopVersion,
                new ArrayList<>(this.include),
                new ArrayList<>(this.prelude),
                new ArrayList<>(this.statements));
    }

}

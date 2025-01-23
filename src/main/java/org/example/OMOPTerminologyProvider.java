package org.example;

import org.opencds.cqf.cql.engine.runtime.Code;
import org.opencds.cqf.cql.engine.terminology.CodeSystemInfo;
import org.opencds.cqf.cql.engine.terminology.TerminologyProvider;
import org.opencds.cqf.cql.engine.terminology.ValueSetInfo;

public class OMOPTerminologyProvider implements TerminologyProvider {

    @Override
    public boolean in(Code code, ValueSetInfo valueSetInfo) {
        return false;
    }

    @Override
    public Iterable<Code> expand(ValueSetInfo valueSetInfo) {
        return null;
    }

    @Override
    public Code lookup(Code code, CodeSystemInfo codeSystemInfo) {
        return null;
    }

}

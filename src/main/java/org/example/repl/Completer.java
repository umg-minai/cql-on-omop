package org.example.repl;

import org.hl7.elm_modelinfo.r1.ClassInfo;
import org.hl7.elm_modelinfo.r1.ModelInfo;
import org.jline.reader.Candidate;
import org.jline.reader.LineReader;
import org.jline.reader.ParsedLine;

import java.util.List;

public class Completer implements org.jline.reader.Completer {

    private final ModelInfo systemModelInfo;

    private final ModelInfo domainModelInfo;

    public Completer(final ModelInfo systemModelInfo,
                     final ModelInfo domainModelInfo) {
        this.systemModelInfo = systemModelInfo;
        this.domainModelInfo = domainModelInfo;
    }

    @Override
    public void complete(final LineReader lineReader,
                         final ParsedLine parsedLine,
                         final List<Candidate> list) {
        final String word = parsedLine.word();

        if (word.startsWith(",")) {
            list.add(new Candidate(",focus",
                    ",focus EXPRESSION",
                    "Command",
                    "Set CQL context value",
                    null,
                    null,
                    true ));
            list.add(new Candidate(",set",
                    ",set PARAMETER EXPRESSION",
                    "Command",
                    "Set CQL parameter value",
                    null,
                    null,
                    true ));
        } else if (word.startsWith("[")) {
            this.domainModelInfo.getTypeInfo().forEach(info -> {
                if (info instanceof ClassInfo classInfo && classInfo.isRetrievable()) {
                    list.add(new Candidate("[" + classInfo.getName(),
                            "[" + classInfo.getName() + "…",
                            "Retrieve",
                            classInfo.getDescription(),
                            null,
                            null,
                            true));
                }
            });
        } else {
            this.systemModelInfo.getTypeInfo().forEach(info -> {
                if (info instanceof ClassInfo classInfo) {
                    final var string = new StringBuilder();
                    final var name = classInfo.getName();
                    string.append(name.substring(name.indexOf(".") + 1));
                    string.append("{");
                    boolean[] first = {true};
                    classInfo.getElement().forEach(field -> {
                        if (first[0]) {
                            first[0] = false;
                        } else {
                            string.append(",");
                        }
                        string.append(field.getName());
                        string.append(":");
                    });
                    string.append("}");
                    final var completion = string.toString();
                    list.add(new Candidate(completion,
                            completion,
                            "System",
                            classInfo.getDescription(),
                            null,
                            null,
                            true
                    ));
                }
            });
            if (word.equals("context")) {
                this.domainModelInfo.getContextInfo().forEach(info ->
                        list.add(new Candidate(info.getName())));
            }
        }
    }

}

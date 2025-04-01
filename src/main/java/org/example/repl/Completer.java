package org.example.repl;

import org.jline.reader.Candidate;
import org.jline.reader.LineReader;
import org.jline.reader.ParsedLine;

import java.util.List;

public class Completer implements org.jline.reader.Completer {

    @Override
    public void complete(final LineReader lineReader,
                         final ParsedLine parsedLine,
                         final List<Candidate> list) {
        list.add(new Candidate(",focus"));
        list.add(new Candidate("[ConditionOccurrence"));
    }

}

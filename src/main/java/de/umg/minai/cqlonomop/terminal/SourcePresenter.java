package de.umg.minai.cqlonomop.terminal;

import org.cqframework.cql.cql2elm.LibraryManager;
import org.cqframework.cql.elm.tracking.TrackBack;
import org.hl7.elm.r1.VersionedIdentifier;
import org.jline.terminal.Terminal;
import org.opencds.cqf.cql.engine.debug.Location;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SourcePresenter {

    private final LibraryManager libraryManager;

    private final Terminal terminal;

    private final Theme theme;

    public SourcePresenter(final Terminal terminal,
                           final Theme theme,
                           final LibraryManager libraryManager) {
        this.libraryManager = libraryManager;
        this.terminal = terminal;
        this.theme = theme;
    }

    public void presentSource(final List<String> sourceLines,
                              final TrackBack location,
                              final ThemeAwareStringBuilder builder) {
        final var startLine = location.getStartLine() - 1;
        final var endLine = location.getEndLine() - 1;
        final var startColumn = Math.max(0, location.getStartChar() - 1);
        final var endColumn = location.getEndChar();
        presentLocation(sourceLines, startLine, endLine, startColumn, endColumn, builder, 4);
    }

    public void presentSource(final List<String> sourceLines,
                              final Location location,
                              final ThemeAwareStringBuilder builder) {
        final var startLine = location.getStartLine() - 1;
        final var endLine = location.getEndLine() - 1;
        final var startColumn = Math.max(0, location.getStartChar() - 1);
        final var endColumn = location.getEndChar();
        presentLocation(sourceLines, startLine, endLine, startColumn, endColumn, builder, 4);
    }

    public void presentLocation(final List<String> sourceLines,
                                 int startLine,
                                 int endLine,
                                 int startColumn,
                                 int endColumn,
                                 final ThemeAwareStringBuilder builder,
                                 int indent) {
        for (var lineNumber = startLine; lineNumber <= endLine; ++lineNumber) {
            final var line = (lineNumber < sourceLines.size())
                    ? sourceLines.get(lineNumber)
                    : null;
            if (line != null) {
                var start = (lineNumber == startLine) ? startColumn : 0;
                var end = (lineNumber == endLine) ? endColumn : line.length();
                if (start == end && start > 0) {
                    start -= 1;
                } else if (start == end && end < line.length()) {
                    end += 1;
                }
                // Don't crash if the source file doesn't match the
                // recorded location, for example when the source file
                // has been modified in the meantime.
                end = Math.min(line.length(), end);
                // Line number and part of line before highlight.
                for (int i = 0; i < indent; ++i) {
                    builder.append(' ');
                }
                builder.withStyle(Theme.Element.INACTIVE, String.format("%4d │ ", lineNumber + 1))
                        .append(line.substring(0, start));
                // Highlight
                final var highlight = line.substring(start, end);
                var style = this.theme.styleForElement(Theme.Element.ERROR);
                if (startLine == endLine) {
                    style = style.underline();
                }
                builder.withStyle(style, highlight)
                        .append(line.substring(end)) // Remainder of line after highlight.
                        .append("\n");
            }
        }
    }

    public List<String> fetchLibrarySource(final VersionedIdentifier library) {
        ArrayList<String> sourceLines = null;
        final var source = this.libraryManager.getLibrarySourceLoader().getLibrarySource(library);
        if (source != null) {
            sourceLines = new ArrayList<>();
            try {
                final var reader = new BufferedReader(new InputStreamReader(source));
                String line;
                while ((line = reader.readLine()) != null) {
                    sourceLines.add(line);
                }
            } catch (IOException e) {
                sourceLines = null;
            }
        }
        return sourceLines;
    }

}

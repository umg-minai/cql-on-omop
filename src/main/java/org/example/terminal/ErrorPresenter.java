package org.example.terminal;

import org.cqframework.cql.cql2elm.CqlCompilerException;
import org.cqframework.cql.cql2elm.LibraryManager;
import org.cqframework.cql.elm.tracking.TrackBack;
import org.example.engine.CompilationFailedException;
import org.hl7.elm.r1.VersionedIdentifier;
import org.jline.terminal.Terminal;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ErrorPresenter {

    private final LibraryManager libraryManager;

    private final Terminal terminal;

    public ErrorPresenter(final LibraryManager libraryManager,
                          final Terminal terminal) {
        this.libraryManager = libraryManager;
        this.terminal = terminal;
    }

    public void presentError(final Exception error) {
        if (error instanceof CompilationFailedException compilationFailedException) {
            presentSpecificError(compilationFailedException);
        } else {
            presentSpecificError(error);
        }
    }

    private void presentSpecificError(final Exception exception) {
        new AttributedStringBuilder()
                .style(new AttributedStyle().foregroundRgb(0xff0000))
                .append("CQL evaluation failed:\n")
                .append(exception.toString())
                .println(terminal);
    }

    private void presentSpecificError(final CompilationFailedException exception) {
        final var builder = new AttributedStringBuilder()
                .style(new AttributedStyle().foregroundRgb(0xff0000))
                .append("CQL compilation failed:\n")
                .style(new AttributedStyle());
        groupErrorByLibrary(exception).forEach((library, errors) ->
                presentErrorsForLibrary(library, errors, builder));
        builder.println(terminal);
    }

    private void presentErrorsForLibrary(final VersionedIdentifier library,
                                         final List<CqlCompilerException> errors,
                                         final AttributedStringBuilder builder) {
        builder.append("• ")
                .style(new AttributedStyle().bold())
                .append(library != null ? library.getId() : "<unknown source>")
                .style(new AttributedStyle())
                .append("\n");
        final var sourceLines = library != null ? fetchLibrarySource(library) : null;
        errors.stream()
                .sorted(ErrorPresenter::compareLocations)
                .forEach(error -> presentErrorWithSource(error, sourceLines, builder));
    }

    private void presentErrorWithSource(final CqlCompilerException error,
                                        final List<String> sourceLines,
                                        final AttributedStringBuilder builder) {
        builder.append("\n  • ").append(error.getMessage()).append("\n");
        if (sourceLines != null) {
            final var locator = error.getLocator();
            if (locator != null) {
                final var startLine = locator.getStartLine() - 1;
                final var endLine = locator.getEndLine() - 1;
                final var startColumn = locator.getStartChar() - 1;
                final var endColumn = locator.getEndChar();
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
                        // Line number and part of line before highlight.
                        builder.style(new AttributedStyle().foregroundRgb(0x808080))
                                .append(String.format("    %4d │ ", lineNumber + 1))
                                .style(new AttributedStyle())
                                .append(line.substring(0, start));
                        // Highlight
                        final var highlight = line.substring(start, end);
                        var style = new AttributedStyle().foregroundRgb(0xff0000);
                        if (startLine == endLine) {
                            style = style.underline();
                        }
                        builder.style(style)
                                .append(highlight)
                                .style(new AttributedStyle());
                        // Remainder of line after highlight.
                        builder.append(line.substring(end))
                                .append("\n");
                    }
                }
            }
        }
    }

    private static Map<VersionedIdentifier, List<CqlCompilerException>> groupErrorByLibrary(CompilationFailedException exception) {
        final var libraryToError = new HashMap<VersionedIdentifier, List<CqlCompilerException>>();
        exception.getErrors().forEach(error -> {
            final var locator = error.getLocator();
            final var errors = libraryToError.computeIfAbsent(
                    locator != null ? locator.getLibrary() : null,
                    (_library) -> new LinkedList<>());
            errors.add(error);
        });
        return libraryToError;
    }

    private List<String> fetchLibrarySource(VersionedIdentifier library) {
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

    private static int compareLocations(final CqlCompilerException a, final CqlCompilerException b) {
        return compareLocations(a.getLocator(), b.getLocator());
    }

    private static int compareLocations(final TrackBack a, final TrackBack b) {
        final var endLineResult = Integer.compare(a.getEndLine(), b.getEndLine());
        if (endLineResult != 0) {
            return endLineResult;
        }
        final var startLineResult = Integer.compare(a.getStartLine(), b.getStartLine());
        if (startLineResult != 0) {
            return -startLineResult;
        }
        final var endColumnResult = Integer.compare(a.getEndChar(), b.getEndChar());
        if (endColumnResult != 0) {
            return endColumnResult;
        }
        return -Integer.compare(a.getStartChar(), b.getStartChar());
    }

}

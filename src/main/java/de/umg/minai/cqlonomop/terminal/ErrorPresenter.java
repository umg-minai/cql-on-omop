package de.umg.minai.cqlonomop.terminal;

import org.cqframework.cql.cql2elm.CqlCompilerException;
import org.cqframework.cql.cql2elm.LibraryManager;
import org.cqframework.cql.elm.tracking.TrackBack;
import de.umg.minai.cqlonomop.engine.CompilationFailedException;
import org.hl7.elm.r1.Element;
import org.hl7.elm.r1.FunctionDef;
import org.hl7.elm.r1.OperandDef;
import org.hl7.elm.r1.VersionedIdentifier;
import org.jline.terminal.Terminal;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.opencds.cqf.cql.engine.exception.Backtrace;
import org.opencds.cqf.cql.engine.exception.CqlException;
import org.opencds.cqf.cql.engine.execution.Variable;

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
        } else if (error instanceof CqlException cqlException) {
            presentSpecificError(cqlException);
        } else {
            presentSpecificError(error);
        }
    }

    private void presentSpecificError(final Exception exception) {
        new AttributedStringBuilder()
                .style(new AttributedStyle().foregroundRgb(0xff0000))
                .append("Internal error:\n")
                .append(exception.toString())
                .println(terminal);
    }

    // Runtime Exceptions

    private void presentSpecificError(final CqlException exception) {
        final var builder = new AttributedStringBuilder()
                .style(new AttributedStyle().foregroundRgb(0xff0000))
                .append("CQL evaluation failed:\n\n")
                .style(new AttributedStyle())
                .append(exception.getMessage())
                .append("\n\n");
        final var frames = exception.getBacktrace().getFrames();
        for (int i = frames.size() - 1; i >= 0; --i) {
            final var frame = frames.get(i);
            final var nextFrame = (i > 0) ? frames.get(i - 1) : null;
            final var expression = frame.getExpression();
            final TrackBack trackback = maybeGetTrackback(expression);
            VersionedIdentifier library = trackback != null ? trackback.getLibrary() : null;

            if (frame instanceof Backtrace.FunctionoidFrame functionFrame) {
                final var functionoid = functionFrame.getDefinition();
                if (library == null) {
                    final var functionTrackback = maybeGetTrackback(functionFrame.getDefinition());
                    if (functionTrackback != null) {
                        library = functionTrackback.getLibrary();
                    }
                }
                builder.append("\n  ")
                        .append(library != null ? library.getId() : "«unknown library»")
                        .append(".")
                        .style(new AttributedStyle().foregroundRgb(0x4040c0))
                        .append(functionoid.getName())
                        .style(new AttributedStyle());
                if (functionoid instanceof FunctionDef functionDef) {
                    builder.append("(");
                    boolean isFirst = true;
                    for (OperandDef operand : functionDef.getOperand()) {
                        if (isFirst) {
                            isFirst = false;
                        } else {
                            builder.append(", ");
                        }
                        builder.append(operand.getName());
                        // TODO(jmoringe): find a good way to present the type
                                /*.append(" ")
                                .append(operand.getOperandTypeSpecifier().toString());*/
                    }
                    builder.append(")");
                }
                builder.append("\n");
                presentVariableList("    Arguments", functionFrame.getArguments(), builder);
                presentVariableList("    Local Variables", functionFrame.getLocalVariables(), builder);
                appendHeader("    Context", builder);
                appendBinding(functionFrame.getContextName(), functionFrame.getContextValue(), builder);
                builder.append("\n");
            }
            appendHeader("    Source\n", builder);
            if (nextFrame == null || nextFrame instanceof Backtrace.FunctionoidFrame) {
                if (trackback != null) {
                    final var sourceLines = fetchLibrarySource(library);
                    presentSource(sourceLines, trackback, builder);
                } else {
                    builder.append("    <missing source>\n");
                }
            }
        }
        builder.println(terminal);
    }

    private AttributedStringBuilder appendHeader(final String title, final AttributedStringBuilder builder) {
        return builder.style(new AttributedStyle().bold())
                .append(title)
                .style(new AttributedStyle());
    }

    private AttributedStringBuilder appendBinding(final String name,
                                                  final Object value,
                                                  final AttributedStringBuilder builder) {
        return builder.append("\n      ")
                .style(new AttributedStyle().foregroundRgb(0x40c040))
                .append(name)
                .style(new AttributedStyle())
                .append("=")
                .style(new AttributedStyle().foregroundRgb(0x4040c0))
                .append(value != null ? value.toString() : "«no value»")  // TODO(jmoringe): use result presenter?
                .style(new AttributedStyle());
    }

    private void presentVariableList(final String title,
                                     final Collection<Variable> variables,
                                     final AttributedStringBuilder builder) {
        if (!variables.isEmpty()) {
            appendHeader(title, builder);
            variables.forEach(argument ->
                    appendBinding(argument.getName(), argument.getValue(), builder));
            builder.append("\n");
        }
    }

    // Compilation errors

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
                presentSource(sourceLines, locator, builder);
            }
        }
    }

    private void presentSource(final List<String> sourceLines,
                               final TrackBack location,
                               final AttributedStringBuilder builder) {
        final var startLine = location.getStartLine() - 1;
        final var endLine = location.getEndLine() - 1;
        final var startColumn = Math.max(0, location.getStartChar() - 1);
        final var endColumn = location.getEndChar();
        presentLocation(sourceLines, startLine, endLine, startColumn, endColumn, builder, 4);
    }

    private void presentLocation(final List<String> sourceLines,
                                 int startLine,
                                 int endLine,
                                 int startColumn,
                                 int endColumn,
                                 final AttributedStringBuilder builder,
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
                builder.style(new AttributedStyle().foregroundRgb(0x808080))
                        .append(String.format("%4d │ ", lineNumber + 1))
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

    private static Map<VersionedIdentifier, List<CqlCompilerException>> groupErrorByLibrary(final CompilationFailedException exception) {
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

    private List<String> fetchLibrarySource(final VersionedIdentifier library) {
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
        if (a == null && b == null) {
            return 0;
        } else if (b == null) {
            return -1;
        } else if (a == null) {
            return 1;
        }
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

    private TrackBack maybeGetTrackback(final Element expression) {
        final var trackbacks = expression.getTrackbacks();
        if (!trackbacks.isEmpty()) {
            return trackbacks.get(0);
        } else {
            return null;
        }
    }

}

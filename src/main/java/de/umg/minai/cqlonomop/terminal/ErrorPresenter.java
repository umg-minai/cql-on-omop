package de.umg.minai.cqlonomop.terminal;

import de.umg.minai.cqlonomop.engine.CompilationFailedException;
import org.cqframework.cql.cql2elm.CqlCompilerException;
import org.cqframework.cql.cql2elm.LibraryManager;
import org.cqframework.cql.elm.tracking.TrackBack;
import org.hl7.elm.r1.Element;
import org.hl7.elm.r1.FunctionDef;
import org.hl7.elm.r1.OperandDef;
import org.hl7.elm.r1.VersionedIdentifier;
import org.jline.terminal.Terminal;
import org.jline.utils.AttributedStyle;
import org.opencds.cqf.cql.engine.exception.Backtrace;
import org.opencds.cqf.cql.engine.exception.CqlException;
import org.opencds.cqf.cql.engine.execution.Variable;

import java.util.*;

public class ErrorPresenter extends AbstractPresenter{

    private final SourcePresenter sourcePresenter;

    private final ValuePresenter valuePresenter;

    public ErrorPresenter(final Terminal terminal,
                          final Theme theme,
                          final SourcePresenter sourcePresenter,
                          final ValuePresenter valuePresenter) {
        super(terminal, theme);
        this.sourcePresenter = sourcePresenter;
        this.valuePresenter = valuePresenter;
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
        present(builder ->
                builder.withStyle(Theme.Element.ERROR,
                        builder2 -> builder2.append("Internal error:\n")
                        .append(exception.toString())));
    }

    // Runtime Exceptions

    private void presentSpecificError(final CqlException exception) {
        present(builder -> {
            builder.withStyle(Theme.Element.ERROR, b ->
                    b.append("CQL evaluation failed:\n\n").append(exception.getMessage()));
            builder.append("\n\n");
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
                            .append(".");
                    builder.withStyle(Theme.Element.DEFINITION_NAME, functionoid.getName());
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
                        final var sourceLines = this.sourcePresenter.fetchLibrarySource(library);
                        this.sourcePresenter.presentSource(sourceLines, trackback, builder);
                    } else {
                        builder.append("    <missing source>\n");
                    }
                }
            }
        });
    }

    private ThemeAwareStringBuilder appendHeader(final String title, final ThemeAwareStringBuilder builder) {
        return builder.withStyle(Theme.Element.HEADING, title);
    }

    private ThemeAwareStringBuilder appendBinding(final String name,
                                                  final Object value,
                                                  final ThemeAwareStringBuilder builder) {
        builder.append("\n      ");
        builder.withStyle(Theme.Element.IDENTIFIER, name)
                .append("=");
        this.valuePresenter.presentValueSimple(value, builder, 120);
        return builder;
    }

    private void presentVariableList(final String title,
                                     final Collection<Variable> variables,
                                     final ThemeAwareStringBuilder builder) {
        if (!variables.isEmpty()) {
            appendHeader(title, builder);
            variables.forEach(argument ->
                    appendBinding(argument.getName(), argument.getValue(), builder));
            builder.append("\n");
        }
    }

    // Compilation errors

    private void presentSpecificError(final CompilationFailedException exception) {
        present(builder -> {
            builder.withStyle(Theme.Element.ERROR, "CQL compilation failed:\n");
            groupErrorByLibrary(exception).forEach((library, errors) ->
                    presentErrorsForLibrary(library, errors, builder));
        });
    }

    private void presentErrorsForLibrary(final VersionedIdentifier library,
                                         final List<CqlCompilerException> errors,
                                         final ThemeAwareStringBuilder builder) {
        builder.append("• ");
        builder.withStyle(new AttributedStyle().bold(), library != null ? library.getId() : "<unknown source>")
                .append("\n");
        final var sourceLines = library != null
                ? (library.getId() != null
                   ? this.sourcePresenter.fetchLibrarySource(library)
                   : null)
                : null;
        errors.stream()
                .sorted(ErrorPresenter::compareLocations)
                .forEach(error -> presentErrorWithSource(error, sourceLines, builder));
    }

    private void presentErrorWithSource(final CqlCompilerException error,
                                        final List<String> sourceLines,
                                        final ThemeAwareStringBuilder builder) {
        builder.append("\n  • ").append(error.getMessage()).append("\n");
        if (sourceLines != null) {
            final var locator = error.getLocator();
            if (locator != null) {
                this.sourcePresenter.presentSource(sourceLines, locator, builder);
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

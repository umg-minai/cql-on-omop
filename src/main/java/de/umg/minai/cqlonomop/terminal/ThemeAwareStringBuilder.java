package de.umg.minai.cqlonomop.terminal;

import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;

import java.util.function.Function;

public class ThemeAwareStringBuilder extends AttributedStringBuilder {

    private final Theme theme;

    public ThemeAwareStringBuilder(final Theme theme) {
        this.theme = theme;
    }

    public ThemeAwareStringBuilder elementStyle(Theme.Element element) {
        style(theme.styleForElement(element));
        return this;
    }

    public ThemeAwareStringBuilder withStyle(final AttributedStyle style,
                                             final Function<ThemeAwareStringBuilder, AttributedStringBuilder> continuation) {
        if (style != null) {
            final var oldStyle = style();
            return (ThemeAwareStringBuilder) continuation.apply((ThemeAwareStringBuilder) style(style)).style(oldStyle);
        } else {
            return (ThemeAwareStringBuilder) continuation.apply(this);
        }
    }

    public ThemeAwareStringBuilder withStyle(final Theme.Element style,
                                             final Function<ThemeAwareStringBuilder, AttributedStringBuilder> continuation) {
        if (style != null) {
            final var oldStyle = style();
            return (ThemeAwareStringBuilder) continuation.apply(elementStyle(style)).style(oldStyle);
        } else {
            return (ThemeAwareStringBuilder) continuation.apply(this);
        }
    }

    public ThemeAwareStringBuilder withStyle(final AttributedStyle style, final String continuation) {
        return withStyle(style, builder -> builder.append(continuation));
    }

    public ThemeAwareStringBuilder withStyle(final Theme.Element style, final String continuation) {
        return withStyle(style, builder -> builder.append(continuation));
    }

}

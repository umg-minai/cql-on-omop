package de.umg.minai.cqlonomop.terminal;

import org.jline.terminal.Terminal;

import java.util.function.Consumer;

public abstract class AbstractPresenter {

    protected final Terminal terminal;

    protected final Theme theme;

    public AbstractPresenter(final Terminal terminal, final Theme theme) {
        this.terminal = terminal;
        this.theme = theme;
    }

    void present(Consumer<ThemeAwareStringBuilder> continuation) {
        final var builder = new ThemeAwareStringBuilder(this.theme);
        continuation.accept(builder);
        builder.print(this.terminal);
    }

}

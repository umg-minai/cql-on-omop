package de.umg.minai.cqlonomop.repl.command;

public abstract class AbstractCommand implements Command {

    private final String name;

    protected AbstractCommand(final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getSyntaxDescription() {
        final var builder = new StringBuilder()
                .append(",")
                .append(getName());
        getArguments().forEach(argument -> builder.append(" ").append(argument));
        return builder.toString();
    }
}

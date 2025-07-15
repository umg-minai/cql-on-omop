package de.umg.minai.cqlonomop.repl;

import de.umg.minai.cqlonomop.repl.command.*;
import org.opencds.cqf.cql.engine.execution.EvaluationResult;

import java.util.HashMap;
import java.util.Map;

public class CommandProcessor {

    private final Evaluator evaluator;

    private final Map<String, Command> commands = new HashMap<>();

    public CommandProcessor(final Evaluator evaluator) {
        this.evaluator = evaluator;
        registerCommand(new CommandQuit());
        registerCommand(new CommandFocus(this.evaluator));
        registerCommand(new CommandSet(this.evaluator));
        registerCommand(new CommandUnset(this.evaluator));
        registerCommand(new CommandGraph(this.evaluator));
        registerCommand(new CommandProfile(this.evaluator));
        registerCommand(new CommandReload(this.evaluator));
        registerCommand(new CommandListing(this.evaluator));
    }

    public Map<String, Command> getCommands() {
        return this.commands;
    }

    public void registerCommand(final Command command) {
        this.commands.put(command.getName(), command);
    }

    public EvaluationResult process(final String string) throws Exception {
        final var commandAndArguments = string.substring(1).split("[ \t]+", 2);
        final var commandName = commandAndArguments[0];
        final var command = this.commands.get(commandName);
        if (command == null) {
            throw new RuntimeException(String.format("Unknown command %s", commandName));
        }
        final var argument = commandAndArguments.length == 2 ? commandAndArguments[1] : null;
        if (argument == null && !command.getArguments().isEmpty()) {
            final var builder = new StringBuilder()
                    .append("The command '")
                    .append(commandName)
                    .append("' expects the following arguments:");
            command.getArguments().forEach(argument2 -> builder.append(" ").append(argument2));
            throw new RuntimeException(builder.toString());
        } else {
            return command.run(argument);
        }
    }

}

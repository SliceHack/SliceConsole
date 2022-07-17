package com.sliceclient.console.command;

import com.sliceclient.console.command.data.CommandInfo;
import lombok.Getter;

/**
 * Base class for all commands
 *
 * @author Nick
 */
@Getter
public abstract class Command {

    /** CommandInfo */
    public final CommandInfo info = getClass().getAnnotation(CommandInfo.class);

    /** data of the command*/
    private final String name;
    private final String description;
    private final String[] aliases;

    /**
     * Constructor
     */
    public Command() {
        if(info == null) {
            throw new IllegalArgumentException("CommandInfo is null");
        }

        name = info.name();
        description = info.description();
        aliases = info.aliases();
    }

    /**
     * Executes a command
     *
     * @param args The arguments of the command
     * */
    public abstract boolean execute(String[] args);
}

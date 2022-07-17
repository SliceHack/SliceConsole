package com.sliceclient.console.command.manager;

import com.sliceclient.console.SliceConsole;
import com.sliceclient.console.command.Command;
import com.sliceclient.console.command.commands.CommandList;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * The class used for handling the Command Manager
 *
 * @author Nick
 * */
@Getter
public class CommandManager {

    /** Commands */
    private final List<Command> commands = new ArrayList<>();

    /** Prefix for the command */
    private final String PREFIX = "/";

    /** Constructor */
    public CommandManager() {
        register(new CommandList());
    }

    public boolean handle(String message) {
        if(message.startsWith(PREFIX)) {
            String command = message.substring(PREFIX.length());

            for(Command c : commands) {

                if(c.getName().startsWith(command)) {
                    String[] args = message.substring(PREFIX.length() + c.getName().length()).split(" ");
                    c.execute(args);
                    return true;
                }

            }
            SliceConsole.INSTANCE.addDisplayMessage("Unable to find command " + command + "!");

            return true;
        }
        return false;
    }

    /**
     * Adds a command to the command manager
     *
     * @param command The command to add
     * */
    public void register(Command command) {
        commands.add(command);
    }
}

package com.sliceclient.console.command.manager;

import com.sliceclient.console.SliceConsole;
import com.sliceclient.console.command.Command;
import com.sliceclient.console.command.commands.CommandBroadcast;
import com.sliceclient.console.command.commands.CommandClear;
import com.sliceclient.console.command.commands.CommandList;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
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
        register(new CommandBroadcast());
        register(new CommandClear());
    }

    public boolean handle(String message) {
        if(message.startsWith(PREFIX)) {
            String command = message.substring(PREFIX.length()).split(" ")[0];

            for(Command c : commands) {
                List<String> aliases = Arrays.asList(c.getAliases());

                if(c.getName().startsWith(command) || aliases.contains(command)) {
                    String[] args = message.substring(PREFIX.length() + command.length()).split(" ");
                    if(args[0].isEmpty()) args = Arrays.copyOfRange(args, 1, args.length);
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

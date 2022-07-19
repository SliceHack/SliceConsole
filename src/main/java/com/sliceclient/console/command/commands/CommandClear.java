package com.sliceclient.console.command.commands;

import com.sliceclient.console.SliceConsole;
import com.sliceclient.console.command.Command;
import com.sliceclient.console.command.data.CommandInfo;

@CommandInfo(name = "clear", description = "clears the console")
public class CommandClear extends Command {

    public boolean execute(String[] args) {
        SliceConsole.INSTANCE.getBox().getList().clear();
        SliceConsole.INSTANCE.getBox().add("Console cleared");
        return false;
    }
}

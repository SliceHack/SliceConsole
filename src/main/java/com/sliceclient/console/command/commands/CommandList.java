package com.sliceclient.console.command.commands;

import com.sliceclient.console.SliceConsole;
import com.sliceclient.console.command.Command;
import com.sliceclient.console.command.data.CommandInfo;

@CommandInfo(name = "list", description = "Lists all users online")
public class CommandList extends Command {

    public boolean execute(String[] args) {
        StringBuilder sb = new StringBuilder();

        sb.append("Users online: ");
        for (String s : SliceConsole.INSTANCE.getIrc().getList()) {
            String[] split = s.split(":");
            sb.append(split[1]).append(", ");
        }
        SliceConsole.INSTANCE.addDisplayMessage(sb.toString());
        return true;
    }
}

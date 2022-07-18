package com.sliceclient.console.command.commands;

import com.sliceclient.console.SliceConsole;
import com.sliceclient.console.command.Command;
import com.sliceclient.console.command.data.CommandInfo;

@CommandInfo(name = "list", description = "Lists all users online")
public class CommandList extends Command {

    public boolean execute(String[] args) {
        StringBuilder sb = new StringBuilder();

        sb.append("Users online: ");

        int index = 0;
        for (String s : SliceConsole.INSTANCE.getIrc().getList()) {

            String[] split = s.split(":");

            sb.append("#55FF55").append(split[1]);
            index++;
        }
        SliceConsole.INSTANCE.addDisplayMessage(sb.toString());
        return true;
    }
}

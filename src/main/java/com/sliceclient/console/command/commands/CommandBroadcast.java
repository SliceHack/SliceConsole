package com.sliceclient.console.command.commands;

import com.sliceclient.console.SliceConsole;
import com.sliceclient.console.command.Command;
import com.sliceclient.console.command.data.CommandInfo;

@CommandInfo(name = "broadcast", description = "Broadcasts a message to the server.", aliases = {"bc"})
public class CommandBroadcast extends Command {

    public boolean execute(String[] args) {
        if(args.length == 0) {
            SliceConsole.INSTANCE.addDisplayMessage("Usage: broadcast <message>");
            return false;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < args.length; i++) {
            sb.append(args[i]);
            if(i != args.length - 1) {
                sb.append(" ");
            }
        }
        SliceConsole.INSTANCE.getIrc().broadcastMessage(sb.toString());
        return true;
    }
}

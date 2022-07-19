package com.sliceclient.console.command.commands;

import com.sliceclient.console.SliceConsole;
import com.sliceclient.console.command.Command;
import com.sliceclient.console.command.data.CommandInfo;

@CommandInfo(name = "connect", description = "Connects to the server", aliases = {"c"})
public class CommandConnect extends Command {

    public boolean execute(String[] args) {
        if(SliceConsole.INSTANCE.getIrc().getSocket().connected()) SliceConsole.INSTANCE.getIrc().getSocket().disconnect();
        SliceConsole.INSTANCE.getIrc().getSocket().connect();
        new Thread(() -> {
            try {
                while (!SliceConsole.INSTANCE.getIrc().getSocket().connected()) {}
                SliceConsole.INSTANCE.getBox().add("Connected to the server!");
                SliceConsole.INSTANCE.getIrc().getSocketEvents().runConnected();
            } catch (Exception ignored) {}
        }).start();
        return false;
    }
}

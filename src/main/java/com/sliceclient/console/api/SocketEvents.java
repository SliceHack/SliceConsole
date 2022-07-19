package com.sliceclient.console.api;

import com.sliceclient.console.SliceConsole;
import com.sliceclient.console.util.hardware.HardwareUtil;
import io.socket.client.Socket;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter @Setter
public class SocketEvents {

    private Socket socket;

    public SocketEvents(Socket socket) {
        this.socket = socket;
        this.runOnEvent();
        this.runConnected();
    }

    private void runOnEvent() {
        socket.on("newMessage", (args) -> {

            String discordName = (String) args[0];
            String message = (String) args[1];

            SliceConsole.INSTANCE.getBox().add("#00FFFF" + args[2] + " #FF5555(#00FFFF" + discordName + "#FF5555)" + "#00FFFF: " + message);
        });

        socket.on("connected", (args) -> runConnected());

        socket.on("usernameSet", (args) -> {
            try {
                JSONArray array = (JSONArray) args[0];
                List<String> list = new ArrayList<>();

                for (int i = 0; i < array.length(); i++) {
                    String name = array.getString(i);
                    list.add(name);
                }
                SliceConsole.INSTANCE.getIrc().setList(list);
            } catch (Exception e) {
                assert args[0] instanceof String;
                String s = (String) args[0];
                s = s.replace("[", "").replace("]", "").replace("\"", "").replace(",", "\n");
                String[] lines = s.split("\n");
                List<String> list = new ArrayList<>(Arrays.asList(lines));
                SliceConsole.INSTANCE.getIrc().setList(list);
            }
        });

        socket.on("ircConnection", (args) -> {
            String discordName = (String) args[0];

            if(discordName == null)
                return;

            SliceConsole.INSTANCE.getBox().add("#00FFFF" + discordName + "#FF5555 has connected");
        });

        socket.on("ircDisconnection", (args) -> {
            String discordName = (String) args[0];

            if(discordName == null)
                return;

            SliceConsole.INSTANCE.getBox().add("#00FFFF" + discordName + "#FF5555 has disconnected");
        });

        socket.on("disconnected", (args) -> SliceConsole.INSTANCE.getBox().add("Disconnected from the server"));

        new Thread(() -> {
            while(true) onKeepAlive();
        }).start();
    }

    /**
     * Runs when the socket is connected.
     * */
    public void runConnected() {
        socket.emit("connected", "Console", "Admin", HardwareUtil.getHardwareID());
    }

    /**
     * onKeepAlive()
     * */
    public void onKeepAlive() {
        try {
            Thread.sleep(5000L);
            socket.emit("keepAlive");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
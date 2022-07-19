package com.sliceclient.console.api;

import com.sliceclient.console.SliceConsole;
import io.socket.client.IO;
import io.socket.client.Socket;
import lombok.Getter;
import lombok.Setter;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * The class used for handling the Socket connection
 *
 * @author Nick & Dylan
 */
@Getter @Setter
public class IRC {

    /** API url */
    private static final String API_URL = "http://localhost:3001";

    private Socket socket;
    private SocketEvents socketEvents;

    private List<String> list = new ArrayList<>();


    /***
     * Connect to an IRC server.
     * */
    public IRC() {
        try {
            IO.Options options = IO.Options.builder().build();
            socket = IO.socket(URI.create(API_URL), options);
            socketEvents = new SocketEvents(socket);

            socket.on("addMessage", (args) -> SliceConsole.INSTANCE.addDisplayMessage(args[0] + ""));

            socket.connect();
        } catch (Exception ignored){}
    }

    /**
     * Sends a message to the socket server.
     *
     * @parma message The message to send to the server.
     * */
    public void sendMessage(String message) {
        if(!socket.connected()) {
            SliceConsole.INSTANCE.addDisplayMessage("Not connected to the server!");
            return;
        }

        socket.emit("message", message);
    }

    /**
     * Broadcasts a message to the server.
     * */
    public void broadcastMessage(String message) {
        if(!socket.connected()) {
            SliceConsole.INSTANCE.addDisplayMessage("Not connected to the server!");
            return;
        }

        socket.emit("broadcast", message);
    }


}

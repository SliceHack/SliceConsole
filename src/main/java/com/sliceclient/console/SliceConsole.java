package com.sliceclient.console;

import com.sliceclient.console.api.API;
import com.sliceclient.console.api.IRC;
import com.sliceclient.console.command.manager.CommandManager;
import com.sliceclient.console.frame.ScreenType;
import com.sliceclient.console.frame.ui.auth.NoAuth;
import com.sliceclient.console.frame.ui.back.Background;
import com.sliceclient.console.frame.Window;
import com.sliceclient.console.frame.ui.box.Box;
import com.sliceclient.console.frame.ui.text.TextArea;
import com.sliceclient.console.handler.KeyHandler;
import com.sliceclient.console.util.hardware.HardwareUtil;
import lombok.Getter;

import static com.sliceclient.console.util.color.ColorUtil.removeHex;

/**
 * The console of Slice
 *
 * @author Nick
 * */
@Getter
public enum SliceConsole {
    INSTANCE; /* using enum as an instance */

    /** Frame of the console */
    public final Window window;

    /** UI */
    private final Background background;
    private final TextArea textArea, textBox;
    private final Box box;
    private final NoAuth noAuth;

    /** server */
    private final IRC irc;

    /** auth */
    public final boolean auth;

    /** manager */
    private final CommandManager commandManager;

    /** The screen the user is on*/
    private ScreenType screen;

    /**
     * Constructor
     * */
    SliceConsole() {
        auth = API.sendAuthRequest();

        window = new Window("Console", 800, 600);

        if(auth) {
            noAuth = null;
            commandManager = new CommandManager();
            irc = new IRC();

            window.add(box = new Box());
            window.add(textArea = new TextArea(0, 520, 500, 20));
            window.add(textBox = new TextArea(15, 5, 750, 500));
            window.add(background = new Background(800, 600));
            window.addKeyListener(new KeyHandler());

            Runtime.getRuntime().addShutdownHook(new Thread(() -> irc.getSocket().disconnect()));
            textBox.setEnabled(false);
            window.setResizable(false);
            window.show(true);
            screen = ScreenType.CONSOLE;
            return;
        }


        irc = null;
        box = null;
        textArea = null;
        textBox = null;
        commandManager = null;
        window.add(noAuth = new NoAuth());
        window.add(background = new Background(800, 600));
        window.setResizable(false);
        window.show(true);
        screen = ScreenType.NOT_AUTH;
    }

    /**
     * Sends a message to the server
     *
     * @param message The message to send
     * */
    public void sendMessage(String message) {
        if(commandManager.handle(message))
            return;

        message = removeHex(message);

        if(message.startsWith(" ")) return;
        if(message.isEmpty()) return;


        irc.sendMessage(message);
    }

    /**
     * Adds a message to the display console area
     *
     * @param message The message to send
     * */
    public void addDisplayMessage(String message) {
        box.add(message);
    }

    /**
     * Used for updating the display
     * */
    public void update() {
        Background background = getBackground();
        background.setBounds(0, 0, getWindow().getFrame().getWidth(), getWindow().getFrame().getHeight());


        if(auth) {
            TextArea textArea = getTextArea();
            textArea.setBounds(15, 520, 750, 30);

            Box box = getBox();
            box.setBounds(15, 5, 750, 500);

            window.repaint();
            return;
        }

        NoAuth noAuth = getNoAuth();
        noAuth.setBounds(0, 0, getWindow().getFrame().getWidth(), getWindow().getFrame().getHeight());
        window.repaint();
    }
}

package com.sliceclient.console.frame;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * The window of the launcher
 * */
@Getter @Setter
@SuppressWarnings("all")
public class Window {

    /** data of the frame */
    private final String title;
    private final int width, height;

    /** The Frame */
    private JFrame frame;

    /**
     * The constructor of the window
     * @param title The title of the window
     * @param width The width of the window
     * @param height The height of the window
     */
    public Window(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
    }

    /**
     * Adds to the frame
     * */
    public void add(JComponent component) {
        frame.add(component);
    }

    /**
     * Adds a key listener to the frame
     * */
    public void addKeyListener(KeyListener listener) {
        frame.addKeyListener(listener);
    }

    /**
     * Adds a mouse listener to the frame
     * */
    public void addMouseListener(MouseListener listener) {
        frame.addMouseListener(listener);
    }

    /**
     * adds a mouse motion listener to the frame
     * */
    public void addMouseMotionListener(MouseMotionListener listener) {
        frame.addMouseMotionListener(listener);
    }

    /**
     * Shows the window
     */
    public void show(boolean visible) {
        frame.setVisible(visible);
    }

    public void setResizable(boolean resizable) {
        if(frame.isVisible()) {
            frame.dispose();
            frame.setResizable(resizable);
            frame.setVisible(true);
            return;
        }
        frame.setResizable(resizable);
    }

    /**
     * repaints the frame
     * */
    public void repaint() {
        frame.repaint();
    }

    /**
     * Sets the window to fullscreen
     * */
    public void setFullScreen(boolean fullScreen) {
        frame.dispose();
        frame.setUndecorated(fullScreen);
        frame.setResizable(!fullScreen);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getScreenDevices()[getCurrentMonitor()];
        gd.setFullScreenWindow(fullScreen ? frame : null);
        frame.setVisible(true);
    }

    public int getCurrentMonitor() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gd = ge.getScreenDevices();
        for(int i = 0; i < gd.length; i++) {
            if(gd[i].getDefaultConfiguration().getBounds().contains(frame.getLocation())) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Checks if the window is fullscreen
     * */
    public boolean isFullScreen() {
        return frame.isUndecorated();
    }
}

package com.sliceclient.console.frame.ui.back;

import com.sliceclient.console.SliceConsole;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Background extends JComponent {

    /** index of background animation */
    private int index;
    private int tick;

    public Background(int width, int height) {
        setBounds(0, 0, width, height);
    }

    /**
     * Draws the background
     */
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        String path = System.getProperty("user.home") + "//Slice//assets//launcher//background";
        path += "//" + "frame_" + format3Places(index) + "_delay-0.03s" + ".png";

        File file = new File(path);

        BufferedImage img = null;
        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // add saturation to the image
        g2d.drawImage(img, 0, 0, getWidth(), getHeight(), Color.WHITE, null);

        SliceConsole.INSTANCE.update();
        tick++;
        if(tick >= 5) {
            updateIndex();
            tick = 0;
        }
    }

    /**
     * format an int to a string with 3 digits
     * */
    public String format3Places(int places) {
        if(places < 10) return "00" + places;
        else if(places == 100) return "100";
        else if(places < 100) return "0" + places;
        else return "" + places;
    }

    /**
     * updates the background
     * count
     * */
    private void updateIndex() {
        if(index >= 215) index = 0;
        index++;
    }

}

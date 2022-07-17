package com.sliceclient.console.frame.ui.auth;

import com.sliceclient.console.SliceConsole;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

/**
 * Auth screen
 *
 * @author Nick
 * */
@Getter
public class NoAuth extends JComponent {

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Poppins-Regular", Font.PLAIN, 50));
        g2d.drawString("Not authorized", getWindowWidth() / 2 - g2d.getFontMetrics().stringWidth("Not authorized") / 2, (getWindowHeight() / 2)-50);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Poppins-Regular", Font.PLAIN, 25));
        g2d.drawString("You must be an Slice Admin to run this program", getWindowWidth() / 2 - g2d.getFontMetrics().stringWidth("You must be an Slice Admin to run this program") / 2, (getWindowHeight() / 2) );
        super.paintComponent(g);
    }

    /**
     * Gets window width
     * */
    public int getWindowWidth() {
        return SliceConsole.INSTANCE.getWindow().getWidth();
    }

    /**
     * Gets window heigt
     * */
    public int getWindowHeight() {
        return SliceConsole.INSTANCE.getWindow().getHeight();
    }
}

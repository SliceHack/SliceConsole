package com.sliceclient.console.frame.ui.text;

import lombok.Getter;
import lombok.Setter;

import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * For Round Borders
 * (might not use this)
 *
 * @author Nick
 * */
@Getter @Setter
public class RoundBorder implements Border {

    private int radius;
    private boolean qPlaque;

    public RoundBorder(int radius, boolean qPlaque) {
        this.radius = radius;
        this.qPlaque = qPlaque;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, getRadius(), getRadius()));
        g2d.dispose();
    }

    public Insets getBorderInsets(Component c) {
        int value = getRadius() / 2;
        return new Insets(value, value, value, value);
    }

    public boolean isBorderOpaque() {
        return qPlaque;
    }

}
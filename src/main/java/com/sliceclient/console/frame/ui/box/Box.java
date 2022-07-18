package com.sliceclient.console.frame.ui.box;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static com.sliceclient.console.util.color.ColorUtil.format;

/***
 * Box for text and stuff
 *
 * @author Nick
 * */
@Getter
public class Box extends JComponent {

    /** list */
    private final ArrayList<JLabel> list = new ArrayList<>();

    @Override
    protected void paintComponent(Graphics g) {

        for (Component component : getComponents()) remove(component);


        ArrayList<JLabel> list = new ArrayList<>(this.list);

        int yAdd = 5;
        for(JLabel s : list) {
            s.setFont(new Font("Arial", Font.PLAIN, 15));
            s.setForeground(Color.WHITE);
            s.setBounds(5, yAdd, getWidth() - 10, 20);
            add(s);
            yAdd += 15;
        }

        if(this.list.size() > 32) {
            this.list.remove(0);
        }

        super.paintComponent(g);
    }

    /**
     * Adds a message to the console area
     * @param s message to add
     * */
    public void add(String s) {
        list.add(new JLabel(format(s)));
    }
}

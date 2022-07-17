package com.sliceclient.console.frame.ui.box;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * Box
 * @author Nick
 * */
@Getter
public class Box extends JComponent {

    /** for hex */
    private static final Pattern pattern = Pattern.compile("#[a-fA-f0-9]{6}");

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

    /**
     * Removes hex from string
     * @param s string to remove hex from
     * */
    public String removeHex(String s) {
        Matcher match = pattern.matcher(s);

        while(match.find()) {
            String color = s.substring(match.start(), match.end());
            s = s.replace(color, "");
            match = pattern.matcher(s);
        }
        return s;
    }

    /**
     * Formats using html and hex colors
     *
     * @param message the string to format
     * @return formatted string as html
     * */
    public String format(String message) {
        // loop through the string if the character is a # add 5 to the index and check if it is a hex color
        for(int i = 0; i < message.length(); i++) {
            if(message.charAt(i) == '#') {
                if(i + 6 < message.length()) {
                    String color = message.substring(i, i + 6);
                    if(isHex(color)) {
                        message = message.replace(color, "<font color=\"" + color + "\">");
                        i += 5;
                    }
                }
            }
        }
        return message;
    }

    public boolean isHex(String s) {
        return pattern.matcher(s).matches();
    }

}

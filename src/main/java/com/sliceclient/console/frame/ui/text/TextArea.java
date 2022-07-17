package com.sliceclient.console.frame.ui.text;

import com.sliceclient.console.SliceConsole;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * TextArea for typing
 *
 * @author Nick
 * */
@Getter @Setter
public class TextArea extends JTextField {

    public TextArea(int x, int y, int width, int height) {
        setBounds(x, y, width, height);

        addActionListener(e -> {
            if(getText().isEmpty())
                return;
            SliceConsole.INSTANCE.sendMessage(getText());
            setText("");
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        setFont(new Font("Poppins", Font.PLAIN, 19));
        setBackground(new Color(0, 0, 0, 155));
        setForeground(Color.WHITE);
        setCaretColor(Color.WHITE);

        super.paintComponent(g2d);
    }
}

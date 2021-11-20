package com.Pages.BasePages;

import javax.swing.*;
import java.awt.*;

public class ImageButton extends JPanel {
    MyButton button = new MyButton();

    public ImageButton(String text, String imgPath) {
        setOpaque(false);   //透明
        setLayout(new BorderLayout());

        button.setIcon(new ImageIcon(imgPath));
        add(BorderLayout.CENTER,button);

        JPanel label = new JPanel(); label.add(new JLabel(text));
        add(BorderLayout.SOUTH, label);
    }
}

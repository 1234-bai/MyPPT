package com.Pages.BasePages;

import javax.swing.*;
import java.awt.*;

public class ImageButton extends ClearPanel {

    ClearButton button = new ClearButton();

    private static class ImageTextLabel extends JLabel{
        public ImageTextLabel(String text) {
            setForeground(Color.BLACK);
            setFont(new Font("微软雅黑", Font.PLAIN, 15));
            setText(text);
        }
    }

    public ImageButton(String text, String imgPath) {
        setLayout(new BorderLayout());

        button.setIcon(new ImageIcon(imgPath));
        add(BorderLayout.CENTER,button);

        ClearPanel label = new ClearPanel(); label.add(new ImageTextLabel(text));
        add(BorderLayout.SOUTH, label);
    }

    public ClearButton getButton() {
        return button;
    }
}

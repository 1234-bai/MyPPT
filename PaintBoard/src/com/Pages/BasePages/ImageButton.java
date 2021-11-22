package com.Pages.BasePages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.net.URL;

public class ImageButton extends ClearPanel {

    ClearButton button = new ClearButton();

    private static class ImageTextLabel extends JLabel{
        public ImageTextLabel(String text) {
            setForeground(Color.BLACK);
            setFont(new Font("微软雅黑", Font.PLAIN, 15));
            setText(text);
        }
    }

    public ImageButton(String text, URL imgPath) {
        setLayout(new BorderLayout());

        button.setCursor(new Cursor(Cursor.HAND_CURSOR));   //设置鼠标进入的是变为手型
        button.setIcon(new ImageIcon(imgPath));
        add(BorderLayout.CENTER,button);

        ClearPanel label = new ClearPanel(); label.add(new ImageTextLabel(text));
        add(BorderLayout.SOUTH, label);
    }

    public ClearButton getButton() {
        return button;
    }

    @Override
    public synchronized void addMouseListener(MouseListener l) {
        super.addMouseListener(l);
        button.addMouseListener(l);
    }
}

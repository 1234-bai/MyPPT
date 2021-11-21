package com.Pages.MenuBar;

import com.Pages.BasePages.ImageButton;
import com.Pages.BasePages.ClearPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class InsertButtonsBar extends ClearPanel {
    private final ImageButton emptyPageButton = new ImageButton("插入空白页","PaintBoard/images/MenuBar/Insert/emptyPage.png");
    private final ImageButton pictureButton = new ImageButton("插入图片","PaintBoard/images/MenuBar/Insert/picture.png");
    private final ImageButton textButton = new ImageButton("插入文字","PaintBoard/images/MenuBar/Insert/text.png");

    public InsertButtonsBar() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(emptyPageButton);
        add(pictureButton);
        add(textButton);
    }

    public JButton getEmptyPageButton() {
        return emptyPageButton.getButton();
    }

    public JButton getPictureButton() {
        return pictureButton.getButton();
    }

    public JButton getTextButton() {
        return textButton.getButton();
    }

    public void setButtonsListener(
            MouseListener emptyPageListener,
            MouseListener pictureListener,
            MouseListener textListener
    ){
        emptyPageButton.addMouseListener(emptyPageListener);
        pictureButton.addMouseListener(pictureListener);
        textButton.addMouseListener(textListener);
    }
}

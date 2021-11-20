package com.Pages.MenuBar;

import com.Pages.BasePages.ImageButton;
import com.Pages.BasePages.ClearPanel;

import javax.swing.*;
import java.awt.*;

public class InsertButtonsBar extends ClearPanel {
    private final ImageButton pictureButton = new ImageButton("插入图片","PaintBoard/images/MenuBar/Insert/picture.png");
    private final ImageButton textButton = new ImageButton("插入文字","PaintBoard/images/MenuBar/Insert/text.png");

    public InsertButtonsBar() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(pictureButton);
        add(textButton);
    }

    public JButton getPictureButton() {
        return pictureButton.getButton();
    }

    public JButton getTextButton() {
        return textButton.getButton();
    }
}

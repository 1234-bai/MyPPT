package com.Pages.MenuBar;

import com.Pages.BasePages.ImageButton;
import com.Pages.BasePages.ClearPanel;

import java.awt.*;

public class InsertButtonsBar extends ClearPanel {
    private ImageButton pictureButton = new ImageButton("插入图片","PaintBoard/images/MenuBar/Insert/picture.png");
    private ImageButton textButton = new ImageButton("插入文字","PaintBoard/images/MenuBar/Insert/text.png");

    public InsertButtonsBar() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(pictureButton);
        add(textButton);
    }

    public ImageButton getPictureButton() {
        return pictureButton;
    }

    public ImageButton getTextButton() {
        return textButton;
    }
}

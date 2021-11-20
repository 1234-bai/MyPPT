package com.Pages.MenuBar;

import com.Pages.BasePages.ImageButton;
import com.Pages.BasePages.MyPanel;

import java.awt.*;

public class FileButtonsBar extends MyPanel {

    private ImageButton saveButton = new ImageButton("打开文件","PaintBoard/images/MenuBar/File/open.png");
    private ImageButton openButton = new ImageButton("保存文件","PaintBoard/images/MenuBar/File/save.png");

    public FileButtonsBar() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(saveButton);
        add(openButton);
    }

    public ImageButton getSaveButton() {
        return saveButton;
    }

    public ImageButton getOpenButton() {
        return openButton;
    }
}

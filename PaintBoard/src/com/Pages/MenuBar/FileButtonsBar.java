package com.Pages.MenuBar;

import com.Pages.BasePages.ImageButton;
import com.Pages.BasePages.ClearPanel;

import javax.swing.*;
import java.awt.*;

public class FileButtonsBar extends ClearPanel {

    private final ImageButton saveButton = new ImageButton("打开文件","PaintBoard/images/MenuBar/File/open.png");
    private final ImageButton openButton = new ImageButton("保存文件","PaintBoard/images/MenuBar/File/save.png");

    public FileButtonsBar() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(saveButton);
        add(openButton);
    }

    public JButton getSaveButton() {
        return saveButton.getButton();
    }

    public JButton getOpenButton() {
        return openButton.getButton();
    }
}

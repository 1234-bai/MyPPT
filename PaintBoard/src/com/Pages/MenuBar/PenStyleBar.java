package com.Pages.MenuBar;

import com.Pages.BasePages.ImageButton;
import com.Pages.BasePages.ClearPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class PenStyleBar extends ClearPanel {
    private final ImageButton colorButton = new ImageButton("颜色",getRightResourceFilePath("images/MenuBar/PenStyle/colorBoard.png"));
    private final ImageButton lineWidthButton = new ImageButton("线宽",getRightResourceFilePath("images/MenuBar/PenStyle/strokewidth.png"));
    private final ImageButton fontFamilyButton = new ImageButton("字体", getRightResourceFilePath("images/MenuBar/PenStyle/fontChange.png"));
    private final ImageButton fontSizeButton = new ImageButton("字号",getRightResourceFilePath("images/MenuBar/PenStyle/fontsize.png"));

    public PenStyleBar() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(colorButton);
        add(lineWidthButton);
        add(fontFamilyButton);
        add(fontSizeButton);
    }

    public JButton getColorButton() {
        return colorButton.getButton();
    }

    public JButton getLineWidthButton() {
        return lineWidthButton.getButton();
    }

    public void setButtonsListener(
            MouseListener colorListener,
            MouseListener lineWidthListener,
            MouseListener fontFamilyListener,
            MouseListener fontSizeListener
    ){
        colorButton.addMouseListener(colorListener);
        lineWidthButton.addMouseListener(lineWidthListener);
        fontFamilyButton.addMouseListener(fontFamilyListener);
        fontSizeButton.addMouseListener(fontSizeListener);
    }
}

package com.Pages.MenuBar;

import com.Pages.BasePages.ImageButton;
import com.Pages.BasePages.ClearPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class PenStyleBar extends ClearPanel {
    private final ImageButton colorButton = new ImageButton("调整颜色","PaintBoard/images/MenuBar/PenStyle/colorBoard.png");
    private final ImageButton lineWidthButton = new ImageButton("调整线宽","PaintBoard/images/MenuBar/PenStyle/strokewidth.png");

    public PenStyleBar() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(colorButton);
        add(lineWidthButton);
    }

    public JButton getColorButton() {
        return colorButton.getButton();
    }

    public JButton getLineWidthButton() {
        return lineWidthButton.getButton();
    }

    public void setButtonsListener(MouseListener colorListener, MouseListener lineWidthListener){
        colorButton.addMouseListener(colorListener);
        lineWidthButton.addMouseListener(lineWidthListener);
    }
}

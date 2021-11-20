package com.Pages.MenuBar;

import com.Pages.BasePages.ImageButton;
import com.Pages.BasePages.ClearPanel;

import java.awt.*;

public class PenStyleBar extends ClearPanel {
    private ImageButton colorButton = new ImageButton("调整颜色","PaintBoard/images/MenuBar/PenStyle/colorBoard.png");
    private ImageButton lineWidthButton = new ImageButton("调整线宽","PaintBoard/images/MenuBar/PenStyle/strokewidth.png");

    public PenStyleBar() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(colorButton);
        add(lineWidthButton);
    }

    public ImageButton getColorButton() {
        return colorButton;
    }

    public ImageButton getLineWidthButton() {
        return lineWidthButton;
    }
}

package com.Pages.MenuBar;

import com.Pages.BasePages.ImageButton;
import com.Pages.BasePages.ClearPanel;

import java.awt.*;

public class DrawButtonsBar extends ClearPanel {

    private ImageButton lineButton = new ImageButton("直线","PaintBoard/images/MenuBar/Draw/line.png");
    private ImageButton curveButton = new ImageButton("曲线","PaintBoard/images/MenuBar/Draw/curve.png");
    private ImageButton polyButton = new ImageButton("多边形","PaintBoard/images/MenuBar/Draw/poly.png");
    private ImageButton circleButton = new ImageButton("圆形","PaintBoard/images/MenuBar/Draw/circle.png");
    private ImageButton filledCircleButton = new ImageButton("实心圆形","PaintBoard/images/MenuBar/Draw/filledCircle.png");
    private ImageButton rectButton = new ImageButton("矩形","PaintBoard/images/MenuBar/Draw/rect.png");
    private ImageButton filledRectButton = new ImageButton("实心矩形","PaintBoard/images/MenuBar/Draw/filledRect.png");

    public DrawButtonsBar() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(lineButton);
        add(curveButton);
        add(polyButton);
        add(circleButton);
        add(filledCircleButton);
        add(rectButton);
        add(filledRectButton);
    }

    public ImageButton getLineButton() {
        return lineButton;
    }

    public ImageButton getCurveButton() {
        return curveButton;
    }

    public ImageButton getPolyButton() {
        return polyButton;
    }

    public ImageButton getCircleButton() {
        return circleButton;
    }

    public ImageButton getFilledCircleButton() {
        return filledCircleButton;
    }

    public ImageButton getRectButton() {
        return rectButton;
    }

    public ImageButton getFilledRectButton() {
        return filledRectButton;
    }
}

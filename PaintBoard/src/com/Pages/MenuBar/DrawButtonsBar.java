package com.Pages.MenuBar;

import com.Pages.BasePages.ImageButton;
import com.Pages.BasePages.ClearPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class DrawButtonsBar extends ClearPanel {

    private final ImageButton lineButton = new ImageButton("直线",getClass().getResource("/images/MenuBar/Draw/line.png"));
    private final ImageButton curveButton = new ImageButton("曲线",getClass().getResource("/images/MenuBar/Draw/curve.png"));
    private final ImageButton polyButton = new ImageButton("多边形",getClass().getResource("/images/MenuBar/Draw/poly.png"));
    private final ImageButton circleButton = new ImageButton("圆形",getClass().getResource("/images/MenuBar/Draw/circle.png"));
    private final ImageButton filledCircleButton = new ImageButton("实心圆形",getClass().getResource("/images/MenuBar/Draw/filledCircle.png"));
    private final ImageButton rectButton = new ImageButton("矩形",getClass().getResource("/images/MenuBar/Draw/rect.png"));
    private final ImageButton filledRectButton = new ImageButton("实心矩形",getClass().getResource("/images/MenuBar/Draw/filledRect.png"));

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


    public JButton getLineButton() {
        return lineButton.getButton();
    }

    public JButton getCurveButton() {
        return curveButton.getButton();
    }

    public JButton getPolyButton() {
        return polyButton.getButton();
    }

    public JButton getCircleButton() {
        return circleButton.getButton();
    }

    public JButton getFilledCircleButton() {
        return filledCircleButton.getButton();
    }

    public JButton getRectButton() {
        return rectButton.getButton();
    }

    public JButton getFilledRectButton() {
        return filledRectButton.getButton();
    }

    public void setButtonsListener(
            MouseListener lineListener,
            MouseListener curveListener,
            MouseListener polyListener,
            MouseListener circleListener,
            MouseListener filledCircleListener,
            MouseListener rectangleListener,
            MouseListener filledRectListener
    ){
        lineButton.addMouseListener(lineListener);
        curveButton.addMouseListener(curveListener);
        polyButton.addMouseListener(polyListener);
        circleButton.addMouseListener(circleListener);
        filledCircleButton.addMouseListener(filledCircleListener);
        rectButton.addMouseListener(rectangleListener);
        filledRectButton.addMouseListener(filledRectListener);
    }
}

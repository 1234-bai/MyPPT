package com.MyShapes.ChildrenShapes;

import com.MyShapes.BaseShape.MyShape;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class MyCircle extends MyShape {
    private final Ellipse2D ellipse;
    private final boolean isFilled;     //封闭图形独有属性，是否填充

    public MyCircle(Ellipse2D ellipse, double coordinateX, double coordinateY, Color color, float lineWidth, boolean isFilled) {
        super(coordinateX, coordinateY, color, lineWidth);
        this.ellipse = ellipse;
        this.isFilled = isFilled;
    }

    @Override
    public Object getDrawContent() {
        return ellipse;
    }

    @Override
    public boolean contains(double x, double y) {
        return ellipse.contains(x, y);
    }

    public boolean isFilled() {
        return isFilled;
    }

    /**
     * String开头的"MyCircle"用于标识图形类型
     * Ellipse2D的恢复采用new Ellipse2D.Double(double x, double y, double w, double h)方法
     */
    @Override
    public String toString() {
        return "MyCircle" + " " +
                ellipse.getX() + " " +
                ellipse.getY() + " " +
                ellipse.getWidth() + " " +
                ellipse.getHeight() + " " +
                isFilled + " " +
                super.toString();
    }
}

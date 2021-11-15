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
}

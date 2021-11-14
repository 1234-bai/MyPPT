package com.MyShapes.ChildrenShapes;

import com.MyShapes.BaseShape.MyShape;

import java.awt.*;

public class MyRectangle extends MyShape {

    public MyRectangle(double coordinateX, double coordinateY, Color color, float lineWidth) {
        super(coordinateX, coordinateY, color, lineWidth);
    }

    @Override
    public Object getDrawContent() {
        return null;
    }

    @Override
    public boolean contains(double x, double y) {
        return false;
    }
}

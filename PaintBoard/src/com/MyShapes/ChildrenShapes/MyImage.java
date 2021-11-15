package com.MyShapes.ChildrenShapes;

import com.MyShapes.BaseShape.MyShape;

import java.awt.*;

public class MyImage extends MyShape {

    private final Image image;

    public MyImage(Image image, double coordinateX, double coordinateY, Color color, float lineWidth) {
        super(coordinateX, coordinateY, color, lineWidth);
        this.image = image;
    }

    @Override
    public Object getDrawContent() {
        return image;
    }

    @Override
    public boolean contains(double x, double y) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        return (x - coordinateX <= width) && (y - coordinateY <= height);
    }
}

package com.MyShapes.ChildrenShapes;

import com.MyShapes.BaseShape.MyShape;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class MyImage extends MyShape {

    private final Image image;

    public MyImage(Image image, double coordinateX, double coordinateY, Color color, float lineWidth) {
        super(coordinateX, coordinateY, color, lineWidth);
        this.image = image;
    }

    @Override
    public boolean contains(double x, double y) {
        x-=translateX; y-=translateY;
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        int deltaX = (int) (x -coordinateX), deltaY = (int) (y - coordinateY);
        return (deltaX >= 0 && deltaX <= width) && (deltaY >= 0 && deltaY <= height);
    }

    @Override
    protected void drawInBoard(Graphics2D g) {
        g.drawImage(image, (int)coordinateX, (int)coordinateY, null);
    }
}

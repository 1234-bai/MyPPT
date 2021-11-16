package com.MyShapes.ChildrenShapes;

import com.MyShapes.BaseShape.MyShape;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

public class MyRectangle extends MyShape{

    private final Rectangle2D rectangle;
    private final boolean isFilled;     //封闭图形独有属性，是否填充

    public MyRectangle(Rectangle2D rectangle, double coordinateX, double coordinateY, Color color, float lineWidth, boolean isFilled) {
        super(coordinateX, coordinateY, color, lineWidth);
        this.rectangle = rectangle;
        this.isFilled = isFilled;
    }

    @Override
    public Object getDrawContent() {
        return rectangle;
    }

    @Override
    public boolean contains(double x, double y) {
        return rectangle.contains(x, y);
    }

    public boolean isFilled() {
        return isFilled;
    }

    @Override
    public void drawInBoard(Graphics2D g) {
        if(isFilled){
            g.draw(rectangle);
        } else{
            g.fill(rectangle);
        }
    }
}

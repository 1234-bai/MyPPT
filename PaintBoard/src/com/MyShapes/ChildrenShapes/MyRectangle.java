package com.MyShapes.ChildrenShapes;

import com.MyShapes.BaseShape.MyShape;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

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
        return rectangle.contains(x-translateX, y-translateY);
    }

    @Override
    public void drawInBoard(Graphics2D g) {
        g.setTransform(AffineTransform.getTranslateInstance(translateX, translateY));
        if(isFilled){
            g.fill(rectangle);
        } else{
            g.draw(rectangle);
        }
        g.setTransform(new AffineTransform());
    }

    /**
     * String开头的"MyRectangle"用于标识图形类型
     * Rectangle2D的恢复采用new Rectangle2D.Double(double x, double y, double w, double h)方法
     */
    @Override
    public String toString() {
        return "MyRectangle" + " " +
                rectangle.getX() + " " +
                rectangle.getY() + " " +
                rectangle.getWidth() + " " +
                rectangle.getHeight() + " " +
                super.toString();
    }
}

package com.MyShapes.ChildrenShapes;

import com.MyShapes.BaseShape.MyShape;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class MyLine extends MyShape {

    private final Line2D line;

    public MyLine(Line2D line, Color color, float lineWidth) {
        super(line.getX1(), line.getY1(), color, lineWidth);
        this.line = line;
    }



    /**
     * 由于line不为封闭图形，所以Line2D的contains()始终返回假，随意需要自己写contains。
     * 通过斜率的方法判断
     * @param x 需要判断的点横坐标
     * @param y 需要判断的点的纵坐标
     * @return  点是否在线上
     */
    @Override
    public boolean contains(double x, double y){
        return pointInLine(line, x-translateX, y-translateY);
    }

    @Override
    protected void drawInBoard(Graphics2D g) {
        g.draw(line);
    }

    /**
     * String开头的"MyLine"用于标识图形类型
     * Line2D的恢复采用new Line2D.Double(double x1, double y1, double x2, double y2)方法
     */
    @Override
    public String toString() {
        return "MyLine" + " | " +
                line.getX1() + " | " +
                line.getY1() + " | " +
                line.getX2() + " | " +
                line.getY2() + " | " +
                super.toString();
    }


}

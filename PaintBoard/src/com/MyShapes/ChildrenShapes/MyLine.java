package com.MyShapes.ChildrenShapes;

import com.MyShapes.BaseShape.MyShape;

import java.awt.*;
import java.awt.geom.Line2D;

public class MyLine extends MyShape {

    private final Line2D line;

    public MyLine(Line2D line, Color color, float lineWidth) {
        super(line.getX1(), line.getY1(), color, lineWidth);
        this.line = line;
    }


    @Override
    public Object getDrawContent() {
        return line;
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
        return pointInLine(line, x, y);
    }

    @Override
    public void drawInBoard(Graphics2D g) {
        g.draw(line);
    }
}

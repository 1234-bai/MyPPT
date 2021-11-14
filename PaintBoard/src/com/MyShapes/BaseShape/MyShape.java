package com.MyShapes.BaseShape;

import java.awt.*;

/**
 * 记录画板上画下自己时的属性。便于再次成画
 */
public abstract class MyShape {

    protected double coordinateX, coordinateY;   //坐标
    protected Color color;    //颜色
    protected float lineWidth;    //线宽

    public MyShape(double coordinateX, double coordinateY, Color color, float lineWidth) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.color = color;
        this.lineWidth = lineWidth;
    }

    /***
     *
     * @return 真正画下的内容物
     */
    public abstract Object getDrawContent();

    public abstract boolean contains(double x, double y);

//    public abstract void drawInBoard(Graphics2D g);

    public double getCoordinateX() {
        return coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }


    public Color getColor() {
        return color;
    }


    public float getLineWidth() {
        return lineWidth;
    }

    protected final static double ZERO_DIRECT = 40.0;
    protected boolean doubleEqual(double x, double y){
        return Math.abs(x - y) <= ZERO_DIRECT;
    }
}

package com.MyShapes.BaseShape;

import java.awt.*;
import java.awt.geom.Line2D;
import java.io.Serializable;

/**
 * 记录画板上画下自己时的属性。便于再次成画
 */
public abstract class MyShape implements Serializable {

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

    protected static double ZERO_DIRECT = 10.0;
    protected static boolean doubleEqual(double x, double y){
        return Math.abs(x - y) <= ZERO_DIRECT;
    }

    /**
     * 判断给出的点是否在在线上（周围）
     * 通过斜率的方法判断
     * @param line 要判断的线
     * @param x 要判断的点的横坐标
     * @param y 要判断的点的纵坐标
     * @return 给出点在线附近，为真否则返回假
     */
    protected boolean pointInLine(Line2D line, double x, double y){
        double startX = line.getX1(), startY = line.getY1();
        double endX = line.getX2(), endY = line.getY2();
        if(startX == endX && endY == startY){   //画出来的直线是一个点
            return (startX == x && startY == y);    //判断点是否重合
        } else {    //画出来的是一条线
            if(doubleEqual(startX, endX)){  //原直线垂直
                return doubleEqual(x, startX); //判断横坐标是否相同
            } else{ //原直线不垂直
                if(doubleEqual(startX, x)){     //鼠标点击的点和第一个端点垂直
                    return doubleEqual(y, startY);  //接近第一个端点
                }
                double k = (endY - startY) / (endX - startX);   //计算直线斜率
                double k1 = (y - startY) / (x - startX);    //计算鼠标线斜率
                return doubleEqual(k, k1) && ((x - startX)*(x - endX) <= ZERO_DIRECT && (y - endY)*(y - startY) <= ZERO_DIRECT);
            }
        }
    }

    /**
     * 将所有属性转成String，用于保存画板
     * 末尾加入换行是为了方便按行读取文件，保证每行一个图形
     *
     * @return 包含所有属性的String
     */
    @Override
    public String toString() {
        return coordinateX + " " +
                coordinateY + " " +
                color.getRGB() + " " +
                lineWidth + "\r\n";
    }
}

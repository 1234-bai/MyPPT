package com.MyShapes.ChildrenShapes;

import com.MyShapes.BaseShape.MyShape;

import java.awt.*;
import java.awt.geom.Line2D;

public class MyLine extends MyShape {
    private final Line2D line2D;

    public MyLine(Line2D line, Color color, float lineWidth) {
        super(line.getX1(), line.getY1(), color, lineWidth);
        line2D = line;
    }


    @Override
    public Object getDrawContent() {
        return line2D;
    }



    /**
     * 由于line不为封闭图形，所以Line2D的contains()始终返回假，随意需要自己写contains。
     * 通过斜率的方法判断
     * @param x 需要判断的点横坐标
     * @param y 需要判断的点的纵坐标
     * @return  点是否在线上
     */
    @Override
    public boolean contains(double x, double y){    //直接算距离得了。。
        double startX = line2D.getX1(), startY = line2D.getY1();
        double endX = line2D.getX2(), endY = line2D.getY2();
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

}

package com.Listeners;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

public class PolygonListener extends DrawListener {

    private static final int lastDict = 10;

    int[] x = new int[20];  //x坐标集合
    int[] y = new int[20];  //y坐标集合
    int n = 0;    //点的个数


    @Override
    public void mouseClicked(MouseEvent e) {
        //记录点坐标
        x[n] = e.getX();
        y[n] = e.getY();
        n++;

        //测试用，控制台显示鼠标点击的坐标
        System.out.println(x[n - 1] + ", " + y[n - 1]);

        //当前点与第一个点的距离在8以内，则形成收尾相连的多边形
        if ((x[n - 1] - x[0]) * (x[n - 1] - x[0]) + (y[n - 1] - y[0]) * (y[n - 1] - y[0]) <= lastDict*lastDict  && n > 2) {
            x[n - 1] = x[0];
            y[n - 1] = y[0];
            Polygon polygon = new Polygon(x, y, n);
            getDrawBoard().refresh();  //最后一次选点多画的线删掉
            super.getListenerPen().draw(polygon);
            super.getListenerPen_copy().draw(polygon);
            n = 0;
        }

        //将点连线
        if(n>1){
            Line2D line = new Line2D.Double(x[n - 2],y[n - 2],x[n - 1],y[n - 1]);
            getListenerPen().draw(line);
            getListenerPen_copy().draw(line);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //当多边形未完成时线条随鼠标移动
        if (n != 0) {
            getDrawBoard().refresh();
            Line2D line = new Line2D.Double(x[n - 1], y[n - 1], e.getX(), e.getY());
            getListenerPen().draw(line);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

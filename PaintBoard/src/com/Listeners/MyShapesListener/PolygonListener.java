package com.Listeners.MyShapesListener;

import com.Listeners.BaseListener.DrawListener;
import com.MyShapes.ChildrenShapes.MyPolygon;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.Vector;

public class PolygonListener extends DrawListener {

    private static final int lastDict = 10; //判定构成多边形的距离参数

    Vector<Integer> x = new Vector<>();  //x坐标集合
    Vector<Integer> y = new Vector<>();  //y坐标集合
    int n = 0;    //点的个数


    @Override
    public void mousePressed(MouseEvent e) {
        //记录点击的点坐标
        int nowX = e.getX(), nowY = e.getY();
        x.add(nowX); y.add(nowY);
        n++;

        //测试用，控制台显示鼠标点击的坐标
        System.out.println(nowX + ", " + nowY);

        //当前点与第一个点的距离在lastDict以内，则形成收尾相连的多边形
        int startX = x.get(0), startY = y.get(0);
        if ((nowX - startX) * (nowX - startX) + (nowY - startY) * (nowY - startY) <= lastDict*lastDict  && n > 2) {

            //最后一个点与第一个点坐标相同
            x.set(n - 1, startX);
            y.set(n - 1, startY);


            getDrawBoard().refresh();  //最后一次选点多画的线删掉
            //画出多边形最后一条线
            Line2D line = new Line2D.Double(x.get(n - 2), y.get(n - 2), startX, startY);
            getListenerPen().draw(line);
            getListenerPen_copy().draw(line);

            try{    //将画的的多边形送入图形栈
                getContentsGroup().add(new MyPolygon(
                        x,
                        y,
                        getListenerPen().getColor(),
                        ((BasicStroke) getListenerPen().getStroke()).getLineWidth()
                ));
            } catch (IndexOutOfBoundsException ex){
                System.out.println("多边形越界");
                ex.printStackTrace();
            }

            n = 0;
            x.clear(); y.clear();
        }

        //将点连线
        if(n>1){
            Line2D line = new Line2D.Double(x.get(n - 2), y.get(n - 2), nowX, nowY);
            getListenerPen().draw(line);
            getListenerPen_copy().draw(line);
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {

        //当多边形未完成时线条随鼠标移动
        if (n != 0) {
            getDrawBoard().refresh();
            Line2D line = new Line2D.Double(x.get(n - 1), y.get(n - 1), e.getX(), e.getY());
            getListenerPen().draw(line);
        }
    }

}

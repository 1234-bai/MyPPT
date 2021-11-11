package com.Listeners.ChildrenListener;

import com.Listeners.ParentListener.DrawListener;

import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

public class CurveListener extends DrawListener {

    int startX, startY;
    int nextX, nextY;

    @Override
    public void mousePressed(MouseEvent e) {
        startX = e.getX(); startY = e.getY();   //获得起始坐标。
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        nextX = e.getX(); nextY = e.getY(); //获得此时拖动过程中的鼠标坐标。
        Line2D line = new Line2D.Double(startX, startY, nextX, nextY);
        getListenerPen().draw(line); getListenerPen_copy().draw(line);  //在画板上作画
        startX = nextX; startY = nextY;
    }

}

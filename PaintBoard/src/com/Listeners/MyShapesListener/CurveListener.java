package com.Listeners.MyShapesListener;

import com.Listeners.BaseListener.DrawListener;
import com.MyShapes.ChildrenShapes.MyCurve;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

public class CurveListener extends DrawListener {

    int startX, startY;
    int nextX, nextY;
    MyCurve curve;

    @Override
    public void mousePressed(MouseEvent e) {
        startX = e.getX(); startY = e.getY();   //获得起始坐标。
        curve = new MyCurve(
                startX,
                startY,
                getListenerPen().getColor(),
                ((BasicStroke)getListenerPen().getStroke()).getLineWidth()
        );
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        nextX = e.getX(); nextY = e.getY(); //获得此时拖动过程中的鼠标坐标。
        Line2D line = new Line2D.Double(startX, startY, nextX, nextY);
        getListenerPen().draw(line); getListenerPen_copy().draw(line);  //在画板上作画
        curve.add(line);
        startX = nextX; startY = nextY;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        getContentsGroup().add(curve);
    }

}

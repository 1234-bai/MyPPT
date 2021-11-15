package com.Listeners.MyShapesListener;

import com.Listeners.BaseListener.DrawListener;
import com.MyShapes.ChildrenShapes.MyLine;


import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;


public class LineListener extends DrawListener {

    int startX, startY;
    int endX, endY;

    @Override
    public void mousePressed(MouseEvent e) {

        //记录起点位置
        startX = e.getX();
        startY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        //记录终点位置
        endX = e.getX();
        endY = e.getY();

        Line2D line = new Line2D.Double(startX, startY, endX, endY);
        getListenerPen().draw(line);
        getListenerPen_copy().draw(line);
        getContentsGroup().add(new MyLine(
                line,
                getListenerPen().getColor(),
                ((BasicStroke) getListenerPen().getStroke()).getLineWidth()
        ));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        getDrawBoard().refresh();

        //记录终点位置
        endX = e.getX();
        endY = e.getY();

        Line2D line = new Line2D.Double(startX, startY, endX, endY);
        getListenerPen().draw(line);
    }


}

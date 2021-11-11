package com.Listeners.ChildrenListener;


import com.Listeners.ParentListener.DrawListener;

import java.awt.event.*;
import java.awt.geom.Ellipse2D;

public class CircleListener extends DrawListener {

    //private Graphics g;
    int startX, startY;
    int endX, endY;

    @Override
    public void mousePressed(MouseEvent e) {
        startX = e.getX(); startY = e.getY();   //记录起点位置
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endX = e.getX(); endY = e.getY();   //记录终点位置
        Ellipse2D ellipse = new Ellipse2D.Double(startX, startY, endX-startX, endY-startY);
        super.getListenerPen().draw(ellipse);
        super.getListenerPen_copy().draw(ellipse);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        getDrawBoard().refresh();
        endX = e.getX(); endY = e.getY();   //记录终点位置
        Ellipse2D ellipse = new Ellipse2D.Double(startX, startY, endX-startX, endY-startY);
        getListenerPen().draw(ellipse);
    }
}

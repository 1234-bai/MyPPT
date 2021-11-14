package com.Listeners.ContentListener;

import com.Listeners.BaseListener.DrawListener;

import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class RectangleListener extends DrawListener {
    int startX, startY;
    int endX, endY;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        startX = e.getX(); startY = e.getY();   //记录起点位置
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endX = e.getX(); endY = e.getY();   //记录终点位置
        Rectangle2D rectangle = new Rectangle2D.Double(startX, startY, endX-startX, endY-startY);
        super.getListenerPen().draw(rectangle);
        super.getListenerPen_copy().draw(rectangle);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        getDrawBoard().refresh();
        endX = e.getX(); endY = e.getY();   //记录终点位置
        Rectangle2D rectangle = new Rectangle2D.Double(startX, startY, endX-startX, endY-startY);
        getListenerPen().draw(rectangle);
    }

}

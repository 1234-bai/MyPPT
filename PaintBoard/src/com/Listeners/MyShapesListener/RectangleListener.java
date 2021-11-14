package com.Listeners.MyShapesListener;

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
        System.out.print(startX+" ");
        System.out.print(startY+" ");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endX = e.getX(); endY = e.getY();   //记录终点位置
        System.out.print(endX+" ");
        System.out.print(endY+" ");
        if (startX < endX){
            Rectangle2D rectangle = new Rectangle2D.Double(startX, startY, endX-startX, endY-startY);
            super.getListenerPen().draw(rectangle);
            super.getListenerPen_copy().draw(rectangle);
        }
        else {
            Rectangle2D rectangle = new Rectangle2D.Double(endX, endY, startX-endX, startY-endY);
            super.getListenerPen().draw(rectangle);
            super.getListenerPen_copy().draw(rectangle);
        }


    }

    @Override
    public void mouseDragged(MouseEvent e) {
        getDrawBoard().refresh();
        endX = e.getX(); endY = e.getY();   //记录终点位置
        if (startX < endX){
            Rectangle2D rectangle = new Rectangle2D.Double(startX, startY, endX-startX, endY-startY);
            super.getListenerPen().draw(rectangle);
        }
        else {
            Rectangle2D rectangle = new Rectangle2D.Double(endX, endY, startX-endX, startY-endY);
            super.getListenerPen().draw(rectangle);
        }
    }

}

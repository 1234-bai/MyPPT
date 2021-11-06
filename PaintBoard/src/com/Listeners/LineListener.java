package com.Listeners;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;

public class LineListener extends DrawListener{

    //想办法把此监听器画的图形传出去
    int startX, startY;
    int endX, endY;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        startX = e.getX(); startY = e.getY();   //记录起点位置
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endX = e.getX(); endY = e.getY();   //记录终点位置
        Line2D line = new Line2D.Double(startX, startY, endX, endY);
        super.getListenerPen().draw(line);
        super.getListenerPenCopy().draw(line);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        getListenerPen().drawImage(getDrawBoard().getDrawBoardCopy(), 0 ,0, null);
        endX = e.getX(); endY = e.getY();   //记录终点位置
        Line2D line = new Line2D.Double(startX, startY, endX, endY);
        getListenerPen().draw(line);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
    }
}

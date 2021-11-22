package com.Listeners.MyShapesListener;

import com.Listeners.BaseListener.DrawListener;
import com.MyShapes.ChildrenShapes.MyLine;
import com.Operations.ChildOperation.DrawShape;

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

        //画出图形
        Line2D line = new Line2D.Double(startX, startY, endX, endY);
        getListenerPen().draw(line);
        getListenerPen_copy().draw(line);
        getContentsGroup().add(new MyLine(
                line,
                getListenerPen().getColor(),
                ((BasicStroke) getListenerPen().getStroke()).getLineWidth()
        ));

        //记录操作
        DrawShape drawShape = new DrawShape();
        drawShape.addOperation(getDrawBoard());
    }

    @Override
    public void mouseDragged(MouseEvent e) {    //鼠标拖动时显示动态效果
        getDrawBoard().refresh();   //刷新画板

        //记录终点位置
        endX = e.getX();
        endY = e.getY();

        Line2D line = new Line2D.Double(startX, startY, endX, endY);
        getListenerPen().draw(line);
    }
}

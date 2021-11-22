package com.Listeners.MyShapesListener;


import com.Listeners.BaseListener.DrawListener;
import com.MyShapes.ChildrenShapes.MyCircle;
import com.Operations.ChildOperation.DrawShape;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

public class CircleListener extends DrawListener {

    int originX, originY;
    int startX, startY;
    int endX, endY;
    boolean isFilled;

    Ellipse2D ellipse;

    public CircleListener(boolean isFilled) {
        this.isFilled = isFilled;
    }

    //最后鼠标释放的点B和最开始点击的点A可能不是A左上B右下的关系（人眼来看，实际上在画板的坐标系里是A右下，B左上）
    //但是只有A左上B右下的情况才能画出图形，所以调整一下
    private void swapXY(){
        if (startX < endX){
            if(startY > endY){
                int temp = startY;
                startY = endY;
                endY = temp;
            }
        } else{
            int temp = startX;
            startX = endX;
            endX = temp;
            if(startY > endY){
                temp = startY;
                startY = endY;
                endY = temp;
            }
        }
    }

    private void drawCircle(MouseEvent e, boolean need_backward){
        endX = e.getX(); endY = e.getY();   //记录终点位置
        startX = originX; startY = originY;
        swapXY();
        ellipse = new Ellipse2D.Double(startX, startY, endX-startX, endY-startY);
        if(isFilled){
            getListenerPen().fill(ellipse);
            if(need_backward){
                getListenerPen_copy().fill(ellipse);
            }
        } else{
            getListenerPen().draw(ellipse);
            if(need_backward){
                getListenerPen_copy().draw(ellipse);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        originX = e.getX(); originY = e.getY();
        startX = originX; startY = originY;   //记录起点位置
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        drawCircle(e, true);

        getContentsGroup().add(new MyCircle(
                ellipse,
                startX,
                startY,
                getListenerPen().getColor(),
                ((BasicStroke)getListenerPen().getStroke()).getLineWidth(),
                isFilled
        ));

        //记录操作
        DrawShape drawShape = new DrawShape();
        drawShape.addOperation(getDrawBoard());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        getDrawBoard().refresh();
        drawCircle(e, false);
    }
}

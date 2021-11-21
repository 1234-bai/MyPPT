package com.Listeners.MyShapesListener;

import com.Listeners.BaseListener.DrawListener;
import com.MyShapes.ChildrenShapes.MyRectangle;
import com.Operations.ChildOperation.DrawShape;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class RectangleListener extends DrawListener {

    int originX, originY;
    int startX, startY;
    int endX, endY;
    boolean isFilled;
    Rectangle2D rectangle;

    public RectangleListener(boolean isFilled) {
        this.isFilled = isFilled;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startX = e.getX(); startY = e.getY();   //记录起点位置
        originX = startX; originY = startY;
//        System.out.println("矩形点击："+startX+","+startY);
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

    private void drawRectangle(MouseEvent e, boolean need_backward){
        endX = e.getX(); endY = e.getY();   //记录终点位置
//        System.out.println("矩形释放:"+endX+","+endY);
        startX = originX; startY = originY;
        swapXY();
        rectangle = new Rectangle2D.Double(startX, startY, endX-startX, endY-startY);
        if(isFilled){
            getListenerPen().fill(rectangle);
            if(need_backward){
                getListenerPen_copy().fill(rectangle);
            }
        } else{
            getListenerPen().draw(rectangle);
            if(need_backward) {
                getListenerPen_copy().draw(rectangle);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        drawRectangle(e, true);

        //加进图形栈
        getContentsGroup().add(new MyRectangle(
                rectangle,
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
        drawRectangle(e, false);
    }

}

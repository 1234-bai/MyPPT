package com.Listeners.BaseListener;

import com.MyShapes.BaseShape.MyShape;
import com.Paint.DrawJPanel;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * 同画板绑定的鼠标监听器。
 */
public class DrawListener extends MouseAdapter {

    private DrawJPanel drawBoard;  //同此监听器绑定的画板

    protected DrawJPanel getDrawBoard() {
        return drawBoard;
    }

    /**
     * 一定注意本方法的执行时机，一定要在画板的初始化后执行。一般供给画板的`setBoardListener()`执行
     * @param drawBoard 绑定的画板
     */
    public void setDrawBoard(DrawJPanel drawBoard) {
        this.drawBoard = drawBoard;
    }

    /**
     *
     * @return 获得本监听器画笔，一般供子类调用
     */
    protected Graphics2D getListenerPen() {
        return drawBoard.getDrawBoardPen();
    }

    /**
     *
     * @return 获得本监听器画笔副本，一般供子类调用
     */
    protected Graphics2D getListenerPen_copy() {
        return drawBoard.getDrawBoardPen_copy();
    }

    protected CopyOnWriteArrayList<MyShape> getContentsGroup() {
        return drawBoard.getContentsGroup();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        drawBoard.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    }
}

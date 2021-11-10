package com.Listeners;

import com.Paint.DrawJPanel;
import java.awt.*;
import java.awt.event.*;

public abstract class DrawListener  extends MouseAdapter implements ActionListener {

    private DrawJPanel drawBoard;  //同此监听器绑定的画板
    //本监听器画的图形，用于本图形的删除和重绘，在图形的相同位置，用背景色画相同图形，但有产生了其它问题：
    //当更换背景颜色的时候，这些图形又会显现出来

    public DrawJPanel getDrawBoard() {
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
    Graphics2D getListenerPen() {
        return drawBoard.getDrawBoardPen();
    }

    /**
     *
     * @return 获得本监听器画笔副本，一般供子类调用
     */
    Graphics2D getListenerPen_copy() {
        return drawBoard.getDrawBoardPen_copy();
    }

}

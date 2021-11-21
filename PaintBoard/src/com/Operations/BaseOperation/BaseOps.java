package com.Operations.BaseOperation;

import com.Paint.DrawJPanel;

import java.awt.*;

public class BaseOps {

    /**
     * 执行操作时将操作行为压栈
     *
     * @param drawBoard 操作所在的画板
     */
    public void addOperation(DrawJPanel drawBoard) {

    }

    /**
     * 执行操作时将操作行为压栈
     *
     * @param drawBoard  操作所在的画板
     * @param translateX 偏移量X
     * @param translateY 偏移量Y
     */
    public void addOperation(DrawJPanel drawBoard, double translateX, double translateY) {

    }

    /**
     * 执行操作时将操作行为压栈
     *
     * @param drawBoard 操作所在的画板
     * @param color     颜色
     */
    public void addOperation(DrawJPanel drawBoard, Color color) {

    }

    /**
     * 清空图形栈以外的所有栈
     * @param drawBoard 操作所在的画板
     */
    public void clearOperation(DrawJPanel drawBoard) {
        drawBoard.clearOperations();
        drawBoard.clearRedoOperations();
        drawBoard.clearRedoContentsGroup();
    }

    /**
     * 执行撤销操作时的处理
     *
     * @param drawBoard 操作所在的画板
     */
    public void revoke(DrawJPanel drawBoard) {

    }

    /**
     * 执行重做操作时的处理
     *
     * @param drawBoard 操作所在的画板
     */
    public void redo(DrawJPanel drawBoard) {

    }
}

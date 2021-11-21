package com.Operations.BaseOperation;

import com.Paint.DrawJPanel;

public abstract class BaseOps {

    /**
     * 执行操作时将操作行为压栈
     *
     * @param drawBoard 操作所在的画板
     */
    public abstract void addOperation(DrawJPanel drawBoard);

    /**
     * 执行撤销操作时的处理
     *
     * @param drawBoard 操作所在的画板
     */
    public abstract void revoke(DrawJPanel drawBoard);

    /**
     * 执行重做操作时的处理
     *
     * @param drawBoard 操作所在的画板
     */
    public abstract void redo(DrawJPanel drawBoard);
}

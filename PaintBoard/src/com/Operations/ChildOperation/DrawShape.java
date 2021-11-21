package com.Operations.ChildOperation;

import com.Operations.BaseOperation.BaseOps;
import com.Paint.DrawJPanel;

public class DrawShape extends BaseOps {

    @Override
    public void addOperation(DrawJPanel drawBoard) {
        drawBoard.operations_Push("DrawShape");

        //执行新操作时，重做操作栈以及重做图形栈清空
        if (drawBoard.getRedoOperations().size() != 0) {
            drawBoard.clearRedoOperations();
            drawBoard.clearRedoContentsGroup();
        }
    }

    @Override
    public void revoke(DrawJPanel drawBoard) {

        //图形移入重做图形栈
        drawBoard.redoContentsGroup_Push(drawBoard.contentsGroup_Pop());

        //操作移入重做操作栈
        drawBoard.redoOperations_Push(drawBoard.operations_Pop());

        //刷新页面
        drawBoard.redraw();
        drawBoard.refresh();
    }

    @Override
    public void redo(DrawJPanel drawBoard) {

        //图形移入图形栈
        drawBoard.contentsGroup_Push(drawBoard.redoContentsGroup_Pop());

        //操作移入操作栈
        drawBoard.operations_Push(drawBoard.redoOperations_Pop());

        //刷新页面
        drawBoard.redraw();
        drawBoard.refresh();
    }
}

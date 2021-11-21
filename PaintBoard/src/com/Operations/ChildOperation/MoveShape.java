package com.Operations.ChildOperation;

import com.Operations.BaseOperation.BaseOps;
import com.Paint.DrawJPanel;

public class MoveShape extends BaseOps {

    @Override
    public void addOperation(DrawJPanel drawBoard) {
        drawBoard.operations_Push("MoveShape");

        //执行新操作时，重做操作栈以及重做图形栈清空
        if (drawBoard.getRedoOperations().size() != 0) {
            drawBoard.clearRedoOperations();
            drawBoard.clearRedoContentsGroup();
        }
    }

    @Override
    public void revoke(DrawJPanel drawBoard) {

    }

    @Override
    public void redo(DrawJPanel drawBoard) {

    }
}

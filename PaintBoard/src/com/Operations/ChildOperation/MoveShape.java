package com.Operations.ChildOperation;

import com.MyShapes.BaseShape.MyShape;
import com.Operations.BaseOperation.BaseOps;
import com.Paint.DrawJPanel;

public class MoveShape extends BaseOps {

    @Override
    public void addOperation(DrawJPanel drawBoard, double translateX, double translateY) {

        //由于选取图像后的操作会改变图形栈的图形次序，为避免操作应用到错误的对象上，故清空操作栈
        //意味着选取图像后重做只能返回到现在的操作前，再之前的操作无法重做
        drawBoard.clearOperations();

        //逆序压栈
        drawBoard.operations_Push(String.valueOf(translateY));
        drawBoard.operations_Push(String.valueOf(translateX));
        drawBoard.operations_Push("MoveShape");

        //执行新操作时，重做操作栈以及重做图形栈清空
        if (drawBoard.getRedoOperations().size() != 0) {
            drawBoard.clearRedoOperations();
            drawBoard.clearRedoContentsGroup();
        }
    }

    @Override
    public void revoke(DrawJPanel drawBoard) {

        //将图形的偏移量移入重做操作栈
        MyShape myShape = drawBoard.contentsGroup_Pop();
        drawBoard.redoOperations_Push(String.valueOf(myShape.getTranslateY()));
        drawBoard.redoOperations_Push(String.valueOf(myShape.getTranslateX()));
        drawBoard.redoOperations_Push(drawBoard.operations_Pop());

        //修改图形的偏移量
        myShape.setTranslateX(Double.parseDouble(drawBoard.operations_Pop()));
        myShape.setTranslateY(Double.parseDouble(drawBoard.operations_Pop()));
        drawBoard.contentsGroup_Push(myShape);

        //刷新页面
        drawBoard.redraw();
        drawBoard.refresh();
    }

    @Override
    public void redo(DrawJPanel drawBoard) {

        //将图形的偏移量移入操作栈
        MyShape myShape = drawBoard.contentsGroup_Pop();
        drawBoard.operations_Push(String.valueOf(myShape.getTranslateY()));
        drawBoard.operations_Push(String.valueOf(myShape.getTranslateX()));
        drawBoard.operations_Push(drawBoard.redoOperations_Pop());

        //修改图形的偏移量
        myShape.setTranslateX(Double.parseDouble(drawBoard.redoOperations_Pop()));
        myShape.setTranslateY(Double.parseDouble(drawBoard.redoOperations_Pop()));
        drawBoard.contentsGroup_Push(myShape);

        //刷新页面
        drawBoard.redraw();
        drawBoard.refresh();
    }
}

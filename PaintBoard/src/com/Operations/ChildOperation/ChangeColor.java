package com.Operations.ChildOperation;

import com.MyShapes.BaseShape.MyShape;
import com.Operations.BaseOperation.BaseOps;
import com.Paint.DrawJPanel;

import java.awt.*;

/**
 * 受程序逻辑限制，相关功能无法继续实现，该类在整个工程中没有使用
 */
public class ChangeColor extends BaseOps {

    @Override
    public void addOperation(DrawJPanel drawBoard, Color color) {

        //由于选取图像后的操作会改变图形栈的图形次序，为避免操作应用到错误的对象上，故清空操作栈
        //意味着选取图像后重做只能返回到现在的操作前，再之前的操作无法重做
        drawBoard.clearOperations();

        //逆序压栈
        drawBoard.operations_Push(String.valueOf(color.getRGB()));
        drawBoard.operations_Push("ChangeColor");

        //执行新操作时，重做操作栈以及重做图形栈清空
        if (drawBoard.getRedoOperations().size() != 0) {
            drawBoard.clearRedoOperations();
            drawBoard.clearRedoContentsGroup();
        }
    }

    @Override
    public void revoke(DrawJPanel drawBoard) {

        //将图形的颜色移入重做操作栈
        MyShape myShape = drawBoard.contentsGroup_Pop();
        drawBoard.redoOperations_Push(String.valueOf(myShape.getColor().getRGB()));
        drawBoard.redoOperations_Push(drawBoard.operations_Pop());

        //修改图形的颜色
        myShape.setColor(new Color(Integer.parseInt(drawBoard.operations_Pop())));
        drawBoard.contentsGroup_Push(myShape);

        //刷新页面
        drawBoard.redraw();
        drawBoard.refresh();
    }

    @Override
    public void redo(DrawJPanel drawBoard) {

        //将图形的颜色移入操作栈
        MyShape myShape = drawBoard.contentsGroup_Pop();
        drawBoard.operations_Push(String.valueOf(myShape.getColor().getRGB()));
        drawBoard.operations_Push(drawBoard.redoOperations_Pop());

        //修改图形的颜色
        myShape.setColor(new Color(Integer.parseInt(drawBoard.redoOperations_Pop())));
        drawBoard.contentsGroup_Push(myShape);

        //刷新页面
        drawBoard.redraw();
        drawBoard.refresh();
    }

}

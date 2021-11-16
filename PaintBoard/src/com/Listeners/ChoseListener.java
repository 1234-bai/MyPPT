package com.Listeners;

import com.Listeners.BaseListener.DrawListener;
import com.MyShapes.BaseShape.MyShape;
import com.MyShapes.ChildrenShapes.MyText;
import com.Paint.DrawJPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ChoseListener extends DrawListener implements KeyListener {

    private MyShape chosenContent = null;   //鼠标点击选中的对象
    private int startX = 0, startY = 0;  //最开始点击的坐标
    private int preX = 0, preY = 0 ;

   //清空之前选中的图形
   private void clearChoseContent(){
        chosenContent = null;
        startX = startY = 0;
        preX = preY = 0;
   }

    @Override
    public void mousePressed(MouseEvent e) {
        clearChoseContent();
        int x = e.getX(), y = e.getY();
        System.out.println(x + "," + y);
        ArrayList<MyShape> contentsGroup = getContentsGroup();
        int length = contentsGroup.size();
        for (int i = length - 1; i >= 0; i--) { //从栈顶开始查询
            MyShape myShape = contentsGroup.get(i);  //获得的是MyShape类
            if(myShape.contains(x, y)){
                startX = x; startY = y; preX = startX; preY = startY;
                chosenContent = myShape;
                contentsGroup.remove(i);
                getDrawBoard().redraw();  //画板重画
                break;
            }
        }
        if(chosenContent != null){     //在原本上重画此图形，但还没有加入图形栈
            //设置画笔样式
            DrawJPanel drawBoard = getDrawBoard();
            drawBoard.setPenStyle(chosenContent.getColor()); // 设置画笔颜色
            drawBoard.setPenStyle(chosenContent.getLineWidth());    //设置画笔线宽
            if(chosenContent instanceof MyText){    //如果是文本则设置字体
                drawBoard.setTextFont(((MyText) chosenContent).getFont());
            }
            chosenContent.drawInBoard(getListenerPen());    //重画
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(chosenContent != null){
            int x = e.getX(), y = e.getY();
            getDrawBoard().refresh();
            chosenContent.translate(x - preX,y - preY);
            chosenContent.drawInBoard(getListenerPen());
            preX = x; preY = y;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       if(chosenContent != null){
           mouseDragged(e);
           chosenContent.drawInBoard(getListenerPen_copy());
           getContentsGroup().add(chosenContent);
       }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println("按下了"+e.getKeyCode()+e.getKeyChar());
        if(keyCode == 24){  //键盘上箭头。则图形向上移动
            System.out.println(24);
        } else if(keyCode == 25) {    //键盘下箭头
            System.out.println(25);
        } else if(keyCode == 26){   //左箭头
            System.out.println(26);
        } else if(keyCode == 27) {   //右箭头
            System.out.println(27);
        } else if(keyCode == 10){   //回车
            System.out.println(10);
            chosenContent.drawInBoard(getListenerPen_copy());
            clearChoseContent();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

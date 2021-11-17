package com.Listeners;

import com.Listeners.BaseListener.DrawListener;
import com.MyShapes.BaseShape.MyShape;
import com.MyShapes.ChildrenShapes.MyText;
import com.Paint.DrawJPanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ChoseListener extends DrawListener implements KeyListener, ChoseListenerIml{

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
        System.out.println("按下了鼠标");
        clearChoseContent();
        int x = e.getX(), y = e.getY();
        System.out.println(x + "," + y);
        ArrayList<MyShape> contentsGroup = getContentsGroup();
        int length = contentsGroup.size();
        for (int i = length - 1; i >= 0; i--) { //从栈顶开始查询
            MyShape myShape = contentsGroup.get(i);  //获得的是MyShape类
            if(myShape.contains(x, y)){
                startX = x; startY = y; preX = startX; preY = startY;
                chosenContent = myShape;    //提取出选中的图形
                contentsGroup.remove(i);
                getDrawBoard().redraw();  //画板重画。重画后除了选中的图形，其余图形全部出现在副本上，但是原本还没有刷新，仍然能看到选中的图形
                break;
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(chosenContent != null){
            int x = e.getX(), y = e.getY();
            translateChoseContent(x - preX, y - preY);
            preX = x; preY = y;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       if(chosenContent != null){
             saveChoseContent();
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

    @Override
    public void translateChoseContent(int tx, int ty) {
        chosenContent.translate(tx,ty);
        getDrawBoard().refresh();
        chosenContent.drawInBoard(getListenerPen());
    }

    @Override
    public void deleteChoseContent() {  //因为没有保存在副本中，所以刷新副本让图形不再呈现在眼前就可以了。
        getDrawBoard().refresh();
    }

    @Override
    public void setChoseContentColor(Color newColor) {
        DrawJPanel drawBoard = getDrawBoard();
        Color oldColor = drawBoard.getPenColor();    //原来的颜色样式
        drawBoard.setPenStyle(newColor);
        chosenContent.drawInBoard(getListenerPen());     //肉眼看到的再画一遍，覆盖掉原来的部分。
        chosenContent.setColor(newColor);   //改变图形内容
        saveChoseContent();     //储存画的内容
        drawBoard.setPenStyle(oldColor);    //修改会原来的画笔样式
    }

    @Override
    public void setChoseContentLineWidth(float newLineWidth) {
        DrawJPanel drawBoard = getDrawBoard();
        float oldLW = drawBoard.getPenLineWidth();    //原来的画笔线宽
        drawBoard.setPenStyle(newLineWidth);
        chosenContent.drawInBoard(getListenerPen());     //肉眼看到的再画一遍，覆盖掉原来的部分。不用载入副本的
        chosenContent.setLineWidth(newLineWidth);   //改变图形内容
        saveChoseContent();     //储存画的内容
        drawBoard.setPenStyle(oldLW);    //修改会原来的画笔样式
    }

    @Override
    public void saveChoseContent() {
        chosenContent.drawInBoard(getListenerPen_copy());
        getContentsGroup().add(chosenContent);
    }
}

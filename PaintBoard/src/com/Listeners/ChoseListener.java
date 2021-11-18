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

/**
 * 能够实现的功能：选择图形，选中后改变样式。但是改变样式的菜单没有写。只有在
 */
public class ChoseListener extends DrawListener implements ChoseListenerIml{

    private MyShape chosenContent = null;   //鼠标点击选中的对象

    private int preX = 0, preY = 0 ;

    private void clearContent(){
        if(chosenContent != null){  //保存之前选中的图形
            saveChoseContent();
            chosenContent = null;
        }
    }

    /**
     * 完成右击显示菜单栏的任务
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON3){    //右击
            //显示右击菜单
        }
    }

    /**
     * 鼠标点击按下去
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        clearContent();
        preX = preY = 0;
        int x = e.getX(), y = e.getY();
        if(choseContent(x, y)){
            choseStatus();
            preX = x; preY = y;
        }
    }

    /**
     * 鼠标点击拖动
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if(chosenContent != null){
            int x = e.getX(), y = e.getY();
            translateChoseContent(x - preX, y - preY);
            preX = x; preY = y;
        }
    }


    /**
     * 用来做一些收尾工作。会导致无法在外界改变颜色
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {
        clearContent();
    }

    @Override
    public boolean choseContent(double x, double y) {
        ArrayList<MyShape> contentsGroup = getContentsGroup();
        int length = contentsGroup.size();
        for (int i = length - 1; i >= 0; i--) { //从栈顶开始查询
            MyShape myShape = contentsGroup.get(i);  //获得的是MyShape类
            if(myShape.contains(x, y)){
                chosenContent = myShape;    //提取出选中的图形
                contentsGroup.remove(i);
                getDrawBoard().redraw();  //画板重画。重画后除了选中的图形，其余图形全部出现在副本上，但是原本还没有刷新，仍然能看到选中的图形
                return true;
            }
        }
        return false;
    }

    @Override
    public void translateChoseContent(int tx, int ty) {
        chosenContent.translate(tx,ty);
        getDrawBoard().refresh();
        chosenContent.draw(getListenerPen());
    }

    @Override
    public void deleteChosenContent() {  //因为没有保存在副本中，所以刷新副本让图形不再呈现在眼前就可以了。
        getDrawBoard().refresh();
    }

    /**
     * 选择状态：值选到的图形将改变成蓝色
     * 测试用！！！
     */
    private void choseStatus(){
        Color oldColor = chosenContent.getColor();    //原来的颜色样式
        chosenContent.setColor(Color.BLUE);
        chosenContent.draw(getListenerPen());     //肉眼看到的再画一遍，覆盖掉原来的部分。
        chosenContent.setColor(oldColor);
    }

    /**
     * 想要达到的目的：选中图形的颜色变色，同时在副本上也留下相同的图形
     * @param newColor
     */
    @Override
    public void setChosenContentColor(Color newColor) {
        chosenContent.setColor(newColor);   //改变图形内容
        chosenContent.draw(getListenerPen());     //肉眼看到的再画一遍，覆盖掉原来的部分。
    }

    @Override
    public void setChosenContentLineWidth(float newLineWidth) {
        chosenContent.setLineWidth(newLineWidth);   //改变图形内容
        chosenContent.draw(getListenerPen());     //肉眼看到的再画一遍，覆盖掉原来的部分。不用载入副本的
    }



    @Override
    public void saveChoseContent() {
        chosenContent.draw(getListenerPen_copy());
        getContentsGroup().add(chosenContent);
    }
}

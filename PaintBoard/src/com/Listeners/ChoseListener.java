package com.Listeners;

import com.Listeners.BaseListener.DrawListener;
import com.MyShapes.BaseShape.MyShape;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ChoseListener extends DrawListener implements KeyListener {

    private final static int _SHAPE = 0;
    private final static int _TEXT = 1;
    private final static int _IMAGE = 2;
    private final static int _CURVE = 3;

    private int contentType;    //选中的内容物类型
    private MyShape chosenContent;   //鼠标点击选中的对象
    private double contentX, contentY; //选中的内容物坐标


    private boolean choseContentIsShape(MyShape myShape, int x, int y){
        if(myShape.contains(x, y)){
            contentType = _SHAPE;
            chosenContent = myShape;
            contentX = myShape.getCoordinateX(); contentY = myShape.getCoordinateY();
            return true;
        }
        return false;
    }

   private void redrawBoard(){
        getDrawBoard().redraw();
   }


    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX(), y = e.getY();
        System.out.println(x + "," + y);
        ArrayList<MyShape> contentsGroup = getContentsGroup();
        int length = contentsGroup.size();
        for (int i = 0; i < length; i++) {
            MyShape myShape = contentsGroup.get(i);  //获得的是MyShape类
            if(choseContentIsShape(myShape, x, y)){
                contentsGroup.remove(i);
                redrawBoard();
                return;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == 24){  //键盘上箭头

        } else if(keyCode == 25) {    //键盘下箭头

        } else if(keyCode == 26){   //左箭头

        } else if(keyCode == 27) {   //右箭头

        } else if(keyCode == 10){   //回车

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

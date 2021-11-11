package com.Listeners.ChildrenListener;

import com.Listeners.ParentListener.DrawListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/*
* 调整样式代码没有写。文本框不透明。得点击两下才能看到文本框
* */
public class FontListener extends DrawListener{

    int x, y;
    JTextField textField;

    private void drawString(){
        Graphics2D listenerPen = getListenerPen();
        Graphics2D listenerPen_copy = getListenerPen_copy();

        String str = textField.getText();   //获得文本
        Font font = textField.getFont();    //获得文本框对应的字体样式

        listenerPen.setFont(font); listenerPen_copy.setFont(font);  //将文本框的字体样式付给字体
        listenerPen.drawString(str, x, y + 5); listenerPen_copy.drawString(str, x, y + 5);

        getDrawBoard().remove(textField);   //移除组件
        textField = null;
        getDrawBoard().refresh();   //刷新，将添加的组件在画板上移除
    }

    private class FontKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
//            System.out.println(e.getKeyChar() + "," + e.getKeyCode());
            if(e.getKeyCode() == 10){
                drawString();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(textField != null){
            if(!textField.getText().equals("")){
                drawString();
            }
            return;
        }
        textField = createTextField(null);
        textField.addKeyListener(new FontKeyListener());
        x = e.getX(); y = e.getY();
//        System.out.println(x + "," + y);
        textField.setBounds(x, y-15, 70, 30);
//        System.out.println("输入框：" + input.getX() + "," + input.getY());
        getDrawBoard().add(textField);
        getDrawBoard().refresh();
    }

    public JTextField createTextField(Font font){
        JTextField jTextField = new JTextField();
        if(font != null){
            jTextField.setFont(font);
        }
        jTextField.setBorder(BorderFactory.createLineBorder(Color.RED));
//        jTextField.setOpaque(false);    //设置成透明的
        return jTextField;
    }

}

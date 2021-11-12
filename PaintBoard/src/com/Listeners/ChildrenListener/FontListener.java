package com.Listeners.ChildrenListener;

import com.Listeners.ParentListener.DrawListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/*
* 文本框不透明。
* */
public class FontListener extends DrawListener{

    int clickX, clickY;
    JTextField textField;
    Font textFont = new Font(null, Font.PLAIN, 20);  //字体样式。默认不加粗不倾斜字号4
    Color textColor = Color.BLACK;    //字体颜色，默认黑色

    private void textRefresh(){
        if(textField == null) return;
        textField.setFont(textFont);
        textField.setForeground(textColor);
        getDrawBoard().refresh();
    }

    private void drawString(){
        Graphics2D listenerPen = getListenerPen();
        Graphics2D listenerPen_copy = getListenerPen_copy();

        String str = textField.getText();   //获得文本
        Color oldColor = listenerPen.getColor();    //记录画笔原来的颜色

        listenerPen.setColor(textColor); listenerPen_copy.setColor(textColor);  //设置画笔颜色同要求字体颜色一致
        listenerPen.setFont(textFont); listenerPen_copy.setFont(textFont);  //将字体样式赋值给字体
        listenerPen.drawString(str, clickX, clickY+6); listenerPen_copy.drawString(str, clickX, clickY + 5);

        getDrawBoard().remove(textField);   //移除组件
        textField = null;   //文本框清空
        listenerPen.setColor(oldColor); listenerPen_copy.setColor(oldColor);    //恢复原来的颜色
        getDrawBoard().refresh();   //刷新，将添加的组件在画板上移除
    }

    private class FontKeyListener extends KeyAdapter {
        /**
         * 按下回车键画上线段。
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == 10){
                drawString();
            }
        }

        /**
         * 当文本框长度不够时，检测到按键抬起就变长。
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {
            if(textField.getWidth() < textField.getText().length() * 15){
                int width = textField.getWidth();
                String text = textField.getText();
                getDrawBoard().remove(textField);   //移除组件
                getDrawBoard().refresh();
                putTextField(clickX, clickY, width+40, 30, text);
            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(textField != null){  //存在正在输入的文本框
            if(!textField.getText().equals("")){
                drawString();
            }
            return;
        }
        putTextField(clickX = e.getX(), clickY = e.getY(), 100, 30, null);
    }

    private void putTextField(int x, int y, int width, int length, String placeHolder){
        textField = createTextField(textFont, textColor, placeHolder);  //输入的文本框
        textField.addKeyListener(new FontKeyListener());    //添加按键监听器，当按下回车。就画图
        textField.setBounds(x, y-15, width, length);
        getDrawBoard().add(textField);
        textRefresh();
        textField.requestFocus();   //文本框获得焦点。
    }

    private JTextField createTextField(Font font, Color color, String placeHolder){
        JTextField jTextField = new JTextField(placeHolder);
//        jTextField.setOpaque(false);
        if(font != null){
            jTextField.setFont(font);
            jTextField.setForeground(color); //设置文本框字体
        }
        jTextField.setBorder(BorderFactory.createLineBorder(Color.RED));    //设置红色边框
        return jTextField;
    }

    /**
     * 设置要写的文字颜色
     * @param color
     */
    public void setFontColor(Color color){
        textColor = color;
        textRefresh();
    }

    /**
     * 设置要写的文字字体
     * @param familyName 字体名字：楷体，宋体等等
     */
    public void setFontFamily(String familyName){
        textFont = new Font(familyName, textFont.getStyle(), textFont.getSize());
        textRefresh();
    }

    /**
     * 设置要写的文字风格
     * @param style Font常量：Font.PLAIN（普通），Font.BOLD（加粗），Font.ITALIC（斜体），ont.BOLD + Font.ITALIC（粗斜体），
     */
    public void setFontStyle(int style){
        textFont = new Font(textFont.getFontName(), style, textFont.getSize());
        textRefresh();
    }

    /**
     * 设置要写的文字的大小
     * @param size
     */
    public void setFontSize(int size){
        textFont = new Font(textFont.getFontName(), textFont.getStyle(), size);
        if(size * 1.5 > textField.getHeight()){
            putTextField(clickX, clickY, textField.getWidth(), (int) (size*1.5), textField.getText());//放置里面自带刷新
        } else{
            textRefresh();
        }
    }

    /**
     * 设置文字样式
     * @param font 外界传进来的Font量，推荐使用。其他函数实际上都是调用的此函数。
     */
    public void setFont(Font font){
        textFont = font;
        textRefresh();
    }
}

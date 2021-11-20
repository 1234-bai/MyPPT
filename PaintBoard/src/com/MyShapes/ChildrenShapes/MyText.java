package com.MyShapes.ChildrenShapes;

import com.MyShapes.BaseShape.MyShape;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class MyText extends MyShape {

    private Font font;  //字体独有的属性：字体样式
    //主要是为了判断鼠标是否点击了此文字
    private int width, height;    //字体独有的属性：宽度，高度。其他图形都自带。实际上是输入时候的文本框的高和宽。
    private String text;

    public MyText(double coordinateX, double coordinateY, int width, int height, Color color, float lineWidth, Font font, String text) {
        super(coordinateX, coordinateY, color, lineWidth);
        this.font = font;
        this.width = width;
        this.height = height;
        this.text = text;
    }

    @Override
    public boolean contains(double x, double y) {
        x-=translateX; y-=translateY;
        int deltaX = (int) (x - coordinateX), deltaY = (int) (coordinateY - y);
        //因为画板画string和画其他图形是不一样的画法。其他图形是在点击点的右下角画，而String是点击点的右上角，所以算deltaY的时候需要反一下
        return (deltaX >= 0 && deltaX <= width) && (deltaY >= 0 && deltaY <= height);
    }

    //为了以后选取器能够修改选中的文本内容
    public void setFont(Font font){
        this.font = font;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    protected void drawInBoard(Graphics2D g) {
        g.drawString(text, (int)coordinateX, (int)coordinateY);
    }

    @Override
    public void draw(Graphics2D g) {
        Font oldFont = g.getFont();
        g.setFont(font);     //将储存的字体赋给画笔
        super.draw(g);
        g.setFont(oldFont); //还原画笔的字体
    }

    /**
     * String开头的"MyText"用于标识图形类型
     * Font的恢复采用new Font(String name, int style, int size)方法
     */
    @Override
    public String toString() {
        return "MyText" + " | " +
                font.getFontName() + " | " +
                font.getStyle() + " | " +
                font.getSize() + " | " +
                width + " | " +
                height + " | " +
                text + " | " +
                super.toString();
    }
}

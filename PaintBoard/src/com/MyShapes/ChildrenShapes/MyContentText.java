package com.MyShapes.ChildrenShapes;

import com.MyShapes.BaseShape.MyShape;

import java.awt.*;

public class MyContentText extends MyShape {

    private final Font font;  //字体独有的属性：字体样式
    private final int width, height;    //字体独有的属性：宽度，高度。其他图形都自带。实际上是输入时候的文本框的高和宽。
    private final String text;

    public MyContentText(double coordinateX, double coordinateY, int width, int height, Color color, float lineWidth, Font font, String text) {
        super(coordinateX, coordinateY, color, lineWidth);
        this.font = font;
        this.width = width;
        this.height = height;
        this.text = text;
    }

    @Override
    public Object getDrawContent() {
        return text;
    }

    @Override
    public boolean contains(double x, double y) {
        return (x - coordinateX >= width) && (y - coordinateY <= height);
    }

    public Font getFont(){
        return font;
    }


}
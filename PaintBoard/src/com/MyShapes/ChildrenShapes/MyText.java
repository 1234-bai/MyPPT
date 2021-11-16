package com.MyShapes.ChildrenShapes;

import com.MyShapes.BaseShape.MyShape;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class MyText extends MyShape {

    private final Font font;  //字体独有的属性：字体样式
    //主要是为了判断鼠标是否点击了此文字
    private final int width, height;    //字体独有的属性：宽度，高度。其他图形都自带。实际上是输入时候的文本框的高和宽。
    private final String text;

    public MyText(double coordinateX, double coordinateY, int width, int height, Color color, float lineWidth, Font font, String text) {
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
        x-=translateX; y-=translateY;
        return (x - coordinateX <= width) && (coordinateY - y <= height);
    }

    public Font getFont(){
        return font;
    }

    @Override
    public void drawInBoard(Graphics2D g) {
        g.setTransform(AffineTransform.getTranslateInstance(translateX, translateY));
        g.drawString(text, (int)coordinateX, (int)coordinateY);
        g.setTransform(new AffineTransform());
    }
}

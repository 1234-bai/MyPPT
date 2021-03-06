package com.MyShapes.BaseShape;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.io.Serializable;

/**
 * 记录画板上画下自己时的属性。便于再次成画
 */
public abstract class MyShape implements Serializable {

    protected double coordinateX, coordinateY;   //原始坐标
    protected double translateX, translateY;    //平移时产生的偏移量

    protected Color color;    //颜色
    protected float lineWidth;    //线宽

    public MyShape(double coordinateX, double coordinateY, Color color, float lineWidth) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.color = color;
        this.lineWidth = lineWidth;
        translateX = translateY = 0;
    }

    //带偏移量的构造方法
    public MyShape(double coordinateX, double coordinateY, double translateX, double translateY, Color color, float lineWidth) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.translateX = translateX;
        this.translateY = translateY;
        this.color = color;
        this.lineWidth = lineWidth;
    }

    /**
     * 判断鼠标点击的点是否在图形内。
     * 因为肉眼看到的图形实际上原图形经过偏移得到的。所以在判断点是否在里面的时候，要将点偏移回去。
     *
     * @param x 鼠标点击的点的横坐标
     * @param y 鼠标点击的点的总坐标
     * @return 是否在图形内或附近
     */
    public abstract boolean contains(double x, double y);

    /**
     * 定义每个图形自己的绘画函数，这样重画的时候就不用判断具体类型。直接调用自己的drawInBoard就可以了。
     *
     * @param g
     */
    protected abstract void drawInBoard(Graphics2D g);


    /**
     * 改变画笔的线宽，就是在画板上画此图形的时候，笔的线宽样式
     *
     * @param pen
     * @param lineWidth
     */
    protected void changePenLineWidth(Graphics2D pen, float lineWidth) {
        BasicStroke stroke = new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        pen.setStroke(stroke);
    }

    public void draw(Graphics2D g) {
        Color oldColor = g.getColor();    //原来的颜色样式
        g.setColor(color);  //将储存的颜色赋给画笔
        float oldLW = ((BasicStroke) g.getStroke()).getLineWidth();    //原来的画笔线宽
        changePenLineWidth(g, lineWidth);   //将储存的样式赋给画笔
        g.setTransform(AffineTransform.getTranslateInstance(translateX, translateY));   //平移量赋给画笔

        drawInBoard(g);

        g.setTransform(new AffineTransform());  //还原画笔的偏移量
        g.setStroke(new BasicStroke(oldLW, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); //还原原来的线宽
        g.setColor(oldColor);   //还原原来的颜色
    }

    public double getTranslateX() {
        return translateX;
    }

    public double getTranslateY() {
        return translateY;
    }

    public Color getColor() {
        return color;
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public void setTranslateX(double translateX) {
        this.translateX = translateX;
    }

    public void setTranslateY(double translateY) {
        this.translateY = translateY;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }

    public void translate(double tx, double ty) {
        translateX += tx;
        translateY += ty;
    }

    protected static double ZERO_DIRECT = 10.0;

    protected static boolean doubleEqual(double x, double y) {
        return Math.abs(x - y) <= ZERO_DIRECT;
    }

    /**
     * 判断给出的点是否在在线上（周围）
     * 通过斜率的方法判断
     *
     * @param line 要判断的线
     * @param x    要判断的点的横坐标
     * @param y    要判断的点的纵坐标
     * @return 给出点在线附近，为真否则返回假
     */
    protected boolean pointInLine(Line2D line, double x, double y) {
        double startX = line.getX1(), startY = line.getY1();
        double endX = line.getX2(), endY = line.getY2();
        if (startX == endX && endY == startY) {   //画出来的直线是一个点
            return (startX == x && startY == y);    //判断点是否重合
        } else {    //画出来的是一条线
            if (doubleEqual(startX, endX)) {  //原直线垂直
                return doubleEqual(x, startX); //判断横坐标是否相同
            } else { //原直线不垂直
                if (doubleEqual(startX, x)) {     //鼠标点击的点和第一个端点垂直
                    return doubleEqual(y, startY);  //接近第一个端点
                }
                double k = (endY - startY) / (endX - startX);   //计算直线斜率
                double k1 = (y - startY) / (x - startX);    //计算鼠标线斜率
                return doubleEqual(k, k1) && ((x - startX) * (x - endX) <= ZERO_DIRECT && (y - endY) * (y - startY) <= ZERO_DIRECT);
            }
        }
    }

    /**
     * 将所有属性转成String，用于保存画板
     * 末尾加入换行是为了方便按行读取文件，保证每行一个图形
     *
     * @return 包含所有属性的String
     */
    @Override
    public String toString() {
        return coordinateX + " | " +
                coordinateY + " | " +
                translateX + " | " +
                translateY + " | " +
                color.getRGB() + " | " +
                lineWidth + "\r\n";
    }
}

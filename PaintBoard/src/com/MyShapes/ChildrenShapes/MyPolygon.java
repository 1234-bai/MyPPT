package com.MyShapes.ChildrenShapes;

import com.MyShapes.BaseShape.MyShape;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Arrays;
import java.util.Vector;

public class MyPolygon extends MyShape {

//    private Vector<Integer> x;  //x坐标集合
//    private Vector<Integer> y;  //y坐标集合
    private final Polygon polygon;

    public MyPolygon(Vector<Integer> x, Vector<Integer> y, Color color, float lineWidth) throws IndexOutOfBoundsException{
        super(x.get(0), y.get(0), color, lineWidth);
//        this.x = x; this.y = y;
        int size = x.size();
        int[] xx = new int[size]; int[] yy = new int[size];
        for (int i = 0; i < size; i++) {
            xx[i] = x.get(i); yy[i] = y.get(i);
        }
        polygon = new Polygon(xx, yy, size);
    }


    @Override
    public Object getDrawContent() {
        return polygon;
    }

    @Override
    public boolean contains(double x, double y) {
        return polygon.contains(x-translateX, y-translateY);
    }

    @Override
    public void drawInBoard(Graphics2D g) {
        g.setTransform(AffineTransform.getTranslateInstance(translateX, translateY));
        g.draw(polygon);
        g.setTransform(new AffineTransform());
    }

    /**
     * String开头的"MyPolygon"用于标识图形类型
     * Polygon的恢复采用new Polygon(int[] xpoints, int[] ypoints, int npoints)方法
     */
    @Override
    public String toString() {
        return "MyPolygon" + " | " +
                Arrays.toString(polygon.xpoints) + " | " +
                Arrays.toString(polygon.ypoints) + " | " +
                polygon.npoints + " | " +
                super.toString();
    }
}

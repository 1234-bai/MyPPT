package com.MyShapes.ChildrenShapes;

import com.MyShapes.BaseShape.MyShape;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Arrays;
import java.util.Vector;

public class MyPolygon extends MyShape {

    private final Polygon polygon;
//    private Vector<Integer> x;  //x坐标集合
//    private Vector<Integer> y;  //y坐标集合

    public MyPolygon(Vector<Integer> x, Vector<Integer> y, Color color, float lineWidth) throws IndexOutOfBoundsException {
        super(x.get(0), y.get(0), color, lineWidth);
//        this.x = x; this.y = y;
        int size = x.size();
        int[] xx = new int[size];
        int[] yy = new int[size];
        for (int i = 0; i < size; i++) {
            xx[i] = x.get(i);
            yy[i] = y.get(i);
        }
        polygon = new Polygon(xx, yy, size);
    }

    //带偏移量的构造方法
    public MyPolygon(Vector<Integer> x, Vector<Integer> y, int size, double coordinateX, double coordinateY, double translateX, double translateY, Color color, float lineWidth) throws IndexOutOfBoundsException {
        super(coordinateX, coordinateY, translateX, translateY, color, lineWidth);
        int[] xx = new int[size];
        int[] yy = new int[size];
        for (int i = 0; i < size; i++) {
            xx[i] = x.get(i);
            yy[i] = y.get(i);
        }
        polygon = new Polygon(xx, yy, size);
    }

    @Override
    public boolean contains(double x, double y) {
        return polygon.contains(x - translateX, y - translateY);
    }

    @Override
    protected void drawInBoard(Graphics2D g) {
        g.draw(polygon);
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

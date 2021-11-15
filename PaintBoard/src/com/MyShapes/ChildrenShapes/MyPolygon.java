package com.MyShapes.ChildrenShapes;

import com.MyShapes.BaseShape.MyShape;

import java.awt.*;
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
        return polygon.contains(x, y);
    }
}

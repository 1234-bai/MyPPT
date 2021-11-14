package com.MyShapes.ChildrenShapes;

import com.MyShapes.BaseShape.MyShape;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class MyCurve extends MyShape {

    private ArrayList<Line2D> lineGroup = new ArrayList<>();

    public MyCurve(double coordinateX, double coordinateY, Color color, float lineWidth) {
        super(coordinateX, coordinateY, color, lineWidth);
    }

    public void add(Line2D line){
        lineGroup.add(line);
    }

    @Override
    public Object getDrawContent() {
        return lineGroup;
    }

    @Override
    public boolean contains(double x, double y) {
        return false;
    }

    /**
     *
     * @param g 要画的画板的画笔
     */
    public void drawInBoard(Graphics2D g){
        lineGroup.forEach(g::draw);
    }
}

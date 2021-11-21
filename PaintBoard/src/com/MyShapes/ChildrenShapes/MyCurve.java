package com.MyShapes.ChildrenShapes;

import com.MyShapes.BaseShape.MyShape;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class MyCurve extends MyShape {

    private ArrayList<Line2D> lineGroup = new ArrayList<>();

    public MyCurve(double coordinateX, double coordinateY, Color color, float lineWidth) {
        super(coordinateX, coordinateY, color, lineWidth);
        ZERO_DIRECT = 1.0;
    }

    public void add(Line2D line){
        lineGroup.add(line);
    }

    public void setLineGroup(ArrayList<Line2D> lineGroup) {
        this.lineGroup = lineGroup;
    }

    private final static int NUM_POINTS = 10;
    @Override
    public boolean contains(double x, double y) {   //构造一个蕴含算法。每取10个点，收尾相连成的矩形中蕴含点
        x-=translateX; y-=translateY;
//        int size = lineGroup.size();
//        int k = 0;
//        while(k < size){
//            int k1 = k, k2 = k + NUM_POINTS;
//            k2
//            int x1 =
//        }
        for(Line2D line : lineGroup){
            if(pointInLine(line, x, y)){return true;}
            if(line.getBounds().contains(x, y)){return true;}
        }
        return false;
    }

    /**
     *
     * @param g 要画的画板的画笔
     */
    @Override
    protected void drawInBoard(Graphics2D g){
        lineGroup.forEach(g::draw);
    }

    /**
     * String开头的"MyCurve"用于标识图形类型
     * MyCurve的恢复采用new MyCurve(double coordinateX, double coordinateY, Color color, float lineWidth)方法
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MyCurve");
        stringBuilder.append(" | ");

        //保存直线条数以及每条直线的4个坐标
        stringBuilder.append(lineGroup.size());
        stringBuilder.append(" | ");
        for (Line2D line : lineGroup) {
            stringBuilder.append(line.getX1());
            stringBuilder.append(" | ");
            stringBuilder.append(line.getY1());
            stringBuilder.append(" | ");
            stringBuilder.append(line.getX2());
            stringBuilder.append(" | ");
            stringBuilder.append(line.getY2());
            stringBuilder.append(" | ");
        }

        stringBuilder.append(super.toString());
        return stringBuilder.toString();
    }
}

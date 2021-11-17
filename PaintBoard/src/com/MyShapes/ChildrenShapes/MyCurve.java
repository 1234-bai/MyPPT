package com.MyShapes.ChildrenShapes;

import com.MyShapes.BaseShape.MyShape;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class MyCurve extends MyShape {

    private final ArrayList<Line2D> lineGroup = new ArrayList<>();

    public MyCurve(double coordinateX, double coordinateY, Color color, float lineWidth) {
        super(coordinateX, coordinateY, color, lineWidth);
        ZERO_DIRECT = 1.0;
    }

    public void add(Line2D line){
        lineGroup.add(line);
    }

    @Override
    public Object getDrawContent() {
        return lineGroup;
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
    public void drawInBoard(Graphics2D g){
        g.setTransform(AffineTransform.getTranslateInstance(translateX, translateY));
        lineGroup.forEach(g::draw);
        g.setTransform(new AffineTransform());
    }
}

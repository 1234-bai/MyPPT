package com.MyShapes.ChildrenShapes;

import com.MyShapes.BaseShape.MyShape;

import java.awt.*;

public class MyImage extends MyShape {

    private final Image image;
    private final String path;  //图片的路径，用于载入画板时重建图像

    public MyImage(Image image, double coordinateX, double coordinateY, Color color, float lineWidth, String path) {
        super(coordinateX, coordinateY, color, lineWidth);
        this.image = image;
        this.path = path;
    }

    //带偏移量的构造方法
    public MyImage(Image image, double coordinateX, double coordinateY, double translateX, double translateY, Color color, float lineWidth, String path) {
        super(coordinateX, coordinateY, translateX, translateY, color, lineWidth);
        this.image = image;
        this.path = path;
    }

    @Override
    public boolean contains(double x, double y) {
        x -= translateX;
        y -= translateY;
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        int deltaX = (int) (x - coordinateX), deltaY = (int) (y - coordinateY);
        return (deltaX >= 0 && deltaX <= width) && (deltaY >= 0 && deltaY <= height);
    }

    @Override
    protected void drawInBoard(Graphics2D g) {
        g.drawImage(image, (int) coordinateX, (int) coordinateY, null);
    }

    /**
     * String开头的"MyImage"用于标识图形类型
     * 图像保存实质是保存文件路径，载入时通过路径重建图像
     */
    @Override
    public String toString() {
        return "MyImage" + " | " +
                path + " | " +
                super.toString();
    }
}

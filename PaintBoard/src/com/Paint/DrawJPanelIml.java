package com.Paint;

import com.Listeners.BaseListener.DrawListener;
import com.MyShapes.BaseShape.MyShape;

import java.awt.*;
import java.util.ArrayList;

/**
 * 画板接口，方便查看DrawJpanel类的函数
 */
public interface DrawJPanelIml {


    /**
     * 刷新本画板，将副本内容载入画板。
     */
    void refresh();

    /**
     *
     * @return 获得现在同画板绑定的监听器
     */
    DrawListener getDrawListener();


    ArrayList<MyShape> getContentsGroup();


    /**
     * 重画，画笔更新，副本更新
     */
    void redraw();

    /**
     * 画笔初始化，在父窗口显示后调用。
     * 完成的任务有：将父类画笔赋值给本身画笔。创建画板副本，同时创建副本画笔。
     */
    void drawBoardPenInitial();


    /**
     * 设置本画板的监听器。
     * @param newListener  本画板需要绑定的监听器
     */
    void setBoardListener(DrawListener newListener);


     Color getPenColor();
    /**
     * 设置画笔样式，多个函数重载，通过参数类型调用的不同的函数
     * @param c 颜色变量：能够改变颜色和透明度，能够实现类荧光笔的效果
     */
    void setPenStyle(Color c);

    float getPenLineWidth();
    /**
     * 调整画笔样式线宽
     * @param lineWidth 调整后的线宽
     */
    void setPenStyle(float lineWidth);

    void setTextFont(Font font);

}

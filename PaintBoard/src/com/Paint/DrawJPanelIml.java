package com.Paint;

import com.Listeners.BaseListener.DrawListener;
import com.Listeners.ChoseListener;
import com.Listeners.MyShapesListener.TextListener;
import com.MyShapes.BaseShape.MyShape;
import com.Operations.ChildOperation.DrawShape;
import com.Operations.ChildOperation.MoveShape;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 画板接口，方便查看DrawJpanel类的函数
 */
interface DrawJPanelIml {


    /**
     * 外界通过获得本画板的画笔，从而达到在本画板画笔的基础上改变样式的功能
     *
     * @return 本画板画笔
     */
    Graphics2D getDrawBoardPen();
    
    Image getDrawBoard_copy();

    /**
     * @return 画板画笔的副本
     */
    Graphics2D getDrawBoardPen_copy();

    /**
     * @return 获得现在同画板绑定的监听器
     */
    DrawListener getDrawListener();


    CopyOnWriteArrayList<MyShape> getContentsGroup();

    CopyOnWriteArrayList<MyShape> getRedoContentsGroup();

    /**
     * 窗口改变大小之后，调用此函数，然后原先绑定的Graphic全部换新了，指向了另一个对象。就得重新绑定一遍，并且样式也要全部载入一遍。
     * 但是画板本身还是自己，没有更换新的对象。
     *
     * @param g 目前仍未搞清楚，这个g到底是谁的画笔
     */
    void paint(Graphics g);

    /**
     * 刷新本画板，将副本内容载入画板。
     */
    void refresh();


    /**
     * 画笔初始化，在父窗口显示后调用。
     * 完成的任务有：将父类画笔赋值给本身画笔。创建画板副本，同时创建副本画笔。
     */
    void drawBoardPenInitial();

    /**
     * 重画，画笔更新，副本更新。
     * 选取时调用。没有父容器，重新载入副本时调用。
     */
    void redraw();



    /**
     * 设置本画板的监听器。
     *
     * @param newListener 本画板需要绑定的监听器
     */
    void setBoardListener(DrawListener newListener);


    Color getPenColor();

    /**
     * 设置画笔样式，多个函数重载，通过参数类型调用的不同的函数
     *
     * @param c 颜色变量：能够改变颜色和透明度，能够实现类荧光笔的效果
     */
    void setPenStyle(Color c);


    
    float getPenLineWidth();

    /**
     * 调整画笔样式线宽
     *
     * @param lineWidth 调整后的线宽
     */
    void setPenStyle(float lineWidth) ;


    void setTextFont(Font font);

    void setTextFamily(String newFontFamily);

    void setTextStyle(int newStyle);

    void setTextSize(int newSize);

    /**
     * 画板转换成String，本质只保存contentsGroup
     *
     * @return 返回contentsGroup的String值
     */
    
    String toString();

    /**
     * 取得操作数组
     *
     * @return 操作数组
     */
    ArrayList<String> getOperations();

    /**
     * 取得重做操作数组
     *
     * @return 重做操作数组
     */
    ArrayList<String> getRedoOperations();
    /**
     * 操作栈压栈
     *
     * @param s 入栈元素
     */
    void operations_Push(String s);

    /**
     * 操作栈退栈
     *
     * @return 出栈元素
     */
    String operations_Pop();

    /**
     * 重做操作栈压栈
     *
     * @param s 入栈元素
     */
    void redoOperations_Push(String s);

    /**
     * 重做操作栈退栈
     *
     * @return 出栈元素
     */
    String redoOperations_Pop();

    /**
     * 图形栈压栈
     *
     * @param myShape 入栈元素
     */
    void contentsGroup_Push(MyShape myShape);

    /**
     * 图形栈退栈
     *
     * @return 出栈元素
     */
    MyShape contentsGroup_Pop();

    /**
     * 重做图形栈压栈
     *
     * @param myShape 入栈元素
     */
    void redoContentsGroup_Push(MyShape myShape);

    /**
     * 重做图形栈退栈
     *
     * @return 出栈元素
     */
    MyShape redoContentsGroup_Pop();

    /**
     * 撤销
     * 受选取逻辑限制，目前撤销与重做功能仅对画图功能有效
     * 有时间会重写，（大概率没有，因为要修改整个底层 orz）
     */
    void revoke();

    /**
     * 重做
     * 受选取逻辑限制，目前撤销与重做功能仅对画图功能有效
     * 有时间会重写，（大概率没有，因为要修改整个底层 orz）
     */
    void redo();

    /**
     * 清空操作栈
     */
    void clearOperations();

    /**
     * 清空重做操作栈
     */
    void clearRedoOperations();
    /**
     * 清空重做栈
     */
    void clearRedoContentsGroup();

    void setContentsGroup(CopyOnWriteArrayList<MyShape> contentsGroup);
}

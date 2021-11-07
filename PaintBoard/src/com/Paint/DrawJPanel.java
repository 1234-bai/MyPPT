package com.Paint;

import com.Listeners.DrawListener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawJPanel extends JPanel {

    private BufferedImage drawBoardCopy;    //画板JPanel的副本

    private Graphics2D drawBoardPen;  //画板的画笔通过监听器中的画笔展现。只有在父类容器可视化之后才能获得画笔！！
    private Graphics2D drawBoardPenCopy;    //画笔的副本
    private DrawListener drawListener;  //画正在运行板的监听器，当按下不同的功能按钮的时候，将此监听器设置为按钮所对应的监听器

    public BufferedImage getDrawBoardCopy() {
        return drawBoardCopy;
    }

    /**
     * 外界通过获得本画板的画笔，从而达到在本画板画笔的基础上改变样式的功能
     * @return 本画板画笔
     */
    public Graphics2D getDrawBoardPen() {
        return drawBoardPen;
    }

    /**
     *
     * @return 画板画笔的副本
     */
    public Graphics2D getDrawBoardPenCopy() {
        return drawBoardPenCopy;
    }

    /**
     * 画笔初始化，在父窗口显示后调用。
     * 完成的任务有：将父类画笔赋值给本身画笔。创建画板副本，同时创建副本画笔。
     */
    public void drawBoardPenInitial() {
        drawBoardPen = (Graphics2D) super.getGraphics();    //将父类画笔赋值给本身画笔
        if(drawBoardCopy == null){  //直接设置成最大尺寸的画布。这样在重绘过程中，不会因画板尺寸改变（画板以前画的东西都会丢失），引起副本的改变（副本不会更新，画的东西也就不会丢失了）
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            drawBoardCopy = (BufferedImage) this.createImage((int)screenSize.getWidth(), (int)screenSize.getHeight());  //创建画板副本
       }
        drawBoardPenCopy = drawBoardCopy.createGraphics();  //创建副本画笔。
    }

    /**
     * 设置本画板的监听器。
     * @param newListener  本画板需要绑定的监听器
     */
    public void setBoardListener(DrawListener newListener) {
        drawListener = newListener;
        if(drawListener != null){  //如果监听器不为空，则
            drawListener.setDrawBoard(this); //将本画板绑定给监听器。同时本画板的画笔样式同样绑定给了监听器。
        } //如果为空，说明目的是清空鼠标画笔，直接退出就完了。
        super.addMouseListener(drawListener);  //调用系统监听器生效函数，使监听器能正常运作
        super.addMouseMotionListener(drawListener);
    }

    /**
     * 设置样式，多个函数重载，通过参数类型调用的不同的函数
     * @param c 颜色变量
     */
    public void setStyle(Color c){
        if(drawBoardPen == null){return;}
        drawBoardPen.setColor(c);
        drawBoardPenCopy.setColor(c);
    }

    /**
     * 窗口改变大小之后，调用此函数，然后原先绑定的Graphic全部换新了，指向了另一个对象。就得重新绑定一遍。
     * 但是画板本身还是自己，没有更换新的对象。
     * @param g 目前仍未搞清楚，这个g到底是谁的画笔
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(drawBoardCopy, 0, 0, null); //重绘
        drawBoardPen = (Graphics2D) super.getGraphics();    //将父类画笔赋值给本身画笔
    }
}

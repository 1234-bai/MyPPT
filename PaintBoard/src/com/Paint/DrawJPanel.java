package com.Paint;

import com.Listeners.ParentListener.DrawListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class DrawJPanel extends JPanel {

    private Graphics2D drawBoardPen;  //画板的画笔通过监听器中的画笔展现。只有在父类容器可视化之后才能获得画笔！！

    private BufferedImage drawBoard_copy;    //画板JPanel的副本
    private Graphics2D drawBoardPen_copy;    //画笔的副本

    private DrawListener drawListener;  //画正在运行板的监听器，当按下不同的功能按钮的时候，将此监听器设置为按钮所对应的监听器





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
    public Graphics2D getDrawBoardPen_copy() {
        return drawBoardPen_copy;
    }




    /**
     * 刷新本画板，将副本内容载入画板。
     */
    public void refresh(){
        drawBoardPen.drawImage(drawBoard_copy, 0 ,0, null);
    }



    /**
     * 窗口改变大小之后，调用此函数，然后原先绑定的Graphic全部换新了，指向了另一个对象。就得重新绑定一遍，并且样式也要全部载入一遍。
     * 但是画板本身还是自己，没有更换新的对象。
     * @param g 目前仍未搞清楚，这个g到底是谁的画笔
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(drawBoard_copy, 0, 0, null); //重绘
        drawBoardPenInitial();
    }



    /**
     * 初始化画笔，是线条更加圆滑，减小锯齿。看起来更美观。
     */
    private void penStyleInitial(){
        drawBoardPen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawBoardPen.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);

        drawBoardPen_copy.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawBoardPen_copy.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);

        setPenStyle(1.5f);
    }

    /**
     * 画笔样式还原。
     * 目前画笔样式只有：颜色，线宽。
     */
    private void penStyleRecover(){
        drawBoardPen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawBoardPen.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);    //还原圆润度
        drawBoardPen.setColor(drawBoardPen_copy.getColor());    //还原颜色
        drawBoardPen.setStroke(drawBoardPen_copy.getStroke());  //还原线宽
    }

    /**
     * 创造本画板以及本画板画笔的副本
     */
    private void createCopy(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        drawBoard_copy = (BufferedImage) this.createImage((int)screenSize.getWidth(), (int)screenSize.getHeight());  //创建画板副本
        drawBoardPen_copy = drawBoard_copy.createGraphics();  //创建副本画笔。
    }

    /**
     * 画笔初始化，在父窗口显示后调用。
     * 完成的任务有：将父类画笔赋值给本身画笔。创建画板副本，同时创建副本画笔。
     */
    public void drawBoardPenInitial() {
        drawBoardPen = (Graphics2D) super.getGraphics();    //将父类画笔赋值给本身画笔
        if(drawBoard_copy == null){  //直接设置成最大尺寸的画布。这样在重绘过程中，不会因画板尺寸改变（画板以前画的东西都会丢失），引起副本的改变（副本不会更新，画的东西也就不会丢失了）
            createCopy();
            penStyleInitial();  //样式初始化
       } else{  //说明副本不为空，说明是画板重绘了。则将画笔样式全部复制给现在的画笔
            penStyleRecover();  //样式还原。
        }
    }




    /**
     * 清空本画板的全部鼠标监听器
     */
    private void clearMouseListeners(){
        if(drawListener != null){
            removeMouseListener(drawListener);
            removeMouseMotionListener(drawListener);
            drawListener.setDrawBoard(null);    //将原来的监听器同本画板解绑
        }
    }

    /**
     * 设置本画板的监听器。
     * @param newListener  本画板需要绑定的监听器
     */
    public void setBoardListener(DrawListener newListener) {
        clearMouseListeners();
        drawListener = newListener;
        if(drawListener != null){  //如果监听器不为空，则
            drawListener.setDrawBoard(this); //将本画板绑定给监听器。同时本画板的画笔样式同样绑定给了监听器。
        } //如果为空，说明目的是清空鼠标画笔，直接退出就完了。
        super.addMouseListener(drawListener);  //调用系统监听器生效函数，使监听器能正常运作
        super.addMouseMotionListener(drawListener);
    }




    /**
     * 设置画笔样式，多个函数重载，通过参数类型调用的不同的函数
     * @param c 颜色变量：能够改变颜色和透明度，能够实现类荧光笔的效果
     */
    public void setPenStyle(Color c){
        if(drawBoardPen == null){return;}
        drawBoardPen.setColor(c);
        drawBoardPen_copy.setColor(c);
    }




    /**
     * 调整画笔样式线宽
     * @param lineWidth 调整后的线宽
     */
    public void setPenStyle(float lineWidth){
        if(drawBoardPen == null){return;}
        BasicStroke stroke = new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        drawBoardPen.setStroke(stroke);
        drawBoardPen_copy.setStroke(stroke);
    }

}

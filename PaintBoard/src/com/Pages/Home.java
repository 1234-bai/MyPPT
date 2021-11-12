package com.Pages;

import com.Listeners.ChildrenListener.*;
import com.Listeners.ParentListener.DrawListener;
import com.Paint.DrawJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home extends JFrame {

    private final DrawJPanel drawBoard = new DrawJPanel();

    private JButton createShapeButton(String name, DrawListener listener){
        JButton jButton = new JButton(name);
        jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawBoard.setBoardListener(listener);
            }
        });
        return jButton;
    }
    public Home() throws HeadlessException {

        //定义图形按钮
        JButton lineButton = createShapeButton("直线", new LineListener());
        JButton polyButton = createShapeButton("多边形", new PolygonListener());
        JButton curveButton = createShapeButton("曲线", new CurveListener());
        JButton circButton = createShapeButton("圆形", new CircleListener());
        JButton rectButton = createShapeButton("矩形", new RectangleListener());
        JButton imageButton = createShapeButton("插入图片", new ImageListener());

        //创建按钮框
        JPanel shapesButtons = new JPanel();
        shapesButtons.setLayout(new GridLayout(6, 1));
        shapesButtons.add(lineButton);
        shapesButtons.add(curveButton);
        shapesButtons.add(polyButton);
        shapesButtons.add(circButton);
        shapesButtons.add(rectButton);
        shapesButtons.add(imageButton);


        //定义样式按钮
        JButton yellowButton = new JButton("黄色");
        yellowButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawBoard.setPenStyle(new Color(127, 157, 31, 71));
            }
        });
        JButton lineWidthButton = new JButton("增大线宽");
        lineWidthButton.addMouseListener(new MouseAdapter() {
            float lineWidth = 1.0f;
            @Override
            public void mouseClicked(MouseEvent e) {
                lineWidth+=0.5f;
                drawBoard.setPenStyle(lineWidth);
            }
        });
        //创建样式按钮框
        JPanel styleButtons = new JPanel();
        styleButtons.add(yellowButton); styleButtons.add(lineWidthButton);

        //定义文字按钮
        JButton textButton = new JButton("输入文字");
        textButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawBoard.setBoardListener(new FontListener());
            }
        });

        //画出界面的大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int)screenSize.getWidth() / 4, (int)screenSize.getHeight() / 4);
        setSize((int)screenSize.getWidth() / 2, (int)screenSize.getHeight() / 2);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("测试画板");
        setLayout(new BorderLayout());  //定义界面布局样式


        add(BorderLayout.WEST, shapesButtons);
        add(BorderLayout.NORTH, styleButtons);
        add(BorderLayout.EAST, textButton);
        add(BorderLayout.CENTER,drawBoard);
        //bbb
    }

    public void Run(){
        setVisible(true);
        drawBoard.drawBoardPenInitial();
    }

    public static void main(String[] args) {
        new Home().Run();
    }

}

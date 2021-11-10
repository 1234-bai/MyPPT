package com.Pages;

import com.Listeners.LineListener;
import com.Listeners.PolygonListener;
import com.Paint.DrawJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home extends JFrame {

    private final DrawJPanel drawBoard = new DrawJPanel();
    private final LineListener lineListener = new LineListener();
    private final PolygonListener polygonListener = new PolygonListener();
    public Home() throws HeadlessException {

        //定义图形按钮
        JButton lineButton = new JButton("直线");
        lineButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawBoard.setBoardListener(lineListener);
            }
        });
        JButton polyButton = new JButton("多边形");
        polyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawBoard.setBoardListener(polygonListener);
            }
        });

        //定义样式按钮
        JButton yellowButton = new JButton("黄色");
        yellowButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawBoard.setPenStyle(Color.yellow);
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

        //画出界面的大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int)screenSize.getWidth() / 4, (int)screenSize.getHeight() / 4);
        setSize((int)screenSize.getWidth() / 2, (int)screenSize.getHeight() / 2);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("测试画板");

        setLayout(new BorderLayout());
        add(BorderLayout.WEST, lineButton);
        add(BorderLayout.EAST, polyButton);
        add(BorderLayout.NORTH, yellowButton);
        add(BorderLayout.SOUTH, lineWidthButton);
        add(BorderLayout.CENTER,drawBoard);
    }

    public void Run(){
        setVisible(true);
        drawBoard.drawBoardPenInitial();
    }

    public static void main(String[] args) {
        new Home().Run();
    }

}

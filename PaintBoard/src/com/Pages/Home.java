package com.Pages;

import com.Listeners.LineListener;
import com.Paint.DrawJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home extends JFrame {

    private final DrawJPanel drawBoard = new DrawJPanel();
    private final LineListener lineListener = new LineListener();
    public Home() throws HeadlessException {

        //定义图形按钮
        JButton lineButton = new JButton("直线");
        lineButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawBoard.setBoardListener(lineListener);
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

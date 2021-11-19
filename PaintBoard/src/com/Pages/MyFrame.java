package com.Pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyFrame extends JFrame {

    private class TitleBarButton extends JButton{
        public TitleBarButton() {
            setOpaque(false);   //透明
            setBorderPainted(false);    //取消边框
            setBackground(null);    //取消背景
            setFocusPainted(false); //取消焦点
        }
    }



    private static final int SCREEN_WIDTH;
    private static final int SCREEN_HEIGHT;
    // 设置窗体默认大小,使其适应屏幕大小
    private static final int DEFAULT_WIDTH;
    private static final int DEFAULT_HEIGHT;
    static {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        SCREEN_WIDTH = (int)toolkit.getScreenSize().getWidth();
        SCREEN_HEIGHT = (int)toolkit.getScreenSize().getHeight();
        DEFAULT_WIDTH = (int)(SCREEN_WIDTH * 0.4);
        DEFAULT_HEIGHT = (int) (SCREEN_HEIGHT * 0.5);
    }



//
//    // 设置窗体位置,使其在屏幕居中
//    private int Location_x = (int) (toolkit.getScreenSize().getWidth() - DEFAULT_WIDTH) / 2;
//    private int Location_y = (int) (toolkit.getScreenSize().getHeight() - DEFAULT_HEIGHT) / 2;


    private int mouseStartX = 0;
    private int mouseStartY = 0;

    JPanel contentPane = new JPanel();  //标题框
    JPanel buttons = new JPanel();  //标题框的按钮框

    public MyFrame() {
        setUndecorated(true);//设置窗体的标题栏不可见
        setLocation(0, 0);//设置窗体在屏幕中的位置
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);// 设置窗体默认大小,使其适应屏幕大小
        setLayout(null);

        //设置窗体可拖动
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseStartX = e.getX();
                mouseStartY = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setLocation((e.getXOnScreen()- mouseStartX),(e.getYOnScreen()- mouseStartY));//设置拖拽后，窗口的位置
            }
        });

        /*
         * 实例化简单组件
         */
        TitleBarButton miniButton = new TitleBarButton();
        miniButton.setIcon(new ImageIcon("PaintBoard/images/TitleBar/minimize.png"));
        TitleBarButton maxNormalButton = new TitleBarButton();
        maxNormalButton.setIcon(new ImageIcon("PaintBoard/images/TitleBar/maximize.png"));
        TitleBarButton closeButton = new TitleBarButton();
        closeButton.setIcon(new ImageIcon("PaintBoard/images/TitleBar/close.png"));

        miniButton.addActionListener(e -> setExtendedState(JFrame.ICONIFIED));
        maxNormalButton.addActionListener(new ActionListener() {
            boolean normal = false;
            ImageIcon normalImg = new ImageIcon("PaintBoard/images/TitleBar/normal-size.png");
            ImageIcon maxImg = new ImageIcon("PaintBoard/images/TitleBar/maximize.png");

            @Override
            public void actionPerformed(ActionEvent e) {
                if(normal){
                    setExtendedState(JFrame.MAXIMIZED_BOTH);
                    maxNormalButton.setIcon(normalImg);
                    normal = false;
                    contentPane.setBounds(0,0, SCREEN_WIDTH, 30);
                }else {
                    setExtendedState(JFrame.NORMAL);
                    maxNormalButton.setIcon(maxImg);
                    normal = true;
                    contentPane.setBounds(0,0, DEFAULT_WIDTH, 30);
                }
            }
        });
        closeButton.addActionListener(e -> System.exit(0));



        contentPane.setBounds(0,0, SCREEN_WIDTH, 30);
        contentPane.setBackground(CONSTANTS.MY_COLOR.TITLE_BAR_COLOR);
        contentPane.setLayout(new BorderLayout());


        buttons.setBackground(CONSTANTS.MY_COLOR.TITLE_BAR_COLOR);
        buttons.setLayout(new GridLayout(1, 3));
        buttons.add(miniButton);
        buttons.add(maxNormalButton);
        buttons.add(closeButton);

        contentPane.add(BorderLayout.EAST, buttons);
        add(contentPane);

        setVisible(true); //设置窗体可见
    }

    public static void main(String[] args) {
        new MyFrame();
    }


}

package com.Pages.BasePages;

import com.Pages.CONSTANTS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 自定义外边框。
 */
public class MyFrame extends JFrame {
    
    protected static class TitleBarButton extends JButton{
        public TitleBarButton() {
            setOpaque(false);   //透明
            setBorderPainted(false);    //取消边框
            setBackground(null);    //取消背景
            setFocusPainted(false); //取消焦点
        }
    }

    protected static final int TITLE_BAR_HEIGHT = 30;

    protected static final int SCREEN_WIDTH;
    protected static final int SCREEN_HEIGHT;
    // 设置窗体默认大小,使其适应屏幕大小
    protected static final int DEFAULT_WIDTH;
    protected static final int DEFAULT_HEIGHT;
    // 设置窗体位置,使其在屏幕居中
    protected static final int LOCATION_X;
    protected static final int LOCATION_Y;
    static {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        SCREEN_WIDTH = (int)toolkit.getScreenSize().getWidth();
        SCREEN_HEIGHT = (int)toolkit.getScreenSize().getHeight();
        DEFAULT_WIDTH = (int)(SCREEN_WIDTH * 0.8);
        DEFAULT_HEIGHT = (int) (SCREEN_HEIGHT * 0.8);
        LOCATION_X = (SCREEN_WIDTH - DEFAULT_WIDTH) / 2;
        LOCATION_Y = (SCREEN_HEIGHT - DEFAULT_HEIGHT) / 2;
    }

    protected JPanel titleBar = new JPanel();  //标题框
    protected JPanel titleCloseButtons = new JPanel();  //标题框的按钮框

    private boolean draggedEnable;  //拖动使能，只有为TRUE时才能移动
    private int mouseStartX = 0;
    private int mouseStartY = 0;

    public MyFrame() {

        setUndecorated(true);//设置窗体的标题栏不可见
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置窗口关闭按钮类型
        setBounds(LOCATION_X, LOCATION_Y,DEFAULT_WIDTH, DEFAULT_HEIGHT);    // 设置窗体默认大小和位置,使其适应屏幕大小
        setLayout(null);    //设置布局为绝对布局
        draggedEnable = true;   //开始为正常大小，可拖动

        /*
         * 实例化简单组件
         */
        TitleBarButton miniButton = new TitleBarButton();
        miniButton.setIcon(new ImageIcon(CONSTANTS.getRightResourceFilePath("images/TitleBar/minimize.png")));
        TitleBarButton maxNormalButton = new TitleBarButton();
        maxNormalButton.setIcon(new ImageIcon(CONSTANTS.getRightResourceFilePath("images/TitleBar/normal-size.png")));
        TitleBarButton closeButton = new TitleBarButton();
        closeButton.setIcon(new ImageIcon(CONSTANTS.getRightResourceFilePath("images/TitleBar/close.png")));

        miniButton.addActionListener(e -> setExtendedState(JFrame.ICONIFIED));
        maxNormalButton.addActionListener(new ActionListener() {
            boolean normal = true;
            final ImageIcon normalImg = new ImageIcon(CONSTANTS.getRightResourceFilePath("images/TitleBar/normal-size.png"));
            final ImageIcon maxImg = new ImageIcon(CONSTANTS.getRightResourceFilePath("images/TitleBar/maximize.png"));

            @Override
            public void actionPerformed(ActionEvent e) {
                if(normal){
                    setExtendedState(JFrame.MAXIMIZED_BOTH);
                    maxNormalButton.setIcon(maxImg);
                    normal = false;
                }else {
                    setExtendedState(JFrame.NORMAL);
                    maxNormalButton.setIcon(normalImg);
                    normal = true;
                }
                draggedEnable = normal;
                titleBar.setBounds(0,0, getWidth(), TITLE_BAR_HEIGHT);
            }
        });
        closeButton.addActionListener(e -> dispose());

        titleCloseButtons.setBackground(CONSTANTS.MY_COLOR.TITLE_BAR_COLOR);
        titleCloseButtons.setLayout(new GridLayout(1, 3));
        titleCloseButtons.add(miniButton);
        titleCloseButtons.add(maxNormalButton);
        titleCloseButtons.add(closeButton);


        titleBar.setBounds(0,0, DEFAULT_WIDTH, TITLE_BAR_HEIGHT);
        titleBar.setBackground(CONSTANTS.MY_COLOR.TITLE_BAR_COLOR);
        titleBar.setLayout(new BorderLayout());


        titleBar.add(BorderLayout.EAST, titleCloseButtons);

        //设置窗体可拖动
        titleBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!draggedEnable){return;}
                mouseStartX = e.getX();
                mouseStartY = e.getY();
            }
        });
        titleBar.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(!draggedEnable){return;}
                setLocation((e.getXOnScreen()- mouseStartX),(e.getYOnScreen()- mouseStartY));//设置拖拽后，窗口的位置
            }
        });

        add(titleBar);
    }



//    public static void main(String[] args) {
//        new MyFrame().setVisible(true);
//    }


}

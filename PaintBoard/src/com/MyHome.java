package com;

import com.Pages.*;
import com.Pages.BasePages.MyFrame;
import com.Pages.BasePages.ClearPanel;
import com.Pages.MenuBar.*;
import com.Pages.MenuBar.MenuBar;
import com.Pages.SlideBar.ImageShowBoard;
import com.Paint.DrawJPanel;
import com.Paint.DrawJPanelFileUtil;
import com.Paint.testPPT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class MyHome extends MyFrame {

    /**
     * 幻灯片内部的空白填充框
     */
    private static class EmptyFillPanel extends JPanel{
        public EmptyFillPanel(int x, int y, int width, int height) {
            setBounds(x, y, width, height);
            setBackground(CONSTANTS.MY_COLOR.EMPTY_AREA_COLOR);
            setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
        }
    }


    //底部说明栏
    private static final int BOTTOM_BAR_HEIGHT = 30;

    //菜单栏
    private static final int MENU_BAR_HEIGHT = 40;

    private final MenuBar menuBar = new MenuBar();

    private final FileButtonsBar fileButtons = new FileButtonsBar();
    private final InsertButtonsBar insertButtons = new InsertButtonsBar();
    private final DrawButtonsBar drawButtons = new DrawButtonsBar();
    private final PenStyleBar penStyleButtons = new PenStyleBar();

    //菜单的按键栏（topBar）
    private static final int TOP_BAR_HEIGHT = 130;
    EmptyFillPanel topBar = new EmptyFillPanel(0, TITLE_BAR_HEIGHT + MENU_BAR_HEIGHT, SCREEN_WIDTH, TOP_BAR_HEIGHT);
    private ClearPanel topButtons; //当前topBar里含有的buttons


    //幻灯片下拉列表
    private static final int SLIDE_BAR_WIDTH = 400;
    EmptyFillPanel imgSlideBar = new EmptyFillPanel(0, TITLE_BAR_HEIGHT + MENU_BAR_HEIGHT + TOP_BAR_HEIGHT, SLIDE_BAR_WIDTH,
                                                        getHeight()-(TITLE_BAR_HEIGHT + MENU_BAR_HEIGHT + TOP_BAR_HEIGHT + BOTTOM_BAR_HEIGHT));

    public MyHome() {
        //菜单栏
        menuBar.setBounds(0, TITLE_BAR_HEIGHT, SCREEN_WIDTH, MENU_BAR_HEIGHT);
        add(menuBar);
        //设置菜单栏的初始情况
        topBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        topBar.add(fileButtons); topButtons = fileButtons;
        add(topBar);
        //设置菜单栏监听器
        setMenuBarListener();

        //测试用
        File file = DrawJPanelFileUtil.choseFile("myppt", "载入...", "确定", "open");
        DrawJPanel DP1 = DrawJPanelFileUtil.loadDrawBoard(file);
        file = DrawJPanelFileUtil.choseFile("myppt", "载入...", "确定", "open");
        DrawJPanel DP2 = DrawJPanelFileUtil.loadDrawBoard(file);
        file = DrawJPanelFileUtil.choseFile("myppt", "载入...", "确定", "open");
        DrawJPanel DP3 = DrawJPanelFileUtil.loadDrawBoard(file);
        file = DrawJPanelFileUtil.choseFile("myppt", "载入...", "确定", "open");
        DrawJPanel DP4 = DrawJPanelFileUtil.loadDrawBoard(file);
        file = DrawJPanelFileUtil.choseFile("myppt", "载入...", "确定", "open");
        DrawJPanel DP5 = DrawJPanelFileUtil.loadDrawBoard(file);
        file = DrawJPanelFileUtil.choseFile("myppt", "载入...", "确定", "open");
        DrawJPanel DP6 = DrawJPanelFileUtil.loadDrawBoard(file);
        testPPT testPPT = new testPPT();
        testPPT.getMyPPT().add(DP1);
        testPPT.getMyPPT().add(DP2);
        testPPT.getMyPPT().add(DP3);
        testPPT.getMyPPT().add(DP4);
        testPPT.getMyPPT().add(DP5);
        testPPT.getMyPPT().add(DP6);
        ImageShowBoard slide = new ImageShowBoard();
        slide.setDrawPPT(testPPT);

        JScrollPane jScrollPane = new JScrollPane(slide.getCopyShowBoard());
        jScrollPane.setPreferredSize(new Dimension(350,getHeight()-(TITLE_BAR_HEIGHT + MENU_BAR_HEIGHT + TOP_BAR_HEIGHT + BOTTOM_BAR_HEIGHT)));
        imgSlideBar.add(jScrollPane);
        //下拉列表
        add(imgSlideBar);
    }

    //刷新组件
    private void refresh(Container con){
        con.setVisible(false);
        con.setVisible(true);
    }

    /**
     * 窗口大小发生变化时，其中组件的大小也要发生变化。
     * 因此设置监听窗口变化时，变化组件大小的监听器
     */
    private void setComponentChangeListener(){
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
    }

    //改变菜单栏的按钮群
    private void changeTopBarButtons(ClearPanel nextButtons){
        topBar.remove(topButtons);
        topBar.add(nextButtons);
        topButtons = nextButtons;
        refresh(topBar);
    }
    /**
     * 设置菜单栏的监听器
     */
    private void setMenuBarListener(){
        menuBar.getFileMenu().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeTopBarButtons(fileButtons);
            }
        });
        menuBar.getInsertMenu().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeTopBarButtons(insertButtons);
            }
        });
        menuBar.getDrawMenu().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeTopBarButtons(drawButtons);
            }
        });
        menuBar.getPenStyleMenu().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeTopBarButtons(penStyleButtons);
            }
        });
    }

    public void run(){
        setVisible(true);
        refresh(imgSlideBar);
    }

    public static void main(String[] args) {
        new MyHome().run();
    }
}

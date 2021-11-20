package com;

import com.Listeners.MyShapesListener.LineListener;
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
     * 幻灯片内部的空白填充框类
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
    private EmptyFillPanel topBar = new EmptyFillPanel(0, TITLE_BAR_HEIGHT + MENU_BAR_HEIGHT, SCREEN_WIDTH, TOP_BAR_HEIGHT);
    private ClearPanel topButtons; //当前topBar里含有的buttons


    //顶部全部栏目的高度，包括标题栏，菜单栏，菜单栏包括菜单和菜单按键
    private static final int ALL_TOP_BAR_HEIGHT = TITLE_BAR_HEIGHT + MENU_BAR_HEIGHT + TOP_BAR_HEIGHT;

    //幻灯片下拉列表
    private static final int SLIDE_BAR_WIDTH = 400;
    private EmptyFillPanel imgSlideBar = new EmptyFillPanel(
                0,
                ALL_TOP_BAR_HEIGHT,
                SLIDE_BAR_WIDTH,
                getHeight()-(ALL_TOP_BAR_HEIGHT + BOTTOM_BAR_HEIGHT
            ));
    JScrollPane imgSlidePane;    //下拉列表，用来放置生成的图像列表，形成滚动条

    //PPT展示板
    //相对滑动板块定位
    private static final int PPT_SLID_X_OFFSET = 100;   //相对滑动板块的偏移量
    private static final int PPT_SLID_Y_OFFSET = 50;   //相对顶部栏的偏移量
    private static final int PPT_BOTTOM_OFFSET = 40;    //距离底部栏的偏移量
    private static final int PPT_RIGHT_OFFSET = 50;     //距离右边界的偏移量
    private static final int PPT_SHOW_BOARD_X = SLIDE_BAR_WIDTH + PPT_SLID_X_OFFSET;    //展示板的横坐标
    private static final int PPT_SHOW_BOARD_Y = ALL_TOP_BAR_HEIGHT + PPT_SLID_Y_OFFSET;     //展示板的纵坐标
    private static final int DRAW_BOARD_COPY_WIDTH = (int)(SCREEN_WIDTH * DrawJPanel.WIDTH_RATE);    //副本真实宽度
    private static final int DRAW_BOARD_COPY_HEIGHT = (int)(SCREEN_HEIGHT * DrawJPanel.HEIGHT_RATE);     //副本真实高度
    private DrawJPanel pptShowBoard = new DrawJPanel();

    public MyHome() {

        setComponentChangeListener();   //设置窗口改变，组件大小也跟着改变的监听器

        //菜单栏
        menuBar.setBounds(0, TITLE_BAR_HEIGHT, SCREEN_WIDTH, MENU_BAR_HEIGHT);
        add(menuBar);
        //设置菜单栏的初始情况
        topBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        topBar.add(fileButtons); topButtons = fileButtons;
        add(topBar);
        //设置菜单栏监听器
        setMenuBarListener();

        //下拉列表
        //测试用
        File file = DrawJPanelFileUtil.choseFile("myppt", "载入...", "确定", "open");
        DrawJPanel DP1 = DrawJPanelFileUtil.loadDrawBoard(file);
        DrawJPanel DP2 = DrawJPanelFileUtil.loadDrawBoard(file);
        DrawJPanel DP3 = DrawJPanelFileUtil.loadDrawBoard(file);
        DrawJPanel DP4 = DrawJPanelFileUtil.loadDrawBoard(file);
        DrawJPanel DP5 = DrawJPanelFileUtil.loadDrawBoard(file);
        DrawJPanel DP6 = DrawJPanelFileUtil.loadDrawBoard(file);
        testPPT testPPT = new testPPT();
        testPPT.getMyPPT().add(DP1);testPPT.getMyPPT().add(DP2);testPPT.getMyPPT().add(DP3);testPPT.getMyPPT().add(DP4);testPPT.getMyPPT().add(DP5);testPPT.getMyPPT().add(DP6);
        ImageShowBoard slide = new ImageShowBoard();
        slide.setDrawPPT(testPPT);
        imgSlidePane = new JScrollPane(slide.getCopyShowBoard());
        imgSlidePane.setPreferredSize(new Dimension(350,getHeight()-(ALL_TOP_BAR_HEIGHT + BOTTOM_BAR_HEIGHT)));
        imgSlideBar.add(imgSlidePane);
        add(imgSlideBar);

        //画板
        pptShowBoard.setBounds(
                PPT_SHOW_BOARD_X,
                PPT_SHOW_BOARD_Y,
                getWidth() - PPT_SHOW_BOARD_X - PPT_RIGHT_OFFSET,
                getHeight() - PPT_SHOW_BOARD_Y - BOTTOM_BAR_HEIGHT - PPT_BOTTOM_OFFSET
        );
        add(pptShowBoard);
        pptShowBoard.setBoardListener(new LineListener());
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
                int width = getWidth(), height = getHeight();
                imgSlideBar.setBounds(
                        0,
                        ALL_TOP_BAR_HEIGHT,
                        SLIDE_BAR_WIDTH,
                        height -(ALL_TOP_BAR_HEIGHT + BOTTOM_BAR_HEIGHT)
                );
                imgSlidePane.setPreferredSize(new Dimension(
                        350,
                        height -(ALL_TOP_BAR_HEIGHT+ BOTTOM_BAR_HEIGHT)
                ));
                refresh(imgSlideBar);
                pptShowBoard.setBounds(
                        PPT_SHOW_BOARD_X,
                        PPT_SHOW_BOARD_Y,
                        Math.min(DRAW_BOARD_COPY_WIDTH, width - PPT_SHOW_BOARD_X - PPT_RIGHT_OFFSET),
                        Math.min(DRAW_BOARD_COPY_HEIGHT, height - PPT_SHOW_BOARD_Y - BOTTOM_BAR_HEIGHT - PPT_BOTTOM_OFFSET)
                );
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
        pptShowBoard.drawBoardPenInitial();
    }

    public static void main(String[] args) {
        new MyHome().run();
    }
}

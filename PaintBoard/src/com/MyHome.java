package com;

import com.Listeners.ChoseListener;
import com.Listeners.MyShapesListener.*;

import com.Pages.*;
import com.Pages.BasePages.MyFrame;
import com.Pages.BasePages.ClearPanel;
import com.Pages.MenuBar.*;
import com.Pages.MenuBar.MenuBar;
import com.Pages.SlideBar.ImageShowBoard;
import com.Paint.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    private MenuBar menuBar;
    private FileButtonsBar fileButtons;
    private InsertButtonsBar insertButtons;
    private DrawButtonsBar drawButtons;
    private PenStyleBar penStyleButtons;
    //菜单的按键栏（topBar）
    private static final int TOP_BAR_HEIGHT = 130;
    private EmptyFillPanel topBar;
    private ClearPanel topButtons; //当前topBar里含有的buttons


    //顶部全部栏目的高度，包括标题栏，菜单栏，菜单栏包括菜单和菜单按键
    private static final int ALL_TOP_BAR_HEIGHT = TITLE_BAR_HEIGHT + MENU_BAR_HEIGHT + TOP_BAR_HEIGHT;

    //幻灯片下拉列表
    private static final int SLIDE_BAR_WIDTH = 400;
    private EmptyFillPanel imgSlideBar;
    ImageShowBoard imageShowGroup;    //图像列表
    JScrollPane imgScrollPane;    //下拉列表框，用来放置生成的图像列表，形成滚动条

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
    private JPanel pptShowBoard = new JPanel(new GridLayout(1,1));
    private DrawJPanel drawBoard = new DrawJPanel();

    public MyHome() {

        setComponentChangeListener();   //设置窗口改变，组件大小也跟着改变的监听器

        //菜单栏初始化
        initMenu();

        //下拉列表初始化
        initSlideBar();


        //画板
        pptShowBoard.add(drawBoard);
        pptShowBoard.setBounds(
                PPT_SHOW_BOARD_X,
                PPT_SHOW_BOARD_Y,
                getWidth() - PPT_SHOW_BOARD_X - PPT_RIGHT_OFFSET,
                getHeight() - PPT_SHOW_BOARD_Y - BOTTOM_BAR_HEIGHT - PPT_BOTTOM_OFFSET
        );
        add(pptShowBoard);
    }

    //刷新组件
    private void refresh(Container con){
        con.setVisible(false);
        con.setVisible(true);
    }
    //刷新本界面
    private void refresh(){
        refresh(this);
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
                imgScrollPane.setPreferredSize(new Dimension(
                        350,
                        height -(ALL_TOP_BAR_HEIGHT+ BOTTOM_BAR_HEIGHT)
                ));
                imgSlideBar.validate();
                pptShowBoard.setBounds(
                        PPT_SHOW_BOARD_X,
                        PPT_SHOW_BOARD_Y,
                        Math.min(DRAW_BOARD_COPY_WIDTH, width - PPT_SHOW_BOARD_X - PPT_RIGHT_OFFSET),
                        Math.min(DRAW_BOARD_COPY_HEIGHT, height - PPT_SHOW_BOARD_Y - BOTTOM_BAR_HEIGHT - PPT_BOTTOM_OFFSET)
                );
                pptShowBoard.validate();
            }
        });
    }


    /**
     * 初始化菜单栏的相关操作
     */
    private void initMenu(){
        //菜单栏定义
        menuBar = new MenuBar();
        fileButtons = new FileButtonsBar();
        insertButtons = new InsertButtonsBar();
        drawButtons = new DrawButtonsBar();
        penStyleButtons = new PenStyleBar();
        //菜单的按键栏（topBar）定义
        topBar = new EmptyFillPanel(0, TITLE_BAR_HEIGHT + MENU_BAR_HEIGHT, SCREEN_WIDTH, TOP_BAR_HEIGHT);
        menuBar.setBounds(0, TITLE_BAR_HEIGHT, SCREEN_WIDTH, MENU_BAR_HEIGHT);
        add(menuBar);
        //设置菜单栏的初始情况
        topBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        topBar.add(fileButtons);
        topButtons = fileButtons;
        add(topBar);
        //设置菜单切换栏监听器
        setMenuChangeListener();
        //设置菜单按钮监听器
        setMenuButtonsListener();
    }
    /**
     *     改变菜单栏的按钮群
     */
    private void changeTopBarButtons(ClearPanel nextButtons){
        topBar.remove(topButtons);
        topBar.add(nextButtons);
        topButtons = nextButtons;
        refresh(topBar);
    }
    /**
     * 设置菜单栏的切换监听器
     */
    private void setMenuChangeListener(){
        menuBar.setButtonsListener(
                new MouseAdapter() {    //点击“文件”按钮时发生的动作
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        changeTopBarButtons(fileButtons);
                    }
                },
                new MouseAdapter() {    //点击“插入”按钮时发生的动作
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        changeTopBarButtons(insertButtons);
                    }
                },
                new MouseAdapter() {    //点击“绘画”按钮时发生的动作
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        changeTopBarButtons(drawButtons);
                    }
                },
                new MouseAdapter() {    //点击“画笔”按钮时发生的动作
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        changeTopBarButtons(penStyleButtons);
                    }
                }
        );
    }
    /**
     * 设置菜单栏各个按钮的监听器
     */
    private void setMenuButtonsListener(){
        //设置文件按钮们的监听器
        fileButtons.setButtonsListener(
                new MouseAdapter() {    //点击“新建”按钮的监听器
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //新开一个页面，但是退出的时候，会全退出
                        new MyHome().run();
                    }
                },
                new MouseAdapter() {    //点击“打开文件”按钮的监听器
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //填充加载代码。如下。
                        MyDrawPPT myDrawPPT = new MyDrawPPT();
                        myDrawPPT.loadPPT();
                        imageShowGroup.setDrawPPT(myDrawPPT);
                        imgSlideBar.validate();
                    }
                },
                new MouseAdapter() {    //点击“保存文件”按钮的监听器
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //填充保存代码。如下。根据实际情况调整
                        imageShowGroup.getDrawPPT().savePPT();
                    }
                }
        );
        //设置插入按钮们的监听器
        insertButtons.setButtonsListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //填充新建代码，以下代码有bug:不知道JList更新元素后咋更新
                        imageShowGroup.addNewPicture(new DrawJPanel());
                        imgScrollPane.validate();
                    }
                },
                new MouseAdapter() {    //“插入图片”的监听器
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        drawBoard.setBoardListener(new ImageListener());
                    }
                },
                new MouseAdapter() {    //“插入文字”的监听器
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        drawBoard.setBoardListener(new TextListener());
                    }
                }
        );
        //设置绘画按钮们的监听器
        drawButtons.setButtonsListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        drawBoard.setBoardListener(new ChoseListener());
                    }
                },
                new MouseAdapter() {    //“直线”的监听器
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        drawBoard.setBoardListener(new LineListener());
                    }
                },
                new MouseAdapter() {    //“曲线”的监听器
                    @Override
                    public void mousePressed(MouseEvent e) {
                        drawBoard.setBoardListener(new CurveListener());
                    }
                },
                new MouseAdapter() {    //多边形监听器
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        drawBoard.setBoardListener(new PolygonListener());
                    }
                },
                new MouseAdapter() {    //圆形监听器
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        drawBoard.setBoardListener(new CircleListener(false));
                    }
                },
                new MouseAdapter() {    //实心圆形监听器
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        drawBoard.setBoardListener(new CircleListener(true));
                    }
                },
                new MouseAdapter() {    //矩形监听器
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        drawBoard.setBoardListener(new RectangleListener(false));
                    }
                },
                new MouseAdapter() {    //实心矩形监听器
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        drawBoard.setBoardListener(new RectangleListener(true));
                    }
                }
        );
    }

    /**
     * 初始化下拉列表
     */
    public void initSlideBar(){
        imgSlideBar = new EmptyFillPanel(
                0,
                ALL_TOP_BAR_HEIGHT,
                SLIDE_BAR_WIDTH,
                getHeight()-(ALL_TOP_BAR_HEIGHT + BOTTOM_BAR_HEIGHT
                ));
        imageShowGroup = new ImageShowBoard();    //图像列表
        imgScrollPane = new JScrollPane(imageShowGroup);    //下拉列表框，用来放置生成的图像列表，形成滚动条
        imgScrollPane.setPreferredSize(new Dimension(350,getHeight()-(ALL_TOP_BAR_HEIGHT + BOTTOM_BAR_HEIGHT)));    //设置下拉框大小
        imgSlideBar.add(imgScrollPane);     //将滑动板加入到界面内
        add(imgSlideBar);
    }

    public void run(){
        setVisible(true);
        drawBoard.drawBoardPenInitial();
    }

}

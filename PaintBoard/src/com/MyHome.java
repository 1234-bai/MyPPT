package com;

import com.Pages.*;
import com.Pages.BasePages.MyFrame;
import com.Pages.BasePages.ClearPanel;
import com.Pages.MenuBar.*;
import com.Pages.MenuBar.MenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyHome extends MyFrame {

    //空白填充框
    private static class EmptyFillPanel extends JPanel{
        public EmptyFillPanel(int x, int y, int width, int height) {
            setBounds(x, y, width, height);
            setBackground(CONSTANTS.MY_COLOR.SLIDE_LIST_COLOR);
            setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
        }
    }

    private static final int MENU_BAR_HEIGHT = 40;
    private static final int TOP_BAR_HEIGHT = 130;

    private final MenuBar menuBar = new MenuBar();
    private ClearPanel topButtons; //当前topBar里含有的buttons
    private final FileButtonsBar fileButtons = new FileButtonsBar();
    private final InsertButtonsBar insertButtons = new InsertButtonsBar();
    private final DrawButtonsBar drawButtons = new DrawButtonsBar();
    private final PenStyleBar penStyleButtons = new PenStyleBar();

    //菜单的按键栏
    EmptyFillPanel topBar = new EmptyFillPanel(0, TITLE_BAR_HEIGHT + MENU_BAR_HEIGHT, SCREEN_WIDTH, TOP_BAR_HEIGHT);

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
    }

    //刷新组件
    private void refresh(Container con){
        con.setVisible(false);
        con.setVisible(true);
    }

    //改变菜单栏的按钮群
    private void changeTopBarButtons(ClearPanel nextButtons){
        topBar.remove(topButtons);
        topBar.add(nextButtons);
        topButtons = nextButtons;
        refresh(topBar);
    }
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
    }

    public static void main(String[] args) {
        new MyHome().run();
    }
}

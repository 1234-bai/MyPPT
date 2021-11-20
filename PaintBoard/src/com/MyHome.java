package com;

import com.Pages.*;
import com.Pages.BasePages.MyFrame;
import com.Pages.MenuBar.*;
import com.Pages.MenuBar.MenuBar;

import javax.swing.*;
import java.awt.*;

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

    private MenuBar menuBar = new MenuBar();
    private FileButtonsBar fileButtons = new FileButtonsBar();
    private InsertButtonsBar insertButtons = new InsertButtonsBar();
    private DrawButtonsBar drawButtonsBar = new DrawButtonsBar();

    public MyHome() {
        //菜单栏
        menuBar.setBounds(0, TITLE_BAR_HEIGHT, SCREEN_WIDTH, MENU_BAR_HEIGHT);
        add(menuBar);

        //菜单的按键栏
        EmptyFillPanel topBar = new EmptyFillPanel(0, TITLE_BAR_HEIGHT + MENU_BAR_HEIGHT, SCREEN_WIDTH, TOP_BAR_HEIGHT);
        topBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        topBar.add(fileButtons);
        topBar.add(insertButtons);
        topBar.add(drawButtonsBar);
        add(topBar);
    }

    public void run(){
        setVisible(true);
    }

    public static void main(String[] args) {
        new MyHome().run();
    }
}

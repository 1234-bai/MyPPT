package com.Pages.MenuBar;

import com.Pages.BasePages.MyButton;
import com.Pages.CONSTANTS;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JPanel {
    private static class MenuBarButton extends MyButton{
        public MenuBarButton(String text){
            setForeground(Color.WHITE);
            setFont(new Font("微软雅黑", Font.BOLD, 20));
            setPreferredSize(new Dimension(100, 40));
            setText(text);
        }
    }

    private MenuBarButton fileMenu = new MenuBarButton("文件");
    private MenuBarButton insertMenu = new MenuBarButton("插入");
    private MenuBarButton drawMenu = new MenuBarButton("绘图");
    private MenuBarButton penStyleMenu = new MenuBarButton("画笔");

    public MenuBar() {
        setBackground(CONSTANTS.MY_COLOR.TITLE_BAR_COLOR);
        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(fileMenu);
        add(insertMenu);
        add(drawMenu);
        add(penStyleMenu);
    }

    public MenuBarButton getFileMenu() {
        return fileMenu;
    }

    public MenuBarButton getInsertMenu() {
        return insertMenu;
    }

    public MenuBarButton getDrawMenu() {
        return drawMenu;
    }
}

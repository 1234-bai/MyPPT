package com.Pages.MenuBar;

import com.Pages.BasePages.ClearButton;
import com.Pages.CONSTANTS;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JPanel {
    private static class MenuBarButton extends ClearButton {
        public MenuBarButton(String text){
            setForeground(Color.WHITE);
            setFont(new Font("微软雅黑", Font.BOLD, 20));
            setPreferredSize(new Dimension(100, 40));
            setText(text);
        }
    }

    private final MenuBarButton fileMenu = new MenuBarButton("文件");
    private final MenuBarButton insertMenu = new MenuBarButton("插入");
    private final MenuBarButton drawMenu = new MenuBarButton("绘图");
    private final MenuBarButton penStyleMenu = new MenuBarButton("画笔");

    public MenuBar() {
        setBackground(CONSTANTS.MY_COLOR.TITLE_BAR_COLOR);
        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(fileMenu);
        add(insertMenu);
        add(drawMenu);
        add(penStyleMenu);
    }

    public JButton getFileMenu() {
        return fileMenu;
    }

    public JButton getInsertMenu() {
        return insertMenu;
    }

    public JButton getDrawMenu() {
        return drawMenu;
    }

    public JButton getPenStyleMenu() {
        return penStyleMenu;
    }
}

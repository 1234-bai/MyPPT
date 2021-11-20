package com.Pages;

import com.Pages.BasePages.MyButton;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JPanel {
    private static class MenuBarButton extends MyButton{
        public MenuBarButton(){
            setForeground(Color.WHITE);
            setFont(new Font("微软雅黑", Font.BOLD, 20));
        }
    }

    public MenuBar() {
        setBackground(CONSTANTS.MY_COLOR.TITLE_BAR_COLOR);
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
    }
}

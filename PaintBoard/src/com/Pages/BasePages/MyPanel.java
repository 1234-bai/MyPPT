package com.Pages.BasePages;

import com.Pages.CONSTANTS;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    public MyPanel() {
        setOpaque(false);   //变透明。透明的前提是有背景。
        setBackground(CONSTANTS.MY_COLOR.SLIDE_LIST_COLOR);
    }
}

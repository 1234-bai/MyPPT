package com.Pages.BasePages;

import com.Pages.CONSTANTS;

import javax.swing.*;

/**
 * 透明的干净的Panel
 */
public class ClearPanel extends JPanel {
    public ClearPanel() {
        setOpaque(false);   //变透明。透明的前提是有背景。
        setBackground(CONSTANTS.MY_COLOR.SLIDE_LIST_COLOR);
    }
}

package com.Pages.BasePages;

import javax.swing.*;

/**
 * 透明的干净的按钮
 */
public class ClearButton extends JButton {
    public ClearButton() {
        setOpaque(false);   //透明
        setBorderPainted(false);    //取消边框
        setBackground(null);    //取消背景
        setFocusPainted(false); //取消焦点
    }
}

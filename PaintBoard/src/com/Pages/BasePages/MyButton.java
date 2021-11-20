package com.Pages.BasePages;

import javax.swing.*;

public class MyButton extends JButton {
    public MyButton() {
        setOpaque(false);   //透明
        setBorderPainted(false);    //取消边框
        setBackground(null);    //取消背景
        setFocusPainted(false); //取消焦点
    }
}

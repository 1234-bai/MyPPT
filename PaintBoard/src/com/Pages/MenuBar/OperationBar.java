package com.Pages.MenuBar;

import com.Pages.BasePages.ImageButton;
import com.Pages.BasePages.ClearPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class OperationBar extends ClearPanel {

    private final ImageButton choseButton = new ImageButton("选取",getRightResourceFilePath("images/MenuBar/Operation/chose.png"));
    private final ImageButton revokeButton = new ImageButton("撤销",getRightResourceFilePath("images/MenuBar/Operation/revoke.png"));
    private final ImageButton redoButton = new ImageButton("重做",getRightResourceFilePath("images/MenuBar/Operation/redo.png"));
    private final ImageButton deleteButton = new ImageButton("删除",getRightResourceFilePath("images/MenuBar/Operation/delete.png"));

    public OperationBar() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(choseButton);
        add(revokeButton);
        add(redoButton);
        add(deleteButton);
    }

    public void setButtonsListener(
            MouseListener choseListener,
            MouseListener revokeListener,
            MouseListener redoListener,
            MouseListener deleteListener
    ){
        choseButton.addMouseListener(choseListener);
        revokeButton.addMouseListener(revokeListener);
        redoButton.addMouseListener(redoListener);
        deleteButton.addMouseListener(deleteListener);
    }
}

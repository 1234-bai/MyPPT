package com.Pages.MenuBar;

import com.Pages.BasePages.ImageButton;
import com.Pages.BasePages.ClearPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class OperationBar extends ClearPanel {

    private final ImageButton choseButton = new ImageButton("选取","PaintBoard/images/MenuBar/Draw/chose.png");
    private final ImageButton revokeButton = new ImageButton("撤销","PaintBoard/images/MenuBar/Draw/revoke.png");
    private final ImageButton redoButton = new ImageButton("重做","PaintBoard/images/MenuBar/Draw/redo.png");
    private final ImageButton deleteButton = new ImageButton("删除","PaintBoard/images/MenuBar/Operation/delete.png");

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

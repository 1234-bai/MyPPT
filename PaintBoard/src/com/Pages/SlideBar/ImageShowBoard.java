package com.Pages.SlideBar;

import com.Pages.BasePages.ClearPanel;
import com.Paint.DrawJPanel;
import com.Paint.MyDrawPPTIml;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class ImageShowBoard {

    private final Vector<ImageIcon> copyGroup = new Vector<>();   //获得的PPT文件的副本组
    private final JList<ImageIcon> copyShowBoard = new JList<>(copyGroup);    //副本的展示板
    private MyDrawPPTIml drawPPT;   //ppt滑动列表展示的ppt;

    public ImageShowBoard() {
        copyShowBoard.setCellRenderer(new ImageCellRenderer()); //为List加入的单元渲染器
    }

    public void setDrawPPT(MyDrawPPTIml drawPP) {
        this.drawPPT = drawPP;
        for(DrawJPanel picture : drawPPT.getMyPPT()){
            picture.redraw();
            copyGroup.add(getFitIcon(picture.getDrawBoard_copy()));
        }
    }

    public static ImageIcon getFitIcon(Image img){
        Image newImg = img.getScaledInstance(300,300,Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }

    public JList<ImageIcon> getCopyShowBoard() {
        return copyShowBoard;
    }
}

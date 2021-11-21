package com.Pages.SlideBar;

import com.Pages.BasePages.ClearPanel;
import com.Paint.DrawJPanel;
import com.Paint.MyDrawPPT;
import com.Paint.MyDrawPPTIml;

import javax.swing.*;
import java.awt.*;

//副本的展示板
public class ImageShowBoard extends JList<ImageIcon>{

    public static ImageIcon getFitIcon(Image img){
        Image newImg = img.getScaledInstance(300,300,Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }

    private final DefaultListModel<ImageIcon> copyGroup = new DefaultListModel<>();   //获得的PPT文件的副本组
    private MyDrawPPTIml drawPPT = new MyDrawPPT();   //ppt滑动列表展示的ppt;默认有一个空的

    public ImageShowBoard() {
        setCellRenderer(new ImageCellRenderer()); //为List加入的单元渲染器
    }

    public void setDrawPPT(MyDrawPPTIml drawPP) {
        this.drawPPT = drawPP;
        for(DrawJPanel picture : drawPPT.getMyPPT()){
            picture.redraw();
            copyGroup.addElement(getFitIcon(picture.getDrawBoard_copy()));
        }
        setModel(copyGroup);
    }

    public void addNewPicture(DrawJPanel newPicture){
        try {
            newPicture.redraw();
            drawPPT.getMyPPT().add(newPicture);
            copyGroup.addElement(getFitIcon(newPicture.getDrawBoard_copy()));
            setModel(copyGroup);
            setSelectedIndex(copyGroup.getSize()-1);    //选中最顶上的那一页
        }catch (NullPointerException e){
            System.out.println("新加画板为空");
            e.printStackTrace();
        }

    }

    public void clear(){
        copyGroup.clear();
        setModel(copyGroup);
    }

    public MyDrawPPTIml getDrawPPT() {
        return drawPPT;
    }
}

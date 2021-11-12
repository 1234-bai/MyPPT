package com.Listeners.ChildrenListener;

import com.Listeners.ParentListener.DrawListener;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ImageListener extends DrawListener {
    int x;
    int y;
    Image image;

    @Override
    public void mouseClicked(MouseEvent e) {

        //点击画板选点并弹出文件选择框，后续图片以该点为左上角显示
        x = e.getX();
        y = e.getY();

        //从硬盘中选择图片
        FileSystemView fileSystemView = FileSystemView.getFileSystemView();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(fileSystemView.getHomeDirectory());
        fileChooser.setDialogTitle("请选择要插入的图片");
        fileChooser.setApproveButtonText("确定");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);  //限制只能选择文件
        fileChooser.setFileFilter(new FileNameExtensionFilter("image(*.jpg, *.png)", "jpg", "png"));    //限制只能选择.jpg与.png

        //检测图像选取结果
        int result = fileChooser.showOpenDialog(null);
        if (JFileChooser.APPROVE_OPTION == result) {
            String path = fileChooser.getSelectedFile().getPath();

            //测试输出
            System.out.println("path: " + path);

            image = Toolkit.getDefaultToolkit().createImage(path);
//            image = new ImageIcon(ClassLoader.getSystemResource(path)).getImage();


            //存在问题，图片不显示
            getListenerPen().drawImage(image, x, y, getDrawBoard());

            System.out.println("图像绘制完毕");
        }
    }
}

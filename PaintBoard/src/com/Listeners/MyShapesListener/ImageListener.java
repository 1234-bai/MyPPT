package com.Listeners.MyShapesListener;

import com.Listeners.BaseListener.DrawListener;
import com.MyShapes.ChildrenShapes.MyImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class ImageListener extends DrawListener {

    @Override
    public void mouseClicked(MouseEvent e) {

        //点击画板选点并弹出文件选择框，后续图片以该点为左上角显示
        int x = e.getX(), y = e.getY();

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

            Image image = null;
            //image = Toolkit.getDefaultToolkit().getImage(path);
            //image = new ImageIcon(ClassLoader.getSystemResource(path)).getImage();
            try {
                image = ImageIO.read(new File(path));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            getListenerPen().drawImage(image, x, y,null);
            getListenerPen_copy().drawImage(image, x, y, null);

            getContentsGroup().add(new MyImage(
                    image,
                    x,
                    y,
                    getListenerPen().getColor(),
                    ((BasicStroke)getListenerPen().getStroke()).getLineWidth()
            ));
        }
    }
}

package com.Paint;

import com.MyShapes.ChildrenShapes.MyCircle;
import com.MyShapes.ChildrenShapes.MyLine;
import com.MyShapes.ChildrenShapes.MyPolygon;
import com.MyShapes.ChildrenShapes.MyRectangle;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.Vector;

public class DrawJPanelFileUtil {

    private static File choseFile(String fileType, String dialogTitle, String approveButtonText){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(dialogTitle);
        fileChooser.setApproveButtonText(approveButtonText);
        fileChooser.setFileFilter(new FileNameExtensionFilter("*."+fileType,fileType));
        fileChooser.setMultiSelectionEnabled(false);
        int result = fileChooser.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    public static DrawJPanel loadDrawBoard(){
        File file = choseFile("myppt", "载入...","确定");
        if(file == null){return null;}
        DrawJPanel drawBoard = new DrawJPanel();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String data;
            //按行读文件
            while ((data = bufferedReader.readLine()) != null) {
                String[] dataSplit = data.split(" \\| ");    //对字符串按照设定规则进行分片
                String type = dataSplit[0];
                switch (type) {
                    case "MyLine": {

                        //恢复Line2D
                        double x1 = Double.parseDouble(dataSplit[1]);
                        double y1 = Double.parseDouble(dataSplit[2]);
                        double x2 = Double.parseDouble(dataSplit[3]);
                        double y2 = Double.parseDouble(dataSplit[4]);
                        Line2D line = new Line2D.Double(x1, y1, x2, y2);

                        //恢复其他参数
                        Color color = new Color(Integer.parseInt(dataSplit[7]));
                        float lineWidth = Float.parseFloat(dataSplit[8]);

                        //添加进图形栈
                        drawBoard.getContentsGroup().add(new MyLine(line, color, lineWidth));

                        break;
                    }
                    case "MyCircle": {

                        //恢复Ellipse2D
                        double x = Double.parseDouble(dataSplit[1]);
                        double y = Double.parseDouble(dataSplit[2]);
                        double w = Double.parseDouble(dataSplit[3]);
                        double h = Double.parseDouble(dataSplit[4]);
                        Ellipse2D ellipse = new Ellipse2D.Double(x, y, w, h);

                        //恢复其他参数
                        boolean isFilled = Boolean.parseBoolean(dataSplit[5]);
                        double coordinateX = Double.parseDouble(dataSplit[6]);
                        double coordinateY = Double.parseDouble(dataSplit[7]);
                        Color color = new Color(Integer.parseInt(dataSplit[8]));
                        float lineWidth = Float.parseFloat(dataSplit[9]);

                        //添加进图形栈
                        drawBoard.getContentsGroup().add(new MyCircle(ellipse, coordinateX, coordinateY, color, lineWidth, isFilled));

                        break;
                    }
                    case "MyRectangle": {

                        //恢复Rectangle2D
                        double x = Double.parseDouble(dataSplit[1]);
                        double y = Double.parseDouble(dataSplit[2]);
                        double w = Double.parseDouble(dataSplit[3]);
                        double h = Double.parseDouble(dataSplit[4]);
                        Rectangle2D rectangle = new Rectangle2D.Double(x, y, w, h);

                        //恢复其他参数
                        boolean isFilled = Boolean.parseBoolean(dataSplit[5]);
                        double coordinateX = Double.parseDouble(dataSplit[6]);
                        double coordinateY = Double.parseDouble(dataSplit[7]);
                        Color color = new Color(Integer.parseInt(dataSplit[8]));
                        float lineWidth = Float.parseFloat(dataSplit[9]);

                        //添加进图形栈
                        drawBoard.getContentsGroup().add(new MyRectangle(rectangle, coordinateX, coordinateY, color, lineWidth, isFilled));

                        break;
                    }
                    case "MyPolygon": {

                        //恢复Polygon的坐标集合
                        String[] ss1 = dataSplit[1].split("[\\[\\] ,]");
                        Vector<Integer> x = new Vector<>();
                        for (String s : ss1) {
                            if (!s.equals("")) {
                                x.add(Integer.parseInt(s));
                            }
                        }
                        String[] ss2 = dataSplit[2].split("[\\[\\] ,]");
                        Vector<Integer> y = new Vector<>();
                        for (String s : ss2) {
                            if (!s.equals("")) {
                                y.add(Integer.parseInt(s));
                            }
                        }

                        //恢复其他参数
                        Color color = new Color(Integer.parseInt(dataSplit[6]));
                        float lineWidth = Float.parseFloat(dataSplit[7]);

                        //添加进图形栈
                        drawBoard.getContentsGroup().add(new MyPolygon(x, y, color, lineWidth));
                        break;
                    }
                }
            }
            drawBoard.redraw();
            drawBoard.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return drawBoard;
    }

    public static boolean saveDrawBoard(DrawJPanel drawBoard){
        File file = choseFile("myppt", "另存为...","确定");
        if(file == null) return false;
        if(!file.getPath().endsWith(".myppt")){
            file = new File(file.getPath()+".myppt");
        }
        try{
            if(!file.exists()){
                file.createNewFile();   //文件不存在则新建文件
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
            bufferedWriter.write(drawBoard.toString());
            bufferedWriter.flush(); //把缓冲区内容压入文件
            bufferedWriter.close(); //关闭文件
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}

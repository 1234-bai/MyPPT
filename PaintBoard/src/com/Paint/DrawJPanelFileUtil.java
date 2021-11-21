package com.Paint;

import com.MyShapes.ChildrenShapes.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

public class DrawJPanelFileUtil {

    /**
     * 调用系统的文件选择类。选择文件，形成文件对象
     *
     * @param fileType          文件后缀名，不带点
     * @param dialogTitle       对话框的标题
     * @param approveButtonText 确认按钮的文字
     * @param dialogType        对话框类型（open/save）
     * @return 选择的文件对象
     */
    public static File choseFile(String fileType, String dialogTitle, String approveButtonText, String dialogType) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(dialogTitle);
        fileChooser.setApproveButtonText(approveButtonText);
        fileChooser.setFileFilter(new FileNameExtensionFilter("*." + fileType, fileType));
        fileChooser.setMultiSelectionEnabled(false);
        int result;
        if (dialogType.equals("save")) {
            result = fileChooser.showSaveDialog(null);
        } else {
            result = fileChooser.showOpenDialog(null);
        }
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }


    /**
     * 读取画板
     * 已弃用
     *
     * @param file 要加载的文件形成的对象
     * @return 加载好的画板
     */
    public static DrawJPanel loadDrawBoard(File file) {
        if (file == null) {
            return null;
        }
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
                    case "MyCurve": {

                        //读取Curve的直线集合
                        ArrayList<Line2D> lineGroup = new ArrayList<>();
                        int size = Integer.parseInt(dataSplit[1]);
                        for (int i = 0; i < size; i++) {
                            double x1 = Double.parseDouble(dataSplit[i * 4 + 2]);
                            double y1 = Double.parseDouble(dataSplit[i * 4 + 3]);
                            double x2 = Double.parseDouble(dataSplit[i * 4 + 4]);
                            double y2 = Double.parseDouble(dataSplit[i * 4 + 5]);
                            Line2D line = new Line2D.Double(x1, y1, x2, y2);
                            lineGroup.add(line);
                        }

                        //恢复其他参数
                        double coordinateX = Double.parseDouble(dataSplit[size * 4 + 2]);
                        double coordinateY = Double.parseDouble(dataSplit[size * 4 + 3]);
                        Color color = new Color(Integer.parseInt(dataSplit[size * 4 + 4]));
                        float lineWidth = Float.parseFloat(dataSplit[size * 4 + 5]);

                        //添加进图形栈
                        MyCurve myCurve = new MyCurve(coordinateX, coordinateY, color, lineWidth);
                        myCurve.setLineGroup(lineGroup);
                        drawBoard.getContentsGroup().add(myCurve);

                        break;
                    }
                    case "MyText": {

                        //恢复Font
                        String name = dataSplit[1];
                        int style = Integer.parseInt(dataSplit[2]);
                        int size = Integer.parseInt(dataSplit[3]);
                        Font font = new Font(name, style, size);

                        //恢复其他属性
                        int width = Integer.parseInt(dataSplit[4]);
                        int height = Integer.parseInt(dataSplit[5]);
                        String text = dataSplit[6];
                        double coordinateX = Double.parseDouble(dataSplit[7]);
                        double coordinateY = Double.parseDouble(dataSplit[8]);
                        Color color = new Color(Integer.parseInt(dataSplit[9]));
                        float lineWidth = Float.parseFloat(dataSplit[10]);

                        //添加进图形栈
                        drawBoard.getContentsGroup().add(new MyText(coordinateX, coordinateY, width, height, color, lineWidth, font, text));

                        break;
                    }
                    case "MyImage": {

                        //恢复Image
                        String path = dataSplit[1];
                        System.out.println(path);
                        Image image = null;
                        try {
                            image = ImageIO.read(new File(path));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //恢复其他属性
                        double coordinateX = Double.parseDouble(dataSplit[2]);
                        double coordinateY = Double.parseDouble(dataSplit[3]);
                        Color color = new Color(Integer.parseInt(dataSplit[4]));
                        float lineWidth = Float.parseFloat(dataSplit[5]);

                        //添加进图形栈
                        drawBoard.getContentsGroup().add(new MyImage(image, coordinateX, coordinateY, color, lineWidth, path));

                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return drawBoard;
    }

    /**
     * String转换成画板
     * 用于ppt的读取
     *
     * @param strings 图像构成的String数组
     * @return 转换后的画板
     */
    public static DrawJPanel toDrawBoard(ArrayList<String> strings) {
        DrawJPanel drawBoard = new DrawJPanel();
        for (String data : strings) {
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
                    double coordinateX = Double.parseDouble(dataSplit[5]);
                    double coordinateY = Double.parseDouble(dataSplit[6]);
                    double translateX = Double.parseDouble(dataSplit[7]);
                    double translateY  = Double.parseDouble(dataSplit[8]);
                    Color color = new Color(Integer.parseInt(dataSplit[9]));
                    float lineWidth = Float.parseFloat(dataSplit[10]);

//                    Color color = new Color(Integer.parseInt(dataSplit[7]));
//                    float lineWidth = Float.parseFloat(dataSplit[8]);

                    //添加进图形栈
                    drawBoard.getContentsGroup().add(new MyLine(
                            line,
                            coordinateX,
                            coordinateY,
                            translateX,
                            translateY,
                            color,
                            lineWidth
                    ));

//                    drawBoard.getContentsGroup().add(new MyLine(line, color, lineWidth));

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
                    double translateX = Double.parseDouble(dataSplit[8]);
                    double translateY  = Double.parseDouble(dataSplit[9]);
                    Color color = new Color(Integer.parseInt(dataSplit[10]));
                    float lineWidth = Float.parseFloat(dataSplit[11]);

//                    Color color = new Color(Integer.parseInt(dataSplit[8]));
//                    float lineWidth = Float.parseFloat(dataSplit[9]);

                    //添加进图形栈
                    drawBoard.getContentsGroup().add(new MyCircle(
                            ellipse,
                            coordinateX,
                            coordinateY,
                            translateX,
                            translateY,
                            color,
                            lineWidth,
                            isFilled
                    ));

//                    drawBoard.getContentsGroup().add(new MyCircle(ellipse, coordinateX, coordinateY, color, lineWidth, isFilled));

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
                    double translateX = Double.parseDouble(dataSplit[8]);
                    double translateY  = Double.parseDouble(dataSplit[9]);
                    Color color = new Color(Integer.parseInt(dataSplit[10]));
                    float lineWidth = Float.parseFloat(dataSplit[11]);

//                    Color color = new Color(Integer.parseInt(dataSplit[8]));
//                    float lineWidth = Float.parseFloat(dataSplit[9]);

                    //添加进图形栈
                    drawBoard.getContentsGroup().add(new MyRectangle(
                            rectangle,
                            coordinateX,
                            coordinateY,
                            translateX,
                            translateY,
                            color,
                            lineWidth,
                            isFilled
                    ));

//                    drawBoard.getContentsGroup().add(new MyRectangle(rectangle, coordinateX, coordinateY, color, lineWidth, isFilled));

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
                    int size = Integer.parseInt(dataSplit[3]);
                    double coordinateX = Double.parseDouble(dataSplit[4]);
                    double coordinateY = Double.parseDouble(dataSplit[5]);
                    double translateX = Double.parseDouble(dataSplit[6]);
                    double translateY = Double.parseDouble(dataSplit[7]);
                    Color color = new Color(Integer.parseInt(dataSplit[8]));
                    float lineWidth = Float.parseFloat(dataSplit[9]);

//                    Color color = new Color(Integer.parseInt(dataSplit[6]));
//                    float lineWidth = Float.parseFloat(dataSplit[7]);

                    //添加进图形栈
                    drawBoard.getContentsGroup().add(new MyPolygon(
                            x,
                            y,
                            size,
                            coordinateX,
                            coordinateY,
                            translateX,
                            translateY,
                            color,
                            lineWidth
                    ));

//                    drawBoard.getContentsGroup().add(new MyPolygon(x, y, color, lineWidth));

                    break;
                }
                case "MyCurve": {

                    //读取Curve的直线集合
                    ArrayList<Line2D> lineGroup = new ArrayList<>();
                    int size = Integer.parseInt(dataSplit[1]);
                    for (int i = 0; i < size; i++) {
                        double x1 = Double.parseDouble(dataSplit[i * 4 + 2]);
                        double y1 = Double.parseDouble(dataSplit[i * 4 + 3]);
                        double x2 = Double.parseDouble(dataSplit[i * 4 + 4]);
                        double y2 = Double.parseDouble(dataSplit[i * 4 + 5]);
                        Line2D line = new Line2D.Double(x1, y1, x2, y2);
                        lineGroup.add(line);
                    }

                    //恢复其他参数
                    double coordinateX = Double.parseDouble(dataSplit[size * 4 + 2]);
                    double coordinateY = Double.parseDouble(dataSplit[size * 4 + 3]);
                    double translateX = Double.parseDouble(dataSplit[size * 4 + 4]);
                    double translateY = Double.parseDouble(dataSplit[size * 4 + 5]);
                    Color color = new Color(Integer.parseInt(dataSplit[size * 4 + 6]));
                    float lineWidth = Float.parseFloat(dataSplit[size * 4 + 7]);

//                    Color color = new Color(Integer.parseInt(dataSplit[size * 4 + 4]));
//                    float lineWidth = Float.parseFloat(dataSplit[size * 4 + 5]);

                    //添加进图形栈
                    MyCurve myCurve = new MyCurve(
                            coordinateX,
                            coordinateY,
                            translateX,
                            translateY,
                            color,
                            lineWidth
                    );

//                    MyCurve myCurve = new MyCurve(coordinateX, coordinateY, color, lineWidth);

                    myCurve.setLineGroup(lineGroup);
                    drawBoard.getContentsGroup().add(myCurve);

                    break;
                }
                case "MyText": {

                    //恢复Font
                    String name = dataSplit[1];
                    int style = Integer.parseInt(dataSplit[2]);
                    int size = Integer.parseInt(dataSplit[3]);
                    Font font = new Font(name, style, size);

                    //恢复其他属性
                    int width = Integer.parseInt(dataSplit[4]);
                    int height = Integer.parseInt(dataSplit[5]);
                    String text = dataSplit[6];
                    double coordinateX = Double.parseDouble(dataSplit[7]);
                    double coordinateY = Double.parseDouble(dataSplit[8]);
                    double translateX = Double.parseDouble(dataSplit[9]);
                    double translateY = Double.parseDouble(dataSplit[10]);
                    Color color = new Color(Integer.parseInt(dataSplit[11]));
                    float lineWidth = Float.parseFloat(dataSplit[12]);

//                    Color color = new Color(Integer.parseInt(dataSplit[9]));
//                    float lineWidth = Float.parseFloat(dataSplit[10]);

                    //添加进图形栈
                    drawBoard.getContentsGroup().add(new MyText(
                            coordinateX,
                            coordinateY,
                            translateX,
                            translateY,
                            width,
                            height,
                            color,
                            lineWidth,
                            font,
                            text
                    ));

//                    drawBoard.getContentsGroup().add(new MyText(coordinateX, coordinateY, width, height, color, lineWidth, font, text));

                    break;
                }
                case "MyImage": {

                    //恢复Image
                    String path = dataSplit[1];
                    System.out.println(path);
                    Image image = null;
                    try {
                        image = ImageIO.read(new File(path));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //恢复其他属性
                    double coordinateX = Double.parseDouble(dataSplit[2]);
                    double coordinateY = Double.parseDouble(dataSplit[3]);
                    double translateX = Double.parseDouble(dataSplit[4]);
                    double translateY = Double.parseDouble(dataSplit[5]);
                    Color color = new Color(Integer.parseInt(dataSplit[6]));
                    float lineWidth = Float.parseFloat(dataSplit[7]);

//                    Color color = new Color(Integer.parseInt(dataSplit[4]));
//                    float lineWidth = Float.parseFloat(dataSplit[5]);

                    //添加进图形栈
                    drawBoard.getContentsGroup().add(new MyImage(
                            image,
                            coordinateX,
                            coordinateY,
                            translateX,
                            translateY,
                            color,
                            lineWidth,
                            path
                    ));

//                    drawBoard.getContentsGroup().add(new MyImage(image, coordinateX, coordinateY, color, lineWidth, path));

                    break;
                }
            }
        }
        return drawBoard;
    }

    /**
     * 保存画板
     *
     * @param file      要保存的文件路径形成的对象
     * @param drawBoard 要保存的画板
     * @return 保存是否成功
     */
    public static boolean saveDrawBoard(File file, DrawJPanel drawBoard) {
        if (file == null) return false;
        if (!file.getPath().endsWith(".myppt")) {
            file = new File(file.getPath() + ".myppt");
        }
        try {
            if (!file.exists()) {
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

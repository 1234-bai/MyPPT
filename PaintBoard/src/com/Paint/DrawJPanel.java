package com.Paint;

import com.Listeners.BaseListener.DrawListener;
import com.MyShapes.BaseShape.MyShape;
import com.MyShapes.ChildrenShapes.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

public class DrawJPanel extends JPanel implements DrawJPanelIml{

    private Graphics2D drawBoardPen;  //画板的画笔通过监听器中的画笔展现。只有在父类容器可视化之后才能获得画笔！！

    private BufferedImage drawBoard_copy;    //画板JPanel的副本
    private Graphics2D drawBoardPen_copy;    //画笔的副本

    private DrawListener drawListener;  //画正在运行板的监听器，当按下不同的功能按钮的时候，将此监听器设置为按钮所对应的监听器

    private ArrayList<MyShape> contentsGroup = new ArrayList<>();    //内容数组，存放画过的图形

    private final ArrayList<MyShape> redoContentsGroup = new ArrayList<>();   //重做内容数组，存放撤销操作删除的图形


    /**
     * 外界通过获得本画板的画笔，从而达到在本画板画笔的基础上改变样式的功能
     * @return 本画板画笔
     */
    public Graphics2D getDrawBoardPen() {
        return drawBoardPen;
    }

    @Override
    public Image getDrawBoard_copy() {
        return drawBoard_copy;
    }

    /**
     *
     * @return 画板画笔的副本
     */
    public Graphics2D getDrawBoardPen_copy() {
        return drawBoardPen_copy;
    }

    /**
     * 刷新本画板，将副本内容载入画板。
     */
    public void refresh(){
        drawBoardPen.drawImage(drawBoard_copy, 0 ,0, null);
    }

    /**
     *
     * @return 获得现在同画板绑定的监听器
     */
    public DrawListener getDrawListener() {
        return drawListener;
    }


    public ArrayList<MyShape> getContentsGroup() {
        return contentsGroup;
    }

    /**
     * 窗口改变大小之后，调用此函数，然后原先绑定的Graphic全部换新了，指向了另一个对象。就得重新绑定一遍，并且样式也要全部载入一遍。
     * 但是画板本身还是自己，没有更换新的对象。
     * @param g 目前仍未搞清楚，这个g到底是谁的画笔
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(drawBoard_copy, 0, 0, null); //重绘
        drawBoardPenInitial();
        //this.setBorder(BorderFactory.createLineBorder(Color.red,3));
    }


    /**
     * 重画，画笔更新，副本更新。
     * 选取时调用。
     */
    public void redraw(){
//        super.paint(drawBoardPen);  //只需要清空副本就可以了，不需要清空原本。
        drawBoardPen_copy = null; drawBoard_copy = null;    //清空副本，但是监听器不清空
        createCopy();   //重新获得全新的副本
        penStyleRecover(drawBoardPen, drawBoardPen_copy);

        for(MyShape myShape : contentsGroup){    //因为重画后需要刷新，所以只在副本上画就可以了
            myShape.draw(drawBoardPen_copy);
        }
//        refresh();  //载入副本。不需要载入副本，因为做着一切的时候，原本没有发生任何变化
    }

    /**
     * 初始化画笔，是线条更加圆滑，减小锯齿。看起来更美观。
     */
    private void penStyleInitial(){
        drawBoardPen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawBoardPen.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);

        drawBoardPen_copy.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawBoardPen_copy.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);

        setPenStyle(1.5f);
    }


    /**
     * 画笔样式还原。
     * 目前画笔样式只有：颜色，线宽。
     * @param oldPen 要继承的原来的画笔
     * @param newPen 新生画笔
     */
    private void penStyleRecover(Graphics2D oldPen, Graphics2D newPen){
        newPen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        newPen.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);    //还原圆润度
        if(oldPen != null){
            newPen.setColor(oldPen.getColor());    //还原颜色
            newPen.setStroke(oldPen.getStroke());  //还原线宽
        }
    }

    /**
     * 创造本画板以及本画板画笔的副本
     */
    private void createCopy(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        drawBoard_copy = (BufferedImage) this.createImage((int)screenSize.getWidth(), (int)screenSize.getHeight());  //创建画板副本
//        drawBoard_copy = new BufferedImage((int)screenSize.getWidth(), (int)screenSize.getHeight(),BufferedImage.TYPE_INT_RGB);
        drawBoardPen_copy = drawBoard_copy.createGraphics();  //创建副本画笔。
    }

    /**
     * 画笔初始化，在父窗口显示后调用。
     * 完成的任务有：将父类画笔赋值给本身画笔。创建画板副本，同时创建副本画笔。
     */
    public void drawBoardPenInitial() {
        drawBoardPen = (Graphics2D) super.getGraphics();    //将父类画笔赋值给本身画笔
        if(drawBoard_copy == null){  //直接设置成最大尺寸的画布。这样在重绘过程中，不会因画板尺寸改变（画板以前画的东西都会丢失），引起副本的改变（副本不会更新，画的东西也就不会丢失了）
            createCopy();   //创造副本
            penStyleInitial();  //样式初始化
       } else{  //说明副本不为空，说明是画板重绘了。则将画笔样式全部复制给现在的画笔
            penStyleRecover(drawBoardPen_copy, drawBoardPen);  //样式还原。
        }
    }




    /**
     * 清空本画板的全部鼠标监听器
     */
    private void clearMouseListeners(){
        if(drawListener != null){
            removeMouseListener(drawListener);
            removeMouseMotionListener(drawListener);
            drawListener.setDrawBoard(null);    //将原来的监听器同本画板解绑
        }
    }

    /**
     * 设置本画板的监听器。
     * @param newListener  本画板需要绑定的监听器
     */
    public void setBoardListener(DrawListener newListener) {
        clearMouseListeners();
        drawListener = newListener;
        if(drawListener != null){  //如果监听器不为空，则
            drawListener.setDrawBoard(this); //将本画板绑定给监听器。同时本画板的画笔样式同样绑定给了监听器。
        } //如果为空，说明目的是清空鼠标画笔，直接退出就完了。
        super.addMouseListener(drawListener);  //调用系统监听器生效函数，使监听器能正常运作
        super.addMouseMotionListener(drawListener);
    }



    public Color getPenColor(){
        return drawBoardPen.getColor();
    }
    /**
     * 设置画笔样式，多个函数重载，通过参数类型调用的不同的函数
     * @param c 颜色变量：能够改变颜色和透明度，能够实现类荧光笔的效果
     */
    public void setPenStyle(Color c){
        if(drawBoardPen == null){return;}
        drawBoardPen.setColor(c);
        drawBoardPen_copy.setColor(c);
    }


    @Override
    public float getPenLineWidth(){
        return ((BasicStroke)drawBoardPen.getStroke()).getLineWidth();
    }
    /**
     * 调整画笔样式线宽
     * @param lineWidth 调整后的线宽
     */
    public void setPenStyle(float lineWidth){
        if(drawBoardPen == null){return;}
        BasicStroke stroke = new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        drawBoardPen.setStroke(stroke);
        drawBoardPen_copy.setStroke(stroke);
    }



    public void setTextFont(Font font){
        if(drawBoardPen == null){return;}
        drawBoardPen.setFont(font);
        drawBoardPen_copy.setFont(font);
    }

    /**
     * 画板转换成String，本质只保存contentsGroup
     * @return 返回contentsGroup的String值
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (MyShape myShape : contentsGroup) {
            if (myShape instanceof MyCircle ||
                    myShape instanceof MyLine ||
                    myShape instanceof MyPolygon ||
                    myShape instanceof MyRectangle) {
                stringBuilder.append(myShape);
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 保存画板内容
     * 由于当前只有一个画板，故先写该类中，等后续相关功能完善后再做修改
     */
    public void saveDrawJPanel(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("另存为...");
        fileChooser.setApproveButtonText("确定");
        fileChooser.setFileFilter(new FileNameExtensionFilter("*.myppt","myppt"));
        fileChooser.setMultiSelectionEnabled(false);

        int result = fileChooser.showSaveDialog(null);
        if(result==JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            if(!file.getPath().endsWith(".myppt")){
                file = new File(file.getPath()+".myppt");
            }
            System.out.println("file path = "+file.getPath());
            try{
                if(!file.exists()){
                    file.createNewFile();   //文件不存在则新建文件
                }
                BufferedWriter bufferedWriter = new BufferedWriter(
                        new FileWriter(file.getAbsoluteFile())
                );
                bufferedWriter.write(this.toString());
                bufferedWriter.flush(); //把缓冲区内容压入文件
                bufferedWriter.close(); //关闭文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 载入画板内容
     * 由于当前只有一个画板，故先写该类中，等后续相关功能完善后再做修改
     */
    public void loadDrawJPanel(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("载入...");
        fileChooser.setApproveButtonText("确定");
        fileChooser.setFileFilter(new FileNameExtensionFilter("*.myppt","myppt"));
        fileChooser.setMultiSelectionEnabled(false);

        int result = fileChooser.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();

            try {
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(file))
                );
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
                            contentsGroup.add(new MyLine(line, color, lineWidth));

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
                            contentsGroup.add(new MyCircle(ellipse, coordinateX, coordinateY, color, lineWidth, isFilled));

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
                            contentsGroup.add(new MyRectangle(rectangle, coordinateX, coordinateY, color, lineWidth, isFilled));

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
                            contentsGroup.add(new MyPolygon(x, y, color, lineWidth));
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        redraw();
        refresh();   //将载入图像显示出来
    }


    /**
     * 撤销
     * 存在问题：目前撤销与重做功能仅对画图功能有效，后续解决图像删除、平移等操作的撤销需要重写
     */
    public void revoke(){
        redoContentsGroup.add(contentsGroup.get(contentsGroup.size()-1));   //撤销图形移入重做图形栈
        contentsGroup.remove(contentsGroup.size()-1);   //移除栈顶图形
        redraw();
        refresh();
    }

    /**
     * 重做
     */
    public void redo(){

        //重做栈非空时才能重做
        if(!redoContentsGroup.isEmpty()){
            contentsGroup.add(redoContentsGroup.get(redoContentsGroup.size()-1));
            redoContentsGroup.remove(redoContentsGroup.size()-1);
            redraw();
            refresh();
        }
    }

    /**
     * 清空重做栈
     */
    public void clearRedoContentsGroup(){
        redoContentsGroup.clear();
    }

    public void setContentsGroup(ArrayList<MyShape> contentsGroup) {
        this.contentsGroup = contentsGroup;
    }
}

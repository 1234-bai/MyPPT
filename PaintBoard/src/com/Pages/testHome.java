package com.Pages;

import com.Listeners.BaseListener.DrawListener;
import com.Listeners.ChoseListener;
import com.Listeners.MyShapesListener.*;
import com.Paint.DrawJPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class testHome extends JFrame {

    private final DrawJPanel drawBoard = new DrawJPanel();

    private JButton createShapeButton(String name, DrawListener listener) {
        JButton jButton = new JButton(name);
        jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawBoard.setBoardListener(listener);
            }
        });
        return jButton;
    }

    public testHome() throws HeadlessException {

//------------------------------------------------------------------------------------------------------------------
        //直线
        JButton lineButton = createShapeButton("", new LineListener());
        lineButton.setIcon(new ImageIcon("PaintBoard/images/line.png"));
        lineButton.setSize(200,200);

        //多边形
        JButton polyButton = createShapeButton("", new PolygonListener());
        polyButton.setIcon(new ImageIcon("PaintBoard/images/polygon.jpg"));
        polyButton.setSize(200,200);

        //曲线
        JButton curveButton = createShapeButton("", new CurveListener());
        curveButton.setIcon(new ImageIcon("PaintBoard/images/curve.png"));
        curveButton.setSize(200,200);

        //圆形
        JButton circButton = createShapeButton("", new CircleListener(false));
        circButton.setIcon(new ImageIcon("PaintBoard/images/ellipse.png"));
        circButton.setSize(200,200);

        //实心圆形
        JButton fillCircButton = createShapeButton("", new CircleListener(true));
        fillCircButton.setIcon(new ImageIcon("PaintBoard/images/filledellipse.png"));
        fillCircButton.setSize(200,200);

        //矩形
        JButton rectButton = createShapeButton("", new RectangleListener(false));
        rectButton.setIcon(new ImageIcon("PaintBoard/images/rectangle.png"));
        rectButton.setSize(200,200);

        //实心矩形
        JButton fillRectButton = createShapeButton("", new RectangleListener(true));
        fillRectButton.setIcon(new ImageIcon("PaintBoard/images/filledrectangle.png"));
        fillRectButton.setSize(200,200);

        //插入图片
        JButton imageButton = createShapeButton("", new ImageListener());
        imageButton.setIcon(new ImageIcon("PaintBoard/images/insert.png"));
        imageButton.setSize(200,200);

        //颜色
        JButton colorButton = new JButton("");
        colorButton.setIcon(new ImageIcon("PaintBoard/images/color.png"));
        colorButton.setSize(200,200);

        //颜色选择器
        colorButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //drawBoard.setPenStyle(Color.yellow);
                Color color = JColorChooser.showDialog(colorButton,"颜色选择",Color.BLACK);
                drawBoard.setPenStyle(color);
            }
        });

        //增大线宽
        JButton lineWidthButton = new JButton("");
        lineWidthButton.setIcon(new ImageIcon("PaintBoard/images/stroke.png"));
        lineWidthButton.setSize(200,200);
        lineWidthButton.addMouseListener(new MouseAdapter() {
            float lineWidth = 1.0f;

            @Override
            public void mouseClicked(MouseEvent e) {
                lineWidth += 0.5f;
                drawBoard.setPenStyle(lineWidth);
            }
        });

        //定义文字按钮
        JButton textButton = new JButton("");
        textButton.setIcon(new ImageIcon("PaintBoard/images/txt.png"));
        textButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawBoard.setBoardListener(new TextListener());
            }
        });

        //定义选择按钮
        JButton choseButton = new JButton("");
        choseButton.setIcon(new ImageIcon("PaintBoard/images/move.png"));
        choseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawBoard.setBoardListener(new ChoseListener());
            }
        });

        //定义撤销按钮
        JButton revokeButton = new JButton("");
        revokeButton.setIcon(new ImageIcon("PaintBoard/images/revoke.png"));
        revokeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawBoard.revoke();
            }
        });

        //定义重做按钮
        JButton redoButton = new JButton("");
        redoButton.setIcon(new ImageIcon("PaintBoard/images/clear.png"));
        redoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawBoard.redo();
            }
        });

        //创建工具按钮框
        JPanel toolButtons = new JPanel();
        toolButtons.setLayout(new GridLayout(2, 7));
        toolButtons.add(lineButton);
        toolButtons.add(curveButton);
        toolButtons.add(polyButton);
        toolButtons.add(circButton);
        toolButtons.add(fillCircButton);
        toolButtons.add(rectButton);
        toolButtons.add(fillRectButton);
        toolButtons.add(imageButton);
        toolButtons.add(colorButton);
        toolButtons.add(lineWidthButton);
        toolButtons.add(textButton);
        toolButtons.add(choseButton);
        toolButtons.add(revokeButton);
        toolButtons.add(redoButton);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        toolButtons.setBounds((int) screenSize.getWidth()/2-500, 0, 1000, 130);
        toolButtons.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));


//------------------------------------------------------------------------------------------------------------------
        //定义保存按钮
        JButton saveButton = new JButton("");
        saveButton.setIcon(new ImageIcon("PaintBoard/images/savefile.png"));
        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawBoard.saveDrawJPanel();
            }
        });

        //定义载入按钮
        JButton loadButton = new JButton("");
        loadButton.setIcon(new ImageIcon("PaintBoard/images/openfile.png"));
        loadButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawBoard.loadDrawJPanel();
            }
        });

        //创建操作按钮框
        JPanel operateButtons = new JPanel();
        operateButtons.setLayout(new GridLayout(1,2));
        operateButtons.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        operateButtons.setBounds(0,0,130,65);
        operateButtons.add(saveButton);
        operateButtons.add(loadButton);

//------------------------------------------------------------------------------------------------------------------
        JPanel slidesList = new JPanel();
        slidesList.setBounds(0,130,(int) screenSize.getWidth()/2-750,screenSize.width/2+80);
        slidesList.setBackground(new Color(230,230,230));
        slidesList.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
//------------------------------------------------------------------------------------------------------------------

        JPanel emptyPanel1 = new JPanel();
        emptyPanel1.setBounds(0,screenSize.width/2+210,(int)screenSize.getWidth(),screenSize.width/2-130);
        emptyPanel1.setBackground(new Color(230,230,230));
        emptyPanel1.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
        JLabel label1=new JLabel("幻灯片");
        emptyPanel1.add(label1);

//------------------------------------------------------------------------------------------------------------------
        JPanel emptyPanel2 = new JPanel();
        emptyPanel2.setBounds(screenSize.width/2*3-1150,130,1250-(int) screenSize.getWidth()/2,screenSize.width/2+80);
        emptyPanel2.setBackground(new Color(230,230,230));
        emptyPanel2.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
//------------------------------------------------------------------------------------------------------------------
        //画出界面的大小
        setSize((int) screenSize.getWidth() , (int) screenSize.getHeight() );
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("测试幻灯片");
        setLayout(null);  //定义界面布局样式

        //画板大小
        drawBoard.setBounds((int) screenSize.getWidth()/2-750, 130, screenSize.width-400, screenSize.width/2+80);

        add(toolButtons);
        add(operateButtons);
        add(slidesList);
        add(emptyPanel1);
        add(emptyPanel2);
        add(drawBoard);

    }

    public void Run() {
        setVisible(true);
        drawBoard.drawBoardPenInitial();
    }

    public static void main(String[] args) {
        new testHome().Run();
    }

}

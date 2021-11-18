package com.Pages;

import com.Listeners.BaseListener.DrawListener;
import com.Listeners.ChoseListener;
import com.Listeners.MyShapesListener.*;
import com.Paint.DrawJPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home extends JFrame {

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

    public Home() throws HeadlessException {

        //定义图形按钮
        //直线
        JButton lineButton = createShapeButton("", new LineListener());
        lineButton.setIcon(new ImageIcon("PaintBoard/images/line.png"));

        //多边形
        JButton polyButton = createShapeButton("", new PolygonListener());
        polyButton.setIcon(new ImageIcon("PaintBoard/images/polygon.jpg"));

        //曲线
        JButton curveButton = createShapeButton("", new CurveListener());
        curveButton.setIcon(new ImageIcon("PaintBoard/images/curve.png"));

        //圆形
        JButton circButton = createShapeButton("", new CircleListener(false));
        circButton.setIcon(new ImageIcon("PaintBoard/images/ellipse.png"));

        //实心圆形
        JButton fillCircButton = createShapeButton("", new CircleListener(true));
        fillCircButton.setIcon(new ImageIcon("PaintBoard/images/filledellipse.png"));

        //矩形
        JButton rectButton = createShapeButton("", new RectangleListener(false));
        rectButton.setIcon(new ImageIcon("PaintBoard/images/rectangle.png"));

        //实心矩形
        JButton fillRectButton = createShapeButton("", new RectangleListener(true));
        fillRectButton.setIcon(new ImageIcon("PaintBoard/images/filledrectangle.png"));

        //插入图片
        JButton imageButton = createShapeButton("", new ImageListener());
        imageButton.setIcon(new ImageIcon("PaintBoard/images/insert.png"));

        //创建按钮框
        JPanel shapesButtons = new JPanel();
        shapesButtons.setLayout(new GridLayout(4, 2));
        shapesButtons.add(lineButton);
        shapesButtons.add(curveButton);
        shapesButtons.add(polyButton);
        shapesButtons.add(circButton);
        shapesButtons.add(fillCircButton);
        shapesButtons.add(rectButton);
        shapesButtons.add(fillRectButton);
        shapesButtons.add(imageButton);


        //定义样式按钮

        //颜色
        JButton colorButton = new JButton("");
        colorButton.setIcon(new ImageIcon("PaintBoard/images/color.png"));

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
        lineWidthButton.addMouseListener(new MouseAdapter() {
            float lineWidth = 1.0f;

            @Override
            public void mouseClicked(MouseEvent e) {
                lineWidth += 0.5f;
                drawBoard.setPenStyle(lineWidth);
            }
        });

        //创建样式按钮框
        JPanel styleButtons = new JPanel();
        styleButtons.add(colorButton);
        styleButtons.add(lineWidthButton);

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
        JButton revokeButton = new JButton("撤销");
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

        //创建操作按钮框
        JPanel operateButtons = new JPanel();
        operateButtons.setLayout(new GridLayout(1,3));
        operateButtons.add(revokeButton);
        operateButtons.add(redoButton);
        operateButtons.add(choseButton);

        //画出界面的大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 4);
        setSize((int) screenSize.getWidth() / 2, (int) screenSize.getHeight() / 2);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("测试画板");
        setLayout(new BorderLayout());  //定义界面布局样式


        add(BorderLayout.WEST, shapesButtons);
        add(BorderLayout.NORTH, styleButtons);
        add(BorderLayout.EAST, textButton);
        add(BorderLayout.SOUTH, operateButtons);
        add(BorderLayout.CENTER, drawBoard);
        //bbb
    }

    public void Run() {
        setVisible(true);
        drawBoard.drawBoardPenInitial();
    }

    public static void main(String[] args) {
        new Home().Run();
    }

}

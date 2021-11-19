package com.Pages;

import com.Listeners.BaseListener.DrawListener;
import com.Listeners.ChoseListener;
import com.Listeners.MyShapesListener.*;
import com.Paint.DrawJPanel;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home extends JFrame {

    private final DrawJPanel drawBoard = new DrawJPanel();
    private JPanel toolButtons = new JPanel();


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
        this.getContentPane().setBackground(Color.BLUE);

//------------------------------------------------------------------------------------------------------------------
        //直线
        JButton lineButton = createShapeButton("", new LineListener());
        lineButton.setIcon(new ImageIcon("PaintBoard/images/line.png"));
        lineButton.setSize(200,200);
        lineButton.setBackground(Color.WHITE);

        //多边形
        JButton polyButton = createShapeButton("", new PolygonListener());
        polyButton.setIcon(new ImageIcon("PaintBoard/images/polygon.jpg"));
        polyButton.setSize(200,200);
        polyButton.setBackground(Color.WHITE);

        //曲线
        JButton curveButton = createShapeButton("", new CurveListener());
        curveButton.setIcon(new ImageIcon("PaintBoard/images/curve.png"));
        curveButton.setSize(200,200);
        curveButton.setBackground(Color.WHITE);

        //圆形
        JButton circButton = createShapeButton("", new CircleListener(false));
        circButton.setIcon(new ImageIcon("PaintBoard/images/ellipse.png"));
        circButton.setSize(200,200);
        circButton.setBackground(Color.WHITE);

        //实心圆形
        JButton fillCircButton = createShapeButton("", new CircleListener(true));
        fillCircButton.setIcon(new ImageIcon("PaintBoard/images/filledellipse.png"));
        fillCircButton.setSize(200,200);
        fillCircButton.setBackground(Color.WHITE);

        //矩形
        JButton rectButton = createShapeButton("", new RectangleListener(false));
        rectButton.setIcon(new ImageIcon("PaintBoard/images/rectangle.png"));
        rectButton.setSize(200,200);
        rectButton.setBackground(Color.WHITE);

        //实心矩形
        JButton fillRectButton = createShapeButton("", new RectangleListener(true));
        fillRectButton.setIcon(new ImageIcon("PaintBoard/images/filledrectangle.png"));
        fillRectButton.setSize(200,200);
        fillRectButton.setBackground(Color.WHITE);

        //插入图片
        JButton imageButton = createShapeButton("", new ImageListener());
        imageButton.setIcon(new ImageIcon("PaintBoard/images/insert.png"));
        imageButton.setSize(200,200);
        imageButton.setBackground(Color.WHITE);

        //颜色
        JButton colorButton = new JButton("");
        colorButton.setIcon(new ImageIcon("PaintBoard/images/color.png"));
        colorButton.setSize(200,200);
        colorButton.setBackground(Color.white);

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
        lineWidthButton.setBackground(Color.WHITE);
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
        textButton.setBackground(Color.WHITE);

        //定义选择按钮
        JButton choseButton = new JButton("");
        choseButton.setIcon(new ImageIcon("PaintBoard/images/move.png"));
        choseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawBoard.setBoardListener(new ChoseListener());
            }
        });
        choseButton.setBackground(Color.WHITE);

        //定义撤销按钮
        JButton revokeButton = new JButton("");
        revokeButton.setIcon(new ImageIcon("PaintBoard/images/revoke.png"));
        revokeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawBoard.revoke();
            }
        });
        revokeButton.setBackground(Color.WHITE);

        //定义重做按钮
        JButton redoButton = new JButton("");
        redoButton.setIcon(new ImageIcon("PaintBoard/images/clear.png"));
        redoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawBoard.redo();
            }
        });
        redoButton.setBackground(Color.WHITE);

        //创建工具按钮框
        //JPanel toolButtons = new JPanel();
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
        //toolButtons.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));




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

        JPanel emptyPanel3 = new JPanel();
        emptyPanel3.setBounds(0,0,(int)screenSize.getWidth(),130);
        emptyPanel3.setBackground(new Color(230,230,230));
        emptyPanel3.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
        emptyPanel3.setLayout(null);


//-------------------------------------------------菜单--------------------------------------------------------------
        JMenuBar bar = new JMenuBar();
        JMenu menu1 = new JMenu("File  ");
        JMenuItem Save = new JMenuItem("Save");
        ImageIcon Savelogo = new ImageIcon("PaintBoard/images/savefile.png");
        Image SaveoldLogo = Savelogo.getImage().getScaledInstance(20,15,Image.SCALE_SMOOTH);
        ImageIcon SavenewLogo = new ImageIcon(SaveoldLogo);
        Save.setIcon(SavenewLogo);
        JMenuItem Open = new JMenuItem("Open");
        ImageIcon Openlogo = new ImageIcon("PaintBoard/images/openfile.png");
        Image OpenoldLogo = Openlogo.getImage().getScaledInstance(20,15,Image.SCALE_SMOOTH);
        ImageIcon OpennewLogo = new ImageIcon(OpenoldLogo);
        Open.setIcon(OpennewLogo);

        menu1.add(Save);
        menu1.add(Open);
        JMenu menu2 = new JMenu("Start  ");
        JMenuItem Edit = new JMenuItem("Edit");
        ImageIcon Editlogo = new ImageIcon("PaintBoard/images/pen.png");
        Image EditoldLogo = Editlogo.getImage().getScaledInstance(20,15,Image.SCALE_SMOOTH);
        ImageIcon EditnewLogo = new ImageIcon(EditoldLogo);
        Edit.setIcon(EditnewLogo);
        menu2.add(Edit);
        bar.add(menu1);
        bar.add(menu2);
        bar.setBackground(new Color(183,71,42));
        //bar.setForeground(Color.white);
        //bar.setSize((int)screenSize.getWidth(),80);
        //bar.setFont(new Font("楷体",Font.BOLD,80));

        this.setJMenuBar(bar);

        Open.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(e.getSource()==Open){
                    drawBoard.loadDrawJPanel();
                }
            }
        });

        Save.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               if(e.getSource()==Save){
                   drawBoard.saveDrawJPanel();
               }
           }
        });

        Edit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(e.getSource()==Edit){
                    emptyPanel3.add(toolButtons);
                    validate();
                }
            }
        });



//------------------------------------------------------------------------------------------------------------------
        //画出界面的大小
        setSize((int) screenSize.getWidth() , (int) screenSize.getHeight() );
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("测试幻灯片");
        setLayout(null);  //定义界面布局样式
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //关闭窗口时退出程序
        try {
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (UnsupportedLookAndFeelException e1) {
            e1.printStackTrace();
        }

        //画板大小
        drawBoard.setBounds((int) screenSize.getWidth()/2-750, 130, screenSize.width-400, screenSize.width/2+80);

        add(slidesList);
        add(emptyPanel1);
        add(emptyPanel2);
        add(emptyPanel3);
        add(drawBoard);



    }

    public void Run() {
        setVisible(true);
        drawBoard.drawBoardPenInitial();
    }

    public static void main(String[] args) {
        new Home().Run();
    }

}

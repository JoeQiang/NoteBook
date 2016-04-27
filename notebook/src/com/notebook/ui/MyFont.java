package com.notebook.ui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 * 2015年1月2日下午6:11:28
 * @author season
 *
 */
public class MyFont extends JFrame {
 
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextArea txtrJanuaryBy = new JTextArea("January 02,2015 By 12052010, look at this ,haiyan ,i did it");
     
    private String myFontName;
    private int myFontSize=15;
    private int myFontType =0;
    private int myFontColor;
     
    private Font f=null ;
 
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        MyFont myview = new MyFont();
        myview.setVisible(true);
         
    }
 
    /**
     * Create the frame.
     */
    public MyFont() {
         
        init();//初始化界面
    }
     
    public void init(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 558, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
                 
        JPanel Color = new JPanel();
        Color.setBounds(10, 10, 215, 46);
        contentPane.add(Color);
        Color.setLayout(null);
         
        /**
         * 字体颜色监听
         */
        final JRadioButton rdbtnRed = new JRadioButton("Red");
 
        rdbtnRed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {               
                if(e.getSource()==rdbtnRed){
                    myFontColor = 1;
                    change(myFontColor,f);
                }          
            }
        });
     
        rdbtnRed.setBounds(5, 5, 61, 23);
        Color.add(rdbtnRed);
         
        final JRadioButton rdbtnBlue = new JRadioButton("Blue");
        rdbtnBlue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 
                if(e.getSource()==rdbtnBlue){
                    myFontColor = 2;
                    change(myFontColor,f);
                }
            }
        });
        rdbtnBlue.setBounds(68, 5, 68, 23);
        Color.add(rdbtnBlue);
         
        final JRadioButton rdbtnGray = new JRadioButton("Gray");
 
        rdbtnGray.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 
                if(e.getSource()==rdbtnGray){
                    myFontColor =3;
                    change(myFontColor,f);
                    //txtrJanuaryBy.setFont(f);
                }
            }
        });
        rdbtnGray.setBounds(138, 5, 71, 23);
        Color.add(rdbtnGray);
         
         
        ButtonGroup buttongroup = new ButtonGroup();
        buttongroup.add(rdbtnGray);
        buttongroup.add(rdbtnBlue);
        buttongroup.add(rdbtnRed);
        txtrJanuaryBy.setLineWrap(true);
        txtrJanuaryBy.setWrapStyleWord(true);
             
        txtrJanuaryBy.setBounds(10, 113, 522, 126);
        contentPane.add(txtrJanuaryBy);
         
        JPanel panel = new JPanel();
        panel.setBounds(235, 10, 151, 46);
        contentPane.add(panel);
        panel.setLayout(null);
        /**
         * 字体形状监听
         */
        JCheckBox chckbxMy = new JCheckBox("Italic");
        chckbxMy.setFont(new Font("宋体", Font.ITALIC, 12));
        chckbxMy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 f= new Font(myFontName,2,myFontSize);
                 change(myFontColor,f);
            }
        });
        chckbxMy.setBounds(69, 6, 61, 23);
        panel.add(chckbxMy);
         
        final JCheckBox checkBox_1 = new JCheckBox("Bold");
        checkBox_1.setFont(new Font("宋体", Font.BOLD, 12));
        checkBox_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 f= new Font(myFontName,1 ,myFontSize);
                 change(myFontColor,f);
            }
        });
        checkBox_1.setBounds(6, 6, 61, 23);
        panel.add(checkBox_1);
         
        String[] myFont = {"宋体","utf-8","others"};
        @SuppressWarnings({ "rawtypes", "unchecked" })
        final JComboBox comboBox = new JComboBox(myFont);
        /**
         * 字体监听
         */
        comboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                 myFontName = comboBox.getSelectedItem().toString();
                 f= new Font(myFontName,myFontType ,myFontSize);
                 change(myFontColor,f);
            }
        });
        comboBox.setBounds(399, 20, 73, 21);
        contentPane.add(comboBox);
        /**
         * 字体大小监听
         */
        String[] mySize ={"10","20","30"};
        @SuppressWarnings({ "rawtypes", "unchecked" })
        final JComboBox comboBox_1 = new JComboBox(mySize);
        comboBox_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 
                 myFontSize =Integer.parseInt( comboBox_1.getSelectedItem().toString());
                 f= new Font(myFontName,myFontType ,myFontSize);
                 change(myFontColor,f);
                 
            }
        });
 
        comboBox_1.setBounds(482, 20, 50, 21);
        contentPane.add(comboBox_1);
         
        JButton btnChangeBackgroupColor = new JButton("Change BackGroup Color");
        btnChangeBackgroupColor.setContentAreaFilled(false);
        btnChangeBackgroupColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeBackGroupColor();
            }
        });
        btnChangeBackgroupColor.setFont(new Font("Consolas", Font.BOLD, 14));
        btnChangeBackgroupColor.setBounds(10, 66, 215, 23);
        contentPane.add(btnChangeBackgroupColor);
 
    }
     
    /**
     * change TODO 改变字体 
     * @param colorType
     * @param myFont void
     */
     
    public void change(int colorType, Font myFont){       
         
        txtrJanuaryBy.setFont(myFont);
         
        if(colorType==1){
            txtrJanuaryBy.setForeground(Color.red);
        }else if(colorType==2){
            txtrJanuaryBy.setForeground(Color.blue);
        }else{
            txtrJanuaryBy.setForeground(Color.gray);
        }
    }
     
    /**
     * changeBackGroupColor TODO 随机产生RGB,改变文本框背景颜色 
     *  void
     */
    public  void changeBackGroupColor(){
        //随机产生三基色
        int red =(int )(Math.random()*255);
        int green =(int )(Math.random()*255);
        int black =(int )(Math.random()*255);
         
        Color color = new Color(red,green,black);
        txtrJanuaryBy.setBackground(color);
    }
}
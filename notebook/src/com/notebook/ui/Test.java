package com.notebook.ui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
public class Test extends JApplet{
  public void init(){
    Container contentPane = getContentPane();
    contentPane.setLayout(new BorderLayout());
    final String[] colHeads = {"时间", "科目", "内容"};
    final Object[][] data={
        {"cai", "1122","1122" },
        {"jie", "2222","2222" },
        {"tt", "3322","3322" }
    };
    JTable table = new JTable(data, colHeads);
    int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
    int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
    JScrollPane jsp = new JScrollPane(table, v, h);
    contentPane.add(jsp, BorderLayout.CENTER);
  }
  public static void main(String[] args) {
	Test test=new Test();
	test.setVisible(true);
}
}
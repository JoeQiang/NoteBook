package com.notebook.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.notebook.dao.DiaryDao;
import com.notebook.pojo.DiaryDomain;

public class QueryDiary extends JFrame implements ActionListener{
	public static int userID;
	JTextField jtf=new JTextField("�������ѯ����",80);
	ButtonGroup grp=new ButtonGroup();
	JRadioButton item=new JRadioButton("����Ŀ");
	JRadioButton date=new JRadioButton("������");
	JRadioButton detial=new JRadioButton("������");
	JButton search=new JButton("����");
	JButton back=new JButton("����");
	JLabel title=new JLabel("���������");
//	���
	final String[] colHeads = {"ʱ��", "��Ŀ", "����"};
	final Object[][] data={
	        {"1��1��", "����","������д�淶" },
	        {"1��2��", "��ѧ","������������" },
	        {"1��3��", "Ӣ��","���" }
	    };
	JTable table = new JTable(data, colHeads);
	int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
    int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
    JScrollPane jsp = new JScrollPane(table, v, h);
    
	public QueryDiary(){
		setBounds(400,150,500,500);
		setLayout(null);
		grp.add(item);
		grp.add(date);
		grp.add(detial);
		jtf.setBounds(40, 20, 340, 30);
		item.setBounds(40, 60, 80, 30);
		date.setBounds(140, 60, 80, 30);
		detial.setBounds(240, 60, 80, 30);
		search.setBounds(390, 20, 60, 30);
		title.setBounds(40, 100, 80, 30);
		back.setBounds(200, 400, 100, 30);
		search.addActionListener(this);
		back.addActionListener(this);
		add(jtf);add(item);add(date);add(detial);add(search);add(title);add(back);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//		���
		jsp.setBounds(0, 150, 500, 500);
		add(jsp, BorderLayout.CENTER);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == search) {
			searchData();
		}
		if (source == back) {
//			չ���ռ�������
		}
	}
	private void searchData() {
		String inputText=jtf.getText();
		String depend="";
		if(item.isSelected()){
			depend="item";
		}
		if(date.isSelected()){
			depend="date";
		}
		if(detial.isSelected()){
			depend="detial";
		}
		DiaryDao diaryDao=new DiaryDao();
		ArrayList<DiaryDomain> diarys=new ArrayList<DiaryDomain>();
		diarys=diaryDao.getDiarysByArg(inputText, depend, userID);
	}
}

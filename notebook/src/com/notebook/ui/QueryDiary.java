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
	JRadioButton titleArgs=new JRadioButton("������");
	JButton search=new JButton("����");
	JButton back=new JButton("����");
	JLabel title=new JLabel("���������");
    
	public QueryDiary(){
		setTitle("��ѯ�ʼ�");
		setBounds(420,60,570,620);
		setLayout(null);
		grp.add(item);
		grp.add(date);
		grp.add(detial);
		grp.add(titleArgs);
		jtf.setBounds(40, 20, 340, 30);
		item.setBounds(40, 60, 80, 30);
		date.setBounds(140, 60, 80, 30);
		detial.setBounds(240, 60, 80, 30);
		titleArgs.setBounds(340, 60, 80, 30);
		search.setBounds(390, 20, 60, 30);
		title.setBounds(40, 100, 80, 30);
		back.setBounds(460, 20, 60, 30);
		search.addActionListener(this);
		back.addActionListener(this);
		add(jtf);add(item);add(date);add(detial);add(titleArgs);add(search);add(title);add(back);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
		}if(titleArgs.isSelected()){
			depend="title";
		}
		DiaryDao diaryDao=new DiaryDao();
		ArrayList<DiaryDomain> diarys=new ArrayList<DiaryDomain>();
		diarys=diaryDao.getDiarysByArg(inputText, depend, userID);
//		���
		final String[] colHeads = {"ʱ��", "��Ŀ","����", "����"};
		final String[][] data=new String[diarys.size()][4];
//		�����ϸ�ֵ����ά����
		for(int i=0;i<diarys.size();i++){
			data[i][0]=diarys.get(i).getDate();
			data[i][1]=diarys.get(i).getItem();
			data[i][2]=diarys.get(i).getContent();
			data[i][3]=diarys.get(i).getTitle();
		}
		JTable table = new JTable(data, colHeads);
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
	    int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
	    JScrollPane jsp = new JScrollPane(table, v, h);
		jsp.setBounds(40, 150, 480, 400);
		add(jsp, BorderLayout.CENTER);
	}
}

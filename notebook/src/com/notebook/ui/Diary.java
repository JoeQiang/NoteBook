package com.notebook.ui;

import javax.swing.tree.DefaultMutableTreeNode;

public class Diary extends DefaultMutableTreeNode {
	DefaultMutableTreeNode month[]=new DefaultMutableTreeNode[13];
	int i;
	DiaryFrame df=new DiaryFrame();
	public Diary() {
	}

	public Diary(String arg0) {
		super(arg0);
		for(i=1;i<=12;i++){
			month[i]=new DefaultMutableTreeNode(i+"月");
			this.add(month[i]);
		}for(i=1;i<=12;i++){
			if(i==1||i==3||i==5||i==7||i==8||i==10||i==12){
				for(int j=1;j<=31;j++){
					month[i].add(new DefaultMutableTreeNode(j+"日"));
				}
			}else if(i==2){
				for(int j=1;j<=28;j++){
					month[i].add(new DefaultMutableTreeNode(j+"日"));
				}
			}else{
				for(int j=1;j<=30;j++){
					month[i].add(new DefaultMutableTreeNode(j+"日"));
				}
			}
		}
	}

}

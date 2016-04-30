package com.notebook.ui;

import javax.swing.tree.DefaultMutableTreeNode;

public class DetialNode extends DefaultMutableTreeNode{
	/**
	 * 重写树节点，用于创建带有笔记标识的内容节点
	 */
	private int currentDiaryID;//保存当前查看状态的笔记ID
	public int getCurrentDiaryID() {
		return currentDiaryID;
	}
	public void setCurrentDiaryID(int currentDiaryID) {
		this.currentDiaryID = currentDiaryID;
	}
	
	public DetialNode(String title){super(title);}
}

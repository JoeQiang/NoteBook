package com.notebook.ui;

import javax.swing.tree.DefaultMutableTreeNode;

public class DetialNode extends DefaultMutableTreeNode{
	/**
	 * ��д���ڵ㣬���ڴ������бʼǱ�ʶ�����ݽڵ�
	 */
	private int currentDiaryID;//���浱ǰ�鿴״̬�ıʼ�ID
	public int getCurrentDiaryID() {
		return currentDiaryID;
	}
	public void setCurrentDiaryID(int currentDiaryID) {
		this.currentDiaryID = currentDiaryID;
	}
	
	public DetialNode(String title){super(title);}
}

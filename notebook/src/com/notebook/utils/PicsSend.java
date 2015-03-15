package com.notebook.utils;

import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

public class PicsSend {
	static ImageIcon img1,img2,img3;
	
	public static void picSend(JTextPane jta,String txt,String from) throws BadLocationException{
		String pathRoot=System.getProperty("user.dir");
		img1 = new ImageIcon(pathRoot+"\\src\\pics\\1.png");
		img2 = new ImageIcon(pathRoot+"\\src\\pics\\2.png");
		img3 = new ImageIcon(pathRoot+"\\src\\pics\\3.png");
		StyledDocument doc = jta.getStyledDocument();
		if(txt.contains("#1;")&&!(txt.equals("#1;"))){
			String[] s=txt.split("#1;");
			for(int i=0;i<s.length;i++){
				if(i==0){
					doc.insertString(doc.getLength(),from+ ":>" +s[i], null);
					jta.setCaretPosition(doc.getLength()); 
					jta.insertIcon(img1);
				}
			else{
					doc.insertString(doc.getLength(),s[i], null);
					jta.setCaretPosition(doc.getLength()); 
					jta.insertIcon(img1);
				}
			}
		}else if(txt.equals("#1;")){
			doc.insertString(doc.getLength(),from+ ":>", null);
				jta.setCaretPosition(doc.getLength()); 
				jta.insertIcon(img1);
		}
		
		else if(txt.contains("#2;")&&!(txt.equals("#2;"))){
			String[] s=txt.split("#2;");
			for(int i=0;i<s.length;i++){
				if(i==0){
					doc.insertString(doc.getLength(),from+ ":>" +s[i], null);
					jta.setCaretPosition(doc.getLength()); 
					jta.insertIcon(img2);
				}
			else{
					doc.insertString(doc.getLength(),s[i], null);
					jta.setCaretPosition(doc.getLength()); 
					jta.insertIcon(img2);
				}
			}
		}else if(txt.equals("#2;")){
			doc.insertString(doc.getLength(),from+ ":>", null);
				jta.setCaretPosition(doc.getLength()); 
				jta.insertIcon(img2);
		}
		
		else if(txt.contains("#3;")&&!(txt.equals("#3;"))){
			String[] s=txt.split("#3;");
			for(int i=0;i<s.length;i++){
				if(i==0){
					doc.insertString(doc.getLength(),from+ ":>" +s[i], null);
					jta.setCaretPosition(doc.getLength()); 
					jta.insertIcon(img3);
				}
			else{
					doc.insertString(doc.getLength(),s[i], null);
					jta.setCaretPosition(doc.getLength()); 
					jta.insertIcon(img3);
				}
			}
		}else if(txt.equals("#3;")){
			doc.insertString(doc.getLength(),from+ ":>", null);
				jta.setCaretPosition(doc.getLength()); 
				jta.insertIcon(img3);
		}
		
		else{
			doc.insertString(doc.getLength(),from+ ":>" +txt, null);
		}
		
		doc.insertString(doc.getLength(),"\n", null);
}
}

package com.notebook.ui;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import com.notebook.biz.UserBiz;
import com.notebook.utils.PicsSend;

public class FrmMain extends Thread {
	JFrame frame;
	JSplitPane splitPaneV, splitpaneH;
	JScrollPane spCenter;
	JPanel pRight;
	JPanel pdown;
	JTextPane jta;
	JLabel lblSend;
	JTextField jtf;
	Socket s;
	JComboBox<String> jcb;
	BufferedReader in;
	PrintWriter out;
	String nickname;
	String password;

	JButton b1, b2, b3;
	// 个人信息按钮
	JButton infoBtn;
	ImageIcon img1, img2, img3;
	StyledDocument doc = null;
	UserBiz biz;
	private String ip = "localhost";
	private JScrollBar jsb;
	private ButtonListener buttonListener;

	public FrmMain() {
	}

	public FrmMain(String uname, String password, String nickname, String ipad) {
		this.nickname = nickname;
		this.password = password;
		if (ipad != null)
			ip = ipad;
		try {
			s = new Socket(ip, 9000);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(s.getOutputStream());
			out.println(nickname);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		frame = new JFrame("聊天->" + nickname);
		jta = new JTextPane() {
			ImageIcon imageIcon = new ImageIcon(DiaryFrame.SRC_PICS_DONGWU_JPG);
			{
				setOpaque(false);
			}

			public void paint(Graphics g) {
				g.drawImage(imageIcon.getImage(), 0, 0, this);
				super.paint(g);
			}
		};
		jta.setEditable(false);
		JScrollPane jsp = new JScrollPane(jta);
		jsb = jsp.getVerticalScrollBar();
		pdown = new JPanel();
		jcb = new JComboBox<String>();
		jcb.addItem("All");
		pdown.add(jcb);
		lblSend = new JLabel("输入：");
		jtf = new JTextField(20);
		pdown.add(lblSend);
		pdown.add(jtf);
		pRight = new JPanel(null);
		splitPaneV = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jsp, pdown);
		splitPaneV.setDividerLocation(320);
		splitpaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPaneV,
				pRight);
		splitpaneH.setDividerLocation(350);

		/**
		 * 图片用相对路径 ， 项目地址为跟路径
		 */
		img1 = new ImageIcon(
				"E:\\eclipse\\workspace\\notebook\\src\\pics\\1.png");
		img2 = new ImageIcon(
				"E:\\eclipse\\workspace\\notebook\\src\\pics\\2.png");
		img3 = new ImageIcon(
				"E:\\eclipse\\workspace\\notebook\\src\\pics\\3.png");
		b1 = new JButton(img1);
		b2 = new JButton(img2);
		b3 = new JButton(img3);
		// 初始化修改个人信息按钮
		infoBtn = new JButton("个人信息");

		pdown.add(b1);
		pdown.add(b2);
		pdown.add(b3);

		pdown.add(infoBtn);

		frame.add(splitpaneH);
		frame.setSize(500, 400);
		frame.setLocation(300, 300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		buttonListener = new ButtonListener();
		b1.addActionListener(buttonListener);
		b2.addActionListener(buttonListener);
		b3.addActionListener(buttonListener);

		jtf.addActionListener(new ActionListenerImpl());

	}

	public void run() {
		try {
			while (true) {
				String text = in.readLine();
				StringTokenizer st = new StringTokenizer(text, ":");
				String type = st.nextToken();
				String tx = st.nextToken();
				if (type.equals("Add")) {
					System.out.println(tx);
					StringTokenizer ipadr = new StringTokenizer(tx, "/");
					String user = ipadr.nextToken();
					if (!(this.nickname.equals(user))) {
						this.jcb.addItem(user);
					}
					this.jta.setText(jta.getText() + user + ""
							+ ipadr.nextToken() + "上线了！\n");
				}
				if (type.equals("Del")) {
					jcb.removeItem(tx);
					jta.setText(jta.getText() + tx + "下线了！\n");
				}
				if (type.equals("Text")) {
					StringTokenizer ss = new StringTokenizer(tx, "$");
					String from = ss.nextToken();
					String to = ss.nextToken();
					String txt = ss.nextToken();
					PicsSend.picSend(jta, txt, from);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private final class ActionListenerImpl implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String text = jtf.getText();
			jtf.setText("");
			if (text.length() == 0) {
				new JOptionPane().showMessageDialog(frame, "不允许   消息为空！");
			} else {
				String to = (String) jcb.getSelectedItem();
				if (to.equals("All")) {
					out.println(nickname + "$" + to + "$" + text);
					out.flush();
				} else {
					doc = jta.getStyledDocument();
					try {
						// doc.insertString(doc.getLength(),
						// name+":>"+text+"\n", null);
						PicsSend.picSend(jta, text, nickname);
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}
					// jta.setText(jta.getText()+name+":>"+text+"\n");
					out.println(nickname + "$" + to + "$" + text);
					out.flush();
				}
			}
			jsb.setValue(jta.getHeight());
		}
	}

	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			doc = jta.getStyledDocument();
			if (e.getSource() == b1) {
				jtf.setText(jtf.getText() + "#1;");
			}
			if (e.getSource() == b2) {
				jtf.setText(jtf.getText() + "#2;");
			}
			if (e.getSource() == b3) {
				jtf.setText(jtf.getText() + "#3;");
			}
		}
	}

	public static void main(String[] args) {
		
	}
}

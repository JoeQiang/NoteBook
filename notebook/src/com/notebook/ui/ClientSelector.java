package com.notebook.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.notebook.biz.UserBiz;
import com.notebook.dao.UserDao;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class ClientSelector extends JFrame {

	private JPanel contentPane;
	private JTextField ip;
	private JTextField nickname;
	private JPasswordField password;
	private static UserBiz biz = UserBiz.getBiz();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientSelector frame = new ClientSelector();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientSelector() {
		setTitle("\u7528\u6237\u767B\u5F55");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 230, 279);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u670D\u52A1\u5668\u5730\u5740");
		lblNewLabel.setBounds(73, 10, 90, 15);
		contentPane.add(lblNewLabel);
		
		ip = new JTextField();
		ip.setText("localhost");
		ip.setBounds(0, 35, 214, 21);
		contentPane.add(ip);
		ip.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u540D");
		lblNewLabel_1.setBounds(89, 64, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		nickname = new JTextField();
		nickname.setBounds(0, 89, 214, 21);
		contentPane.add(nickname);
		nickname.setColumns(10);
		
		JLabel label = new JLabel("\u5BC6\u7801");
		label.setBounds(89, 120, 54, 15);
		contentPane.add(label);
		
		JButton button = new JButton("\u767B\u5F55");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnClick();
			}
		});
		button.setBounds(20, 194, 75, 23);
		contentPane.add(button);
		
		password = new JPasswordField();
		password.setBounds(0, 148, 214, 21);
		contentPane.add(password);
		
		JButton button_1 = new JButton("\u6CE8\u518C");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnClickReg();
			}
		});
		button_1.setBounds(116, 194, 75, 23);
		contentPane.add(button_1);
	}

	protected void btnClickReg() {
		FrmRegist fr = new FrmRegist();
		fr.setLocation(400,180);
		fr.setVisible(true);
		this.dispose();
	}

	protected void btnClick() {
		String ipaddr = ip.getText();

		String username = nickname.getText();
		String userpwd = new String(password.getPassword());
		if ("".equals(username) || "".equals(userpwd)) {
			JOptionPane.showMessageDialog(null, "用户名或密码不能为空", "提示", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		boolean login = biz.login(username, userpwd);
		String nickName = biz.getNickName(username);

		if (!login) {
			JOptionPane.showMessageDialog(null, "错误的用户名或密码", "提示", JOptionPane.WARNING_MESSAGE);
			return;
		}

		FrmMain trc = new FrmMain(username,userpwd,nickName, ipaddr);
		trc.start();
		this.dispose();
	}
}

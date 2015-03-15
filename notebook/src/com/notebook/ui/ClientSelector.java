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
	public static int userID;
	private JPanel contentPane;
	private JTextField ip;
	private static UserBiz biz = UserBiz.getBiz();
	/**
	 * Create the frame.
	 */
	public ClientSelector() {
		setTitle("\u7528\u6237\u767B\u5F55");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(350, 80, 250, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u670D\u52A1\u5668\u5730\u5740");
		lblNewLabel.setBounds(73, 10, 90, 15);
		contentPane.add(lblNewLabel);
		
		ip = new JTextField();
		ip.setText("localhost");
		ip.setBounds(10, 35, 214, 30);
		contentPane.add(ip);
		ip.setColumns(10);
		
		JButton button = new JButton("Ω¯»Î¡ƒÃÏ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnClick();
			}
		});
		button.setBounds(32, 120, 150, 30);
		contentPane.add(button);
	}

	protected void btnClick() {
		String ipaddr = ip.getText();
		String nickName = biz.getNickNameByUserID(userID);
		FrmMain trc = new FrmMain(nickName, ipaddr);
		trc.start();
		this.dispose();
	}
}

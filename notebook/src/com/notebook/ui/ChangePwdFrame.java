package com.notebook.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.text.SimpleDateFormat;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.notebook.dao.UserDao;
import com.notebook.pojo.User;
import com.notebook.utils.Constant;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * 修改密码页面
 * 
 * @author Joe_Huang
 * 
 */
public class ChangePwdFrame extends JFrame implements ActionListener {

	private UserDao userDao;

	private JPanel contentPane;
	private JPasswordField textpassword;
	private JPasswordField text_rpassword;
	private JButton button_back;
	private JTextPane text_account;
	private JButton button_ok;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { ChangePwdFrame frame = new
	 * ChangePwdFrame(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */
	/**
	 * Create the frame.
	 */
	public ChangePwdFrame() {

		userDao = new UserDao();

		setTitle("\u5BC6\u7801\u4FEE\u6539");
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(500, 200, 287, 211);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel label_account = new JLabel("\u8D26\u53F7");
		label_account.setHorizontalAlignment(SwingConstants.CENTER);
		label_account.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		JLabel label_pwd = new JLabel("\u5BC6\u7801");
		label_pwd.setHorizontalAlignment(SwingConstants.CENTER);
		label_pwd.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		JLabel label_rpwd = new JLabel("\u91CD\u8F93");
		label_rpwd.setHorizontalAlignment(SwingConstants.CENTER);
		label_rpwd.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		text_account = new JTextPane();
		text_account.setEnabled(false);
		text_account.setEditable(false);

		textpassword = new JPasswordField();
		textpassword
				.setToolTipText("\u8BF7\u8F93\u5165\u65B0\u7684\u5BC6\u7801");

		text_rpassword = new JPasswordField();
		text_rpassword.setToolTipText("\u518D\u6B21\u8F93\u5165\u5BC6\u7801");

		button_ok = new JButton("\u786E\u5B9A");
		button_ok.addActionListener(this);

		button_back = new JButton("\u8FD4\u56DE");
		button_back.addActionListener(this);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				label_account,
																				GroupLayout.PREFERRED_SIZE,
																				46,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				text_account,
																				GroupLayout.PREFERRED_SIZE,
																				192,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				label_pwd,
																				GroupLayout.PREFERRED_SIZE,
																				46,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				textpassword))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				label_rpwd,
																				GroupLayout.PREFERRED_SIZE,
																				46,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				text_rpassword,
																				GroupLayout.PREFERRED_SIZE,
																				192,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(21, Short.MAX_VALUE))
						.addGroup(
								Alignment.TRAILING,
								gl_contentPane
										.createSequentialGroup()
										.addContainerGap(131, Short.MAX_VALUE)
										.addComponent(button_ok)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(button_back).addGap(22)));
		gl_contentPane
				.setVerticalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGap(17)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																text_account,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																label_account,
																GroupLayout.PREFERRED_SIZE,
																30,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																textpassword,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																label_pwd,
																GroupLayout.PREFERRED_SIZE,
																30,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																text_rpassword,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																label_rpwd,
																GroupLayout.PREFERRED_SIZE,
																30,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(button_ok)
														.addComponent(
																button_back))
										.addContainerGap(11, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
		initData();
	}

	/**
	 * 加载需要加载的数据
	 */
	private void initData() {
		User user = null;
		int id = Constant.USER_ID;
		if (id != -1) {
			user = userDao.findById(id);
			text_account.setText(user.getName());
		}
	}

	@SuppressWarnings("unused")
	private int saveData() {
		int flag = -1;
		User user = null;
		int id = Constant.USER_ID;
		if (id != -1) {
			user = userDao.findById(id);
			@SuppressWarnings("deprecation")
			String pwd = textpassword.getText();
			@SuppressWarnings("deprecation")
			String rpwd = text_rpassword.getText();
			System.out.println("pwd:" + pwd);
			System.out.println("rpwd:" + rpwd);
			if (pwd == null || rpwd == null || pwd == "" || rpwd == "") {
				JOptionPane.showMessageDialog(this, "请输入新密码，密码不能为空", "警告",
						JOptionPane.WARNING_MESSAGE);
			}
			if (!pwd.equals(rpwd)) {
				JOptionPane.showMessageDialog(this, "两次输入密码不一致，请重新输入", "警告",
						JOptionPane.WARNING_MESSAGE);
			} else {
				user.setPassword(pwd);
				flag = userDao.update(user);
				JOptionPane.showMessageDialog(null, "保存成功", "",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		return flag;
	}

	public JButton getButton_back() {
		return button_back;
	}

	public JTextPane getText_account() {
		return text_account;
	}

	public JPasswordField getText_rpassword() {
		return text_rpassword;
	}

	public JPasswordField getTextpassword() {
		return textpassword;
	}

	public JButton getButton_ok() {
		return button_ok;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button_back) {
			System.out.println("点击了返回按钮");
			this.dispose();
			System.out.println("关闭修改密码窗口");
		} else if (e.getSource() == button_ok) {
			System.out.println("点击了确定按钮,正在修改密码...请稍等");
			int flag = saveData();
			System.out.println(flag > 0 ? "修改密码成功！" : "修改密码失败,请检查原因");
		}
	}
}

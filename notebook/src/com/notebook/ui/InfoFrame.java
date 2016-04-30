package com.notebook.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.notebook.dao.UserDao;
import com.notebook.pojo.User;
import com.notebook.utils.Constant;

/**
 * 用户个人信息页
 * 
 * @author Joe_Huang
 * 
 */
@SuppressWarnings("serial")
public class InfoFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField text_nickname;
	private JTextField text_birth;
	private JButton button_update;
	private JButton button_back;
	private JTextArea textArea_signature;
	private JTextPane text_account;

	private UserDao userDao;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { InfoFrame frame = new InfoFrame();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 * }); }
	 */

	/**
	 * Create the frame.
	 */
	public InfoFrame() {
		setResizable(false);
		userDao = new UserDao();

		setType(Type.POPUP);
		setTitle("\u4E2A\u4EBA\u4FE1\u606F\u9875");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(500, 100, 341, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel label_account = new JLabel("\u8D26\u53F7");
		label_account.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_account.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel label_name = new JLabel("\u6635\u79F0");
		label_name.setHorizontalAlignment(SwingConstants.CENTER);
		label_name.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		JLabel label_birth = new JLabel("\u751F\u65E5");
		label_birth.setHorizontalAlignment(SwingConstants.CENTER);
		label_birth.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		JLabel label_signature = new JLabel("\u7B7E\u540D");
		label_signature.setHorizontalAlignment(SwingConstants.CENTER);
		label_signature.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		text_nickname = new JTextField();
		text_nickname.setEditable(false);
		text_nickname.setColumns(10);

		text_birth = new JTextField();
		text_birth.setEditable(false);
		text_birth.setColumns(10);

		text_account = new JTextPane();
		text_account.setEnabled(false);
		text_account.setEditable(false);

		JScrollPane scrollPane = new JScrollPane();

		button_update = new JButton("\u4FEE\u6539");
		button_update.addActionListener(this);

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
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_contentPane
																										.createSequentialGroup()
																										.addComponent(
																												label_name,
																												GroupLayout.PREFERRED_SIZE,
																												53,
																												GroupLayout.PREFERRED_SIZE)
																										.addGap(18)
																										.addComponent(
																												text_nickname,
																												GroupLayout.PREFERRED_SIZE,
																												222,
																												GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								gl_contentPane
																										.createSequentialGroup()
																										.addComponent(
																												label_birth,
																												GroupLayout.PREFERRED_SIZE,
																												53,
																												GroupLayout.PREFERRED_SIZE)
																										.addGap(18)
																										.addComponent(
																												text_birth,
																												GroupLayout.PREFERRED_SIZE,
																												222,
																												GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								gl_contentPane
																										.createSequentialGroup()
																										.addComponent(
																												label_account,
																												GroupLayout.PREFERRED_SIZE,
																												53,
																												GroupLayout.PREFERRED_SIZE)
																										.addGap(18)
																										.addComponent(
																												text_account))
																						.addGroup(
																								gl_contentPane
																										.createSequentialGroup()
																										.addComponent(
																												label_signature,
																												GroupLayout.PREFERRED_SIZE,
																												53,
																												GroupLayout.PREFERRED_SIZE)
																										.addGap(18)
																										.addComponent(
																												scrollPane,
																												GroupLayout.DEFAULT_SIZE,
																												222,
																												Short.MAX_VALUE)))
																		.addGap(30))
														.addGroup(
																Alignment.TRAILING,
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				button_update)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				button_back)
																		.addGap(32)))));
		gl_contentPane
				.setVerticalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGap(27)
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
																27,
																GroupLayout.PREFERRED_SIZE))
										.addGap(13)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																label_name,
																GroupLayout.PREFERRED_SIZE,
																27,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																text_nickname,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(10)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																label_birth,
																GroupLayout.PREFERRED_SIZE,
																27,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																text_birth,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																label_signature,
																GroupLayout.PREFERRED_SIZE,
																27,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																scrollPane,
																GroupLayout.PREFERRED_SIZE,
																52,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																button_update)
														.addComponent(
																button_back))
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		textArea_signature = new JTextArea();
		textArea_signature.setEditable(false);
		scrollPane.setViewportView(textArea_signature);
		contentPane.setLayout(gl_contentPane);
		/**
		 * 初始化加载数据
		 */
		initData();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("修改")) {
			System.out.println("点击了修改按钮");
			button_update.setText("保存");
			text_birth.setEditable(true);
			text_nickname.setEditable(true);
			textArea_signature.setEditable(true);
		} else if (e.getActionCommand().equals("保存")) {
			System.out.println("点击了保存按钮,信息保存");
			try {
				saveData();
			} catch (ParseException e1) {
				System.out.println("日期转换异常，日期格式必须是yyyy-MM-dd,例如:2016-02-21");
				e1.printStackTrace();
			}
			button_update.setText("修改");
		} else if (e.getActionCommand().equals("返回")) {
			System.out.println("点击了返回按钮");
			this.dispose();
		}
	}

	/**
	 * 加载需要加载的数据
	 */
	private void initData() {
		User user = null;
		int id = Constant.USER_ID;
		if (id != -1) {
			user = userDao.findById(id);
			if (user.getBirthday() != null) {
				String date = new SimpleDateFormat("yyyy-MM-dd").format(user
						.getBirthday());
				text_birth.setText(date);
			}
			text_account.setText(user.getName());
			text_nickname.setText(user.getNickname());
			textArea_signature.setText(user.getSignature());
		} else {
			String unknowError = "未知错误";
			text_account.setText(unknowError);
			text_nickname.setText(unknowError);
			textArea_signature.setText(unknowError);
			text_birth.setText(unknowError);
		}

	}

	private void saveData() throws ParseException {
		User user = null;
		int id = Constant.USER_ID;
		if (id != -1) {
			user = userDao.findById(id);
			String signature = textArea_signature.getText();
			String nickname = text_nickname.getText();
			String date = text_birth.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date birth = sdf.parse(date);

			user.setNickname(nickname);
			user.setSignature(signature);
			user.setBirthday(birth);
			userDao.update(user);
		}
	}

	public JButton getButton_update() {
		return button_update;
	}

	public JButton getButton_back() {
		return button_back;
	}

	public JTextArea getTextArea_signature() {
		return textArea_signature;
	}

	public JTextField getText_birth() {
		return text_birth;
	}

	public JTextField getText_nickname() {
		return text_nickname;
	}

	public JTextPane getText_account() {
		return text_account;
	}
}

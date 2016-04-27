package com.notebook.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.notebook.biz.UserBiz;
import com.notebook.dao.UserDao;
import com.notebook.pojo.User;

public class FrmRegist extends JFrame {

	private JPanel contentPane;
	private JTextField nameText;
	private JTextField nickNameText;
	private JPasswordField passwordText;
	private JPasswordField passwordagain;
	private UserDao dao = new UserDao();
	private UserBiz biz = UserBiz.getBiz();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegist frame = new FrmRegist();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmRegist() {
		setTitle("\u7528\u6237\u6CE8\u518C");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("\u7528\u6237\u540D");
		label.setFont(label.getFont().deriveFont(20f));
		label.setBounds(64, 30, 66, 24);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\u6635  \u79F0");
		label_1.setFont(label_1.getFont().deriveFont(20f));
		label_1.setBounds(64, 68, 80, 24);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("\u5BC6  \u7801");
		label_2.setFont(label_2.getFont().deriveFont(20f));
		label_2.setBounds(64, 102, 66, 24);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		label_3.setFont(label_3.getFont().deriveFont(20f));
		label_3.setBounds(64, 136, 93, 24);
		contentPane.add(label_3);

		nameText = new JTextField();
		nameText.setBounds(180, 34, 164, 21);
		contentPane.add(nameText);
		nameText.setColumns(10);

		nickNameText = new JTextField();
		nickNameText.setBounds(180, 72, 164, 21);
		contentPane.add(nickNameText);
		nickNameText.setColumns(10);

		passwordText = new JPasswordField();
		passwordText.setBounds(180, 106, 164, 21);
		contentPane.add(passwordText);
		passwordText.setColumns(10);

		passwordagain = new JPasswordField();
		passwordagain.setBounds(180, 140, 164, 21);
		contentPane.add(passwordagain);
		passwordagain.setColumns(10);

		JButton button = new JButton("\u786E\u5B9A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameText.getText();
				String nickname = nickNameText.getText();
				String pwd =String.valueOf(passwordText.getPassword());
				String pwdag =String.valueOf(passwordagain.getPassword());
				if (name != null && nickname != null && pwd != null && pwdag != null) {
					match(name, nickname, pwd, pwdag);
				}
			}

			private void match(String name, String nickname, String pwd, String pwdag) {
				// TODO 自动生成的方法存根

				if ("".equals(name) || name == null || "".equals(nickname) || nickname == null || "".equals(pwd)
						|| pwd == null || "".equals(pwdag) || pwdag == null) {
					JOptionPane.showMessageDialog(FrmRegist.this, "输入不能为空，请重新输入！");
					return;
				}
				if (biz.regit(nickname,name)) {
					JOptionPane.showMessageDialog(FrmRegist.this, "该用户名已经被使用，请选择另外的用户名！");
					return;
				}

				if (pwd.equals(pwdag)) {
					User user = new User(1, name, nickname, pwd);
					int save = dao.save(user);
					if (save > 0) {
						JOptionPane.showMessageDialog(FrmRegist.this, "注册成功！");
//						ClientSelector lgo = new ClientSelector();
//						lgo.setLocation(500, 250);
						FrmLogin lg=new FrmLogin("用户登录");
						lg.setVisible(true);
						FrmRegist.this.dispose();
//						lgo.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(FrmRegist.this, "注册失败");
					}

				} else {
					JOptionPane.showMessageDialog(FrmRegist.this, "两次密码不一样，请重新输入！");
				}
			}
		});
		button.setBounds(64, 209, 93, 23);
		contentPane.add(button);

		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameText.setText("");
				nickNameText.setText("");
				passwordText.setText("");
				passwordagain.setText("");
			}
		});
		button_1.setBounds(166, 209, 93, 23);
		contentPane.add(button_1);

		JButton button_2 = new JButton("\u8FD4\u56DE");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnReturnClick();
			}
		});
		button_2.setBounds(269, 209, 93, 23);
		contentPane.add(button_2);
	}

	protected void btnReturnClick() {
		// TODO 自动生成的方法存根
		this.setVisible(false);
//		ClientSelector frmLogin = new ClientSelector();
		FrmLogin frmLogin=new FrmLogin("用户登录");
		frmLogin.setLocation(500, 250);
		frmLogin.setVisible(true);
	}

}

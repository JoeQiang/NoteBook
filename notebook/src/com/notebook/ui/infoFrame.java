package com.notebook.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.notebook.dao.UserDao;
import com.notebook.pojo.User;
import com.notebook.utils.InfoCollection;

@SuppressWarnings("serial")
public class infoFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField nicknameInput;
	private JButton changeBtn;
	private JButton backBtn;

	private UserDao userDao;
	private JLabel account;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					infoFrame frame = new infoFrame();
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
	public infoFrame() {

		userDao = new UserDao();

		setTitle("\u4E2A\u4EBA\u4FE1\u606F");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 308, 268);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel nicknameLabel = new JLabel("\u6635\u79F0:");
		nicknameLabel.setFont(new Font("΢���ź�", Font.PLAIN, 18));

		nicknameInput = new JTextField();
		nicknameInput.setEditable(false);
		nicknameInput.setColumns(10);

		JLabel accountLabel = new JLabel("\u5E10\u53F7:");
		accountLabel.setFont(new Font("΢���ź�", Font.PLAIN, 18));

		account = new JLabel("dsa");
		account.setHorizontalAlignment(SwingConstants.LEFT);
		account.setToolTipText("\u7528\u6237\u5E10\u53F7");
		// �޸İ�ť
		changeBtn = new JButton("\u4FEE\u6539");
		changeBtn.addActionListener(this);
		// ���ذ�ť
		backBtn = new JButton("\u8FD4\u56DE");
		// ��ӵ���¼�
		backBtn.addActionListener(this);
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
																				accountLabel,
																				GroupLayout.PREFERRED_SIZE,
																				40,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addComponent(
																				account,
																				GroupLayout.DEFAULT_SIZE,
																				204,
																				Short.MAX_VALUE))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				nicknameLabel)
																		.addGap(18)
																		.addComponent(
																				nicknameInput,
																				GroupLayout.DEFAULT_SIZE,
																				204,
																				Short.MAX_VALUE))
														.addGroup(
																Alignment.TRAILING,
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				changeBtn)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				backBtn)))
										.addContainerGap()));
		gl_contentPane
				.setVerticalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGap(14)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																account,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																accountLabel,
																GroupLayout.PREFERRED_SIZE,
																24,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.TRAILING,
																false)
														.addComponent(
																nicknameInput,
																Alignment.LEADING)
														.addComponent(
																nicknameLabel,
																Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addPreferredGap(
												ComponentPlacement.RELATED,
												106, Short.MAX_VALUE)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(changeBtn)
														.addComponent(backBtn))
										.addContainerGap()));
		contentPane.setLayout(gl_contentPane);

		User user = null;
		int userId = InfoCollection.USER_ID;
		if (userId != -1) {
			user = userDao.findById(InfoCollection.USER_ID);
			nicknameInput.setText(user.getNickname());
			account.setText(user.getName());
			System.out.println("�ʺ�:" + user.getName());
			System.out.println("�ǳ�:" + user.getNickname());
		} else {
			nicknameInput.setText("����δ֪����");
			account.setText("����δ֪����");
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("����")) {
			System.out.println("����˷��ذ�ť");
			this.setVisible(false);
		} else if (e.getActionCommand().equals("�޸�")) {
			System.out.println("������޸İ�ť");
			nicknameInput.setEditable(true);
			changeBtn.setText("ȷ��");
		} else if (e.getActionCommand().equals("ȷ��")) {
			System.out.println("�����ȷ����ť");
			User user;
			user = userDao.findById(InfoCollection.USER_ID);
			System.out.println("�޸�ǰ�ǳƣ�" + user.getNickname());
			if (nicknameInput.isEnabled()) {
				String nickname = nicknameInput.getText();
				user.setNickname(nickname);
				userDao.update(user);
				System.out.println("�޸ĺ��ǳƣ�" + user.getNickname());
			}
			nicknameInput.setEditable(false);
			changeBtn.setText("�޸�");
		}
	}

	protected JButton getChangeBtn() {
		return changeBtn;
	}

	protected JButton getBackBtn() {
		return backBtn;
	}

	protected JTextField getNicknameInput() {
		return nicknameInput;
	}

	protected JLabel getAccount() {
		return account;
	}
}

package com.notebook.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.border.StandardBorderPainter;
import org.jvnet.substance.button.ClassicButtonShaper;
import org.jvnet.substance.painter.StandardGradientPainter;
import org.jvnet.substance.skin.EmeraldDuskSkin;
import org.jvnet.substance.theme.SubstanceTerracottaTheme;

import com.notebook.biz.UserBiz;
import com.notebook.dao.DiaryDao;
import com.notebook.dao.UserDao;
import com.notebook.pojo.DiaryDomain;
import com.notebook.utils.Constant;

public class FrmLogin extends JFrame implements ActionListener {
	private JPanel p;
	private JLabel lblName, lblPwd;
	private JTextField txtName;
	private JPasswordField txtPwd;
	private JButton btnOk, btnCancel, btnRegist;
	private UserBiz biz = UserBiz.getBiz();

	public FrmLogin(String title) {
		super("�û���¼");
		p = new JPanel();
		p.setLayout(null);
		lblName = new JLabel("�û���");
		lblPwd = new JLabel("��  ��");
		txtName = new JTextField(20);
		txtPwd = new JPasswordField(20);
		txtPwd.setEchoChar('*');
		btnOk = new JButton("ȷ��");
		btnCancel = new JButton("ȡ��");
		btnRegist = new JButton("ע��");
		lblName.setBounds(30, 40, 60, 25);
		lblPwd.setBounds(30, 110, 60, 25);

		txtName.setBounds(95, 30, 254, 40);
		txtPwd.setBounds(95, 100, 254, 40);

		btnOk.setBounds(30, 180, 100, 30);
		btnCancel.setBounds(140, 180, 100, 30);
		btnRegist.setBounds(250, 180, 100, 30);
		btnOk.addActionListener(this);
		btnCancel.addActionListener(this);
		btnRegist.addActionListener(this);
		p.add(lblName);
		p.add(txtName);
		p.add(lblPwd);
		p.add(txtPwd);
		p.add(btnOk);
		p.add(btnCancel);
		p.add(btnRegist);
		this.add(p);
		this.setSize(400, 270);
		this.setLocation(520, 200);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == btnOk) {
			btnOkClick();
		}
		if (source == btnCancel) {
			btnCancelClick();
		}
		if (source == btnRegist) {
			btnRegistClick();
		}
	}

	private void btnOkClick() {
		String username = txtName.getText();
		String userpwd = new String(txtPwd.getPassword());
		//
		if (username != null && !"".equals(username) && userpwd != null
				&& !"".equals(userpwd)) {
			System.out.println(username + ":" + userpwd);
			boolean login = biz.login(username, userpwd);
			if (login) {
				this.setVisible(false);
				System.out.println("��¼�ɹ�");
				UserDao userDao = new UserDao();
				DiaryDao diaryDao = new DiaryDao();
				ArrayList<DiaryDomain> diarys = new ArrayList<DiaryDomain>();
				diarys = (ArrayList<DiaryDomain>) diaryDao.getDiarys(userDao
						.findByName(username));
				// �������
				for (int i = 0; i < diarys.size(); i++) {
					System.out.println(diarys.get(i).getContent());
				}
				// չʾ�ʼ�������
				DiaryFrame df = new DiaryFrame();
				df.userID = userDao.findByName(username);
				Constant.USER_ID = df.userID;
				df.launchFrame();
			} else {
				JOptionPane.showMessageDialog(btnOk, "������û���������", "��ʾ",
						JOptionPane.WARNING_MESSAGE);
			}

		} else {
			JOptionPane.showMessageDialog(btnOk, "�û��������벻��Ϊ��", "��ʾ",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void btnCancelClick() {
		txtName.setText("");
		txtPwd.setText("");
	}

	private void btnRegistClick() {
		this.setVisible(false);
		FrmRegist frmRegist = new FrmRegist();
		frmRegist.setVisible(true);
	}

	public static void main(String[] args) {
		// Ƥ����
		// try {
		// UIManager.setLookAndFeel(new SubstanceLookAndFeel());
		// JFrame.setDefaultLookAndFeelDecorated(true);
		// JDialog.setDefaultLookAndFeelDecorated(true);
		// SubstanceLookAndFeel.setCurrentTheme(new SubstanceTerracottaTheme());
		// SubstanceLookAndFeel.setSkin(new EmeraldDuskSkin());
		// SubstanceLookAndFeel.setCurrentButtonShaper(new
		// ClassicButtonShaper());
		// SubstanceLookAndFeel.setCurrentWatermark(new
		// SubstanceBubblesWatermark());
		// SubstanceLookAndFeel.setCurrentBorderPainter(new
		// StandardBorderPainter());
		// SubstanceLookAndFeel.setCurrentGradientPainter(new
		// StandardGradientPainter());
		// SubstanceLookAndFeel.setCurrentTitlePainter(new FlatTitePainter());
		// } catch (Exception e) {
		// System.err.println("Something went wrong!");
		// }
		FrmLogin frmLogin = new FrmLogin("�û���¼");
		frmLogin.setVisible(true);
	}
}
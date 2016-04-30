package com.notebook.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.notebook.dao.DiaryDao;
import com.notebook.pojo.DiaryDomain;
import com.notebook.utils.FileIO;

public class DiaryFrame extends JFrame implements ActionListener {
	public static String SRC_PICS_DONGWU_JPG = "./src/pics/dongwu.jpg";
	public static int userID;
	public static int currentDiaryID;//保存当前编辑笔记ID
	MenuItem infoItem, cpwdItem;
	
	DefaultTreeModel model;
	static JTree tree = null;
	static DefaultMutableTreeNode root = new DefaultMutableTreeNode("我的笔记");
	static ArrayList<DefaultMutableTreeNode> nodes = new ArrayList<DefaultMutableTreeNode>();
	List<String> newCreateNodesContent = new ArrayList<String>();;

	final String PATH = "E:\\MyEclipseWorkspace\\nobe\\NoteBook\\notebook\\src\\data.txt";
	JTextArea jta = new JTextArea();
	MenuBar menu = null;
	Menu m1, m2, m3, m4, m5;
	MenuItem m1a, m1b, m1c, m1d, m1e,m1f, m2a, m2b, m2c, m2d, m3a, m4a;

	PopupMenu pMenu = null;
	JButton bSave, bDel;
	Diary diary = null;
	private JScrollPane jsp2;

	public DiaryFrame() {
	}

	public void launchFrame() {
		// 获取科目名称
		// 更改数据库获取科目数据
		DiaryDao diaryDao = new DiaryDao();
		List<String> nodesContents = diaryDao.getItemByUserID(userID);
		// List<String> nodesContents = FileIO.readTxtFile(PATH);
		int WIDTH = 1000, HEIGHT = 600;
		setTitle("课堂笔记本");
		setSize(WIDTH, HEIGHT);
		setLocation(200, 50);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		jta.setBackground(Color.LIGHT_GRAY);
		jta.setSize(20, 30);
		menu = new MenuBar();
		pMenu = new PopupMenu();
		m1 = new Menu("笔记管理");
		m2 = new Menu("字体大小");
		m4 = new Menu("格式设置");
		m3 = new Menu("联系同学");
		m5 = new Menu("个人资料");

		m1a = new MenuItem("新建科目");
		m1a.setShortcut(new MenuShortcut(KeyEvent.VK_N));
		m1b = new MenuItem("保存笔记");
		m1b.setShortcut(new MenuShortcut(KeyEvent.VK_S));
		m1c = new MenuItem("清空笔记");
		m1c.setShortcut(new MenuShortcut(KeyEvent.VK_D));
		m1d = new MenuItem("退出系统");
		m1d.setShortcut(new MenuShortcut(KeyEvent.VK_Q));
		m1e = new MenuItem("查询笔记");
		m1e.setShortcut(new MenuShortcut(KeyEvent.VK_U));
		m1f=new MenuItem("新建笔记");
		m1f.setShortcut(new MenuShortcut(KeyEvent.VK_B));

		infoItem = new MenuItem("个人信息");
		cpwdItem = new MenuItem("修改密码");

		m1.add(m1a);
		m1.addSeparator();
		m1.add(m1f);
		m1.addSeparator();
		m1.add(m1b);
		m1.addSeparator();
		m1.add(m1e);
		m1.addSeparator();
		m1.add(m1c);
		m1.addSeparator();
		m1.add(m1d);
		menu.add(m1);

		m2a = new MenuItem("大");
		m2b = new MenuItem("中");
		m2c = new MenuItem("小");
		m2d = new MenuItem("默认");

		m2.add(m2a);
		m2.addSeparator();
		m2.add(m2b);
		m2.addSeparator();
		m2.add(m2c);
		m2.addSeparator();
		m2.add(m2d);
		menu.add(m2);

		m3a = new MenuItem("聊天");
		m3.add(m3a);
		m4a = new MenuItem("颜色格式");
		m4.add(m4a);

		m5.add(infoItem);
		m5.add(cpwdItem);

		menu.add(m4);
		menu.add(m3);
		menu.add(m5);
		this.setMenuBar(menu);
		tree = new JTree(root);
		model = (DefaultTreeModel)tree.getModel();
		JScrollPane jsp1 = new JScrollPane(tree);
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		jsp2 = new JScrollPane(jta);
		jp.add(jsp2, BorderLayout.CENTER);
		JPanel bottom = new JPanel();
		bSave = new JButton("保存");
		bDel = new JButton("删除");
		bottom.add(bSave);
		bottom.add(bDel);
		jp.add(bottom, BorderLayout.SOUTH);
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				jsp1, jp);
		splitPane.setDividerLocation(180);
		// 赋值科目节点
		Iterator<String> it = nodesContents.iterator();
		while (it.hasNext()) {
			DefaultMutableTreeNode node = new Diary(it.next());
			nodes.add(node);
		}
		MyListener listener = new MyListener();

		tree.addTreeSelectionListener(listener);
		initTree();
		bSave.addActionListener(listener);
		bDel.addActionListener(listener);
		MenuListener menuListener = new MenuListener();
		m1.addActionListener(menuListener);
		m2.addActionListener(menuListener);
		m3.addActionListener(menuListener);
		m4.addActionListener(menuListener);

		infoItem.addActionListener(this);
		cpwdItem.addActionListener(this);
		this.getContentPane().add(splitPane);
		this.setVisible(true);
	}

	private static void initTree() {
		Iterator<DefaultMutableTreeNode> it = nodes.iterator();
		while (it.hasNext()) {
			DefaultMutableTreeNode n = it.next();
			root.add(n);
		}
		tree.updateUI();
		tree.repaint();
	}

	class MenuListener implements ActionListener {
		DiaryFrame df = new DiaryFrame();
		private int r;
		private int g;
		private int b;

		@Override
		public void actionPerformed(ActionEvent e) {
			String label = ((MenuItem) e.getSource()).getLabel();
			if (label.equals("格式设置")) {
				if (e.getActionCommand().equals("颜色格式")) {
					MyFont myview = new MyFont();
					myview.setVisible(true);
				}
			}
			if (e.getSource() == infoItem) {
				System.out.println("个人信息页面");
			}
			if (e.getSource() == cpwdItem) {
				System.out.println("更改密码页面");
			}
			if (label.equals("笔记管理")) {
				if (e.getActionCommand().equals("新建科目")) {
					String value = new JOptionPane().showInputDialog(df,
							"请输入新建科目名称！");
					DefaultMutableTreeNode node = new Diary(value);
					nodes.add(node);
					newCreateNodesContent.add(value);
					DiaryFrame.initTree();
				} else if (e.getActionCommand().equals("保存笔记")) {
					for (int i = 0; i < newCreateNodesContent.size(); i++) {
						try {
							FileIO.save(PATH, newCreateNodesContent.get(i));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}else if (e.getActionCommand().equals("新建笔记")) {
//					直接创建默认节点并保存数据库
					//获取选中节点  
	                DefaultMutableTreeNode selectedNode  
	                    = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();  
	                //如果节点为空，直接返回  
	                if (selectedNode == null) return;  
	                //创建一个新节点  
	                DetialNode newNode = new DetialNode("新增笔记");
	                //直接通过节点添加新节点，则需要调用tree的updateUI方法  
	                selectedNode.add(newNode);
//	               	 保存笔记数据到数据库
	                DiaryDomain diary = new DiaryDomain();
					diary.setUserID(userID);
					diary.setItem(newNode.getParent().getParent().getParent().toString());
					diary.setDate(newNode.getParent().getParent().toString()+newNode.getParent().toString());
					diary.setContent("笔记内容为空，赶紧添加吧！");
					DiaryDao diaryDao = new DiaryDao();
					int key = 0;
					try {
						key=diaryDao.addDiaryReKey(diary);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					newNode.setCurrentDiaryID(key);
	                //--------下面代码实现显示新节点（自动展开父节点）-------  
	                TreeNode[] nodes = model.getPathToRoot(newNode);  
	                TreePath path = new TreePath(nodes);  
	                tree.scrollPathToVisible(path);  
	                tree.updateUI();
				}else if (e.getActionCommand().equals("查询笔记")) {
					QueryDiary ad = new QueryDiary();
					ad.userID = userID;
					ad.setVisible(true);
				} else if (e.getActionCommand().equals("清空笔记")) {
					try {
						FileIO.save_2(PATH, "");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

				else if (e.getActionCommand().equals("退出系统")) {
					System.exit(0);
				}
			} else if (label.equals("联系同学")) {
				if (e.getActionCommand().equals("聊天")) {
					ClientSelector cs = new ClientSelector();
					cs.setLocation(500, 250);
					cs.setVisible(true);
				}

			} else if (label.equals("字体大小")) {
				if (e.getActionCommand().equals("大")) {
					jta.setFont(new Font("Serif", 1, 30));
				} else if (e.getActionCommand().equals("中")) {
					jta.setFont(new Font("Serif", 1, 24));
				} else if (e.getActionCommand().equals("小")) {
					jta.setFont(new Font("Serif", 1, 18));
				} else if (e.getActionCommand().equals("默认")) {
					jta.setFont(new Font(null));
				}
			}

		}
	}

	class MyListener implements ActionListener, TreeSelectionListener {
		DiaryFrame df = new DiaryFrame();

		@Override
		public void actionPerformed(ActionEvent e) {
//			保存笔记
			if (e.getSource() == bSave) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
						.getLastSelectedPathComponent();
				String str = node.toString();
				if (node.isLeaf()) {
					BufferedWriter out = null;
					DiaryDomain diary = new DiaryDomain();
					diary.setUserID(userID);
					diary.setItem(node.getParent().getParent().toString());
					diary.setDate(str + node.getParent().toString());
					diary.setContent(jta.getText());
					DiaryDao diaryDao = new DiaryDao();
					System.out.println(currentDiaryID+"   "+jta.getText());
					diaryDao.updateDiarysById(currentDiaryID,jta.getText());
					new JOptionPane().showMessageDialog(df, "笔记保存成功！");
				}
			} else if (e.getSource() == bDel) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
						.getLastSelectedPathComponent();
				String str = node.toString();
				if (node.isLeaf()) {
					BufferedWriter out = null;
					try {
						String fileName = node.getParent().getParent()
								.toString()
								+ node.getParent().toString() + str + ".txt";
						File file = new File(fileName);
						out = new BufferedWriter(new FileWriter(new File(
								"del.bat")));
						String cmd = "del" + file.getAbsolutePath().toString();
						out.write(cmd);
						out.newLine();
						out.flush();
						jta.setText("文件已被删除");
						del();
					} catch (IOException err) {
						err.printStackTrace();
					} finally {
						try {
							if (out != null)
								out.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}

		}

		private void del() {
			Runtime rt = Runtime.getRuntime();
			try {
				rt.exec("del.bat");
				new JOptionPane().showMessageDialog(df, "笔记已被删除！");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/* 
		 * 双击树节点事件
		 */
		@Override
		public void valueChanged(TreeSelectionEvent e) {
			jta.setText("");
			if (e.getSource() == tree) {
//				实例化树节点
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
//				如果节点没有子节点
				if (node.isLeaf()) {
//					判断该用户是否有笔记
					DiaryDao diaryDao=new DiaryDao();
//					如果有笔记
					if(diaryDao.hasItem(userID)){
//						判断节点类型：日/笔记内容
						String nodeType="";
						if(node.getParent().toString().indexOf("日")>=0){
							nodeType="内容节点";
						}
						if(node.toString().indexOf("日")>=0){
							nodeType="日节点";
						}
						String item=node.getParent().getParent().toString();
						String month=node.getParent().toString();
						String day= node.toString();
						System.out.println("节点名称:"+item+month+day+",节点类型:"+nodeType);
//						查询该月该日该科目笔记
						ArrayList<DiaryDomain> detials=diaryDao.getDetialByAll(userID, item, month+day);
						if(nodeType.equals("内容节点")){
//							查询数据库结果并且赋值给JTextArea
							currentDiaryID=((DetialNode) node).getCurrentDiaryID();
							String jtaContent=diaryDao.getDiaryById(currentDiaryID).getContent();
							jta.setText(jtaContent);
						}
						if(detials.size()==0 && nodeType.equals("日节点")){
							new JOptionPane().showMessageDialog(df, "该条件下并没有笔记！");
						}
						if(detials.size()!=0 && nodeType.equals("日节点")){
							for(int i=0;i<detials.size();i++){
								String title=detials.get(i).getContent().substring(0,10);
								DetialNode detialNode = new DetialNode(title);
								detialNode.setCurrentDiaryID(detials.get(i).getId());
								node.add(detialNode);
							}
						}
					}else{
//						如果没有新建科目
						new JOptionPane().showMessageDialog(df, "你还没有新建科目，请先新建！");
					}
				}
			}

		}

	}
//格式设置类
	class MyFont extends JFrame {
		private static final long serialVersionUID = 1L;
		private JPanel contentPane;
		private JLabel lb1 = new JLabel("字体颜色设置");
		private String myFontName;
		private int myFontSize = 15;
		private int myFontType = 0;
		private int myFontColor;

		private Font f = null;

		/**
		 * Create the frame.
		 */
		public MyFont() {

			init();// 初始化界面
		}

		public void init() {
			setTitle("格式设置");
			lb1.setBounds(20, 20, 80, 30);
			setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			setBounds(400, 150, 558, 250);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			JPanel Color = new JPanel();
			Color.setBounds(10, 10, 215, 46);
			contentPane.add(Color);
			Color.setLayout(null);

			/**
			 * 字体颜色监听
			 */
			final JRadioButton rdbtnRed = new JRadioButton("红");

			rdbtnRed.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() == rdbtnRed) {
						myFontColor = 1;
						change(myFontColor, f);
					}
				}
			});

			rdbtnRed.setBounds(5, 5, 61, 23);
			Color.add(rdbtnRed);

			final JRadioButton rdbtnBlue = new JRadioButton("黑");
			rdbtnBlue.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (e.getSource() == rdbtnBlue) {
						myFontColor = 2;
						change(myFontColor, f);
					}
				}
			});
			rdbtnBlue.setBounds(68, 5, 68, 23);
			Color.add(rdbtnBlue);

			final JRadioButton rdbtnGray = new JRadioButton("亮灰");

			rdbtnGray.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (e.getSource() == rdbtnGray) {
						myFontColor = 3;
						change(myFontColor, f);
						// txtrJanuaryBy.setFont(f);
					}
				}
			});
			rdbtnGray.setBounds(138, 5, 71, 23);
			Color.add(rdbtnGray);

			ButtonGroup buttongroup = new ButtonGroup();
			buttongroup.add(rdbtnGray);
			buttongroup.add(rdbtnBlue);
			buttongroup.add(rdbtnRed);
			jta.setLineWrap(true);
			jta.setWrapStyleWord(true);

			JPanel panel = new JPanel();
			panel.setBounds(235, 10, 151, 46);
			contentPane.add(panel);
			panel.setLayout(null);
			/**
			 * 字体形状监听
			 */
			JCheckBox chckbxMy = new JCheckBox("Italic");
			chckbxMy.setFont(new Font("宋体", Font.ITALIC, 12));
			chckbxMy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f = new Font(myFontName, 2, myFontSize);
					change(myFontColor, f);
				}
			});
			chckbxMy.setBounds(69, 6, 61, 23);
			panel.add(chckbxMy);

			final JCheckBox checkBox_1 = new JCheckBox("Bold");
			checkBox_1.setFont(new Font("宋体", Font.BOLD, 12));
			checkBox_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f = new Font(myFontName, 1, myFontSize);
					change(myFontColor, f);
				}
			});
			checkBox_1.setBounds(6, 6, 61, 23);
			panel.add(checkBox_1);

			String[] myFont = { "宋体", "微软雅黑", "黑体" };
			@SuppressWarnings({ "rawtypes", "unchecked" })
			final JComboBox comboBox = new JComboBox(myFont);
			/**
			 * 字体监听
			 */
			comboBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					myFontName = comboBox.getSelectedItem().toString();
					f = new Font(myFontName, myFontType, myFontSize);
					change(myFontColor, f);
				}
			});
			comboBox.setBounds(399, 20, 73, 21);
			contentPane.add(comboBox);
			/**
			 * 字体大小监听
			 */
			String[] mySize = { "10", "20", "30" };
			@SuppressWarnings({ "rawtypes", "unchecked" })
			final JComboBox comboBox_1 = new JComboBox(mySize);
			comboBox_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					myFontSize = Integer.parseInt(comboBox_1.getSelectedItem()
							.toString());
					f = new Font(myFontName, myFontType, myFontSize);
					change(myFontColor, f);

				}
			});

			comboBox_1.setBounds(482, 20, 50, 21);
			contentPane.add(comboBox_1);

			JButton btnChangeBackgroupColor = new JButton("改变背景颜色");
			btnChangeBackgroupColor.setContentAreaFilled(false);
			btnChangeBackgroupColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					changeBackGroupColor();
				}
			});
			btnChangeBackgroupColor
					.setFont(new Font("Consolas", Font.BOLD, 14));
			btnChangeBackgroupColor.setBounds(10, 66, 215, 23);
			contentPane.add(btnChangeBackgroupColor);

		}

		/**
		 * change TODO 改变字体
		 * 
		 * @param colorType
		 * @param myFont
		 *            void
		 */

		public void change(int colorType, Font myFont) {

			jta.setFont(myFont);

			if (colorType == 1) {
				jta.setForeground(Color.red);
			} else if (colorType == 2) {
				jta.setForeground(Color.black);
			} else {
				jta.setForeground(Color.gray);
			}
		}

		/**
		 * changeBackGroupColor TODO 随机产生RGB,改变文本框背景颜色 void
		 */
		public void changeBackGroupColor() {
			// 随机产生三基色
			int red = (int) (Math.random() * 255);
			int green = (int) (Math.random() * 255);
			int black = (int) (Math.random() * 255);
			Color color = new Color(red, green, black);
			jta.setBackground(color);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == infoItem) {
			System.out.println("个人信息页");
			InfoFrame infoFrame = new InfoFrame();
			infoFrame.setVisible(true);
		} else if (e.getSource() == cpwdItem) {
			System.out.println("修改密码页");
			ChangePwdFrame changePwdFrame = new ChangePwdFrame();
			changePwdFrame.setVisible(true);
		}
	}
}

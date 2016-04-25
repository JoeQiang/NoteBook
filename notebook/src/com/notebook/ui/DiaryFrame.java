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
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.notebook.utils.FileIO;

public class DiaryFrame extends JFrame {
	public static String SRC_PICS_DONGWU_JPG = "./src/pics/dongwu.jpg";

	public DiaryFrame() {
	}

	static JTree tree = null;
	static DefaultMutableTreeNode root = new DefaultMutableTreeNode("我的笔记");
	static ArrayList<DefaultMutableTreeNode> nodes = new ArrayList<DefaultMutableTreeNode>();
	List<String> newCreateNodesContent = new ArrayList<String>();;

	final String PATH = "E:\\eclipse\\workspace\\notebook\\src\\data.txt";
	List<String> nodesContents = FileIO.readTxtFile(PATH);
	JTextArea jta = new JTextArea(){
		ImageIcon imageIcon = new ImageIcon(SRC_PICS_DONGWU_JPG);
		{
			setOpaque(false);
		}

		public void paint(Graphics g) {
			g.drawImage(imageIcon.getImage(), 0, 0, this);
			super.paint(g);
		}
	};
	
	MenuBar menu = null;
	Menu m1, m2, m3;
	MenuItem m1a, m1b, m1c,m1d, m2a, m2b, m2c, m2d,m3a;
	PopupMenu pMenu = null;
	JButton bSave, bDel;
	Diary diary = null;
	private JScrollPane jsp2;

	public void launchFrame() {
		int WIDTH = 640, HEIGHT = 480;
		setTitle("课堂笔记本");
		setSize(WIDTH, HEIGHT);
		setLocation(400, 180);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		jta.setSize(20, 30);
		menu = new MenuBar();
		pMenu = new PopupMenu();
		m1 = new Menu("笔记管理");
		m2 = new Menu("字体大小");
		m3 = new Menu("联系同学");

		m1a = new MenuItem("新建笔记");
		m1a.setShortcut(new MenuShortcut(KeyEvent.VK_N));
		m1b = new MenuItem("保存笔记");
		m1b.setShortcut(new MenuShortcut(KeyEvent.VK_S));
		m1c = new MenuItem("清空笔记");
		m1c.setShortcut(new MenuShortcut(KeyEvent.VK_D));
		m1d = new MenuItem("退出系统");
		m1d.setShortcut(new MenuShortcut(KeyEvent.VK_Q));
		m1.add(m1a);
		m1.addSeparator();
		m1.add(m1b);
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
		menu.add(m3);

		

		this.setMenuBar(menu);
		tree = new JTree(root);
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
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jsp1, jp);

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
			if (label.equals("笔记管理")) {
				if (e.getActionCommand().equals("新建笔记")) {
					String value = new JOptionPane().showInputDialog(df, "请输入新建笔记名称！");
					DefaultMutableTreeNode node = new Diary(value);
					nodes.add(node);
					newCreateNodesContent.add(value);
					DiaryFrame.initTree();
				}
				else if (e.getActionCommand().equals("保存笔记")) {
					for (int i = 0; i < newCreateNodesContent.size(); i++) {
						try {
							FileIO.save(PATH, newCreateNodesContent.get(i));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
				else if (e.getActionCommand().equals("清空笔记")) {
						try {
							FileIO.save_2(PATH,"");
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
					jta.setFont(new Font("Serif",1,30));
				} else if (e.getActionCommand().equals("中")) {
					jta.setFont(new Font("Serif",1,24));
				} else if (e.getActionCommand().equals("小")) {
					jta.setFont(new Font("Serif",1,18));
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
			if (e.getSource() == bSave) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				String str = node.toString();
				if (node.isLeaf()) {
					BufferedWriter out = null;
					try {
						String fileName = node.getParent().getParent().toString() + node.getParent().toString() + str
								+ ".txt";
						File file = new File(fileName);
						out = new BufferedWriter(new FileWriter(file));
						out.write(jta.getText(), 0, (jta.getText()).length());
						out.flush();
						new JOptionPane().showMessageDialog(df, "笔记创建成功！");
					} catch (IOException err) {
						new JOptionPane().showMessageDialog(df, "笔记创建失败！");
						err.printStackTrace();
					} catch (Exception err) {
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
			} else if (e.getSource() == bDel) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				String str = node.toString();
				if (node.isLeaf()) {
					BufferedWriter out = null;
					try {
						String fileName = node.getParent().getParent().toString() + node.getParent().toString() + str
								+ ".txt";
						File file = new File(fileName);
						out = new BufferedWriter(new FileWriter(new File("del.bat")));
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

		@Override
		public void valueChanged(TreeSelectionEvent e) {
			jta.setText("");
			if (e.getSource() == tree) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (node.isLeaf()) {
					String str = node.toString();
					for (int i = 1; i <= 12; i++) {
						if (node.getParent().toString().equals(i + "月")) {
							BufferedReader br = null;
							try {
								String fileName = node.getParent().getParent().toString() + node.getParent().toString()
										+ str + ".txt";
								File file = new File(fileName);
								br = new BufferedReader(new FileReader(file));
								String line = null;
								while ((line = br.readLine()) != null) {
									jta.append(line + "\n");
								}
							} catch (FileNotFoundException err) {
								jta.setText("未找到笔记文件");
								err.printStackTrace();
							} catch (IOException err) {
								err.printStackTrace();
							} finally {
								try {
									if (br != null)
										br.close();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
						}
					}
				}
			}

		}

	}
	
	public static void main(String[] args) {
		DiaryFrame df = new DiaryFrame();
		df.launchFrame();
	}
}

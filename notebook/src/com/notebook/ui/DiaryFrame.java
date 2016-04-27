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

import com.notebook.dao.DiaryDao;
import com.notebook.pojo.DiaryDomain;
import com.notebook.utils.FileIO;

public class DiaryFrame extends JFrame {
	public static String SRC_PICS_DONGWU_JPG = "./src/pics/dongwu.jpg";
	public static int userID;
	public DiaryFrame() {
	}

	static JTree tree = null;
	static DefaultMutableTreeNode root = new DefaultMutableTreeNode("�ҵıʼ�");
	static ArrayList<DefaultMutableTreeNode> nodes = new ArrayList<DefaultMutableTreeNode>();
	List<String> newCreateNodesContent = new ArrayList<String>();;
	
	final String PATH = "E:\\MyEclipseWorkspace\\nobe\\NoteBook\\notebook\\src\\data.txt";
	JTextArea jta = new JTextArea();
	MenuBar menu = null;
	Menu m1, m2, m3,m4;
	MenuItem m1a, m1b, m1c,m1d,m1e, m2a, m2b, m2c, m2d,m3a,m4a;
	PopupMenu pMenu = null;
	JButton bSave, bDel;
	Diary diary = null;
	private JScrollPane jsp2;
	public void launchFrame() {
//		��ȡ��Ŀ����
//		�������ݿ��ȡ��Ŀ����
		DiaryDao diaryDao=new DiaryDao();
		List<String> nodesContents=diaryDao.getItemByUserID(userID);
//		List<String> nodesContents = FileIO.readTxtFile(PATH);
		int WIDTH = 640, HEIGHT = 480;
		setTitle("���ñʼǱ�");
		setSize(WIDTH, HEIGHT);
		setLocation(400, 180);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
        jta.setBackground(Color.DARK_GRAY);
		jta.setSize(20, 30);
		menu = new MenuBar();
		pMenu = new PopupMenu();
		m1 = new Menu("�ʼǹ���");
		m2 = new Menu("�����С");
		m4=new Menu("��ʽ����");
		m3 = new Menu("��ϵͬѧ");

		m1a = new MenuItem("�½��ʼ�");
		m1a.setShortcut(new MenuShortcut(KeyEvent.VK_N));
		m1b = new MenuItem("����ʼ�");
		m1b.setShortcut(new MenuShortcut(KeyEvent.VK_S));
		m1c = new MenuItem("��ձʼ�");
		m1c.setShortcut(new MenuShortcut(KeyEvent.VK_D));
		m1d = new MenuItem("�˳�ϵͳ");
		m1d.setShortcut(new MenuShortcut(KeyEvent.VK_Q));
		m1e = new MenuItem("��ѯ�ʼ�");
		m1e.setShortcut(new MenuShortcut(KeyEvent.VK_U));
		m1.add(m1a);
		m1.addSeparator();
		m1.add(m1b);
		m1.addSeparator();
		m1.add(m1e);
		m1.addSeparator();
		m1.add(m1c);
		m1.addSeparator();
		m1.add(m1d);
		menu.add(m1);

		m2a = new MenuItem("��");
		m2b = new MenuItem("��");
		m2c = new MenuItem("С");
		m2d = new MenuItem("Ĭ��");

		m2.add(m2a);
		m2.addSeparator();
		m2.add(m2b);
		m2.addSeparator();
		m2.add(m2c);
		m2.addSeparator();
		m2.add(m2d);
		menu.add(m2);

		m3a = new MenuItem("����");
		m3.add(m3a);
		m4a = new MenuItem("��ɫ��ʽ");
		m4.add(m4a);
		menu.add(m4);
		menu.add(m3);		

		this.setMenuBar(menu);
		tree = new JTree(root);
		JScrollPane jsp1 = new JScrollPane(tree);
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		jsp2 = new JScrollPane(jta);
		jp.add(jsp2, BorderLayout.CENTER);
		JPanel bottom = new JPanel();
		bSave = new JButton("����");
		bDel = new JButton("ɾ��");
		bottom.add(bSave);
		bottom.add(bDel);
		jp.add(bottom, BorderLayout.SOUTH);
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jsp1, jp);
//		��ֵ�ڵ�
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
			if(label.equals("��ʽ����")){
				if (e.getActionCommand().equals("��ɫ��ʽ")) {
					MyFont myview = new MyFont();
			        myview.setVisible(true);
				}
			}
			if (label.equals("�ʼǹ���")) {
				if (e.getActionCommand().equals("�½��ʼ�")) {
					String value = new JOptionPane().showInputDialog(df, "�������½��ʼ����ƣ�");
					DefaultMutableTreeNode node = new Diary(value);
					nodes.add(node);
					newCreateNodesContent.add(value);
					DiaryFrame.initTree();
				}
				else if (e.getActionCommand().equals("����ʼ�")) {
					for (int i = 0; i < newCreateNodesContent.size(); i++) {
						try {
							FileIO.save(PATH, newCreateNodesContent.get(i));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
				else if (e.getActionCommand().equals("��ѯ�ʼ�")) {
					QueryDiary ad=new QueryDiary();
					ad.userID=userID;
					ad.setVisible(true);
				}
				else if (e.getActionCommand().equals("��ձʼ�")) {
						try {
							FileIO.save_2(PATH,"");
					} catch (IOException e1) {
							e1.printStackTrace();
						}
				}

				else if (e.getActionCommand().equals("�˳�ϵͳ")) {
					System.exit(0);
				}
			} else if (label.equals("��ϵͬѧ")) {
				if (e.getActionCommand().equals("����")) {
					ClientSelector cs = new ClientSelector();
					cs.setLocation(500, 250);
					cs.setVisible(true);
				}
				
			} else if (label.equals("�����С")) {
				if (e.getActionCommand().equals("��")) {
					jta.setFont(new Font("Serif",1,30));
				} else if (e.getActionCommand().equals("��")) {
					jta.setFont(new Font("Serif",1,24));
				} else if (e.getActionCommand().equals("С")) {
					jta.setFont(new Font("Serif",1,18));
				} else if (e.getActionCommand().equals("Ĭ��")) {
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
					DiaryDomain diary=new DiaryDomain();
					diary.setItem(node.getParent().getParent().toString());
					diary.setDate(str+node.getParent().toString());
					diary.setContent(jta.getText());
					DiaryDao diaryDao=new DiaryDao();
					try {
						diaryDao.addDiary(diary);
						new JOptionPane().showMessageDialog(df, "�ʼǴ����ɹ���");
					} catch (SQLException e1) {
						new JOptionPane().showMessageDialog(df, "�ʼǴ���ʧ�ܣ�");
						e1.printStackTrace();
					}
//					try {
//						String fileName = node.getParent().getParent().toString() + node.getParent().toString() + str
//								+ ".txt";
//						File file = new File(fileName);
//						out = new BufferedWriter(new FileWriter(file));
//						out.write(jta.getText(), 0, (jta.getText()).length());
//						out.flush();
//						new JOptionPane().showMessageDialog(df, "�ʼǴ����ɹ���");
//					} catch (IOException err) {
//						new JOptionPane().showMessageDialog(df, "�ʼǴ���ʧ�ܣ�");
//						err.printStackTrace();
//					} catch (Exception err) {
//						err.printStackTrace();
//					} finally {
//						try {
//							if (out != null)
//								out.close();
//						} catch (IOException e1) {
//							e1.printStackTrace();
//						}
//					}
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
						jta.setText("�ļ��ѱ�ɾ��");
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
				new JOptionPane().showMessageDialog(df, "�ʼ��ѱ�ɾ����");
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
						if (node.getParent().toString().equals(i + "��")) {
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
								jta.setText("δ�ҵ��ʼ��ļ�");
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
	class MyFont extends JFrame{
		private static final long serialVersionUID = 1L;
	    private JPanel contentPane;
	    private JLabel lb1=new JLabel("������ɫ����");
	    private String myFontName;
	    private int myFontSize=15;
	    private int myFontType =0;
	    private int myFontColor;
	     
	    private Font f=null ;
	    /**
	     * Create the frame.
	     */
	    public MyFont() {
	         
	        init();//��ʼ������
	    }
	     
	    public void init(){
	    	lb1.setBounds(20, 20, 80, 30);
	        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	        setBounds(300, 100, 558, 300);
	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        setContentPane(contentPane);
	        contentPane.setLayout(null);
	                 
	        JPanel Color = new JPanel();
	        Color.setBounds(10, 10, 215, 46);
	        contentPane.add(Color);
	        Color.setLayout(null);
	         
	        /**
	         * ������ɫ����
	         */
	        final JRadioButton rdbtnRed = new JRadioButton("��");
	 
	        rdbtnRed.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {               
	                if(e.getSource()==rdbtnRed){
	                    myFontColor = 1;
	                    change(myFontColor,f);
	                }          
	            }
	        });
	     
	        rdbtnRed.setBounds(5, 5, 61, 23);
	        Color.add(rdbtnRed);
	         
	        final JRadioButton rdbtnBlue = new JRadioButton("��");
	        rdbtnBlue.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                 
	                if(e.getSource()==rdbtnBlue){
	                    myFontColor = 2;
	                    change(myFontColor,f);
	                }
	            }
	        });
	        rdbtnBlue.setBounds(68, 5, 68, 23);
	        Color.add(rdbtnBlue);
	         
	        final JRadioButton rdbtnGray = new JRadioButton("����");
	 
	        rdbtnGray.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                 
	                if(e.getSource()==rdbtnGray){
	                    myFontColor =3;
	                    change(myFontColor,f);
	                    //txtrJanuaryBy.setFont(f);
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
	         * ������״����
	         */
	        JCheckBox chckbxMy = new JCheckBox("Italic");
	        chckbxMy.setFont(new Font("����", Font.ITALIC, 12));
	        chckbxMy.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                 f= new Font(myFontName,2,myFontSize);
	                 change(myFontColor,f);
	            }
	        });
	        chckbxMy.setBounds(69, 6, 61, 23);
	        panel.add(chckbxMy);
	         
	        final JCheckBox checkBox_1 = new JCheckBox("Bold");
	        checkBox_1.setFont(new Font("����", Font.BOLD, 12));
	        checkBox_1.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                 f= new Font(myFontName,1 ,myFontSize);
	                 change(myFontColor,f);
	            }
	        });
	        checkBox_1.setBounds(6, 6, 61, 23);
	        panel.add(checkBox_1);
	         
	        String[] myFont = {"����","΢���ź�","Arial"};
	        @SuppressWarnings({ "rawtypes", "unchecked" })
	        final JComboBox comboBox = new JComboBox(myFont);
	        /**
	         * �������
	         */
	        comboBox.addItemListener(new ItemListener() {
	            public void itemStateChanged(ItemEvent e) {
	                 myFontName = comboBox.getSelectedItem().toString();
	                 f= new Font(myFontName,myFontType ,myFontSize);
	                 change(myFontColor,f);
	            }
	        });
	        comboBox.setBounds(399, 20, 73, 21);
	        contentPane.add(comboBox);
	        /**
	         * �����С����
	         */
	        String[] mySize ={"10","20","30"};
	        @SuppressWarnings({ "rawtypes", "unchecked" })
	        final JComboBox comboBox_1 = new JComboBox(mySize);
	        comboBox_1.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                 
	                 myFontSize =Integer.parseInt( comboBox_1.getSelectedItem().toString());
	                 f= new Font(myFontName,myFontType ,myFontSize);
	                 change(myFontColor,f);
	                 
	            }
	        });
	 
	        comboBox_1.setBounds(482, 20, 50, 21);
	        contentPane.add(comboBox_1);
	         
	        JButton btnChangeBackgroupColor = new JButton("�ı䱳����ɫ");
	        btnChangeBackgroupColor.setContentAreaFilled(false);
	        btnChangeBackgroupColor.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                changeBackGroupColor();
	            }
	        });
	        btnChangeBackgroupColor.setFont(new Font("Consolas", Font.BOLD, 14));
	        btnChangeBackgroupColor.setBounds(10, 66, 215, 23);
	        contentPane.add(btnChangeBackgroupColor);
	 
	    }
	     
	    /**
	     * change TODO �ı����� 
	     * @param colorType
	     * @param myFont void
	     */
	     
	    public void change(int colorType, Font myFont){       
	         
	    	jta.setFont(myFont);
	         
	        if(colorType==1){
	        	jta.setForeground(Color.red);
	        }else if(colorType==2){
	        	jta.setForeground(Color.blue);
	        }else{
	        	jta.setForeground(Color.gray);
	        }
	    }
	     
	    /**
	     * changeBackGroupColor TODO �������RGB,�ı��ı��򱳾���ɫ 
	     *  void
	     */
	    public  void changeBackGroupColor(){
	        //�����������ɫ
	        int red =(int )(Math.random()*255);
	        int green =(int )(Math.random()*255);
	        int black =(int )(Math.random()*255);
	        Color color = new Color(red,green,black);
	        jta.setBackground(color);
	    }
	}
}

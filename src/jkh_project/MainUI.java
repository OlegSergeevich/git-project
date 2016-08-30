package jkh_project;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class MainUI extends JFrame{
	
	public MainUI(){
		
		JFrame jkhProjectFrame = new JFrame("������ ���");
		jkhProjectFrame.setSize(800, 600);
		jkhProjectFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jkhProjectFrame.setLocationRelativeTo(null);
		jkhProjectFrame.setLayout(new GridBagLayout());
		
		final JTabbedPane tabbedPane = new JTabbedPane();
		JPanel TabbedPanel1 = new JPanel();
		JPanel TabbedPanel2 = new JPanel();
		JPanel TabbedPanel3 = new JPanel();
		JPanel TabbedPanel4 = new JPanel();
		JPanel TabbedPanel5 = new JPanel();
		JPanel mainPanel = new JPanel();
		JPanel panel = new JPanel();
		JPanel controlSpacePanel = new JPanel();
        JPanel workSpacePanel = new JPanel();  
        JPanel menuSpacePanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        panel.setLayout(new GridBagLayout());
        controlSpacePanel.setLayout(new GridBagLayout());
        workSpacePanel.setLayout(new GridBagLayout());
        menuSpacePanel.setLayout(new GridBagLayout());
        
        JButton insertButton = new JButton("��������");
		JButton updateButton = new JButton("�������������");
		JButton deleteButton = new JButton("�������");
		JButton exitButton = new JButton("�����");
		JLabel nameOfControl = new JLabel("������ � ��������");
		
		JButton fasadInfoButton = new JButton("�����");
		JButton areaInfoButton = new JButton("�������");
		JButton supportRepairButton = new JButton("������� ������");
		JButton majorRepairButton = new JButton("����������� ������");
		JButton planButton = new JButton("������������");
		JButton reportButton = new JButton("����������");
		
		GeneralInformationTableModel gtm = new GeneralInformationTableModel();
		JTable GeneralInformation = new JTable(gtm);
		GeneralInformation.getTableHeader().setReorderingAllowed( false );
		JScrollPane GeneralInformationScrollPage = new JScrollPane(GeneralInformation);
		GeneralInformationScrollPage.setPreferredSize(new Dimension(1000, 600));
		GeneralInformation.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		//���������� ������ ��������� �������
		GeneralInformation.getTableHeader().setPreferredSize(new Dimension(2200, 50));
		
		//������������/������������ �������� �� �������������� ������
		TableColumn column = null;
		Component comp = null;
		int headerWidth = 0;
		TableCellRenderer HeaderRenderer = null;
			try {
				HeaderRenderer = GeneralInformation.getTableHeader().getDefaultRenderer();
					for (int i = 0; i < GeneralInformation.getColumnCount(); i++) {
						column = GeneralInformation.getColumnModel().getColumn(i);
						comp = HeaderRenderer.getTableCellRendererComponent(null, column.getHeaderValue(),false, false, -1, i);
							headerWidth = comp.getPreferredSize().width+4;
								if ((GeneralInformation.getColumnClass(i) == String.class) && (headerWidth<40))
									headerWidth = 40;
								if ((GeneralInformation.getColumnClass(i) == Integer.class) && (headerWidth<40))
									headerWidth = 40;
								if ((GeneralInformation.getColumnClass(i) == BigDecimal.class) && (headerWidth<40))
									headerWidth = 40;
								if ((GeneralInformation.getColumnClass(i) == Date.class) && (headerWidth<40))
									headerWidth = 40;
									column.setPreferredWidth(headerWidth+20);
			}
			} catch (NullPointerException e){}
			
			
		//����� ���������� ������ �����
			class MultiLineCellRenderer extends JTextArea implements TableCellRenderer {
				 
				  public MultiLineCellRenderer() {
				    setLineWrap(true);
				    setWrapStyleWord(true);
				    setOpaque(true);
				  }
				  public Component getTableCellRendererComponent(JTable table, Object value,
				      boolean isSelected, boolean hasFocus, int row, int column) {
				    if (isSelected) {
				      setForeground(table.getSelectionForeground());
				      setBackground(table.getSelectionBackground());
				    } else {
				      setForeground(table.getForeground());
				      setBackground(table.getBackground());
				    }
				    setFont(table.getFont());
				    if (hasFocus) {
				      setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
				      if (table.isCellEditable(row, column)) {
				        setForeground(UIManager.getColor("Table.focusCellForeground"));
				        setBackground(UIManager.getColor("Table.focusCellBackground"));
				      }
				    } else {
				      setBorder(new EmptyBorder(1, 2, 1, 2));
				    }
				    setText((value == null) ? "" : value.toString());
				    return this;
				  }
				}
		//���������� ������ �����
			int lines = 2;
			GeneralInformation.setRowHeight(GeneralInformation.getRowHeight() * lines);
			GeneralInformation.setDefaultRenderer(String.class, new MultiLineCellRenderer());
		
		String url = "jdbc:mysql://localhost:3306/jkhdb";
		
		Properties properties = new Properties();
		properties.setProperty("user", "root");
		properties.setProperty("password", "root");
		properties.setProperty("characterEncoding", "UTF-8");
		properties.setProperty("useUnocode", "true");
		
		DBConnection connect = new DBConnection(url,properties);
		
		connect.init();
		gtm.addData(connect);
		connect.finalize();
		
		JButton addTab = new JButton("�������� �������");
        addTab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int i = 0;
				tabbedPane.addTab("������� " + i++, new JPanel());
            }
        });
        
        //��������� ������ �� �����
        jkhProjectFrame.add(mainPanel, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        
        mainPanel.add(tabbedPane, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		tabbedPane.add(workSpacePanel);
		
		workSpacePanel.add(panel, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		panel.add(menuSpacePanel, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		panel.add(controlSpacePanel, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		workSpacePanel.add(GeneralInformationScrollPage, new GridBagConstraints(0, 0, 1, 4,
				                    1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, 
				                                                new Insets(5, 5, 5, 3), 0, 0));
		
		//��������� ������ ���������� 
		controlSpacePanel.add(nameOfControl, new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		controlSpacePanel.add(insertButton, new GridBagConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		controlSpacePanel.add(updateButton, new GridBagConstraints(2, 2, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		controlSpacePanel.add(deleteButton, new GridBagConstraints(2, 3, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		controlSpacePanel.setBorder(BorderFactory.createLineBorder(Color.black));
	
		//��������� ������ ���� 
		
		menuSpacePanel.add(areaInfoButton, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		menuSpacePanel.add(fasadInfoButton, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		menuSpacePanel.add(supportRepairButton, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		menuSpacePanel.add(majorRepairButton, new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		menuSpacePanel.add(planButton, new GridBagConstraints(0, 5, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		menuSpacePanel.add(reportButton, new GridBagConstraints(0, 6, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		menuSpacePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		// ���� ������������� ������
		jkhProjectFrame.addWindowListener(new WindowListener() {
			 
            public void windowActivated(WindowEvent event) {
            }
 
            public void windowClosed(WindowEvent event) {
            } 
 
            public void windowClosing(WindowEvent event) {
                Object[] options = { "��", "���!" };
                int n = JOptionPane
                        .showOptionDialog(event.getWindow(), "����� �� ���������?",
                                "������������� ������", JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE, null, options,
                                options[0]);
                if (n == 0) {
                    event.getWindow().setVisible(false);
                    System.exit(0);
                }
            }
 
            public void windowDeactivated(WindowEvent event) {
 
            }
 
            public void windowDeiconified(WindowEvent event) {
 
            }
 
            public void windowIconified(WindowEvent event) {
 
            }
 
            public void windowOpened(WindowEvent event) {
 
            }
 
        });
		
		insertButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				   InsertToGeneralFrame sd = new InsertToGeneralFrame();
			}
		});
		
		
		areaInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {    
				
				tabbedPane.addTab("������� ", TabbedPanel1);
				TabbedPanel1.setSize(800, 600);
				TabbedPanel1.setLayout(new GridBagLayout());
				JPanel mainPanelArea = new JPanel();
				JPanel panelArea = new JPanel();
				JPanel controlSpacePanelArea = new JPanel();
		        JPanel workSpacePanelArea = new JPanel();  
		        JPanel menuSpacePanelArea = new JPanel();
		        mainPanelArea.setLayout(new GridBagLayout());
		        panelArea.setLayout(new GridBagLayout());
		        controlSpacePanelArea.setLayout(new GridBagLayout());
		        workSpacePanelArea.setLayout(new GridBagLayout());
		        menuSpacePanelArea.setLayout(new GridBagLayout());
		        
		        JButton insertButtonArea = new JButton("��������");
				JButton updateButtonArea = new JButton("�������������");
				JButton deleteButtonArea = new JButton("�������");
				JButton exitButtonArea = new JButton("����������");
				
				AreaTableModel atm = new AreaTableModel();
				JTable Area = new JTable(atm);
				Area.getTableHeader().setReorderingAllowed( false );
				JScrollPane AreaScrollPage = new JScrollPane(Area);
				AreaScrollPage.setPreferredSize(new Dimension(1000, 600));
				Area.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				//���������� ������ ��������� �������
				Area.getTableHeader().setPreferredSize(new Dimension(2200, 50));
						
				
				//���������� ������ �����
					int lines = 1;
					Area.setRowHeight(GeneralInformation.getRowHeight() * lines);
					Area.setDefaultRenderer(String.class, new MultiLineCellRenderer());	
		        
		        //��������� ������ �� �����
				TabbedPanel1.add(mainPanelArea, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		      
				mainPanelArea.add(workSpacePanelArea);
				
				workSpacePanelArea.add(panelArea, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				
				
				panelArea.add(controlSpacePanelArea, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				
				workSpacePanelArea.add(AreaScrollPage, new GridBagConstraints(0, 0, 1, 4, 1, 1, GridBagConstraints.NORTH,
															GridBagConstraints.BOTH, new Insets(5, 5, 5, 3), 0, 0));
				
				//��������� ������ ����������
				controlSpacePanelArea.add(insertButtonArea, new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				controlSpacePanelArea.add(updateButtonArea, new GridBagConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				controlSpacePanelArea.add(deleteButtonArea, new GridBagConstraints(2, 2, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				controlSpacePanelArea.add(exitButtonArea, new GridBagConstraints(2, 3, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
			
				//������������/������������ �������� �� �������������� ������
				TableColumn column = null;
				Component comp = null;
				int headerWidth = 0;
				TableCellRenderer HeaderRenderer = null;
					
						HeaderRenderer = Area.getTableHeader().getDefaultRenderer();
							for (int i = 0; i < Area.getColumnCount(); i++) {
								column = Area.getColumnModel().getColumn(i);
								comp = HeaderRenderer.getTableCellRendererComponent(null, column.getHeaderValue(),false, false, -1, i);
									headerWidth = comp.getPreferredSize().width+4;
										if ((Area.getColumnClass(i) == String.class) && (headerWidth<40))
											headerWidth = 40;
										if ((Area.getColumnClass(i) == Integer.class) && (headerWidth<40))
											headerWidth = 40;
										if ((Area.getColumnClass(i) == BigDecimal.class) && (headerWidth<40))
											headerWidth = 40;
										if ((Area.getColumnClass(i) == Date.class) && (headerWidth<40))
											headerWidth = 40;
											column.setPreferredWidth(headerWidth+20);
					}
							
							String url = "jdbc:mysql://localhost:3306/jkhdb";
							
							Properties properties = new Properties();
							properties.setProperty("user", "root");
							properties.setProperty("password", "root");
							properties.setProperty("characterEncoding", "UTF-8");
							properties.setProperty("useUnocode", "true");
							
							DBConnection connect = new DBConnection(url,properties);
							
							connect.init();
							atm.addData(connect);
							connect.finalize();
            }
		});
		
		
		
		fasadInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                
				tabbedPane.addTab("����� ", TabbedPanel2);
				
				TabbedPanel2.setSize(800, 600);
				TabbedPanel2.setLayout(new GridBagLayout());
				JPanel mainPanelFasad = new JPanel();
				JPanel panelFasad = new JPanel();
				JPanel controlSpacePanelFasad = new JPanel();
		        JPanel workSpacePanelFasad = new JPanel();  
		        JPanel menuSpacePanelFasad = new JPanel();
		        JPanel firstcontrolSpacePanelTecRem = new JPanel();
		        firstcontrolSpacePanelTecRem.setLayout(new GridBagLayout());
		        mainPanelFasad.setLayout(new GridBagLayout());
		        panelFasad.setLayout(new GridBagLayout());
		        controlSpacePanelFasad.setLayout(new GridBagLayout());
		        workSpacePanelFasad.setLayout(new GridBagLayout());
		        menuSpacePanelFasad.setLayout(new GridBagLayout());
		        JTextField mkd = new JTextField();
				mkd.setLayout(new GridBagLayout());
		        
		        JLabel vvod = new JLabel("������� ����� ����");
		        JButton showthis = new JButton("��������");
		        JButton insertButtonFasad = new JButton("��������");
				JButton updateButtonFasad = new JButton("�������������");
				JButton deleteButtonFasad = new JButton("�������");
				JButton exitButtonFasad = new JButton("�����");
				
				FasadTableModel ftm = new FasadTableModel();
				JTable Fasad = new JTable(ftm);
				Fasad.getTableHeader().setReorderingAllowed( false );
				JScrollPane FasadScrollPage = new JScrollPane(Fasad);
				FasadScrollPage.setPreferredSize(new Dimension(1000, 600));
				Fasad.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				//���������� ������ ��������� �������
				Fasad.getTableHeader().setPreferredSize(new Dimension(2200, 50));
						
				
				//���������� ������ �����
					int lines = 1;
					Fasad.setRowHeight(GeneralInformation.getRowHeight() * lines);
					Fasad.setDefaultRenderer(String.class, new MultiLineCellRenderer());	
		        
		        //��������� ������ �� �����
				TabbedPanel2.add(mainPanelFasad, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		      
				mainPanelFasad.add(workSpacePanelFasad);
				
				workSpacePanelFasad.add(panelFasad, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				
				panelFasad.add(firstcontrolSpacePanelTecRem, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				
				panelFasad.add(controlSpacePanelFasad, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				
				workSpacePanelFasad.add(FasadScrollPage, new GridBagConstraints(0, 0, 1, 4, 1, 1, GridBagConstraints.NORTH,
															GridBagConstraints.BOTH, new Insets(5, 5, 5, 3), 0, 0));
				
				//��������� ������ ����������
				controlSpacePanelFasad.add(insertButtonFasad, new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				controlSpacePanelFasad.add(updateButtonFasad, new GridBagConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				controlSpacePanelFasad.add(deleteButtonFasad, new GridBagConstraints(2, 2, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				controlSpacePanelFasad.add(exitButtonFasad, new GridBagConstraints(2, 3, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				
				firstcontrolSpacePanelTecRem.add(vvod, new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				firstcontrolSpacePanelTecRem.add(mkd, new GridBagConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				firstcontrolSpacePanelTecRem.add(showthis, new GridBagConstraints(2, 2, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				
				firstcontrolSpacePanelTecRem.setBorder(BorderFactory.createLineBorder(Color.black));	
				
				//������������/������������ �������� �� �������������� ������
				TableColumn column = null;
				Component comp = null;
				int headerWidth = 0;
				TableCellRenderer HeaderRenderer = null;
					
						HeaderRenderer = Fasad.getTableHeader().getDefaultRenderer();
							for (int i = 0; i < Fasad.getColumnCount(); i++) {
								column = Fasad.getColumnModel().getColumn(i);
								comp = HeaderRenderer.getTableCellRendererComponent(null, column.getHeaderValue(),false, false, -1, i);
									headerWidth = comp.getPreferredSize().width+4;
										if ((Fasad.getColumnClass(i) == String.class) && (headerWidth<40))
											headerWidth = 40;
										if ((Fasad.getColumnClass(i) == Integer.class) && (headerWidth<40))
											headerWidth = 40;
										if ((Fasad.getColumnClass(i) == BigDecimal.class) && (headerWidth<40))
											headerWidth = 40;
										if ((Fasad.getColumnClass(i) == Date.class) && (headerWidth<40))
											headerWidth = 40;
											column.setPreferredWidth(headerWidth+20);						
					}
							String url = "jdbc:mysql://localhost:3306/jkhdb";
							
							Properties properties = new Properties();
							properties.setProperty("user", "root");
							properties.setProperty("password", "root");
							properties.setProperty("characterEncoding", "UTF-8");
							properties.setProperty("useUnocode", "true");
							
							DBConnection connect = new DBConnection(url,properties);
							
							connect.init();
							ftm.addData(connect);
							connect.finalize();
            }
		});
		
		supportRepairButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                
				tabbedPane.addTab("������� ������ ", TabbedPanel3);
				
				JTextField mkd = new JTextField();
				mkd.setLayout(new GridBagLayout());
				TabbedPanel3.setSize(800, 600);
				TabbedPanel3.setLayout(new GridBagLayout());
				JPanel mainPanelTecRem = new JPanel();
				JPanel panelTecRem = new JPanel();
				JPanel controlSpacePanelTecRem = new JPanel();
				JPanel firstcontrolSpacePanelTecRem = new JPanel();
		        JPanel workSpacePanelTecRem = new JPanel();  
		        JPanel menuSpacePanelTecRem = new JPanel();
		        firstcontrolSpacePanelTecRem.setLayout(new GridBagLayout());
		        mainPanelTecRem.setLayout(new GridBagLayout());
		        panelTecRem.setLayout(new GridBagLayout());
		        controlSpacePanelTecRem.setLayout(new GridBagLayout());
		        workSpacePanelTecRem.setLayout(new GridBagLayout());
		        menuSpacePanelTecRem.setLayout(new GridBagLayout());
		        
		        JLabel vvod = new JLabel("������� ����� ����");
		        JButton insertButtonTecRem = new JButton("��������");
				JButton updateButtonTecRem = new JButton("�������������");
				JButton deleteButtonTecRem = new JButton("�������");
				JButton exitButtonTecRem = new JButton("����������");
				JButton showthis = new JButton("��������");
				
				TecRemTableModel ttm = new TecRemTableModel();
				JTable TecRem = new JTable(ttm);
				TecRem.getTableHeader().setReorderingAllowed( false );
				JScrollPane TecRemScrollPage = new JScrollPane(TecRem);
				TecRemScrollPage.setPreferredSize(new Dimension(1000, 600));
				TecRem.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				//���������� ������ ��������� �������
				TecRem.getTableHeader().setPreferredSize(new Dimension(2200, 50));
						
				
				//���������� ������ �����
					int lines = 1;
					TecRem.setRowHeight(GeneralInformation.getRowHeight() * lines);
					TecRem.setDefaultRenderer(String.class, new MultiLineCellRenderer());	
		        
		        //��������� ������ �� �����
				TabbedPanel3.add(mainPanelTecRem, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		      
				mainPanelTecRem.add(workSpacePanelTecRem);
				
				workSpacePanelTecRem.add(panelTecRem, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				
				panelTecRem.add(firstcontrolSpacePanelTecRem, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				panelTecRem.add(controlSpacePanelTecRem, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				
				workSpacePanelTecRem.add(TecRemScrollPage, new GridBagConstraints(0, 0, 1, 4, 1, 1, GridBagConstraints.NORTH,
															GridBagConstraints.BOTH, new Insets(5, 5, 5, 3), 0, 0));
				
				//��������� ������ ����������
				controlSpacePanelTecRem.add(insertButtonTecRem, new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				controlSpacePanelTecRem.add(updateButtonTecRem, new GridBagConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				controlSpacePanelTecRem.add(deleteButtonTecRem, new GridBagConstraints(2, 2, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				controlSpacePanelTecRem.add(exitButtonTecRem, new GridBagConstraints(2, 3, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));	
				
				
				firstcontrolSpacePanelTecRem.add(vvod, new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				firstcontrolSpacePanelTecRem.add(mkd, new GridBagConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				firstcontrolSpacePanelTecRem.add(showthis, new GridBagConstraints(2, 2, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				
				firstcontrolSpacePanelTecRem.setBorder(BorderFactory.createLineBorder(Color.black));
				
				//������������/������������ �������� �� �������������� ������
				TableColumn column = null;
				Component comp = null;
				int headerWidth = 0;
				TableCellRenderer HeaderRenderer = null;
					
						HeaderRenderer = TecRem.getTableHeader().getDefaultRenderer();
							for (int i = 0; i < TecRem.getColumnCount(); i++) {
								column = TecRem.getColumnModel().getColumn(i);
								comp = HeaderRenderer.getTableCellRendererComponent(null, column.getHeaderValue(),false, false, -1, i);
									headerWidth = comp.getPreferredSize().width+4;
										if ((TecRem.getColumnClass(i) == String.class) && (headerWidth<40))
											headerWidth = 40;
										if ((TecRem.getColumnClass(i) == Integer.class) && (headerWidth<40))
											headerWidth = 40;
										if ((TecRem.getColumnClass(i) == BigDecimal.class) && (headerWidth<40))
											headerWidth = 40;
										if ((TecRem.getColumnClass(i) == Date.class) && (headerWidth<40))
											headerWidth = 40;
											column.setPreferredWidth(headerWidth+20);						
					}
							String url = "jdbc:mysql://localhost:3306/jkhdb";
							
							Properties properties = new Properties();
							properties.setProperty("user", "root");
							properties.setProperty("password", "root");
							properties.setProperty("characterEncoding", "UTF-8");
							properties.setProperty("useUnocode", "true");
							
							DBConnection connect = new DBConnection(url,properties);
							
							connect.init();
							ttm.addData(connect);
							connect.finalize();
            }
		});

		
		majorRepairButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                
				tabbedPane.addTab("����������� ������ ", TabbedPanel4);
				
				JTextField mkd4 = new JTextField();
				mkd4.setLayout(new GridBagLayout());
				TabbedPanel4.setSize(800, 600);
				TabbedPanel4.setLayout(new GridBagLayout());
				JPanel mainPanelKapRem = new JPanel();
				JPanel panelKapRem = new JPanel();
				JPanel controlSpacePanelKapRem = new JPanel();
				JPanel firstcontrolSpacePanelKapRem = new JPanel();
		        JPanel workSpacePanelKapRem = new JPanel();  
		        JPanel menuSpacePanelKapRem = new JPanel();
		        firstcontrolSpacePanelKapRem.setLayout(new GridBagLayout());
		        mainPanelKapRem.setLayout(new GridBagLayout());
		        panelKapRem.setLayout(new GridBagLayout());
		        controlSpacePanelKapRem.setLayout(new GridBagLayout());
		        workSpacePanelKapRem.setLayout(new GridBagLayout());
		        menuSpacePanelKapRem.setLayout(new GridBagLayout());
		        
		        JLabel vvod4 = new JLabel("������� ����� ����");
		        JButton insertButtonKapRem = new JButton("��������");
				JButton updateButtonKapRem = new JButton("�������������");
				JButton deleteButtonKapRem = new JButton("�������");
				JButton exitButtonKapRem = new JButton("����������");
				JButton showthis4 = new JButton("��������");
				
				TecRemTableModel ktm = new TecRemTableModel();
				JTable KapRem = new JTable(ktm);
				KapRem.getTableHeader().setReorderingAllowed( false );
				JScrollPane KapRemScrollPage = new JScrollPane(KapRem);
				KapRemScrollPage.setPreferredSize(new Dimension(1000, 600));
				KapRem.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				//���������� ������ ��������� �������
				KapRem.getTableHeader().setPreferredSize(new Dimension(2200, 50));
						
				
				//���������� ������ �����
					int lines = 1;
					KapRem.setRowHeight(GeneralInformation.getRowHeight() * lines);
					KapRem.setDefaultRenderer(String.class, new MultiLineCellRenderer());	
		        
		        //��������� ������ �� �����
				TabbedPanel4.add(mainPanelKapRem, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		      
				mainPanelKapRem.add(workSpacePanelKapRem);
				
				workSpacePanelKapRem.add(panelKapRem, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				
				panelKapRem.add(firstcontrolSpacePanelKapRem, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				panelKapRem.add(controlSpacePanelKapRem, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				
				workSpacePanelKapRem.add(KapRemScrollPage, new GridBagConstraints(0, 0, 1, 4, 1, 1, GridBagConstraints.NORTH,
															GridBagConstraints.BOTH, new Insets(5, 5, 5, 3), 0, 0));
				
				//��������� ������ ����������
				controlSpacePanelKapRem.add(insertButtonKapRem, new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				controlSpacePanelKapRem.add(updateButtonKapRem, new GridBagConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				controlSpacePanelKapRem.add(deleteButtonKapRem, new GridBagConstraints(2, 2, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				controlSpacePanelKapRem.add(exitButtonKapRem, new GridBagConstraints(2, 3, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));	
				
				
				firstcontrolSpacePanelKapRem.add(vvod4, new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				firstcontrolSpacePanelKapRem.add(mkd4, new GridBagConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				firstcontrolSpacePanelKapRem.add(showthis4, new GridBagConstraints(2, 2, 1, 1, 0, 0, GridBagConstraints.NORTH, 
						GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				
				firstcontrolSpacePanelKapRem.setBorder(BorderFactory.createLineBorder(Color.black));
				
				//������������/������������ �������� �� �������������� ������
				TableColumn column = null;
				Component comp = null;
				int headerWidth = 0;
				TableCellRenderer HeaderRenderer = null;
					
						HeaderRenderer = KapRem.getTableHeader().getDefaultRenderer();
							for (int i = 0; i < KapRem.getColumnCount(); i++) {
								column = KapRem.getColumnModel().getColumn(i);
								comp = HeaderRenderer.getTableCellRendererComponent(null, column.getHeaderValue(),false, false, -1, i);
									headerWidth = comp.getPreferredSize().width+4;
										if ((KapRem.getColumnClass(i) == String.class) && (headerWidth<40))
											headerWidth = 40;
										if ((KapRem.getColumnClass(i) == Integer.class) && (headerWidth<40))
											headerWidth = 40;
										if ((KapRem.getColumnClass(i) == BigDecimal.class) && (headerWidth<40))
											headerWidth = 40;
										if ((KapRem.getColumnClass(i) == Date.class) && (headerWidth<40))
											headerWidth = 40;
											column.setPreferredWidth(headerWidth+20);						
					}
							String url = "jdbc:mysql://localhost:3306/jkhdb";
							
							Properties properties = new Properties();
							properties.setProperty("user", "root");
							properties.setProperty("password", "root");
							properties.setProperty("characterEncoding", "UTF-8");
							properties.setProperty("useUnocode", "true");
							
							DBConnection connect = new DBConnection(url,properties);
							
							connect.init();
							ktm.addData(connect);
							connect.finalize();
            }
		});
		
		planButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 
				 tabbedPane.addTab("������������", TabbedPanel5);
				 
					TabbedPanel5.setSize(800, 600);
					TabbedPanel5.setLayout(new GridBagLayout());
					String str[] = {"�. ������, ��. ������, �. 6",
							"�. ������, ��. �.��������, �. 10","�. ������, ��. �.��������, �. 12",
							"�. ������, ��. �.��������, �. 14","�. ������, ��. �.��������, �. 16",
							"�. ������, ��. �.��������, �. 18","�. ������, ��. ��������, �. 18",
							"�. ������, ��. �������, �. 13","�. ������, ��. �������, �. 4","�. ������, ��. �����, �. 10"};
					JList rating = new JList(str);
					String str2[] = {"��������� �����","������� ������","��������� �����"};
					JList analiticInfo = new JList(str2);
					
					JPanel mainPlanPanel = new JPanel();
					JPanel planPanel = new JPanel();
					JPanel analiticInfoPael = new JPanel();
					mainPlanPanel.setLayout(new GridBagLayout());
					planPanel.setLayout(new GridBagLayout());
					analiticInfoPael.setLayout(new GridBagLayout());
			        
			        JLabel ratingLabel = new JLabel("������������ ����������� ��������� ���");
			        JLabel analiticInfoLabel = new JLabel("������������� ����� �� ��");

			        JButton planButton = new JButton("�������� ������������");
			        JButton reportButton = new JButton("����������");
					JButton mkdWorkButton = new JButton("������ �� ���");
					JButton allMkdWorkButton = new JButton("������ �� ���� ���");
					JButton exitPlanButton = new JButton("�������");
					
					JScrollPane ratingScrollPane = new JScrollPane(rating);
					ratingScrollPane.setPreferredSize(new Dimension(200,200) );
			        
					JScrollPane analiticInfoScrollPane = new JScrollPane(analiticInfo);
					ratingScrollPane.setPreferredSize(new Dimension(200,200) );
					
			        //��������� ������ �� �����
					TabbedPanel5.add(mainPlanPanel, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, 
							GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
					
					mainPlanPanel.add(planPanel, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, 
							GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
					mainPlanPanel.add(analiticInfoPael, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, 
							GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
					
					
					//���������� ������������� ������
					analiticInfoPael.add(analiticInfoLabel, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, 
							GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
					analiticInfoPael.add(analiticInfoScrollPane, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.NORTH, 
							GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
					analiticInfoPael.add(mkdWorkButton, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, 
							GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
					analiticInfoPael.add(allMkdWorkButton, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.NORTH, 
							GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
					analiticInfoPael.add(exitPlanButton, new GridBagConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.NORTH, 
							GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
					
					
					
					//���������� ������ ��������
					planPanel.add(ratingLabel, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, 
							GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
					planPanel.add(ratingScrollPane, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.NORTH, 
							GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
					planPanel.add(planButton, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, 
							GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
					planPanel.add(reportButton, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.NORTH, 
							GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
					
					
			}
		});
            
		jkhProjectFrame.setVisible(true); 
		jkhProjectFrame.pack();	
	}

}

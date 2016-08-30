package jkh_project;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;

public class InsertToGeneralFrame extends JFrame{
	
	JTextField adressTextField = new JTextField(15);
	JTextField vidTextField = new JTextField(15);
	JTextField dataVvodaTextField = new JTextField(15);
	JTextField godPostroikiTextField = new JTextField(15);
	JTextField dataPrivatizaciiTextField = new JTextField(15);
	JTextField physIznosTextField = new JTextField(15);
	JTextField celesoobraznostTextField = new JTextField(15);
	JTextField upravlOrgTextField = new JTextField(15);
	
	public InsertToGeneralFrame() {
		JFrame InsertToGeneralFrame = new JFrame("Заполнение Инфорационной Базы");
		InsertToGeneralFrame.setSize(800, 600);
		InsertToGeneralFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		InsertToGeneralFrame.setLocationRelativeTo(null);
	    InsertToGeneralFrame.setVisible(true);
	    InsertToGeneralFrame.setLayout(new GridBagLayout());
		
		JLabel insertTitlelabel = new JLabel("Введите Необходимую Информацию");
		JLabel adresslabel = new JLabel("Адрес дома");
		JLabel vidlabel = new JLabel("Вид жилого фонда");
		JLabel dataVvodalabel = new JLabel("Дата ввода в эксплуатацию");
		JLabel godPostroikilabel = new JLabel("Год постройки");
		JLabel dataPrivatizaciilabel = new JLabel("Дата приватизации первого жилого помещения");
		JLabel physIznoslabel = new JLabel("Физический износ (%)");
		JLabel celesoobraznostlabel = new JLabel("Ремонт нецелесообразен");
		JLabel upravlOrglabel = new JLabel("Управляющая организация");
		
		
		
		JButton saveButton = new JButton("Сохранить");
		JButton cancelButton = new JButton("Отмена");
		
		InsertToGeneralFrame.add(insertTitlelabel, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 150, 5, 5), 0, 0));
		
		InsertToGeneralFrame.add(adresslabel, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		InsertToGeneralFrame.add(vidlabel, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		InsertToGeneralFrame.add(dataVvodalabel, new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		InsertToGeneralFrame.add(godPostroikilabel, new GridBagConstraints(0, 5, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		InsertToGeneralFrame.add(dataPrivatizaciilabel, new GridBagConstraints(0, 6, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		InsertToGeneralFrame.add(physIznoslabel, new GridBagConstraints(0, 7, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		InsertToGeneralFrame.add(celesoobraznostlabel, new GridBagConstraints(0, 8, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		InsertToGeneralFrame.add(upravlOrglabel, new GridBagConstraints(0, 9, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		
		InsertToGeneralFrame.add(adressTextField, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		InsertToGeneralFrame.add(vidTextField, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		InsertToGeneralFrame.add(dataVvodaTextField, new GridBagConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		InsertToGeneralFrame.add(godPostroikiTextField, new GridBagConstraints(1, 5, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		InsertToGeneralFrame.add(dataPrivatizaciiTextField, new GridBagConstraints(1, 6, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		InsertToGeneralFrame.add(physIznosTextField, new GridBagConstraints(1, 7, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		InsertToGeneralFrame.add(celesoobraznostTextField, new GridBagConstraints(1, 8, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		InsertToGeneralFrame.add(upravlOrgTextField, new GridBagConstraints(1, 9, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		InsertToGeneralFrame.add(saveButton, new GridBagConstraints(0, 10, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		InsertToGeneralFrame.add(cancelButton, new GridBagConstraints(1, 10, 1, 1, 0, 0, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		InsertToGeneralFrame.pack();
	InsertToGeneralFrame.setVisible(true);
	
	
	saveButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String url = "jdbc:mysql://localhost:3306/jkhdb";
			
			Properties properties = new Properties();
			properties.setProperty("user", "root");
			properties.setProperty("password", "root");
			properties.setProperty("characterEncoding", "UTF-8");
			properties.setProperty("useUnocode", "true");
			
			DBConnection connect = new DBConnection(url,properties);
			Connection mySqlConnect = null;
			try {
				mySqlConnect = DriverManager.getConnection(url, properties);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			String s1 = adressTextField.getText();
			String s2 = vidTextField.getText();
			String s3 = dataVvodaTextField.getText();
			String s4 = godPostroikiTextField.getText();
			String s5 = dataPrivatizaciiTextField.getText();
			String s6 = physIznosTextField.getText();
			String s7 = celesoobraznostTextField.getText();
			String s8 = upravlOrgTextField.getText();
			
			connect.init();
			
			String query ="INSERT IGNORE INTO generalinfo (adress, jil_fond, data_vvoda, build_year, data_privatizacii, Phys_iznos, celesoobr_remonta, upravl_org) VALUES ('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"')"; 
			Statement geter;
			try {
				geter = mySqlConnect.createStatement();
				geter.executeUpdate(query);
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			connect.finalize();
			
			
        }
		
	});
	
}
	
}

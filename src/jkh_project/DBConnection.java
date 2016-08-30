package jkh_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JTextField;

public class DBConnection {

	private String host;
	private String root;
	private String password;
	private String nameDb;
	private String url;
	
	private Properties properties = new Properties();
	private Connection mySqlConnect;
	
	public DBConnection(String host ,String root ,String password ,String nameDb) {
		this.host = host;
		this.root = root;
		this.password = password;
		this.nameDb = nameDb;
	}
	
	public DBConnection(String url, Properties properties) {
		this.url = url;
		this.properties = properties;
	}
	
	public void init() {
		if (mySqlConnect==null) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			mySqlConnect = DriverManager.getConnection(url, properties);
			if (!mySqlConnect.isClosed()){
				System.out.println("Соединение с БД установленно!");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	  }
		
		
	}
	
	public void finalize() {
		try {
			mySqlConnect.close();
			if (mySqlConnect.isClosed()){
				System.out.println("Соединение с БД закрыто!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet resultSetQuery(String query) {
		ResultSet result = null;
		try {
			Statement stmt = mySqlConnect.createStatement();
			result = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void sqlQuery(String query) {
		try {
			Statement stmt = mySqlConnect.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}
	
	
	public void geter() {
		try {
			JTextField adressTextField = new JTextField(15);
			JTextField vidTextField = new JTextField(15);
			JTextField dataVvodaTextField = new JTextField(15);
			JTextField godPostroikiTextField = new JTextField(15);
			JTextField dataPrivatizaciiTextField = new JTextField(15);
			JTextField physIznosTextField = new JTextField(15);
			JTextField celesoobraznostTextField = new JTextField(15);
			JTextField upravlOrgTextField = new JTextField(15);

			String s1 = adressTextField.getText();
			String s2 = vidTextField.getText();
			String s3 = dataVvodaTextField.getText();
			String s4 = godPostroikiTextField.getText();
			String s5 = dataPrivatizaciiTextField.getText();
			String s6 = physIznosTextField.getText();
			String s7 = celesoobraznostTextField.getText();
			String s8 = upravlOrgTextField.getText();
			
			
		String query ="INSERT IGNORE INTO generalinfo (id, adress, jil_fond, data_vvoda, build_year, data_privatizacii, Phys_iznos, celesoobr_remonta, upravl_org) VALUES ('111','"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"')"; 
			Statement geter = mySqlConnect.createStatement();
			geter.executeUpdate(query);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}


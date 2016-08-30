package jkh_project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class GeneralInformationTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = -8868147766659865224L;
	
	private int columnCount = 8;
	private ArrayList <String[]> dataArrayList;
	
	public GeneralInformationTableModel() {
		dataArrayList = new ArrayList <String[]>();
		for(int i=0;i<dataArrayList.size();i++){
			dataArrayList.add(new String[getColumnCount()]);
		}
		
	}

	@Override
	public int getColumnCount() {
		return columnCount;
	}

	
	@Override  
	   public boolean isCellEditable(int row, int column){  
	    return true; 
	}
	
	
	@Override
	public String getColumnName(int columnIndex) {
		switch(columnIndex){
		case 0:  return "Адрес дома";
		case 1:  return "Вид жилого фонда";
		case 2:  return "Дата ввода в эксплуатацию";
		case 3:  return "Год постройки";
		case 4:  return "Дата приватизации первого жилого помещения";
		case 5:  return "Физический износ (%)";
		case 6: return "Ремонт нецелесообразен";
		case 7: return "Управляющая организация";
		}
		return "";
	}

	@Override
	public int getRowCount() {
		return dataArrayList.size();
	}
	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		String []rows = dataArrayList.get(rowIndex);
		return rows[columnIndex];
	}
	
	public void updateValue(String newValue, int rowIndex, int columnIndex){
	    String[] row = dataArrayList.get(rowIndex);
	    row[columnIndex] = newValue;
	};

	
	public void addData(String []row){
		String []rowTable = new String [getColumnCount()];
		rowTable = row;
		dataArrayList.add(rowTable);
	}
	
	public void addData(DBConnection connect) {
		ResultSet result = connect.resultSetQuery("SELECT * FROM jkhdb.generalinfo;");
		try {
			while (result.next()) {
				
				String []row = {
						result.getString("adress"),
						result.getString("jil_fond"),
						result.getString("data_vvoda"),
						result.getString("build_year"),
						result.getString("data_privatizacii"),
						result.getString("Phys_iznos"),
						result.getString("celesoobr_remonta"),
						result.getString("upravl_org") };
				
				addData(row);
				System.out.println(row);
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

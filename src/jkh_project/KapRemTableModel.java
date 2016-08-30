package jkh_project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class KapRemTableModel extends AbstractTableModel {

	private int columnCount = 4;
	private ArrayList <String[]> dataArrayList;
	
	public KapRemTableModel() {
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
		case 0:  return "Наименование работ";
		case 1:  return "Объем работ";
		case 2:  return "Цена работ";
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
		ResultSet result = connect.resultSetQuery("SELECT * FROM jkhdb.kaprem;");
		try {
			while (result.next()) {
				
				String []row = {
						result.getString("name"),
						result.getString("size"),
						result.getString("cost"), };
				
				addData(row);
				System.out.println(row);
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

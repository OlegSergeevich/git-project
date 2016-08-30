package jkh_project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class FasadTableModel extends AbstractTableModel {

	private int columnCount = 4;
	private ArrayList <String[]> dataArrayList;
	
	public FasadTableModel() {
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
		case 0:  return "Фасад";
		case 1:  return "Год установки КЭ";
		case 2:  return "Процент износа КЭ";
		case 3:  return "Объем КЭ";
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
		ResultSet result = connect.resultSetQuery("SELECT * FROM jkhdb.fasad;");
		try {
			while (result.next()) {
				
				String []row = {
						result.getString("fasad"),
						result.getString("god_vvoda_ce"),
						result.getString("teh_iznos_ce"),
						result.getString("size_ce"), };
				
				addData(row);
				System.out.println(row);
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

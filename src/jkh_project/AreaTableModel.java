package jkh_project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class AreaTableModel extends AbstractTableModel {

	private int columnCount = 10;
	private ArrayList <String[]> dataArrayList;
	
	public AreaTableModel() {
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
		case 0:  return "����� ����";
		case 1:  return "�����";
		case 2:  return "������� �������������";
		case 3:  return "������������� �������������";
		case 4:  return "��������������� �������������";
		case 5:  return "����� ������� ����� � ������� ���������";
		case 6:  return "����� �����";
		case 7:  return "����� � ������������� �������";
		case 8:  return "������� ��������������� ����������";
		case 9:  return "��������� ������ �����������";
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
		ResultSet result = connect.resultSetQuery("SELECT * FROM jkhdb.area;");
		try {
			while (result.next()) {
				
				String []row = {
						result.getString("adress"),
						result.getString("obshaya"),
						result.getString("shastnoi_sobstvennosti"),
						result.getString("municipalnoi_sobstvennosti"),
						result.getString("gos_sobstvennosti"),
						result.getString("obshaya_jil_nejil_pomesheni"),
						result.getString("jil_vsego"),
						result.getString("jil_nahodyashihsya_v_sobstvennosti_grajdan"),
						result.getString("nejil_functional_naznasheniya"),
						result.getString("ploshad_pomesheni_obshego_polzovaniya"), };
				
				addData(row);
				System.out.println(row);
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}



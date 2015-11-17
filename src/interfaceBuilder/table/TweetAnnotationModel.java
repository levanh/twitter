package interfaceBuilder.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import utility.Tweet;

public class TweetAnnotationModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String [] columnNames = {"Tweet", "Note"};
	private List<Tweet> data = new ArrayList<Tweet>();
	
	public int getRowCount() {
		return data.size();
	}

	public int getColumnCount() {
		return 2;
	}
	
	public List<Tweet> getData() {
		return data;
	}
	
	public String getColumnName(int col) {
        return columnNames[col];
    }

	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0){
			return data.get(rowIndex).getTweetContent();
		}
		else {
			return data.get(rowIndex).getNote().toString();
		}
	}
	
	public void addRow(Tweet t){
		
		data.add(t);
		fireTableRowsInserted(data.size(), data.size());
	}
	
	public void addAll(List<Tweet> list){
		for(Tweet t: list){
			addRow(t);
		}
		fireTableStructureChanged();
	}
	
	public Class getColumnClass(int c) {
		if (c==0){
			return String.class;
		}
		else {
			return String.class;
		}
	}
	
	public boolean isCellEditable(int row, int col){
		if (col == 0){
			return false;
		}
		else {
			return true;
		}
	}
	
	public void setValueAt(Object value, int row, int col){
		System.out.println("Setting value at " + row + "," + col
	            + " to " + value + " (an instance of "
	            + value.getClass() + ")");
		if (col == 0  && value instanceof String) {
			data.get(row).setTweetContent((String) value);
		}
		else if (col == 1 && value instanceof String){
			data.get(row).setNote(Integer.parseInt((String) value));
			
		}
		fireTableCellUpdated(row, col);
		
	}
	
	

}

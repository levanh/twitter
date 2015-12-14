package interfaceBuilder.table;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;

import utility.Tweet;

public class TweetAnnotationTable extends JPanel{

	private JTable table;
	private TweetAnnotationModel model;
	private static final long serialVersionUID = 1L;
	
	public TweetAnnotationTable(){
		 super(new GridLayout(1,0));
		 
		 model = new TweetAnnotationModel();
	     setTable(new JTable(model));
	     getTable().setPreferredScrollableViewportSize(new Dimension(800, 300));
	     
	     // Set up note column
	     String[] Notes = {"-1", "0", "2", "4"};
		 JComboBox<String> comboBox = new JComboBox<String>(Notes);
		 
	     table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBox));
	     
	     // Set up columns widths
	     table.getColumnModel().getColumn(0).setPreferredWidth(700);
	     table.getColumnModel().getColumn(1).setPreferredWidth(100);
	}
	
	public TweetAnnotationModel getModel() {
		return this.model;
	}
	
	public void addRow(Tweet t){
		model.addRow(t);
	}
	
	public void addAll(List<Tweet> list){
		model.addAll(list);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	

}

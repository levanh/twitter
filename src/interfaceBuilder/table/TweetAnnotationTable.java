package interfaceBuilder.table;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import utility.Tweet;

public class TweetAnnotationTable extends JPanel{

	private JTable table;
	private TweetAnnotationModel model;
	private static final long serialVersionUID = 1L;
	
	public TweetAnnotationTable(){

		 super(new GridLayout(1,0));
		 
		 model = new TweetAnnotationModel();
	     table = new JTable(model);
	     table.setPreferredScrollableViewportSize(new Dimension(500, 300));
	     
	     setNoteColumn(table.getColumnModel().getColumn(1));
	     
	     JScrollPane scrollPane = new JScrollPane(table);
	     table.setFillsViewportHeight(true);
	     
	     add(scrollPane);
	     
	}
	
	public void setNoteColumn(TableColumn noteColumn){
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("-1");
		comboBox.addItem("0");
		comboBox.addItem("2");
		comboBox.addItem("4");
		
		noteColumn.setCellEditor(new DefaultCellEditor(comboBox));
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
	

}

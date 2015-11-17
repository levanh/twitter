package interfaceBuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextField;

import interfaceBuilder.table.TweetAnnotationTable;
import utility.CSVGenerator;
import utility.Tweet;

public class SaveListener implements ActionListener {

	private JTextField loadField;
	private TweetAnnotationTable table;

	public SaveListener(JTextField loadField, TweetAnnotationTable table) {
		this.loadField = loadField;
		this.table = table;
	}

	public void actionPerformed(ActionEvent e) {
		String filename = loadField.getText();
		if (filename.matches(".*\\.csv")) {
			CSVGenerator gener = new CSVGenerator(filename);
			gener.writeCSV(table.getModel().getData());
		}
	}

}

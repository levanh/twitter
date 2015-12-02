package interfaceBuilder.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import interfaceBuilder.table.TweetAnnotationTable;
import utility.CSVReader;

public class LoadListener implements ActionListener {

	private JTextField loadField;
	private TweetAnnotationTable table;

	public LoadListener(JTextField loadField, TweetAnnotationTable table) {
		this.loadField = loadField;
		this.table = table;
	}

	public void actionPerformed(ActionEvent e) {
		String filename = loadField.getText();
		if (filename.matches(".*\\.csv")) {
			CSVReader reader = new CSVReader(filename);
			table.getModel().addAll(reader.readCSV());
		}
	}

}
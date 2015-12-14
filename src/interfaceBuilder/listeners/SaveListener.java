package interfaceBuilder.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import interfaceBuilder.table.TweetAnnotationTable;
import utility.CSVGenerator;
import utility.Tweet;

public class SaveListener implements ActionListener {

	private JTextField loadField;
	private TweetAnnotationTable table;
	private JTextArea text;

	public SaveListener(JTextField loadField, TweetAnnotationTable table,JTextArea text) {
		this.loadField = loadField;
		this.table = table;
		this.text = text;
	}

	public void actionPerformed(ActionEvent e) {
		this.text.setText("");
		String filename = loadField.getText();
		if (filename.matches(".*\\.csv")) {
			CSVGenerator gener = new CSVGenerator(filename);
			gener.writeCSV(table.getModel().getData());
		}
		this.text.setText("Le fichier "+ filename + " a été enregistré au format CSV");
	}

}

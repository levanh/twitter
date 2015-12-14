package interfaceBuilder.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import interfaceBuilder.table.TweetAnnotationTable;
import utility.CSVReader;

public class LoadListener implements ActionListener {

	private JTextField loadField;
	private TweetAnnotationTable table;
	private JTextArea text;

	public LoadListener(JTextField loadField, TweetAnnotationTable table, JTextArea text) {
		this.loadField = loadField;
		this.table = table;
		this.text = text;
	}

	public void actionPerformed(ActionEvent e) {
		this.text.setText("");
		String filename = loadField.getText();
		if (filename.matches(".*\\.csv")) {
			CSVReader reader = new CSVReader(filename);
			table.getModel().addAll(reader.readCSV());
		}
		this.text.setText("Le fichier "+ filename + " a été chargé pour la notation");
	}

}
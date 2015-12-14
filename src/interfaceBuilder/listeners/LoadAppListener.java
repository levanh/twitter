package interfaceBuilder.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import utility.CSVReader;
import utility.Tweet;

public class LoadAppListener implements ActionListener {

	private JTextField loadField;
	private List<Tweet> app;
	private JTextArea text;

	public LoadAppListener(JTextField loadField, List<Tweet> app, JTextArea text) {
		this.loadField = loadField;
		this.app = app;
		this.text = text;
	}

	public void actionPerformed(ActionEvent e) {
		this.text.setText("");
		String filename = loadField.getText();
		app.clear();
		if (filename.matches(".*\\.csv")) {
			CSVReader reader = new CSVReader(filename);
			app.addAll(reader.readCSV());
			System.out.println(app);
		}
		this.text.setText("Le fichier "+ filename + " a été chargé comme base d'apprentissage");
	}

}
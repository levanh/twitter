package interfaceBuilder.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import interfaceBuilder.table.TweetAnnotationTable;
import utility.CSVReader;
import utility.Tweet;

public class LoadAppListener implements ActionListener {

	private JTextField loadField;
	private List<Tweet> app;

	public LoadAppListener(JTextField loadField, List<Tweet> app) {
		this.loadField = loadField;
		this.app = app;
	}

	public void actionPerformed(ActionEvent e) {
		String filename = loadField.getText();
		app.clear();
		if (filename.matches(".*\\.csv")) {
			CSVReader reader = new CSVReader(filename);
			app.addAll(reader.readCSV());
			System.out.println(app);
		}
	}

}
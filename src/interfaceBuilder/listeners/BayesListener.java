package interfaceBuilder.listeners;

import interfaceBuilder.MainWindow;
import interfaceBuilder.table.TweetAnnotationTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JTextArea;

import bayes.BayesClassifier;
import utility.Tweet;

public class BayesListener implements ActionListener {
	
	private TweetAnnotationTable table;
	private List<Tweet> app;
	private JCheckBox freq;
	private JCheckBox word;
	private JTextArea text;
	
	

	public BayesListener(TweetAnnotationTable table, List<Tweet> app, JCheckBox freq,JCheckBox word, JTextArea text) {
		super();
		this.table = table;
		this.app = app;
		this.freq = freq;
		this.word = word;
		this.text = text;
	}



	public void actionPerformed(ActionEvent e) {
		BayesClassifier bc = new BayesClassifier(freq.isSelected(), word.isSelected(), null);
		for (Tweet t: table.getModel().getData())
			t.setTweetContent(t.getTweetContent().toLowerCase());

		bc.classList(table.getModel().getData(), app);
		table.getModel().fireTableDataChanged();
	}

}

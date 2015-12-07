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
	private JTextArea text;
	
	

	public BayesListener(TweetAnnotationTable table, List<Tweet> app,
			JTextArea text) {
		super();
		this.table = table;
		this.app = app;
		this.text = text;
	}



	public void actionPerformed(ActionEvent e) {
		BayesClassifier bc = new BayesClassifier(MainWindow.getFreq(), null, null);

		bc.bayesClassList(table.getModel().getData(), app);
		table.getModel().fireTableDataChanged();
	}

}

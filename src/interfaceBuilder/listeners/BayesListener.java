package interfaceBuilder.listeners;

import interfaceBuilder.table.TweetAnnotationTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JCheckBox;

import utility.Tweet;

public class BayesListener implements ActionListener {
	
	private TweetAnnotationTable table;
	private List<Tweet> app;
	private JCheckBox freq;
	
	

	public BayesListener(TweetAnnotationTable table, List<Tweet> app,
			JCheckBox freq) {
		super();
		this.table = table;
		this.app = app;
		this.freq = freq;
	}



	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

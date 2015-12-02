package interfaceBuilder.listeners;

import interfaceBuilder.table.TweetAnnotationTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import utility.Tweet;

public class KNNListener implements ActionListener {
	
	private TweetAnnotationTable table;
	private List<Tweet> app;

	

	public KNNListener(TweetAnnotationTable table, List<Tweet> app) {
		this.table = table;
		this.app = app;
	}





	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

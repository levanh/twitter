package interfaceBuilder.listeners;

import interfaceBuilder.table.TweetAnnotationTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import utility.Tweet;

public class ResetListener implements ActionListener{

	
	private TweetAnnotationTable table;
	private List<Tweet> app;
	public ResetListener(TweetAnnotationTable table, List<Tweet> app) {
		super();
		this.table = table;
		this.app = app;
	}
	public void actionPerformed(ActionEvent e) {
		app.clear();
		table.getModel().removeAll();
	}
	
	
}

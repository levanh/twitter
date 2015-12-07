package interfaceBuilder.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import interfaceBuilder.table.TweetAnnotationTable;
import utility.Tweet;

public class SplitAppListener implements ActionListener {
	
	private TweetAnnotationTable table;
	private List<Tweet> app;

	
	
	public SplitAppListener(TweetAnnotationTable table, List<Tweet> app) {
		super();
		this.table = table;
		this.app = app;
	}



	public void actionPerformed(ActionEvent arg0) {
		int length = app.size();
		length = length/5;
		table.getModel().removeAll();
		
		for (int i = 0; i<length; i++){
			table.addRow(app.remove(0));
		}
	}

}

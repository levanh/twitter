package interfaceBuilder.listeners;

import interfaceBuilder.table.TweetAnnotationTable;
import knn.KnnAlgo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import utility.Tweet;

public class KNNListener implements ActionListener {
	
	private TweetAnnotationTable table;
	private List<Tweet> app;
	private int knear;

	

	public KNNListener(TweetAnnotationTable table, List<Tweet> app) {
		this.table = table;
		this.app = app;
		this.knear = 3;
	}





	public void actionPerformed(ActionEvent e) {
		KnnAlgo knn = new KnnAlgo(app);
		for (Tweet t: table.getModel().getData()){
			knn.noteTweet(knear, t);
		}
		
	}

}

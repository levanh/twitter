package interfaceBuilder.listeners;

import interfaceBuilder.table.TweetAnnotationTable;
import knn.KnnAlgo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import utility.Tweet;

public class KNNListener implements ActionListener {
	
	private TweetAnnotationTable table;
	private List<Tweet> app;
	private JTextField knear;
	private JTextArea text;

	

	public KNNListener(TweetAnnotationTable table, List<Tweet> app, JTextField knear, JTextArea text) {
		this.table = table;
		this.app = app;
		this.knear = knear;
		this.text = text;
	}





	public void actionPerformed(ActionEvent e) {
		KnnAlgo knn = new KnnAlgo(app);
		int k = Integer.parseInt(knear.getText());
		for (Tweet t: table.getModel().getData()){
			knn.noteTweet(k, t);
		}
		table.getModel().fireTableDataChanged();
	}

}

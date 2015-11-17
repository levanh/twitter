package interfaceBuilder;

import interfaceBuilder.table.TweetAnnotationTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import search.TwitterSearch;
import utility.Tweet;
import utility.TweetCleaner;

public class SearchListener implements ActionListener {
	private TweetAnnotationTable table;
	private JTextField searchArea;

	public SearchListener(JTextField searchArea, TweetAnnotationTable table) {
		this.searchArea = searchArea;
		this.table = table;
	}

	public void actionPerformed(ActionEvent e) {
		String topic = this.searchArea.getText();
		System.out.println("New search:" + topic);
		TwitterSearch searcher = new TwitterSearch(topic, 1);
		List<Tweet> resultList = searcher.search();
		
		TweetCleaner cleaner = TweetCleaner.getInstance();
		for (Tweet result : resultList) {
			cleaner.clean(result);
		}


		table.addAll(resultList);
		
	}

}

package interfaceBuilder;

import interfaceBuilder.table.TweetAnnotationTable;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import utility.Tweet;

@SuppressWarnings("serial")
public class MainWindow extends JPanel {


	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("Tweet Search");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up the content pane, where the "main GUI" lives.
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		// Set up the search pane.
		JPanel searchPane = new JPanel();
		searchPane.setLayout(new FlowLayout());
		searchPane.setBorder(new TitledBorder("Searching"));

		JTextField searchField = new JTextField();
		searchField.setMaximumSize(new Dimension(100, 30));
		searchField.setColumns(15);

		JLabel searchLabel = new JLabel("Tweet keyword(s): ");
		searchLabel.setLabelFor(searchField);

		JButton searchTweetButton = new JButton("Search");

		searchPane.add(searchLabel);
		searchPane.add(searchField);
		searchPane.add(searchTweetButton);
		
		
		
		

		// Set up the tweets display pane.
		TweetAnnotationTable tweetPane = new TweetAnnotationTable();
		//tweetPane.setLayout(new BoxLayout(tweetPane, BoxLayout.Y_AXIS));
		
		
		
		
		

		

		// Set up the editing (cleaning, rating, saving, etc.) pane.
		JPanel editPane = new JPanel();
		
		editPane.setLayout(new FlowLayout());
		editPane.setBorder(new TitledBorder("Editing"));
	
		JTextField loadField = new JTextField();
		loadField.setMaximumSize(new Dimension(100, 30));
		loadField.setColumns(15);
		
		editPane.add(loadField);
		
		
		JButton saveTweetButton = new JButton("Save in CSV base");
		JButton loadTweetButton = new JButton("Load from CSV base");

		editPane.add(saveTweetButton);
		editPane.add(loadTweetButton);

		SearchListener searchListener = new SearchListener(searchField, tweetPane);
		searchTweetButton.addActionListener(searchListener);
		SaveListener saveListener = new SaveListener(loadField, tweetPane);
		saveTweetButton.addActionListener(saveListener);
		LoadListener loadListener = new LoadListener(loadField, tweetPane);
		loadTweetButton.addActionListener(loadListener);

		// Add various panes to the content pane.
		contentPane.add(searchPane);
		contentPane.add(tweetPane);
		contentPane.add(editPane);

		// Show the window.
		frame.pack();
		frame.getRootPane().setDefaultButton(searchTweetButton);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
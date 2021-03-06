package interfaceBuilder;

import interfaceBuilder.itemListeners.ChangeConfigListener;
import interfaceBuilder.listeners.BayesListener;
import interfaceBuilder.listeners.KNNListener;
import interfaceBuilder.listeners.KeyWordListener;
import interfaceBuilder.listeners.LoadAppListener;
import interfaceBuilder.listeners.LoadListener;
import interfaceBuilder.listeners.ResetListener;
import interfaceBuilder.listeners.SaveListener;
import interfaceBuilder.listeners.SearchListener;
import interfaceBuilder.listeners.SplitAppListener;
import interfaceBuilder.table.TweetAnnotationTable;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import utility.ChangeConfig;
import utility.Tweet;

@SuppressWarnings("serial")
public class MainWindow extends JPanel {
	
	static List<Tweet> appList;
	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		
		ChangeConfig.standardConfig();
		// Create and set up the window.
		JFrame frame = new JFrame("Tweet Annoter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up the content pane, where the "main GUI" lives.
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		appList = new ArrayList<Tweet>();
		JTextArea resultArea = new JTextArea(5,50);

		/**
		 * Set up the search pane.
		 */
		
		JPanel searchPane = new JPanel();
		searchPane.setLayout(new FlowLayout());
		searchPane.setBorder(new TitledBorder("Recherche"));

		JTextField searchField = new JTextField();
		searchField.setMaximumSize(new Dimension(100, 30));
		searchField.setColumns(15);

		JLabel searchLabel = new JLabel("Mots clefs pour la recherche: ");
		searchLabel.setLabelFor(searchField);
		
		JCheckBox configBox = new JCheckBox("Mode avec proxy pour l'université");
		ChangeConfigListener configListener = new ChangeConfigListener();
		configBox.addItemListener(configListener);

		JButton searchTweetButton = new JButton("Rechercher");

		searchPane.add(searchLabel);
		searchPane.add(searchField);
		searchPane.add(searchTweetButton);
		searchPane.add(configBox);

		/**
		 * Set up the tweets display pane.
		 */
		
		TweetAnnotationTable tweetPane = new TweetAnnotationTable();
		
	     // Set up table pane
	     JScrollPane scrollPane = new JScrollPane(tweetPane.getTable());
	     tweetPane.getTable().setFillsViewportHeight(true);
	     
	     // Display table
	     tweetPane.add(scrollPane);

		/**
		 * Set up the editing (cleaning, rating, saving, etc.) pane.
		 */
		JPanel editPane = new JPanel();
		
		editPane.setLayout(new FlowLayout());
		editPane.setBorder(new TitledBorder("Tweets à noter"));
	
		JTextField loadField = new JTextField();
		loadField.setMaximumSize(new Dimension(100, 30));
		loadField.setColumns(15);
		
		editPane.add(loadField);
		
		JButton saveTweetButton = new JButton("Enregistrer en CSV");
		JButton loadTweetButton = new JButton("Charger pour annotation");
		JButton loadAppButton = new JButton("Charger pour apprentissage");

		editPane.add(saveTweetButton);
		editPane.add(loadTweetButton);
		editPane.add(loadAppButton);

		SearchListener searchListener = new SearchListener(searchField, tweetPane);
		searchTweetButton.addActionListener(searchListener);
		SaveListener saveListener = new SaveListener(loadField, tweetPane, resultArea);
		saveTweetButton.addActionListener(saveListener);
		LoadListener loadListener = new LoadListener(loadField, tweetPane, resultArea);
		loadTweetButton.addActionListener(loadListener);
		LoadAppListener loadAppListener = new LoadAppListener(loadField, appList, resultArea);
		loadAppButton.addActionListener(loadAppListener);
		
		
		// Panel to launch algorithms
		
		JPanel algoPane = new JPanel();
		
		GridLayout algoLayout = new GridLayout(4,4);
		
		algoLayout.setVgap(10);
		algoLayout.setHgap(30);
		
		algoPane.setLayout(algoLayout);
		algoPane.setBorder(new TitledBorder("Algorithmes"));
		
		
		
		JLabel blank1 = new JLabel("");
		JLabel blank2 = new JLabel("");
		JLabel blank3 = new JLabel("");
		JButton startKey = new JButton("Mots-clés");
		startKey.setMaximumSize(new Dimension(50, 30));
		
		JTextField knnField = new JTextField("10");
		knnField.setMaximumSize(new Dimension(100, 30));
		knnField.setColumns(3);
		JLabel knnLabel = new JLabel("Nombre de voisins pour KNN:");
		knnLabel.setLabelFor(knnField);
		JCheckBox distKNN = new JCheckBox("Distance de Levenshtein?");
		JButton startKNN = new JButton("KNN");
		
		JCheckBox freqBayes = new JCheckBox("Frequence");
		JCheckBox wordBayes = new JCheckBox("Pas de petits mots");
		JCheckBox biBayes = new JCheckBox("Avec bi-grammes");
		JButton startBayes = new JButton("Bayes");
		
		
		JButton splitAppButton = new JButton("Diviser apprentissage");
		JButton resetButton = new JButton("Reset");
		JCheckBox expAnalyse = new JCheckBox("Analyse experimentale");
		
		//First line
		algoPane.add(blank1);
		algoPane.add(blank2);
		algoPane.add(blank3);
		algoPane.add(startKey);
		
		//Second line, knn
		algoPane.add(knnLabel);
		algoPane.add(knnField);
		algoPane.add(distKNN);
		algoPane.add(startKNN);
		
		//Third line, Bayes
		algoPane.add(freqBayes);
		algoPane.add(wordBayes);
		algoPane.add(biBayes);
		algoPane.add(startBayes);
		
		//Fourth line, options
		algoPane.add(splitAppButton);
		algoPane.add(resetButton);
		algoPane.add(expAnalyse);
		
		
		
		KeyWordListener keyWordListener = new KeyWordListener(tweetPane, appList ,resultArea,  expAnalyse, resultArea);
		startKey.addActionListener(keyWordListener);
		KNNListener knnListener = new KNNListener(tweetPane, appList, knnField, distKNN,  expAnalyse, resultArea);
		startKNN.addActionListener(knnListener);
		BayesListener bayesListener = new BayesListener(tweetPane, appList, freqBayes, wordBayes, biBayes, expAnalyse, resultArea);
		startBayes.addActionListener(bayesListener);
		SplitAppListener splitApp = new SplitAppListener(tweetPane, appList);
		splitAppButton.addActionListener(splitApp);
		
		ResetListener resetListener = new ResetListener(tweetPane, appList);
		resetButton.addActionListener(resetListener);
		
		
		
		
		//Panel to print results
		
		
		JPanel resultPane = new JPanel();
		resultPane.setLayout(new FlowLayout());
		resultPane.setBorder(new TitledBorder("Résultats"));
		
		
		
		resultPane.add(resultArea);

		// Add various panes to the content pane.
		contentPane.add(searchPane);
		contentPane.add(tweetPane);
		contentPane.add(editPane);
		contentPane.add(algoPane);
		contentPane.add(resultPane);

		// Show the window.
		frame.pack();
		frame.setLocationByPlatform(true);
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
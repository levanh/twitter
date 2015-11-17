package bayes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utility.NameCounters;
import utility.Tweet;

public class BayesClassifier {

	
	private boolean type;
	private WordChooser word;
	private WordCombo combo;
	
	public BayesClassifier(boolean type, WordChooser word, WordCombo combo) {
		super();
		this.type = type;
		this.word = word;
		this.combo = combo;
	}
	
	public float getProbaTweet(Tweet t, List<Tweet> app) {
		int ntot = NameCounters.countList(app);
		
		List<Tweet> listePos = new ArrayList<Tweet>();
		List<Tweet> listeNeu = new ArrayList<Tweet>();
		List<Tweet> listeNeg = new ArrayList<Tweet>();
		
		NameCounters.splitTweetList(app, listePos, listeNeu, listeNeg);
		
		int nPos = NameCounters.countList(listePos);
		int nNeu = NameCounters.countList(listeNeu);
		int nNeg = NameCounters.countList(listeNeg);
		
		float probPos = (float)nPos / ntot;
		float probNeu = (float)nNeu / ntot;
		float probNeg = (float)nNeg / ntot;
		
		String[] parts = t.getTweetContent().split("\\s+");
		
		Set<String> wordSet = new HashSet<String>(Arrays.asList(parts));
		
		float probaProd = 1;
		
		for(String word: wordSet){
			probaProd = probaProd * ((+1)/())
		}
	}

}

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
	
	public BayesResult getProbaTweet(Tweet t, List<Tweet> app) {
		BayesResult res = new BayesResult();
		int nTot = NameCounters.countList(app);
		
		List<Tweet> listePos = new ArrayList<Tweet>();
		List<Tweet> listeNeu = new ArrayList<Tweet>();
		List<Tweet> listeNeg = new ArrayList<Tweet>();
		
		//On cree des listes pour chaque classe, puis calculer leur nombre de mots et la probabilite de chaque classe
		NameCounters.splitTweetList(app, listePos, listeNeu, listeNeg);
		
		int nPos = NameCounters.countList(listePos);
		int nNeu = NameCounters.countList(listeNeu);
		int nNeg = NameCounters.countList(listeNeg);
		
		float probPos = (float)nPos / nTot;
		float probNeu = (float)nNeu / nTot;
		float probNeg = (float)nNeg / nTot;
		
		//On cree un ensemble contenant les differents mots du Tweet dont on veut evaluer la probabilite
		
		String[] parts = t.getTweetContent().split("\\s+");
		
		Set<String> wordSet = new HashSet<String>(Arrays.asList(parts));
		
		//On multiplie la probabilite de la classe par la probabilite du mot 
		
		for(String word: wordSet){
			probPos = probPos * ((NameCounters.countNameList(listePos, word)+1)/(nPos+nTot));
			probNeu = probNeu * ((NameCounters.countNameList(listeNeu, word)+1)/(nPos+nTot));
			probNeg = probNeg * ((NameCounters.countNameList(listeNeg, word)+1)/(nPos+nTot));
		}
		
		
		res.setProbPos(probPos);
		res.setProbNeu(probNeu);
		res.setProbNeg(probNeg);
		return res;
	}
	
	public static void main(String[] args){
	}

}

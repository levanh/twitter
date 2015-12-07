package bayes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import utility.NameCounters;
import utility.Tweet;

public class BayesClassifier {

	
	private boolean type;
	private WordChooser wordChoose;
	private WordCombo combo;
	private Map<String, Integer> nameMapPos;
	private Map<String, Integer> nameMapNeu;
	private Map<String, Integer> nameMapNeg;
	
	public BayesClassifier(boolean type, WordChooser wordChoose, WordCombo combo) {
		super();
		this.type = type;
		this.wordChoose = wordChoose;
		this.combo = combo;
	}
	
	public void bayesClassList(List<Tweet> test, List<Tweet> app){
		
		int nTot = NameCounters.countList(app);
		
		List<Tweet> listePos = new ArrayList<Tweet>();
		List<Tweet> listeNeu = new ArrayList<Tweet>();
		List<Tweet> listeNeg = new ArrayList<Tweet>();
		
		NameCounters.splitTweetList(app, listePos, listeNeu, listeNeg);
		
		int nPos = NameCounters.countList(listePos);
		int nNeu = NameCounters.countList(listeNeu);
		int nNeg = NameCounters.countList(listeNeg);
		
		nameMapPos = new HashMap<String, Integer>();
		nameMapNeu = new HashMap<String, Integer>();
		nameMapNeg = new HashMap<String, Integer>();
		
		for (Tweet t: listePos){
			String[] parts = t.getTweetContent().split("\\s+");
			List<String> wordList = (Arrays.asList(parts));
			for(String word: wordList){
				if (nameMapPos.containsKey(word))
					nameMapPos.put(word, nameMapPos.get(word) + 1);
				else
					nameMapPos.put(word, 1);
			}
		}
		for (Tweet t: listeNeu){
			String[] parts = t.getTweetContent().split("\\s+");
			List<String> wordList = (Arrays.asList(parts));
			for(String word: wordList){
				if (nameMapNeu.containsKey(word))
					nameMapNeu.put(word, nameMapNeu.get(word) + 1);
				else
					nameMapNeu.put(word, 1);
			}
		}
		for (Tweet t: listeNeg){
			String[] parts = t.getTweetContent().split("\\s+");
			List<String> wordList = (Arrays.asList(parts));
			for(String word: wordList){
				if (nameMapNeg.containsKey(word))
					nameMapNeg.put(word, nameMapNeg.get(word) + 1);
				else
					nameMapNeg.put(word, 1);
			}
		}
		
		for (Tweet t: test){
			BayesResult br = getProbaTweet(t, nTot, nPos, nNeu, nNeg);
			t.setNote(br.noteResult());
			System.out.println(t.getTweetContent() + " : " + br.noteResult());
		}
	}
	
	
	
	public BayesResult getProbaTweet(Tweet t,int nTot, int nPos, int nNeu, int nNeg) {
		BayesResult res = new BayesResult();
		
		
		
		//On cree des listes pour chaque classe, puis calculer leur nombre de mots et la probabilite de chaque classe
		
		
		float probPos = (float)nPos / nTot;
		float probNeu = (float)nNeu / nTot;
		float probNeg = (float)nNeg / nTot;
		
		//On cree un ensemble contenant les differents mots du Tweet dont on veut evaluer la probabilite
		
		String[] parts = t.getTweetContent().split("\\s+");
		
		Set<String> wordSet = new HashSet<String>(Arrays.asList(parts));
		
		//On multiplie la probabilite de la classe par la probabilite du mot 
		
		for(String word: wordSet){
			int temp;
			if (nameMapPos.containsKey(word))
				temp = nameMapPos.get(word);
			else
				temp = 0;
			probPos = probPos * ((temp+1)/(nPos+nTot));
			if (nameMapNeu.containsKey(word))
				temp = nameMapNeu.get(word);
			else
				temp = 0;
			probNeu = probNeu * ((temp+1)/(nPos+nTot));
			if (nameMapNeg.containsKey(word))
				temp = nameMapNeg.get(word);
			else
				temp = 0;
			probNeg = probNeg * ((temp+1)/(nPos+nTot));
		}
		
		
		res.setProbPos(probPos);
		res.setProbNeu(probNeu);
		res.setProbNeg(probNeg);
		return res;
	}
	
	public static void main(String[] args){
	}

}

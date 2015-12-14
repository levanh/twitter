package bayes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import utility.Classifier;
import utility.NameCounters;
import utility.Tweet;

public class BayesClassifier implements Classifier{

	
	private boolean type;
	private boolean wordChoose;
	private boolean bigramme;
	private Map<String, Integer> nameMapPos;
	private Map<String, Integer> nameMapNeu;
	private Map<String, Integer> nameMapNeg;
	
	public BayesClassifier(boolean type, boolean wordChoose, boolean bigramme) {
		super();
		this.type = type;
		this.wordChoose = wordChoose;
		this.bigramme = bigramme;
	}
	
	public void classList(List<Tweet> test, List<Tweet> app){
		
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
			List<String> wordList;
			if (bigramme){
				wordList = new ArrayList<String>(Arrays.asList(parts));
				int end = wordList.size();
				for (int i = 0; i<end-1; i++){
					wordList.add(wordList.get(i) + " " + wordList.get(i+1));
				}
			}
			else 
				wordList = (Arrays.asList(parts));
			for(String word: wordList){
				if (!wordChoose || word.length()>3)
				if (nameMapPos.containsKey(word))
					nameMapPos.put(word, nameMapPos.get(word) + 1);
				else
					nameMapPos.put(word, 1);
			}
		}
		for (Tweet t: listeNeu){
			String[] parts = t.getTweetContent().split("\\s+");
			List<String> wordList;
			if (bigramme){
				wordList = new ArrayList<String>(Arrays.asList(parts));
				int end = wordList.size();
				for (int i = 0; i<end-1; i++){
					wordList.add(wordList.get(i) + " " + wordList.get(i+1));
				}
			}
			else 
				wordList = (Arrays.asList(parts));
			for(String word: wordList){
				if (!wordChoose || word.length()>3)
				if (nameMapNeu.containsKey(word))
					nameMapNeu.put(word, nameMapNeu.get(word) + 1);
				else
					nameMapNeu.put(word, 1);
			}
		}
		for (Tweet t: listeNeg){
			String[] parts = t.getTweetContent().split("\\s+");
			List<String> wordList;
			if (bigramme){
				wordList = new ArrayList<String>(Arrays.asList(parts));
				int end = wordList.size();
				for (int i = 0; i<end-1; i++){
					wordList.add(wordList.get(i) + " " + wordList.get(i+1));
				}
			}
			else 
				wordList = (Arrays.asList(parts));
			for(String word: wordList){
				if (!wordChoose || word.length()>3)
				if (nameMapNeg.containsKey(word))
					nameMapNeg.put(word, nameMapNeg.get(word) + 1);
				else
					nameMapNeg.put(word, 1);
			}
		}
		
		for (Tweet t: test){
			BayesResult br = getProbaTweet(t, nTot, nPos, nNeu, nNeg);
			t.setNote(br.noteResult());
			System.out.println( br.getProbPos() + " " + br.getProbNeu() + " " + br.getProbNeg() + " : " + br.noteResult() +" : " + t.getTweetContent());
		}
	}
	
	
	
	public BayesResult getProbaTweet(Tweet t,int nTot, int nPos, int nNeu, int nNeg) {
		BayesResult res = new BayesResult();
		
		
		
		//On cree des listes pour chaque classe, puis calculer leur nombre de mots et la probabilite de chaque classe
		
		
		double probPos = (double)nPos / nTot;
		double probNeu = (double)nNeu / nTot;
		double probNeg = (double)nNeg / nTot;
		
		//On cree un ensemble contenant les differents mots du Tweet dont on veut evaluer la probabilite
		
		String[] parts = t.getTweetContent().split("\\s+");
		
		Iterable<String> wordIter;
		
		if (type){
			if (bigramme){
				ArrayList<String>wordList = new ArrayList<String>(Arrays.asList(parts));
				int end = ((ArrayList<String>)wordList).size();
				for (int i = 0; i<end-1; i++){
					wordList.add(wordList.get(i) + " " + wordList.get(i+1));
				}
				wordIter = wordList;
			}
			else 
				wordIter = (Arrays.asList(parts));
		}
		else{
			if (bigramme){
				ArrayList<String>wordList = new ArrayList<String>(Arrays.asList(parts));
				int end = ((ArrayList<String>)wordList).size();
				for (int i = 0; i<end-1; i++){
					wordList.add(wordList.get(i) + " " + wordList.get(i+1));
				}
				wordIter = new HashSet<String>(wordList);
			}
			else 
				wordIter = new HashSet<String>(Arrays.asList(parts));
		}
		
		//On multiplie la probabilite de la classe par la probabilite du mot 
		
		for(String word: wordIter){
			if (!wordChoose || word.length()>3){
				int temp;
				if (nameMapPos.containsKey(word))
					temp = nameMapPos.get(word);
				else
					temp = 0;
				probPos = probPos * ((double)(temp+1)/(nPos+nTot));
				if (nameMapNeu.containsKey(word))
					temp = nameMapNeu.get(word);
				else
					temp = 0;
				probNeu = probNeu * ((double)(temp+1)/(nNeu+nTot));
				if (nameMapNeg.containsKey(word))
					temp = nameMapNeg.get(word);
				else
					temp = 0;
				probNeg = probNeg * ((double)(temp+1)/(nNeg+nTot));
			}
		}
		
		
		res.setProbPos(probPos);
		res.setProbNeu(probNeu);
		res.setProbNeg(probNeg);
		return res;
	}
	
	public static void main(String[] args){
		String[] parts = "Test de bigramme tres".split("\\s+");
		ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(parts));
			int end = wordList.size();
			for (int i = 0; i<end-1; i++){
				wordList.add(wordList.get(i) + " " + wordList.get(i+1));
			}
			for (String s: wordList)
			System.out.println(s);
	}

}

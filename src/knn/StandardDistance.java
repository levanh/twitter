package knn;

import java.util.HashSet;
import java.util.Set;

import utility.Tweet;

public class StandardDistance implements DistanceCalculator{

	private static StandardDistance myInstance = new StandardDistance();
	
	public static StandardDistance getInstance() {
		return myInstance;
	}
	
	public float tweetDistance(Tweet t1, Tweet t2) {
		int commonWords = 0;
		int totalWords = 0;
		String[] wordList1 = t1.getTweetContent().split("\\s+");
		String[] wordList2 = t2.getTweetContent().split("\\s+");
		Set<String> wordSet1 = new HashSet<String>();
		Set<String> wordSet2 = new HashSet<String>();
		for (int i = 0; i<wordList1.length;i++){
			if(wordSet1.add(wordList1[i]))
				totalWords++;
		}
		for (int j = 0; j<wordList2.length;j++){
			if(wordSet1.contains(wordList2[j])) {
				commonWords++;
			}
			else {
				if(wordSet2.add(wordList2[j]))
					totalWords++;
			}
		}
		return ((totalWords-commonWords)/(float)totalWords);

	}

}

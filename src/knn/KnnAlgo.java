package knn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utility.Tweet;

public class KnnAlgo {
	
	private List<Tweet> learningList;

	public KnnAlgo(List<Tweet> learningList) {
		this.learningList = learningList;
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
	
	public int knnResult(int k, Tweet t){
		List<Tweet> kClose = new ArrayList<Tweet>();
		for (int i = 0; i<k; i++) {
			kClose.add(this.learningList.get(i));
			
		}
	}
	
	public static void main(String[] args){
		Tweet t1 = new Tweet("Ceci est test");
		Tweet t2 = new Tweet("Ceci test est aussi");
		KnnAlgo knn = new KnnAlgo(null);
		System.out.println(knn.tweetDistance(t2, t1));
	}
}

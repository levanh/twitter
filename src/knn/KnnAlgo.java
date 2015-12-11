package knn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utility.Classifier;
import utility.Tweet;

public class KnnAlgo implements Classifier {
	
	private int k;

	public KnnAlgo(int k) {
		this.k = k;
	}
	
	public void classList(List<Tweet> test, List<Tweet> app) {
		for (Tweet t: test){
			this.noteTweet(t, app);
		}
		
	}
	
	public void noteTweet(Tweet t, List<Tweet> app){
		int note = this.majorityNote(knnResult(t, app));
		t.setNote(note);
		//System.out.println(t.getTweetContent() + " : " + note);
	}
	
	public static float tweetDistance(Tweet t1, Tweet t2) {
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
	
	public List<Tweet> knnResult(Tweet t, List<Tweet> app){
		List<Tweet> kClose = new ArrayList<Tweet>();
		Tweet temp;
		float distTemp;
		int indMax;
		float distMax;
		float[] dist = new float[k];
		for (int i = 0; i<k; i++) {
			temp = app.get(i);
			kClose.add(temp);
			dist[i] = KnnAlgo.tweetDistance(t, temp);
		}
		for (int i = k; i<app.size(); i++) {
			temp = app.get(i);
			distTemp = KnnAlgo.tweetDistance(t, temp);
			indMax = 0;
			distMax = dist[0];
			for (int j = 1; j<k; j++){
				if (dist[j] > distMax){
					distMax = dist[j];
					indMax = j;
				}
			}
			if (distTemp < distMax) {
				dist[indMax] = distTemp;
				kClose.set(indMax, temp);
			}
		}
		return kClose;
	}
	
	public int majorityNote(List<Tweet> lis){
		int countNeg = 0;
		int countNeu = 0;
		int countPos = 0;
		for(Tweet t: lis){
			switch (t.getNote().intValue()){
			case 0:
				countNeg++;
				break;
			case 2:
				countNeu++;
				break;
			case 4:
				countPos++;
				break;
			}
		}
		if (countPos > countNeu){
			if (countPos > countNeg)
				return 4;
			else
				return 0;
		}
		else{
			if (countNeu > countNeg)
				return 0;
			else
				return 2;
		}
	}
	
	public static void main(String[] args){
		Tweet t1 = new Tweet("Ceci est test");
		Tweet t2 = new Tweet("Ceci test est aussi");
		System.out.println(KnnAlgo.tweetDistance(t2, t1));
	}


}

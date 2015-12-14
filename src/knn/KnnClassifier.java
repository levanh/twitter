package knn;

import java.util.ArrayList;
import java.util.List;

import utility.Classifier;
import utility.Tweet;

public class KnnClassifier implements Classifier {
	
	private int k;
	private DistanceCalculator distCalc;

	public KnnClassifier(int k, DistanceCalculator distCalc) {
		this.k = k;
		this.distCalc = distCalc;
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
			dist[i] = distCalc.tweetDistance(t, temp);
		}
		for (int i = k; i<app.size(); i++) {
			temp = app.get(i);
			distTemp = distCalc.tweetDistance(t, temp);
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
		Tweet t2 = new Tweet("Ceci change test mieux");
		System.out.println(LevenshteinDistance.getInstance().tweetDistance(t2, t1));
	}


}

package knn;

import utility.Tweet;

public interface DistanceCalculator {

	public float tweetDistance(Tweet t1, Tweet t2);
	
	
}

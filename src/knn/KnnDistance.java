package knn;

import utility.Tweet;

public class KnnDistance {

	private Tweet t;
	private int distance;
	public KnnDistance(Tweet t, int distance) {
		super();
		this.t = t;
		this.distance = distance;
	}
	public Tweet getT() {
		return t;
	}
	public void setT(Tweet t) {
		this.t = t;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	
}

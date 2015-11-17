package utility;

import java.util.List;

public class NameCounters {
	
	public static int countTweet(Tweet t){
		String[] parts = t.getTweetContent().split("\\s+");
		return parts.length;
	}	
	
	public static int countNameTweet(Tweet t, String name){
		int res = 0;
		String[] parts = t.getTweetContent().split("\\s+");
		for(int i = 0; i<parts.length; i++) {
			if (parts[i].equals(name)){
				res++;
			}
		}
		return res;
		
	}
	
	public static int countList(List<Tweet> lis){
		int res = 0;
		for (Tweet t: lis){
			res = res + countTweet(t);
		}
		return res;
	}
	
	public static int countNameList(List<Tweet> lis, String name){
		int res = 0;
		for (Tweet t: lis){
			res = res + countNameTweet(t, name);
		}
		return res;
	}
	
	public static void splitTweetList(List<Tweet> app, List<Tweet> listePos, List<Tweet> listeNeu, List<Tweet> listeNeg){
		
	}
	
	
	public static void main(String[] args) {
		String test = "super test super bien";
		Tweet t = new Tweet(test);
		System.out.println(NameCounters.countNameTweet(t, "test"));

	}

}

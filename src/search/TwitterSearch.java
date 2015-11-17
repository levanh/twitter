package search;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import utility.Tweet;

public class TwitterSearch {

	private String topic;
	private int resNumber;
	
	public TwitterSearch(String topic, int resNumber) {
		this.topic = topic;
		this.resNumber = resNumber;
	}
	
	public List<Tweet> search(){
		List<Tweet> searchList = new ArrayList<Tweet>();
		Twitter twitter = new TwitterFactory().getInstance();
	    try {
	        Query query = new Query(this.topic);
	        QueryResult result;
	        int i = 0;
	        do {
	        	System.out.println("Test1");
		        result = twitter.search(query);
		        List<Status> tweets = result.getTweets();
		        for (Status e: tweets) {
		        	Tweet newTweet = new Tweet();
		        	newTweet.setId(e.getId());
		        	newTweet.setUser(e.getUser().getScreenName());
		        	newTweet.setTweetContent(e.getText());
		        	newTweet.setCreationDate(e.getCreatedAt());
		        	newTweet.setTopic(this.topic);
		        	newTweet.setNote(-1);
		        	searchList.add(newTweet);
		        	query = result.nextQuery();
		        }
		        i++;
	        } while ((i<this.resNumber) && (query = result.nextQuery()) != null);
	    } catch (TwitterException te) {
	        te.printStackTrace();
	        System.out.println("Failed to search tweets: " + te.getMessage());
	        System.exit(-1);
	    }
		return searchList;
	}
}

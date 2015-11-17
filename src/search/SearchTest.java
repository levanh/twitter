package search;

import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class SearchTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 if (args.length < 1) {
	            System.exit(-1);
	        }
	        Twitter twitter = new TwitterFactory().getInstance();
	        try {
	            Query query = new Query(args[0]);
	            QueryResult result;
	            result = twitter.search(query);
	            List<Status> tweets = result.getTweets();
	            System.out.println("@" + tweets.get(1).getUser().getScreenName() + " ----- " + tweets.get(1).getText());
	            System.exit(0);
	        } catch (TwitterException te) {
	            te.printStackTrace();
	            System.out.println("Failed to search tweets: " + te.getMessage());
	            System.exit(-1);
	        }

	}

}

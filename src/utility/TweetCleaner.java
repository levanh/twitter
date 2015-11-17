package utility;

public class TweetCleaner {
	
	private static TweetCleaner singleCleaner = new TweetCleaner();

	private TweetCleaner() {
	}

	public static TweetCleaner getInstance(){
		return singleCleaner;
	}
	
	public void clean(Tweet tweet){
		String cleaned = tweet.getTweetContent();
		cleaned = cleaned.replaceAll("\\w*\\d\\w*", " ");
		cleaned = cleaned.replaceAll(",", " ");
		cleaned = cleaned.replaceAll("\n", " ");
		cleaned = cleaned.replaceAll("\t", " ");
		cleaned = cleaned.replaceAll("\r", " ");
		tweet.setTweetContent(cleaned);
	}
	
	public static void main(String[] args){
		Tweet t = new Tweet("Testtrop bien \n der 456");
		TweetCleaner.getInstance().clean(t);
		System.out.println(t.getTweetContent());
	}

}

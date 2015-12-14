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
		cleaned = cleaned.replaceAll("[^\\s]*http[^\\s]*", " ");
		cleaned = cleaned.replaceAll("@\\w*", " ");
		cleaned = cleaned.replaceAll("é|è|ê|ë", "e");
		cleaned = cleaned.replaceAll("à|â", "a");
		cleaned = cleaned.replaceAll("ô", "o");
		cleaned = cleaned.replaceAll("û", "u");
		cleaned = cleaned.replaceAll("ç", "c");
		cleaned = cleaned.replaceAll("ï|î", "i");
		cleaned = cleaned.replaceAll("[^\\s\\w]+"," ");
		cleaned = cleaned.replaceAll("RT\\s"," ");
		cleaned = cleaned.replaceAll("\\s+", " ");
		cleaned = cleaned.toLowerCase();
		tweet.setTweetContent(cleaned.trim());
	}
	
	public static void main(String[] args){
		Tweet t = new Tweet("daz ,; faz: è _' a");
		TweetCleaner.getInstance().clean(t);
		System.out.println(t.getTweetContent());
	}

}

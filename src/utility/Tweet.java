package utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Tweet {

	
	private static SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
	
	private long id;
	private String user;
	private String tweetContent;
	private String topic;
	private Date creationDate;
	private Integer note;
	
	public Tweet(long id, String user, String tweetContent, String topic, Date creationDate, Integer note) {
		super();
		this.id = id;
		this.user = user;
		this.tweetContent = tweetContent;
		this.topic = topic;
		this.creationDate = creationDate;
		this.note = note;
	}
	
	public Tweet() {
		
	}

	public Tweet(String tweetContent) {
		this.tweetContent = tweetContent;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getTweetContent() {
		return tweetContent;
	}
	public void setTweetContent(String tweetContent) {
		this.tweetContent = tweetContent;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getNote() {
		return note;
	}
	public void setNote(Integer note) {
		this.note = note;
	}
	
	public ArrayList<String> generateRecord() {
		ArrayList<String> record = new ArrayList<String>();
		record.add(Long.toString(this.id));
		record.add(this.user);
		record.add(this.tweetContent);
		record.add(this.topic);
		record.add(dateformat.format(this.creationDate));
		record.add(Integer.toString(this.note));
		return record;
	}
	
}

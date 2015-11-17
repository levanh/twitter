package utility;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVReader {
	private CSVParser parser;
	private File source;
	private String filename;
	
	private static SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
	
	public CSVReader(String filename){
		this.filename = filename;
	}
	
	public List<Tweet> readCSV(){
		System.out.println("Trying to load CSV from "+ this.filename);
		List<Tweet> newList = new ArrayList<Tweet>();
		try {
			source = new File(this.filename);
			this.parser = CSVParser.parse(source, Charset.defaultCharset() , CSVFormat.DEFAULT);
			for (CSVRecord csvRecord : parser) {
			    Tweet newTweet = new Tweet(Long.parseLong(csvRecord.get(0)), csvRecord.get(1), csvRecord.get(2),
			    		csvRecord.get(3), dateformat.parse(csvRecord.get(4)), Integer.parseInt(csvRecord.get(5)));
			    newList.add(newTweet);
			}
			this.parser.close();
			System.out.println("Successfully loaded CSV from "+ this.filename);
		}
		catch(IOException e) {
			System.out.println("IOFailed to load CSV from" + this.filename);
		}
		catch(Exception e){
			System.out.println("Failed to load CSV from" + this.filename);
		}
		
		return newList;
	}
}

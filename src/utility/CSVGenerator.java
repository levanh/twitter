package utility;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CSVGenerator {

	private String filename;
	private FileWriter writer;
	private CSVPrinter printer;
	
	public CSVGenerator(String filename){
		this.filename = filename;
	}

	public void writeCSV(List<Tweet> tweetList){
		System.out.println("Trying to save CSV to "+ this.filename);
		try {
			this.writer = new FileWriter(this.filename);
			this.printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
			for (Tweet t: tweetList){
				printer.printRecord(t.generateRecord());
			}
			printer.close();
			writer.close();
			System.out.println("Successfully saved CSV to "+ this.filename);
		}
		catch (IOException e){
			System.out.println("Erreur d'ecriture CSV");
		}
	}
	
}

package keyWord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utility.Classifier;
import utility.Tweet;

public class KeyWordClassifier implements Classifier{

	private Set<String> negWord;
	private Set<String> posWord;
	
	public KeyWordClassifier(){
		try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader("positive.txt");

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            
            bufferedReader.readLine();
            String posline = bufferedReader.readLine();
            
            String[] posArray = posline.split(",");
            posWord = new HashSet<String>(Arrays.asList(posArray));

            // Always close files.
            bufferedReader.close();         
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file positive");                  
        }
		
		try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader("negative.txt");

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            
            bufferedReader.readLine();
            String negline = bufferedReader.readLine();
            
            String[] negArray = negline.split(",");
            negWord = new HashSet<String>(Arrays.asList(negArray));

            // Always close files.
            bufferedReader.close();         
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file positive");                  
        }
	}

	
	public void classList(List<Tweet> test, List<Tweet> app) {
		for (Tweet t: test){
			int pos = 0;
			int neg = 0;
			List<String> words = Arrays.asList(t.getTweetContent().split(" "));
			for (String s:words){
				if (posWord.contains(s))
					pos++;
				if (negWord.contains(s))
					neg++;
			}
			if (pos > neg)
				t.setNote(4);
			else if (neg > pos)
				t.setNote(0);
			else
				t.setNote(2);
			
		}
		
	}
	
	public static void main(String[] args){
		KeyWordClassifier kc = new KeyWordClassifier();
		System.out.println(kc.negWord);
		System.out.println(kc.posWord);
	}
	
	
}

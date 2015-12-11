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

	@Override
	public void classList(List<Tweet> test, List<Tweet> app) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args){
		KeyWordClassifier kc = new KeyWordClassifier();
		System.out.println(kc.negWord);
		System.out.println(kc.posWord);
	}
	
	
}

package expAnalyse;

import java.util.ArrayList;
import java.util.List;

import bayes.BayesClassifier;
import knn.KnnClassifier;
import knn.StandardDistance;
import utility.CSVReader;
import utility.Classifier;
import utility.Tweet;

public class CrosstestCreator {
	
	private List<Tweet> crossBase;
	private Classifier cla;
	
	public CrosstestCreator(List<Tweet> crossBase, Classifier cla) {
		super();
		this.crossBase = crossBase;
		this.cla = cla;
	}


	public float crossTest(){
		List<Tweet> noteList = new ArrayList<Tweet>();
		List<Tweet> appList = new ArrayList<Tweet>();
		int errors = 0;
		int total = crossBase.size();
		Tweet t = new Tweet();
		for (Tweet tb: crossBase)
			tb.setTweetContent(tb.getTweetContent().toLowerCase());
		List<List<Tweet>> splitList = CrosstestCreator.createXTest(crossBase);
		
		
		for (int i = 0; i<10; i++){
			CrosstestCreator.getNoteAndAppList(i, noteList, appList, splitList);
			
			

			cla.classList(noteList, appList);
				
			for (int j = 0;  j < noteList.size(); j++){
				t = noteList.get(j);
				if (! splitList.get(i).get(j).getNote().equals(t.getNote())){
					errors++;
					System.out.println("Result: list "+i+" tweet "+j+" not equal "
							+ t.getNote() +"   "+ splitList.get(i).get(j).getNote());
				}
			}
		}
		System.out.println(errors);
		System.out.println(total);
		return (float)errors/total;
		
	}
	
	
	public static List<List<Tweet>> createXTest(List<Tweet> appBase){
	
		List<List<Tweet>> splitList = new ArrayList<List<Tweet>>();
		for (int i = 0; i<10; i++)
			splitList.add(new ArrayList<Tweet>());
		int cptPos = 0;
		int cptNeu = 0;
		int cptNeg = 0;
		
		for (Tweet t: appBase){
			switch (t.getNote()){
			case 4:
				splitList.get(cptPos).add(t);
				cptPos = (cptPos + 1) % 10;
				break;
			case 2:
				splitList.get(cptNeu).add(t);
				cptNeu = (cptNeu + 1) % 10;
				break;
			case 0:
				splitList.get(cptNeg).add(t);
				cptNeg = (cptNeg + 1) % 10;
				break;
			}
		}
		
		
		return splitList;
		
	}
	
	public static void getNoteAndAppList(int n, List<Tweet> note, List<Tweet> app, List<List<Tweet>> appBase){
		if (n < appBase.size()){
			note.clear();
			app.clear();
			for (int i = 0; i<appBase.size(); i++){
				if (i == n){
					for (Tweet t: appBase.get(i)){
						note.add(t.copyToNote());
					}
				}
				else {
					app.addAll(appBase.get(i));
				}
			}
		}
	}
	
	public static void main(String[] args){
		CSVReader r = new CSVReader("tweetsStable.csv");
		List<Tweet> test = r.readCSV();
		CrosstestCreator tester = new CrosstestCreator(test, new KnnClassifier(10, StandardDistance.getInstance()));
		System.out.println(tester.crossTest());
	}

}

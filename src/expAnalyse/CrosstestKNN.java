package expAnalyse;

import java.util.ArrayList;
import java.util.List;

import knn.KnnAlgo;

import utility.CSVReader;
import utility.Tweet;

public class CrosstestKNN {
	
	private List<Tweet> appBase;
	private int k;
	
		
	public CrosstestKNN(List<Tweet> appBase, int k) {
		super();
		this.appBase = appBase;
		this.k = k;
	}

	public float crossTest(){
		List<Tweet> noteList = new ArrayList<Tweet>();
		List<Tweet> appList = new ArrayList<Tweet>();
		int errors = 0;
		int total = appBase.size();
		Tweet t = new Tweet();
		List<List<Tweet>> splitList = CrosstestCreator.createXTest(appBase);
		
		
		for (int i = 0; i<10; i++){
			CrosstestCreator.getNoteAndAppList(i, noteList, appList, splitList);
			KnnAlgo knn = new KnnAlgo(appList);
			System.out.println(noteList.size());
			for (int j = 0;  j < noteList.size(); j++){
				System.out.println(j);
				t = noteList.get(j);
				knn.noteTweet(k, t);
				if (! splitList.get(i).get(j).getNote().equals(t.getNote())){
					errors++;
					System.out.println("Result: list "+i+" tweet "+j+" not equal "
							+ t.getNote() +"   "+ splitList.get(i).get(j).getNote());
				}
			}
		}
		return (float)errors/total;
		
	}
	
	public static void main(String[] args){
		CSVReader r = new CSVReader("tweets.csv");
		List<Tweet> test = r.readCSV();
		CrosstestKNN tester = new CrosstestKNN(test, 5);
		
		System.out.println(tester.crossTest());
	}
}

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
		KnnAlgo knn = new KnnAlgo(k);
		List<Tweet> noteList = new ArrayList<Tweet>();
		List<Tweet> appList = new ArrayList<Tweet>();
		int errors = 0;
		int total = appBase.size();
		Tweet t = new Tweet();
		//for (Tweet tb: appBase)
		//	tb.setTweetContent(tb.getTweetContent().toLowerCase());
		List<List<Tweet>> splitList = CrosstestCreator.createXTest(appBase);
		
		
		for (int i = 0; i<10; i++){
			CrosstestCreator.getNoteAndAppList(i, noteList, appList, splitList);
			knn.classList(noteList, appList);
			for (int j = 0;  j < noteList.size(); j++){
				t = noteList.get(j);
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
		CSVReader r = new CSVReader("tweetsStable.csv");
		List<Tweet> test = r.readCSV();
		CrosstestKNN tester = new CrosstestKNN(test, 15);
		
		System.out.println(tester.crossTest());
	}
}

package expAnalyse;

import java.util.ArrayList;
import java.util.List;

import bayes.BayesClassifier;
import bayes.WordCombo;
import utility.CSVReader;
import utility.Tweet;

public class CrosstestBayes {

	private List<Tweet> appBase;
	private boolean type;
	private boolean wordChoose;
	private WordCombo combo;
	
	
	
	public CrosstestBayes(List<Tweet> appBase, boolean type, boolean wordChoose, WordCombo combo) {
		super();
		this.appBase = appBase;
		this.type = type;
		this.wordChoose = wordChoose;
		this.combo = combo;
	}

	public float crossTest(){
		BayesClassifier bc = new BayesClassifier(type, wordChoose, combo);
		List<Tweet> noteList = new ArrayList<Tweet>();
		List<Tweet> appList = new ArrayList<Tweet>();
		int errors = 0;
		int total = appBase.size();
		Tweet t = new Tweet();
		for (Tweet tb: appBase)
			tb.setTweetContent(tb.getTweetContent().toLowerCase());
		List<List<Tweet>> splitList = CrosstestCreator.createXTest(appBase);
		
		
		for (int i = 0; i<10; i++){
			CrosstestCreator.getNoteAndAppList(i, noteList, appList, splitList);
			
			

			bc.classList(noteList, appList);
				
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
	
	public static void main(String[] args){
		CSVReader r = new CSVReader("tweetsStable.csv");
		List<Tweet> test = r.readCSV();
		/*int pos = 0;
		int neu = 0;
		int neg = 0;
		for (Tweet t: test)
			switch(t.getNote()){
			case 4:
				pos++;
				break;
			case 2:
				neu++;
				break;
			case 0:
				neg++;
				break;
			}
		System.out.println(pos);
		System.out.println(neu);
		System.out.println(neg);*/
		CrosstestBayes tester = new CrosstestBayes(test, true, false, null);
		
		System.out.println(tester.crossTest());
	}
	
	
}

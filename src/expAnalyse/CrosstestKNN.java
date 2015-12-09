package expAnalyse;

import java.util.ArrayList;
import java.util.List;

import utility.Tweet;

public class CrosstestKNN {
	
	private List<Tweet> appBase;
	
	public float crossTest(){
		List<Tweet> oldList;
		List<Tweet> newApplist = new ArrayList<Tweet>();
		int errors = 0;
		int total = appBase.size();
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
		
		for (int i = 0; i<10; i++){
			oldList = splitList.get(i).;
		}
		
	}
}

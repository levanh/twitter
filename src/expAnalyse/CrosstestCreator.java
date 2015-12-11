package expAnalyse;

import java.util.ArrayList;
import java.util.List;

import utility.Tweet;

public class CrosstestCreator {
	
	
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

}

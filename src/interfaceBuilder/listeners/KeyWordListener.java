package interfaceBuilder.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JTextArea;

import expAnalyse.CrosstestCreator;
import interfaceBuilder.table.TweetAnnotationTable;
import keyWord.KeyWordClassifier;
import utility.Tweet;

public class KeyWordListener implements ActionListener {
	
	private TweetAnnotationTable table;
	private List<Tweet> app;
	private JCheckBox expAnalyse;
	private JTextArea text;

	public KeyWordListener(TweetAnnotationTable table, List<Tweet> app, JTextArea text, JCheckBox expAnalyse, JTextArea resultArea) {
		this.table = table;
		this.app = app;
		this.expAnalyse = expAnalyse;
		this.text = text;
	}

	public void actionPerformed(ActionEvent arg0) {
		KeyWordClassifier kc = new KeyWordClassifier();
		if (expAnalyse.isSelected()){
			CrosstestCreator cross = new CrosstestCreator(app, kc);
			float error = cross.crossTest()*100;
			String result = "La validation croisée avec la classification par mots-clés a été réalisée.\n";
			result = result + "Le taux d'erreur obtenu est de " + error + "%.";
			this.text.setText(result);
		}
		for (Tweet t: table.getModel().getData())
			t.setTweetContent(t.getTweetContent().toLowerCase());
		kc.classList(table.getModel().getData(), app);
		table.getModel().fireTableDataChanged();
		table.getModel().fireTableDataChanged();
		int tot = 0;
		int pos = 0;
		int neu = 0;
		int neg = 0;
		for(Tweet t: table.getModel().getData()){
		switch (t.getNote()){
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
		tot++;
		}
		String result = "Les tweets ont été annotés automatiquement avec la classification par mots-clés.\n";
		result = result + "On obtient la tendance suivante:";
		result = result + "Tweets positifs: " + ((float)pos/tot) + "%.";
		result = result + "Tweets neutres: " + ((float)neu/tot) + "%.";
		result = result + "Tweets négatifs: " + ((float)neg/tot) + "%.";
		this.text.setText(result);
	}
	
	
	

}

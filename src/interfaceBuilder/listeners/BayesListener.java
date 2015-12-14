package interfaceBuilder.listeners;

import interfaceBuilder.table.TweetAnnotationTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JTextArea;

import bayes.BayesClassifier;
import expAnalyse.CrosstestCreator;
import utility.Classifier;
import utility.Tweet;

public class BayesListener implements ActionListener {
	
	private TweetAnnotationTable table;
	private List<Tweet> app;
	private JCheckBox freq;
	private JCheckBox word;
	private JCheckBox bi;
	private JCheckBox expAnalyse;
	private JTextArea text;
	
	

	public BayesListener(TweetAnnotationTable table, List<Tweet> app, JCheckBox freq,JCheckBox word, JCheckBox bi, JCheckBox expAnalyse, JTextArea text) {
		super();
		this.table = table;
		this.app = app;
		this.freq = freq;
		this.word = word;
		this.bi = bi;
		this.expAnalyse = expAnalyse;
		this.text = text;
	}



	public void actionPerformed(ActionEvent e) {
		this.text.setText("");
		BayesClassifier bc = new BayesClassifier(freq.isSelected(), word.isSelected(), bi.isSelected());
		if (expAnalyse.isSelected()){
			CrosstestCreator cross = new CrosstestCreator(app, bc);
			float error = cross.crossTest()*100;
			String result = "La validation croisée avec la classification Bayesienne et les options choisies a été réalisée.\n";
			result = result + "Le taux d'erreur obtenu est de " + error + "%.";
			this.text.setText(result);
		}
		else {
		
			for (Tweet t: table.getModel().getData())
				t.setTweetContent(t.getTweetContent().toLowerCase());
	
			bc.classList(table.getModel().getData(), app);
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
			String result = "Les tweets ont été annotés automatiquement avec la classification Bayesienne.\n";
			result = result + "On obtient la tendance suivante:\n";
			result = result + "Tweets positifs: " + ((float)pos/tot) + "%.\n";
			result = result + "Tweets neutres: " + ((float)neu/tot) + "%.\n";
			result = result + "Tweets négatifs: " + ((float)neg/tot) + "%.\n";
			this.text.setText(result);
		}
	}

}

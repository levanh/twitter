package interfaceBuilder.listeners;

import interfaceBuilder.table.TweetAnnotationTable;
import knn.KnnClassifier;
import knn.LevenshteinDistance;
import knn.StandardDistance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import expAnalyse.CrosstestCreator;
import utility.Tweet;

public class KNNListener implements ActionListener {
	
	private TweetAnnotationTable table;
	private List<Tweet> app;
	private JTextField knear;
	private JTextArea text;
	private JCheckBox distKNN;
	private JCheckBox expAnalyse;

	

	public KNNListener(TweetAnnotationTable table, List<Tweet> app, JTextField knear, JCheckBox distKNN, JCheckBox expAnalyse, JTextArea text) {
		this.table = table;
		this.app = app;
		this.knear = knear;
		this.distKNN = distKNN;
		this.expAnalyse = expAnalyse;
		this.text = text;
	}





	public void actionPerformed(ActionEvent e) {
		this.text.setText("");
		int k = Integer.parseInt(knear.getText());
		KnnClassifier knn;
		if (distKNN.isSelected())
			knn = new KnnClassifier(k, LevenshteinDistance.getInstance());
		else
			knn = new KnnClassifier(k, StandardDistance.getInstance());
		if (expAnalyse.isSelected()){
			CrosstestCreator cross = new CrosstestCreator(app, knn);
			float error = cross.crossTest()*100;
			String result = "La validation croisée avec la classification par KNN et les options choisies a été réalisée.\n";
			result = result + "Le taux d'erreur obtenu est de " + error + "%.";
			this.text.setText(result);
		}
		else {
			for (Tweet t: table.getModel().getData())
				t.setTweetContent(t.getTweetContent().toLowerCase());
	
			knn.classList(table.getModel().getData(), app);
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
			String result = "Les tweets ont été annotés automatiquement avec la classification par KNN.\n";
			result = result + "On obtient la tendance suivante:";
			result = result + "Tweets positifs: " + ((float)pos/tot) + "%.";
			result = result + "Tweets neutres: " + ((float)neu/tot) + "%.";
			result = result + "Tweets négatifs: " + ((float)neg/tot) + "%.";
			this.text.setText(result);
		}
	}

}

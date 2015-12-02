package interfaceBuilder.itemListeners;

import interfaceBuilder.MainWindow;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FreqItemListener implements ItemListener{
	
	
	public FreqItemListener(){
	}
	

	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.DESELECTED) {
            MainWindow.freq = false;
            System.out.println(false);
        }
		else{
			MainWindow.freq = true;
			System.out.println(true);
		}
		
	}

}

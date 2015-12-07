package interfaceBuilder.itemListeners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FreqItemListener implements ItemListener{
	
	
	public FreqItemListener(){
	}
	

	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.DESELECTED) {
            System.out.println(false);
        }
		else{
			System.out.println(true);
		}
		
	}

}

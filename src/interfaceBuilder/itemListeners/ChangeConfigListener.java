package interfaceBuilder.itemListeners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import utility.ChangeConfig;

public class ChangeConfigListener implements ItemListener{

	
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.DESELECTED) {
            ChangeConfig.standardConfig();
        }
		else{
			ChangeConfig.proxyConfig();
		}
		
	}
	
}

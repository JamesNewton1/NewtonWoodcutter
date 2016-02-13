package org.newton.wc.gui;

import org.newton.wc.data.Trees;

public interface WoodcutterGUIListener {

	public void onGUISubmit(Trees[] treesToCut, int cutRadius, String[] necessaryItems, boolean useBank);
	
	public void onGUIStop();
	
}

package org.newton.wc.gui;

import java.awt.event.WindowListener;

import org.newton.wc.data.Tree;

public interface WoodcutterGUIListener extends WindowListener {

	public void onGUISubmit(Tree[] treesToCut, int cutRadius, String[] necessaryItems, boolean useBank, boolean prioritiseBestTree);
	
	public void onGUIStop();
	
}

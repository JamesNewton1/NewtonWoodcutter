package org.newton.wc;

import org.newton.api.map.TileArea;
import org.newton.wc.data.Trees;

public class WoodcutterSettings {

	private Trees[] treesToCut;
	private TileArea cuttingArea, bankArea;	
	private String[] necessaryItems;
	
	public WoodcutterSettings(Trees[] treesToCut, TileArea cuttingArea, TileArea bankArea, String[] necessaryItems) {
		this.treesToCut = treesToCut;
		this.cuttingArea = cuttingArea;
		this.bankArea = bankArea;
		this.necessaryItems = necessaryItems;
	}
	
	public WoodcutterSettings(Trees[] treesToCut, TileArea cuttingArea, String[] necessaryItems) {
		this(treesToCut, cuttingArea, null, necessaryItems);
	}
		
	public Trees[] getTreesToCut() {
		return treesToCut;
	}
	
	public TileArea getCuttingArea() {
		return cuttingArea;
	}
	
	public TileArea getBankArea() {
		return bankArea;
	}
	
	public String[] getNecessaryItems() {
		return necessaryItems;
	}
	
}

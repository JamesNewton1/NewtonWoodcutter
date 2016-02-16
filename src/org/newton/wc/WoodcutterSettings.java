package org.newton.wc;

import org.newton.api.map.TileArea;
import org.newton.wc.data.Tree;

public class WoodcutterSettings {

	private Tree[] treesToCut;
	private TileArea cuttingArea, bankArea;	
	private String[] necessaryItems;
	private boolean prioritiseBestTree;
	
	public WoodcutterSettings(Tree[] treesToCut, TileArea cuttingArea, TileArea bankArea, String[] necessaryItems, boolean prioritiseBestTree) {
		this.treesToCut = treesToCut;
		this.cuttingArea = cuttingArea;
		this.bankArea = bankArea;
		this.necessaryItems = necessaryItems;
		this.prioritiseBestTree = prioritiseBestTree;
	}
	
	public WoodcutterSettings(Tree[] treesToCut, TileArea cuttingArea, String[] necessaryItems, boolean onlyCutBestAvailableTree) {
		this(treesToCut, cuttingArea, null, necessaryItems, onlyCutBestAvailableTree);
	}
		
	public Tree[] getTreesToCut() {
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

	public boolean prioritiseBestTree() {
		return prioritiseBestTree;
	}
	
	public Tree getBestTreeToCut() {
		int highestLevel = 0;
		Tree bestTree = null;
		
		for(Tree tree : getTreesToCut()) {
			if(tree.getLevelRequired() > highestLevel) {
				bestTree = tree;
			}
		}
		
		return bestTree;
	}
	
}

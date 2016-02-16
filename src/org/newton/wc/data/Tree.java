package org.newton.wc.data;

import java.util.ArrayList;

public enum Tree {
	NORMAL("Tree", "Logs", 1, new int[]{ }),
	WILLOW("Willow", "Willow logs", 30, new int[]{ }),
	OAK("Oak", "Oak logs", 15, new int[]{ }),
	MAPLE("Maple tree", "Maple logs", 45, new int[] { }),
	YEW("Yew", "Yew logs", 60, new int[] { }),
	MAGIC("Magic tree", "Magic logs", 75, new int[] { });
	
	private String treeName;
	private String logName;
	private int levelRequired;
	private int[] ids;

	Tree(String treeName, String logName, int levelRequired, int[] ids) {
		this.treeName = treeName;
		this.logName = logName;
		this.ids = ids;
	}
	
	public int[] getIds() {
		return ids;
	}
	
	public String getLogName() {
		return logName;
	}
	
	public String getTreeName() {
		return treeName;
	}
	
	public static String[] getLogsNames() {
		String[] logsNames = new String[Tree.values().length];
		
		for(int i = 0; i < Tree.values().length; i++) {
			logsNames[i] = Tree.values()[i].getLogName();
		}
		
		return logsNames;
	}
	
	public int getLevelRequired() {
		return levelRequired;
	}
	
	public static Tree[] filterTreesByWoodcuttingLevel(int woodcuttingLevel, Tree[] trees) {
		ArrayList<Tree> cuttableTrees = new ArrayList<Tree>();
		
		for(Tree tree : trees) {
			if(woodcuttingLevel >= tree.getLevelRequired()) cuttableTrees.add(tree);
		}
		
		return cuttableTrees.toArray(new Tree[cuttableTrees.size()]);
	}
	
	public static Tree getTreeByName(String name) {
		for(Tree t : values()) {
			if(t.getTreeName().equals(name)) {
				return t;
			}
		}
		
		return null;
	}
	
}

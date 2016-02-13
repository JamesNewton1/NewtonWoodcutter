package org.newton.wc.data;

public enum Trees {
	NORMAL("Tree", "Logs", new int[]{ }),
	WILLOW("Willow tree", "Willow logs", new int[]{ }),
	OAK("Oak tree", "Oak logs", new int[]{ }),
	MAPLE("Maple tree", "Maple logs", new int[] { }),
	YEW("Yew tree", "Yew logs", new int[] { }),
	MAGIC("Magic tree", "Magic logs", new int[] { });
	
	private String treeName;
	private String logName;
	private int[] ids;

	Trees(String treeName, String logName, int[] ids) {
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
		String[] logsNames = new String[Trees.values().length];
		
		for(int i = 0; i < Trees.values().length; i++) {
			logsNames[i] = Trees.values()[i].getLogName();
		}
		
		return logsNames;
	}
	
}

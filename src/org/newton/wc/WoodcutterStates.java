package org.newton.wc;

import org.newton.api.map.TileArea;
import org.newton.wc.data.Tree;

//woodcutter:
//cut tree -> is inventory full? -> perform inventory disposal (bank or drop) -> ...
public abstract class WoodcutterStates {

	protected abstract boolean isTreeAvailable(Tree[] treesToFind, TileArea treeArea);
		
	protected abstract boolean isInventoryFull();
	
	protected abstract boolean inventoryContainsUnecessaryItems(String[] necessaryItems);
	
	protected abstract boolean hasLogs();
	
	protected abstract boolean isBankAvailable();
	
	protected abstract boolean isBankOpen();
	
	protected abstract boolean isCurrentlyCuttingTree();
	
	protected abstract boolean isMoving();
	
	private int waitWalkConsecutiveIterations = 0;
	
	public State getState(State currentState, WoodcutterSettings settings) {
		boolean useBank = settings.getBankArea() != null;
		
		boolean inventoryContainsUnecessaryItems = inventoryContainsUnecessaryItems(settings.getNecessaryItems());
		
		boolean treeAvailable = isTreeAvailable(settings.getTreesToCut(), settings.getCuttingArea());
				
		boolean inDroppingPhase = (!useBank && currentState == State.DROP && hasLogs());
		boolean inBankingPhase = (useBank && isBankAvailable() && inventoryContainsUnecessaryItems && isBankOpen());
		
		boolean shouldDisposeItems = isInventoryFull() || inDroppingPhase || inBankingPhase;
				
		if(isMoving() && waitWalkConsecutiveIterations < 5) {
			waitWalkConsecutiveIterations++;
			return State.WAIT_WALK;
		}
		waitWalkConsecutiveIterations = 0;
		
		if(shouldDisposeItems) {
			if(!useBank) return State.DROP;
			
			if(!isBankAvailable()) return State.TRAVEL_TO_BANK;
			
			if(isBankOpen()) {
				return State.BANK;
			}
			else {
				return State.OPEN_BANK;
			}
		}
				
		if(isBankOpen()) {
			return State.CLOSE_BANK;
		}
		
		if(treeAvailable) {
			if(!isCurrentlyCuttingTree()) return State.CUT_TREE;
			
			return State.WAIT_CUT_TREE;
		}

		return State.FIND_TREE;
	}
	
	public enum State {
		WAIT_WALK("Waiting walk"), CLOSE_BANK("Closing bank"), FIND_TREE("Attempting to find tree"), CUT_TREE("Attempting to cut tree"), WAIT_CUT_TREE("Cutting tree"), BANK("Banking"), OPEN_BANK("Opening bank"), TRAVEL_TO_BANK("Travelling to bank"), DROP("Dropping");
		
		private String stateText;
		
		State(String stateText) {
			this.stateText = stateText;
		}
		
		public String getStateText() {
			return stateText;
		}
		
	}
	
}

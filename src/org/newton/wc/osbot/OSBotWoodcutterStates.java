package org.newton.wc.osbot;

import java.util.Arrays;

import org.newton.api.map.TileArea;
import org.newton.wc.WoodcutterStates;
import org.newton.wc.data.Trees;
import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.script.MethodProvider;

public class OSBotWoodcutterStates extends WoodcutterStates {

	private MethodProvider methodProvider;
	
	public OSBotWoodcutterStates(MethodProvider methodProvider) {
		this.methodProvider = methodProvider;
	}
	
	@Override
	protected boolean isTreeAvailable(Trees[] treesToFind, TileArea treeArea) {
		return OSBotUtil.getClosestAvailableTree(methodProvider.getObjects(), treesToFind, treeArea) != null;
	}

	@Override
	protected boolean isInventoryFull() {
		return methodProvider.getInventory().isFull();
	}

	@Override
	protected boolean inventoryContainsUnecessaryItems(final String[] necessaryItems) {
		return !OSBotUtil.getUnecesarryItems(methodProvider.getInventory(), necessaryItems).isEmpty();
	}

	@Override
	protected boolean hasLogs() {
		Filter<Item> logsFilter = new Filter<Item>() {

			@Override
			public boolean match(Item item) {
				if(item == null || item.getName() == null) return false;

				return Arrays.asList(Trees.getLogsNames()).contains(item.getName());
			}
			
		};
		
		return methodProvider.getInventory().contains(logsFilter);
	}

	@Override
	protected boolean isBankAvailable() {
		return OSBotUtil.getClosestAvailableBank(methodProvider.getObjects()) != null;
	}

	@Override
	protected boolean isBankOpen() {
		return methodProvider.getBank().isOpen();
	}

	@Override
	protected boolean isCurrentlyCuttingTree() {
		Player myPlayer = methodProvider.myPlayer();
		return myPlayer != null && myPlayer.isAnimating();
	}

	@Override
	protected boolean isMoving() {
		return methodProvider.myPlayer().isMoving();
	}
	
}

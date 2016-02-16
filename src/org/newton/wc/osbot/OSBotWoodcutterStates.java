package org.newton.wc.osbot;

import java.util.Arrays;

import org.newton.api.map.TileArea;
import org.newton.wc.WoodcutterStates;
import org.newton.wc.data.Tree;
import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.MethodProvider;

public class OSBotWoodcutterStates extends WoodcutterStates {

	private MethodProvider methodProvider;
	
	public OSBotWoodcutterStates(MethodProvider methodProvider) {
		this.methodProvider = methodProvider;
	}
	
	@Override
	protected boolean isTreeAvailable(Tree[] treesToFind, TileArea treeArea) {
		RS2Object availableTree = OSBotUtil.getClosestAvailableTree(methodProvider.getSkills().getStatic(Skill.WOODCUTTING), methodProvider.getObjects(), treesToFind, treeArea, false);
		
		return availableTree != null;
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

				return Arrays.asList(Tree.getLogsNames()).contains(item.getName());
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

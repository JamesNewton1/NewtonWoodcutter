package org.newton.wc.osbot;

import org.newton.api.util.RandomUtil;
import org.newton.wc.Woodcutter;
import org.newton.wc.WoodcutterSettings;
import org.newton.wc.data.Tree;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.MethodProvider;

public class OSBotWoodcutter extends Woodcutter {
	
	private MethodProvider methodProvider;
	
	public OSBotWoodcutter(MethodProvider methodProvider, WoodcutterSettings settings) {
		super(new OSBotWoodcutterStates(methodProvider), settings);
		this.methodProvider = methodProvider;
	}

	@Override
	protected int bank() {
		methodProvider.getBank().depositAll(OSBotUtil.getUnecesarryItems(methodProvider.getInventory(), settings.getNecessaryItems()));
		
		return RandomUtil.randomGaussian(200, 20000, 800, 200);
	}

	@Override
	protected int cutTree() {
		RS2Object closestAvailableTree = OSBotUtil.getClosestAvailableTree(methodProvider.getSkills().getStatic(Skill.WOODCUTTING), methodProvider.getObjects(), settings.getTreesToCut(), settings.getCuttingArea(), settings.prioritiseBestTree());
		
		if(closestAvailableTree != null && closestAvailableTree.interact("Chop down")) {
			return RandomUtil.randomGaussian(200, 20000, 1500, 250);
		}
		
		return RandomUtil.randomGaussian(150, 20000, 500, 300);
	}

	@Override
	protected int drop() {
		methodProvider.getInventory().dropAll(OSBotUtil.getUnecesarryItems(methodProvider.getInventory(), settings.getNecessaryItems()));
		
		return RandomUtil.randomGaussian(200, 20000, 800, 200);
	}

	@Override
	protected int findTree() {
		Position centerPos = OSBotUtil.convertTileToPosition(settings.getCuttingArea().getCenter());
		methodProvider.getWalking().webWalk(new Area(centerPos, centerPos));
		
		return RandomUtil.randomGaussian(200, 20000, 800, 200);
	}

	@Override
	protected int openBank() {
		try {
			methodProvider.getBank().open();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return RandomUtil.randomGaussian(200, 20000, 800, 200);
	}

	@Override
	protected int travelToBank() {
		//Position centerPos = OSBotUtil.convertTileToPosition(settings.getBankArea().getCenter());
		methodProvider.getWalking().webWalk(OSBotUtil.getBanks());
		
		return RandomUtil.randomGaussian(200, 20000, 800, 200);
	}

	@Override
	protected int waitCutTree() {
		return RandomUtil.randomGaussian(200, 20000, 800, 200);
	}

	@Override
	protected int closeBank() {
		methodProvider.getBank().close();
		
		return RandomUtil.randomGaussian(200, 20000, 800, 200);
	}

	@Override
	protected int waitWalk() {
		return RandomUtil.randomGaussian(200, 20000, 800, 200);
	}

}

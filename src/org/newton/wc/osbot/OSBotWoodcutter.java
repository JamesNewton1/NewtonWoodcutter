package org.newton.wc.osbot;

import org.newton.api.util.RandomUtil;
import org.newton.wc.Woodcutter;
import org.newton.wc.WoodcutterSettings;
import org.osbot.rs07.api.model.RS2Object;
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
		RS2Object closestAvailableTree = OSBotUtil.getClosestAvailableTree(methodProvider.getObjects(), settings.getTreesToCut(), settings.getCuttingArea());
		
		closestAvailableTree.interact("Chop down");
		
		return RandomUtil.randomGaussian(200, 20000, 800, 200);
	}

	@Override
	protected int drop() {
		methodProvider.getInventory().dropAll(OSBotUtil.getUnecesarryItems(methodProvider.getInventory(), settings.getNecessaryItems()));
		
		return RandomUtil.randomGaussian(200, 20000, 800, 200);
	}

	@Override
	protected int findTree() {
		methodProvider.getWalking().webWalk(OSBotUtil.convertTileToPosition(settings.getCuttingArea().getCenter()));
		
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
		methodProvider.getWalking().webWalk(OSBotUtil.convertTileToPosition(settings.getBankArea().getCenter()));
		
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

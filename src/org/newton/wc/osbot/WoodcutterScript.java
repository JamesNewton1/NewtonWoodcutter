package org.newton.wc.osbot;

import java.awt.EventQueue;
import java.awt.Graphics2D;

import org.newton.api.map.CircleTileArea;
import org.newton.api.map.TileArea;
import org.newton.wc.WoodcutterSettings;
import org.newton.wc.data.Trees;
import org.newton.wc.gui.WoodcutterGUI;
import org.newton.wc.gui.WoodcutterGUIListener;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

@ScriptManifest(author = "Newton", info = "Generic script attempt with OSBot implementation", logo = "", name = "NewtonWoodcutter", version = 0)
public class WoodcutterScript extends Script implements WoodcutterGUIListener {

	private OSBotWoodcutter woodcutter;
	private boolean stopped;
	private WoodcutterSettings settings = null;
		
	@Override
	public void onStart() throws InterruptedException {
		final WoodcutterGUIListener listener = this;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WoodcutterGUI window = new WoodcutterGUI(listener);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		super.onStart();
	}
	
	@Override
	public int onLoop() throws InterruptedException {
		if(stopped || this.woodcutter == null) return 100;
		
		return woodcutter.onLoop();
	}

	@Override
	public void onGUISubmit(Trees[] treesToCut, int cutRadius, String[] necessaryItems, boolean useBank) {
		TileArea cuttingArea = new CircleTileArea(OSBotUtil.convertPositionToTile(myPlayer().getPosition()), cutRadius);
		TileArea closestBankArea = useBank ? OSBotUtil.closestBankAreaTo(cuttingArea) : null;
		
		this.settings = new WoodcutterSettings(treesToCut, cuttingArea, closestBankArea, necessaryItems);
		this.woodcutter = new OSBotWoodcutter(this, settings);
		
		stopped = false;
	}
	
	@Override
	public void onGUIStop() {
		stopped = true;
	}
	
	@Override
	public void onPaint(Graphics2D g2d) {
		super.onPaint(g2d);
		
		g2d.drawString("State: " + ((stopped || woodcutter == null || woodcutter.getCurrentState() == null) ? "GUI" : woodcutter.getCurrentState().name()), 100, 100);
	}
	
}

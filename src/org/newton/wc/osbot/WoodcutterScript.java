package org.newton.wc.osbot;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.newton.api.map.CircleTileArea;
import org.newton.api.map.TileArea;
import org.newton.api.util.Timer;
import org.newton.wc.WoodcutterSettings;
import org.newton.wc.data.Tree;
import org.newton.wc.gui.WoodcutterGUI;
import org.newton.wc.gui.WoodcutterGUIListener;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

@ScriptManifest(author = "Newton", info = "Generic script attempt with OSBot implementation", logo = "", name = "NewtonWoodcutter", version = 1.00)
public class WoodcutterScript extends Script implements WoodcutterGUIListener {

	private OSBotWoodcutter woodcutter;
	private boolean stopped;
	private WoodcutterSettings settings = null;
	private WoodcutterGUI GUI;	
	
	private int initialLevel;
	private int initialXp;
	
	private Timer timer;
	
	private boolean paintVisible = true;
	
	@Override
	public void onStart() throws InterruptedException {
		super.onStart();

		addToggleMouseListener();
		try {
			GUI = new WoodcutterGUI(this);
			GUI.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onExit() throws InterruptedException {
		if(GUI != null) {
			GUI.setVisible(false);
			GUI = null;
		}
	}
	
	@Override
	public int onLoop() throws InterruptedException {
		if(stopped || this.woodcutter == null) return 100;
		
		return woodcutter.onLoop();
	}

	@Override
	public void onGUISubmit(Tree[] treesToCut, int cutRadius, String[] necessaryItems, boolean useBank, boolean prioritiseBestTree) {
		TileArea cuttingArea = new CircleTileArea(OSBotUtil.convertPositionToTile(myPlayer().getPosition()), cutRadius);
		TileArea closestBankArea = useBank ? OSBotUtil.closestBankAreaTo(cuttingArea) : null;
		
		this.settings = new WoodcutterSettings(treesToCut, cuttingArea, closestBankArea, necessaryItems, prioritiseBestTree);
		this.woodcutter = new OSBotWoodcutter(this, settings);
		this.timer = new Timer(Long.MAX_VALUE);
		this.initialLevel = getSkills().getStatic(Skill.WOODCUTTING);
		this.initialXp = getSkills().getExperience(Skill.WOODCUTTING);
		
		stopped = false;
	}
	
	@Override
	public void onGUIStop() {
		stopped = true;
	}
	
	private static Image getImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch(IOException e) {
            return null;
        }
    }

    private final Color color1 = new Color(255, 255, 255);
    private final Font font1 = new Font("Arial", 0, 9);
    private final Image paintImg = getImage("http://i.imgur.com/I68hise.png");
    private final Image toggleImg = getImage("http://i.imgur.com/z1sGoUM.png");
    private final Rectangle toggleRect = new Rectangle(4, 341, 82, 25);
    
    @Override
    public void onPaint(Graphics2D g) {
    	super.onPaint(g);

    	if(paintVisible) {
            g.drawImage(paintImg, 0, 338, null);
            g.setFont(font1);
            g.setColor(color1);
            g.drawString(woodcutter.getCurrentState().getStateText(), 90, 428);
            g.drawString(Timer.format(timer.getElapsed()), 99, 459);
            g.drawString("" + (getSkills().getStatic(Skill.WOODCUTTING) - initialLevel), 378, 428);
            g.drawString("" + (getSkills().getExperience(Skill.WOODCUTTING) - initialXp), 345, 459);
    	}
        
        g.drawImage(toggleImg, 5, 343, null);
    }

    
    private void addToggleMouseListener() {
		bot.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				log("Clicked: " +e.getPoint());
				log(toggleRect +" contains = " + toggleRect.contains(e.getPoint()));
				if(toggleRect.contains(e.getPoint())) {
					paintVisible = !paintVisible;
				}
			}
		});
    }
    
    
    
	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		this.stop(false);
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

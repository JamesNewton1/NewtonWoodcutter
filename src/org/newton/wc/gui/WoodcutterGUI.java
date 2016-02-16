package org.newton.wc.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.newton.wc.data.Axes;
import org.newton.wc.data.Tree;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;

public class WoodcutterGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5471151181580362084L;
	private ArrayList<WoodcutterGUIListener> listeners;
	private boolean started = false;
	
	JRadioButton normalTreeRadioBtn;
	JRadioButton oakTreeRadioBtn;
	JRadioButton willowTreeRadioBtn;
	JRadioButton mapleTreeRadioBtn;
	JRadioButton yewTreeRadioBtn;
	JRadioButton magicTreeRadioBtn;
	
	JCheckBox prioritiseBestTree;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WoodcutterGUI window = new WoodcutterGUI(null, null);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WoodcutterGUI(WoodcutterGUIListener... submitListeners) {
		initialize();
		this.listeners = new ArrayList<WoodcutterGUIListener>();
		this.listeners.addAll(Arrays.asList(submitListeners));
		for(WoodcutterGUIListener listener : submitListeners) {
			addWindowListener(listener);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 44, 414, 207);
		getContentPane().add(tabbedPane);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setToolTipText("Main");
		tabbedPane.addTab("Main", null, mainPanel, null);
		mainPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Settings", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 389, 157);
		mainPanel.add(panel);
		panel.setLayout(null);
		
		JLabel choppingMethodLabel = new JLabel("Chopping method");
		choppingMethodLabel.setBounds(10, 26, 105, 14);
		panel.add(choppingMethodLabel);
		
		JComboBox<String> choppingMethodComboBox = new JComboBox<String>();
		choppingMethodComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Bank", "Powerchop"}));
		choppingMethodComboBox.setBounds(112, 23, 123, 20);
		panel.add(choppingMethodComboBox);
		
		JLabel TreeToCutLabel = new JLabel("Tree to cut");
		TreeToCutLabel.setBounds(10, 51, 92, 14);
		panel.add(TreeToCutLabel);
		
		prioritiseBestTree = new JCheckBox("Prioritise best tree?");
		prioritiseBestTree.setBounds(110, 51, 200, 14);
		panel.add(prioritiseBestTree);
		
		normalTreeRadioBtn = new JRadioButton("Normal Tree");
		normalTreeRadioBtn.setBounds(10, 67, 99, 23);
		panel.add(normalTreeRadioBtn);
		
		oakTreeRadioBtn = new JRadioButton("Oak Tree");
		oakTreeRadioBtn.setBounds(136, 67, 99, 23);
		panel.add(oakTreeRadioBtn);
		
		willowTreeRadioBtn = new JRadioButton("Willow Tree");
		willowTreeRadioBtn.setBounds(252, 67, 99, 23);
		panel.add(willowTreeRadioBtn);
		
		mapleTreeRadioBtn = new JRadioButton("Maple Tree");
		mapleTreeRadioBtn.setBounds(10, 93, 99, 23);
		panel.add(mapleTreeRadioBtn);
		
		yewTreeRadioBtn = new JRadioButton("Yew Tree");
		yewTreeRadioBtn.setBounds(136, 93, 99, 23);
		panel.add(yewTreeRadioBtn);
		
		magicTreeRadioBtn = new JRadioButton("Magic Tree");
		magicTreeRadioBtn.setBounds(252, 93, 99, 23);
		panel.add(magicTreeRadioBtn);
		
		JButton startStopBtn = new JButton("Start/Stop");
		startStopBtn.setToolTipText("Start/Stop");
		startStopBtn.setBounds(146, 123, 233, 23);
		panel.add(startStopBtn);
		
		JLabel chopRadiusLabel = new JLabel("Chop radius:");
		chopRadiusLabel.setBounds(10, 127, 73, 14);
		panel.add(chopRadiusLabel);
		
		JSpinner chopRadiusSpinner = new JSpinner();
		chopRadiusSpinner.setToolTipText("Chop radius");
		chopRadiusSpinner.setBounds(80, 124, 43, 20);
		panel.add(chopRadiusSpinner);
		
		JPanel aboutPanel = new JPanel();
		aboutPanel.setToolTipText("About");
		tabbedPane.addTab("About", null, aboutPanel, null);
		aboutPanel.setLayout(null);
		
		JLabel aboutLabel = new JLabel("I hope you enjoy the script. Created by Newton.");
		aboutLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		aboutLabel.setBounds(10, 11, 334, 19);
		aboutPanel.add(aboutLabel);
		
		JLabel titleLabel = new JLabel("Newton Woodcutter");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial Black", Font.BOLD, 18));
		titleLabel.setBounds(104, 11, 228, 26);
		getContentPane().add(titleLabel);
		

		startStopBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				started = !started;
				for(WoodcutterGUIListener listener : listeners) {
					boolean useBank = choppingMethodComboBox.getSelectedIndex() == 0;

					if(started) {
						listener.onGUISubmit(getSelectedTrees(), (Integer) chopRadiusSpinner.getValue(), Axes.AXE_NAMES, useBank, prioritiseBestTree.isSelected());
					}
					else {
						listener.onGUIStop();
					}
				}
			}
		});
	}
	
	
		
	private Tree[] getSelectedTrees() {
		ArrayList<Tree> selectedTree = new ArrayList<Tree>();
		if(normalTreeRadioBtn.isSelected()) {
			selectedTree.add(Tree.NORMAL);
		}
		if(willowTreeRadioBtn.isSelected()) {
			selectedTree.add(Tree.WILLOW);
		}
		if(oakTreeRadioBtn.isSelected()) {
			selectedTree.add(Tree.OAK);
		}
		if(mapleTreeRadioBtn.isSelected()) {
			selectedTree.add(Tree.MAPLE);
		}
		if(yewTreeRadioBtn.isSelected()) {
			selectedTree.add(Tree.YEW);
		}
		if(magicTreeRadioBtn.isSelected()) {
			selectedTree.add(Tree.MAGIC);
		}
		
		return selectedTree.toArray(new Tree[selectedTree.size()]);
	}
				
}

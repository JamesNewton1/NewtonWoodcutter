package org.newton.wc;

import org.newton.api.script.Script;
import org.newton.api.util.RandomUtil;
import org.newton.wc.WoodcutterStates.State;

public abstract class Woodcutter implements Script {

	private WoodcutterStates states;
	private State currentState;
	protected WoodcutterSettings settings;
	
	public Woodcutter(WoodcutterStates states, WoodcutterSettings settings) {
		this.states = states;
		this.settings = settings;
	}

	@Override
	public int onLoop() {
		switch(currentState = states.getState(currentState, settings)) {
			case BANK:
				return bank();
			case CUT_TREE:
				return cutTree();
			case DROP:
				return drop();
			case FIND_TREE:
				return findTree();
			case OPEN_BANK:
				return openBank();
			case TRAVEL_TO_BANK:
				return travelToBank();
			case WAIT_CUT_TREE:
				return waitCutTree();
			case CLOSE_BANK:
				return closeBank();
			case WAIT_WALK:
				return waitWalk();
			default:
				break;
		}
		return RandomUtil.randomGaussian(50, 2000, 800, 500);
	}
	
	public State getCurrentState() {
		return currentState;
	}
	
	protected abstract int bank();
	protected abstract int cutTree();
	protected abstract int drop();
	protected abstract int findTree();
	protected abstract int openBank();
	protected abstract int travelToBank();
	protected abstract int waitCutTree();
	protected abstract int closeBank();
	protected abstract int waitWalk();
	
}

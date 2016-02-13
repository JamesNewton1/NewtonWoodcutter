package org.newton.api.map;

public class InfiniteTileArea implements TileArea {
		
	private Tile center;
	
	public InfiniteTileArea(Tile center) {
		this.center = center;
	}
	
	public InfiniteTileArea() {
		this(null);
	}
	
	@Override
	public Tile getCenter() {
		return center;
	}
	
	@Override
	public boolean contains(Tile tile) {
		return true;
	}
	
}

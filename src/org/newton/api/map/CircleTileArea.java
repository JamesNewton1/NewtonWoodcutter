package org.newton.api.map;

public class CircleTileArea implements TileArea {

	private Tile center;
	private int radius;
	
	public CircleTileArea(Tile center, int radius) {
		this.center = center;
		this.radius = radius;
	}
		
	@Override
	public boolean contains(Tile tile) {
		return center.euclideanDistanceTo(tile) <= radius;
	}

	@Override
	public Tile getCenter() {
		return center;
	}

	@Override
	public String toString() {
		return "CircleTileArea[center:" + center.toString() + ",radius:" + radius + "]";
	}
	
}

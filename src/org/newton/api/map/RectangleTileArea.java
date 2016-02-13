package org.newton.api.map;

public class RectangleTileArea implements TileArea {

	private Tile southWest, northEast, center;
	
	public RectangleTileArea(Tile southWest, Tile northEast) {
		if(southWest.getZ() != northEast.getZ()) throw new IllegalArgumentException("Z values must match. Rectangle is 2d");
		this.southWest = southWest;
		this.northEast = northEast;
		this.center = new Tile((southWest.getX() + northEast.getX())/2, (southWest.getY() + northEast.getY())/2, southWest.getZ());
	}
	
	@Override
	public boolean contains(Tile tile) {
		if(tile.getZ() != southWest.getZ()) return false;
		
		boolean inXBounds = (tile.getX() <= northEast.getX() && tile.getX() >= southWest.getX());
		boolean inYBounds = (tile.getY() <= northEast.getY() && tile.getY() >= southWest.getY());
		
		return inXBounds && inYBounds;
	}

	@Override
	public Tile getCenter() {
		return center;
	}

}

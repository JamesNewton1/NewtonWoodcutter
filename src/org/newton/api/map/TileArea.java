package org.newton.api.map;

public interface TileArea {
	
	public boolean contains(Tile tile);
	
	public Tile getCenter();
		
	public static TileArea NULL_TILE_AREA = new TileArea() {
		@Override
		public Tile getCenter() {
			return null;
		}
		@Override
		public boolean contains(Tile tile) {
			return false;
		}
	};
	
}

package org.newton.api.map;

public class Tile {

	private int x, y, z;
	
	public Tile(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getZ() {
		return z;
	}
	
	public double euclideanDistanceTo(Tile target) {
		if(this.z != target.z) return Integer.MAX_VALUE;
		
		double distX = Math.abs(target.x - this.x);
		double distY = Math.abs(target.y - this.y);
		
		return Math.hypot(distX, distY);
	}
	
	public double euclideanDistanceFrom(Tile source) {
		return source.euclideanDistanceTo(this);
	}
	
	@Override
	public String toString() {
		return "Tile[" + x + "," + y + "," + z + "]";
	}

}


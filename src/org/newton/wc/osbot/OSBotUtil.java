package org.newton.wc.osbot;

import java.util.List;

import org.newton.api.map.RectangleTileArea;
import org.newton.api.map.Tile;
import org.newton.api.map.TileArea;
import org.newton.wc.data.Trees;
import org.osbot.rs07.api.Inventory;
import org.osbot.rs07.api.Objects;
import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.RS2Object;

public class OSBotUtil {

	public static String[] BANK_ACTIONS = {"Bank", "Open bank"};
	
	public static Tile convertPositionToTile(Position pos) {
		return new Tile(pos.getX(), pos.getY(), pos.getZ());
	}
	
	public static Position convertTileToPosition(Tile tile) {
		return new Position(tile.getX(), tile.getY(), tile.getZ());
	}
		
	public static List<Item> getUnecesarryItems(Inventory inventory, String...necessaryItems) {
		Filter<Item> unecessaryItemsFilter = new Filter<Item>() {
			
			@Override
			public boolean match(Item item) {
				if(item == null || item.getName() == null) return false;
				
				for(String necessaryItemName : necessaryItems) {
					if(item.getName().equals(necessaryItemName)) return false;
				}
				
				return true;
			}
			
		};
		
		List<Item> unecessaryItems = inventory.filter(unecessaryItemsFilter);
		
		return unecessaryItems;
	}
	
	public static RS2Object getClosestAvailableBank(Objects localObjects) {
		Filter<RS2Object> bankFilter = new Filter<RS2Object>() {

			@Override
			public boolean match(RS2Object obj) {
				if(obj == null || obj.getName() == null || obj.getActions() == null) return false;
				return obj.getName().toLowerCase().contains("bank") || obj.hasAction(BANK_ACTIONS);
			}
			
		};
		
		return localObjects.closest(bankFilter);
	}
	
	public static RS2Object getClosestAvailableTree(Objects localObjects, Trees[] treesToFind, TileArea treeArea) {
		Filter<RS2Object> treesAvailableFilter = new Filter<RS2Object>() {

			@Override
			public boolean match(RS2Object obj) {
				if(obj == null || obj.getName() == null || obj.getPosition() == null) return false;

				for(Trees tree : treesToFind) {
					if(tree.getTreeName().equals(obj.getName().trim())) {
						return treeArea.contains(OSBotUtil.convertPositionToTile(obj.getPosition()));
					}
				}
				return false;
			}
			
		};
		
		return localObjects.closest(treesAvailableFilter);
	}
	
	public static TileArea closestBankAreaTo(TileArea target) {
		double closestDistance = Double.MAX_VALUE;
		TileArea closest = null;
		
		for(TileArea area : getBankTileAreas()) {
			double dist = area.getCenter().euclideanDistanceTo(target.getCenter());
			if(dist < closestDistance) {
				closestDistance = dist;
				closest = area;
			}
		}
		 
		return closest;
	}
	
	public static TileArea areaToTileArea(Area area) {
		return new RectangleTileArea(convertPositionToTile(area.getRandomPosition()), convertPositionToTile(area.getRandomPosition())); //TODO: Not ideal, but ok for now
	}
	
	private static TileArea[] getBankTileAreas() {
		Area[] bankAreas = getBanks();
		TileArea[] result = new TileArea[bankAreas.length];
		for(int i = 0; i < bankAreas.length; i++) {
			result[i] = areaToTileArea(bankAreas[i]);
		}
		
		return result;
	}
	
	private static Area[] getBanks() {
		return new Area[] { Banks.AL_KHARID, Banks.ARDOUGNE_NORTH, Banks.ARDOUGNE_SOUTH, Banks.CAMELOT, Banks.CANIFIS, Banks.CANIFIS, Banks.CASTLE_WARS, Banks.CASTLE_WARS, Banks.CATHERBY, Banks.DRAYNOR, Banks.DUEL_ARENA, Banks.EDGEVILLE, Banks.FALADOR_EAST, Banks.FALADOR_WEST, Banks.GNOME_STRONGHOLD, Banks.LUMBRIDGE_LOWER, Banks.LUMBRIDGE_UPPER, Banks.PEST_CONTROL, Banks.TZHAAR, Banks.VARROCK_EAST, Banks.VARROCK_WEST, Banks.YANILLE };
	}
		
}

package org.group11.Packages.Game.Scripts.Logic;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Tile.tileTypes;

import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Finds a path along the map from one point to another
 */
public class Pathfinder {
	//******************************************************************************************************************
	//* methods
	//******************************************************************************************************************
	/**
	 * Given a tile map, finds a path from point A to point B
	 * @param map object that contains all the Tiles in the map
	 * @param pointA the point from which to path find from
	 * @param pointB the point to where to find a path to
	 * @return the first Vector3 in the path to from point A to point B
	 */




	public   Vector3 FindPath(Map map, Vector3 pointA, Vector3 pointB){

		Vector3 nextposition = new Vector3(0,0,0);
		nextposition= map.getAlltile(pointA,pointB);
		String positionkey = map.getPosKey(nextposition);
		System.out.printf(pointB.x+" "+pointB.y+"\n");
		System.out.println(positionkey + " this is the next position ");
		return nextposition;

	}

}

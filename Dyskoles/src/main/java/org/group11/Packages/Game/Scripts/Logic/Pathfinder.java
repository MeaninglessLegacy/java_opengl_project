package org.group11.Packages.Game.Scripts.Logic;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Tile.tileTypes;

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

	// these will help to check if we have reached point B
	boolean pointBReached = false;
	int step=0;

	/**
	 * Findpath gets below parameters and returns nextposition ,
	 * @param map
	 * @param pointA
	 * @param pointB
	 * @return
	 */

	public  Vector3 FindPath(Map map, Vector3 pointA, Vector3 pointB){
		Vector3 nextposition = new Vector3(0,0,0);
		nextposition= map.getNextPosition(pointA,pointB);
		return nextposition;

	}









}

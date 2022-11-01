package org.group11.Packages.Game.Scripts.Logic;

import jdk.incubator.vector.VectorOperators;
import org.group11.Packages.Engine.Vector3;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Tile;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Tile.tileTypes;

/**
 * Finds a path in a map from one point to another
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

	public static Vector3 FindPath(Map map, Vector3 pointA, Vector3 pointB){

		Vector3 nexttile;

		Vector3 currenttile = pointA;

		Vector3 righttile = currenttile;
		righttile.x = currenttile.x +1;

		Vector3 lefttile = currenttile;
		lefttile.x = currenttile.x -1;

		Vector3 uptile = currenttile;
		uptile.y = currenttile.y +1;

		Vector3 downtile = currenttile;
		downtile.x = currenttile.x -1;




		// calcute the distance between the point A and poinb

		float xdistance = pointB.x - currenttile.x; 
		// if this positive = right
		// if negative = left
		// if zero up or down 

		float ydistance = pointB.y - currenttile.y;
		// if ydistance positive = down
		// if           negative = up
		// if 0 left or right 


		while(map.getTile(currenttile) != map.getTile(pointB)) {


			// A move to the right or up
			if(currenttile.x < pointB.x && currenttile.y < pointB.y) {// 1st shortest way is right and 2nd is up 


				if(Math.abs(xdistance)<=Math.abs(ydistance) ) { 

					if (map.getTile(righttile).getTileType() != tileTypes.wall) { // go right

						currenttile = righttile;
						return currenttile;

					}else if(map.getTile(uptile).getTileType() != tileTypes.wall) { // go up
						currenttile = uptile;
						return currenttile;


					} else {
						//go to other options in case those two are walls
						if(map.getTile(uptile).getTileType() != tileTypes.wall) {
							currenttile = lefttile;
							return currenttile;
						}else {
							currenttile = downtile;
							return currenttile;
						}
					}
				}



			}


			if(currenttile.x < pointB.x && currenttile.y > pointB.y) {
				//  A move to the right or down

				if(Math.abs(xdistance)<=Math.abs(ydistance) && map.getTile(righttile).getTileType() != tileTypes.wall) { // go right


					currenttile = righttile;
					return currenttile;

				}else if(map.getTile(downtile).getTileType() != tileTypes.wall) { // go down
					currenttile =downtile;
					return currenttile;


				}else {
					//go to other options in case those two are tiles 
				}

			}



			//  A move to the left or up
			if(pointA.x > pointB.x && pointA.y < pointB.y) {


				if(Math.abs(xdistance)<=Math.abs(ydistance) && map.getTile(lefttile).getTileType() != tileTypes.wall) { // go right


					currenttile =lefttile;
					return currenttile;

				}else if(map.getTile(uptile).getTileType() != tileTypes.wall) { // go up
					currenttile = uptile;
					return currenttile;


				}else {
					//go to other options in case those two are tiles 
				}



			}



			//  A move to the left or down
			if(pointA.x > pointB.x && pointA.y > pointB.y) {


				if(Math.abs(xdistance)<=Math.abs(ydistance) && map.getTile(lefttile).getTileType() != tileTypes.wall) { // go right


					currenttile =lefttile;
					return currenttile;

				}else if(map.getTile(downtile).getTileType() != tileTypes.wall) { // go down
					currenttile = downtile;
					return currenttile;


				}else {
					//go to other options in case those two are tiles 
				}


			}








		}

		return null;

	}









}

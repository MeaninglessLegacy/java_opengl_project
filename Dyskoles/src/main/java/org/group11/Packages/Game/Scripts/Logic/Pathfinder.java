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


		float ydistance = pointB.y - currenttile.y;


		while(map.getTile(currenttile) != map.getTile(pointB)) {


			// 1 A move to the right or up
			if(currenttile.x < pointB.x && currenttile.y < pointB.y) { // move right or up


				if(Math.abs(xdistance)<=Math.abs(ydistance) ) { // 1st shortest way is right and 2nd is up 

					if (map.getTile(righttile).getTileType() != tileTypes.wall) { // go right

						currenttile = righttile;
						return currenttile;

					}else if(map.getTile(uptile).getTileType() != tileTypes.wall) { // go up
						currenttile = uptile;
						return currenttile;


					} else {
						//go to other options in case those two are walls
						if(map.getTile(lefttile).getTileType() != tileTypes.wall) {
							currenttile = lefttile;
							return currenttile;
						}else {
							currenttile = downtile;
							return currenttile;
						}
					}
				}else if (Math.abs(xdistance)>=Math.abs(ydistance) ) { // up 1st and right second


					if(map.getTile(uptile).getTileType() != tileTypes.wall) { // go up
						currenttile = uptile;
						return currenttile;


					}else if(map.getTile(righttile).getTileType() != tileTypes.wall) { // go right

						currenttile = righttile;
						return currenttile;

					}else {
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

			//2  A move to the right or down
			if(currenttile.x < pointB.x && currenttile.y > pointB.y) {


				if(Math.abs(xdistance)<=Math.abs(ydistance) ) { // 1st shortest way is right and 2nd is down 

					if (map.getTile(righttile).getTileType() != tileTypes.wall) { // go right

						currenttile = righttile;
						return currenttile;

					}else if(map.getTile(downtile).getTileType() != tileTypes.wall) { // go  down
						currenttile = downtile;
						return currenttile;


					} else {
						//go to other options in case those two are walls
						if(map.getTile(lefttile).getTileType() != tileTypes.wall) {
							currenttile = lefttile;
							return currenttile;
						}else {
							currenttile = uptile;
							return currenttile;
						}
					}
				}else if (Math.abs(xdistance)>=Math.abs(ydistance) ) { // down 1st and right right


					if(map.getTile(downtile).getTileType() != tileTypes.wall) { // go down
						currenttile = downtile;
						return currenttile;


					}else if(map.getTile(righttile).getTileType() != tileTypes.wall) { // go right

						currenttile = righttile;
						return currenttile;

					}else {
						//go to other options in case those two are walls
						if(map.getTile(lefttile).getTileType() != tileTypes.wall) {
							currenttile = lefttile;
							return currenttile;
						}else {
							currenttile = uptile;
							return currenttile;
						}
					}


				}

			}



			//3  A move to the left or up
			if(pointA.x > pointB.x && pointA.y < pointB.y) {

				if(Math.abs(xdistance)<=Math.abs(ydistance) ) { // 1st shortest way is left and 2nd is up 

					if (map.getTile(lefttile).getTileType() != tileTypes.wall) { // go left

						currenttile = lefttile;
						return currenttile;

					}else if(map.getTile(uptile).getTileType() != tileTypes.wall) { // go up
						currenttile = uptile;
						return currenttile;


					} else {
						//go to other options in case those two are walls
						if(map.getTile(downtile).getTileType() != tileTypes.wall) {
							currenttile = downtile;
							return currenttile;
						}else {
							currenttile = righttile;
							return currenttile;
						}
					}
				}else if (Math.abs(xdistance)>=Math.abs(ydistance) ) { // up 1st and left second


					if(map.getTile(uptile).getTileType() != tileTypes.wall) { // go up
						currenttile = uptile;
						return currenttile;


					}else if(map.getTile(lefttile).getTileType() != tileTypes.wall) { // go right

						currenttile = lefttile;
						return currenttile;

					}else {
						//go to other options in case those two are walls
						if(map.getTile(downtile).getTileType() != tileTypes.wall) {
							currenttile = downtile;
							return currenttile;
						}else {
							currenttile = righttile;
							return currenttile;
						}
					} 

				}

			}



			//  A move to the left or down
			if(pointA.x > pointB.x && pointA.y > pointB.y) {


				if(Math.abs(xdistance)<=Math.abs(ydistance) ) { // 1st shortest way is left and 2nd is down 

					if (map.getTile(lefttile).getTileType() != tileTypes.wall) { // go left

						currenttile = lefttile;
						return currenttile;

					}else if(map.getTile(downtile).getTileType() != tileTypes.wall) { // go down
						currenttile = downtile;
						return currenttile;


					} else {
						//go to other options in case those two are walls
						if(map.getTile(uptile).getTileType() != tileTypes.wall) {
							currenttile = uptile;
							return currenttile;
						}else {
							currenttile = righttile;
							return currenttile;
						}
					}
				}else if (Math.abs(xdistance)>=Math.abs(ydistance) ) { // down 1st and left second


					if(map.getTile(downtile).getTileType() != tileTypes.wall) { // go down
						currenttile = downtile;
						return currenttile;


					}else if(map.getTile(lefttile).getTileType() != tileTypes.wall) { // go right

						currenttile = lefttile;
						return currenttile;

					}else {
						//go to other options in case those two are walls
						if(map.getTile(uptile).getTileType() != tileTypes.wall) {
							currenttile = downtile;
							return currenttile;
						}else {
							currenttile = righttile;
							return currenttile;
						}
					} 

				}

			}

		}

		return null;

	}









}

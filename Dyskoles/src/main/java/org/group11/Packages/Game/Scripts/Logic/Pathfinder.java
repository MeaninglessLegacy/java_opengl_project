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

	public  Vector3 FindPath(Map map, Vector3 pointA, Vector3 pointB){

		Vector3 nextTile;

		Vector3 currentTile = pointA;

		Vector3 rightTile = new Vector3(currentTile.x + 1, currentTile.y, currentTile.z);

		Vector3 leftTile = new Vector3(currentTile.x - 1, currentTile.y, currentTile.z);

		Vector3 upTile = new Vector3(currentTile.x, currentTile.y + 1, currentTile.z);

		Vector3 downTile = new Vector3(currentTile.x, currentTile.y - 1, currentTile.z);




		// calculate the distance between the pointA and pointB

		float xDistance = pointB.x - currentTile.x; 


		float yDistance = pointB.y - currentTile.y;


		while(map.getTile(currentTile) != map.getTile(pointB)) {


			// 1 A move to the right or up
			if(currentTile.x <= pointB.x && currentTile.y <= pointB.y) { // move right or up


				if(Math.abs(xDistance)<=Math.abs(yDistance) ) { // 1st shortest way is right and 2nd is up 

					if (map.getTile(rightTile).getTileType() != tileTypes.wall) { // go right

						currentTile = rightTile;
						return currentTile;

					}else if(map.getTile(upTile).getTileType() != tileTypes.wall) { // go up
						currentTile = upTile;
						return currentTile;


					} else {
						//go to other options in case those two are walls
						if(map.getTile(leftTile).getTileType() != tileTypes.wall) {
							currentTile = leftTile;
							return currentTile;
						}else {
							currentTile = downTile;
							return currentTile;
						}
					}
				}else if (Math.abs(xDistance)>=Math.abs(yDistance) ) { // up 1st and right second


					if(map.getTile(upTile).getTileType() != tileTypes.wall) { // go up
						currentTile = upTile;
						return currentTile;


					}else if(map.getTile(rightTile).getTileType() != tileTypes.wall) { // go right

						currentTile = rightTile;
						return currentTile;

					}else {
						//go to other options in case those two are walls
						if(map.getTile(leftTile).getTileType() != tileTypes.wall) {
							currentTile = leftTile;
							return currentTile;
						}else {
							currentTile = downTile;
							return currentTile;
						}
					} 



				}

			}

			//2  A move to the right or down
			if(currentTile.x <= pointB.x && currentTile.y >= pointB.y) {


				if(Math.abs(xDistance)<=Math.abs(yDistance) ) { // 1st shortest way is right and 2nd is down 

					if (map.getTile(rightTile).getTileType() != tileTypes.wall) { // go right

						currentTile = rightTile;
						return currentTile;

					}else if(map.getTile(downTile).getTileType() != tileTypes.wall) { // go  down
						currentTile = downTile;
						return currentTile;


					} else {
						//go to other options in case those two are walls
						if(map.getTile(leftTile).getTileType() != tileTypes.wall) {
							currentTile = leftTile;
							return currentTile;
						}else {
							currentTile = upTile;
							return currentTile;
						}
					}
				}else if (Math.abs(xDistance)>=Math.abs(yDistance) ) { // down 1st and right right


					if(map.getTile(downTile).getTileType() != tileTypes.wall) { // go down
						currentTile = downTile;
						return currentTile;


					}else if(map.getTile(rightTile).getTileType() != tileTypes.wall) { // go right

						currentTile = rightTile;
						return currentTile;

					}else {
						//go to other options in case those two are walls
						if(map.getTile(leftTile).getTileType() != tileTypes.wall) {
							currentTile = leftTile;
							return currentTile;
						}else {
							currentTile = upTile;
							return currentTile;
						}
					}


				}

			}



			//3  A move to the left or up
			if(pointA.x >= pointB.x && pointA.y <= pointB.y) {

				if(Math.abs(xDistance)<=Math.abs(yDistance) ) { // 1st shortest way is left and 2nd is up 

					if (map.getTile(leftTile).getTileType() != tileTypes.wall) { // go left

						currentTile = leftTile;
						return currentTile;

					}else if(map.getTile(upTile).getTileType() != tileTypes.wall) { // go up
						currentTile = upTile;
						return currentTile;


					} else {
						//go to other options in case those two are walls
						if(map.getTile(rightTile).getTileType() != tileTypes.wall) {
							currentTile = rightTile;
							return currentTile;
						}else {
							currentTile = downTile;
							return currentTile;
						}
					}
				}else if (Math.abs(xDistance)>=Math.abs(yDistance) ) { // up 1st and left second


					if(map.getTile(upTile).getTileType() != tileTypes.wall) { // go up
						currentTile = upTile;
						return currentTile;


					}else if(map.getTile(leftTile).getTileType() != tileTypes.wall) { // go right

						currentTile = leftTile;
						return currentTile;

					}else {
						//go to other options in case those two are walls
						if(map.getTile(rightTile).getTileType() != tileTypes.wall) {
							currentTile = rightTile;
							return currentTile;
						}else {
							currentTile = downTile ;
							return currentTile;
						}
					} 

				}

			}



			//4  A move to the left or down
			if(pointA.x >= pointB.x && pointA.y >= pointB.y) { //fix the error


				if(Math.abs(xDistance)<=Math.abs(yDistance) ) { // 1st shortest way is left and 2nd is down 

					if (map.getTile(leftTile).getTileType() != tileTypes.wall) { // go left

						currentTile = leftTile;
						return currentTile;

					}else if(map.getTile(downTile).getTileType() != tileTypes.wall) { // go down
						currentTile = downTile;
						return currentTile;


					} else {
						//go to other options in case those two are walls
						if(map.getTile(rightTile).getTileType() != tileTypes.wall) {
							currentTile = rightTile;
							return currentTile;
						}else {
							currentTile = upTile ;
							return currentTile;
						}
					}
				}else if (Math.abs(xDistance)>=Math.abs(yDistance) ) { // down 1st and left second


					if(map.getTile(downTile).getTileType() != tileTypes.wall) { // go down
						currentTile = downTile;
						return currentTile;


					}else if(map.getTile(leftTile).getTileType() != tileTypes.wall){
						if(map.getTile(leftTile).getTileType() != tileTypes.wall) { // go right
							currentTile = leftTile;
							return currentTile;
						}

					}else {
						//go to other options in case those two are walls
						if(map.getTile(upTile).getTileType() != tileTypes.wall) {
							currentTile = downTile;
							return currentTile;
						}else {
							currentTile = rightTile;
							return currentTile;
						}
					} 

				}

			}

		}

		return null;

	}









}

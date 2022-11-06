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

	// these will help to check if we have reached point B
	// screen setting
	final int maxCol = 100;
	final int maxRow = 100;
	final int nodeSize = 70;

	// this will help check if the goal is reached or not
	boolean goalReached = false;
	int step = 0;

	//Node
	// these are the nodes that will contain everything
	Node [][] node = new Node [maxCol][maxRow];
	Node startNode, goalNode, currentNode;
	ArrayList<Node> openList = new ArrayList<>();
	ArrayList<Node> checkedList = new ArrayList<>();

	public   Vector3 FindPath(Map map, Vector3 pointA, Vector3 pointB){
	//	map.getAlltile();




		return pointA;

	}

	// this will get the row




	//this will set starting postion
	private void setStartNode(int col, int row) {
		node[col][row] .setAsStart();// this will set node/tile as start
		startNode = node[col][row];
		currentNode = startNode;
	}

	// this will set the ending position
	private void setGoalNode(int col, int row) {
		node[col][row] .setAsGoal();
		goalNode = node[col] [row];

	}

	// this will check if the tile is solid(wall)
	private void setSolidNode(int col, int row) { // will need to study well how to check if it's a wall

		node[col][row].setAsSolid();

	}

	// this will set the cost of each node
	private void setCostOnNodes() {

		int col = 0;
		int row = 0;
		while(col < maxCol && row < maxRow) {
			getCost(node[col][row]);
			col ++;
			if(col == maxCol) {
				col = 0;
				row ++;
			}
		}
	}


	//  calculates the distance between start,current and goal nodes
	private void getCost(Node node) {
		// GET G COST (The distance from the start node)
		int xDistance = Math.abs(node.col - startNode.col);
		int yDistance = Math.abs(node.row - startNode.row);
		node.gCost = xDistance + yDistance;
		// GET H COST (The distance from the goal node).
		xDistance = Math.abs(node.col - goalNode.col);
		yDistance = Math.abs(node.row - goalNode.row);
		node.hCost = xDistance + yDistance;
		// GET F COST (The toal cost)

		node.fCost = node.gCost + node.hCost;

	}



	// this will autosearch the path automaticlay
	public void autosearch() {

		while(goalReached == false && step<300) {
			int col = currentNode.col;

			int row = currentNode.row;
			currentNode.setAsChecked(); // this will check each node
			checkedList.add(currentNode); //
			openList.remove(currentNode);


			// OPEN THE UP NODE
			if(row -1 >= 0) {
				openNode(node[col] [row-1]);
			}
			// OPEN THE LEFT NODE
			if(col -1 >= 0) {
				openNode(node[col-1] [row]);
			}
			// OPEN THE DOWN NODE
			if(row +1 < maxRow) {
				openNode(node[col] [row+1]);
			}
			// OPEN THE RIGHT NODE
			if(col +1 < maxCol) {
				openNode(node[col+1] [row]);
			}

			// FIND THE BEST NODE
			int bestNodeIndex = 0;
			int bestNodefCost = 999;
			for(int i= 0; i < openList.size() ; i ++) {
				// Check if this node's F cost is better
				if(openList.get (i) .fCost < bestNodefCost) {
					bestNodeIndex=i;
					bestNodefCost = openList.get(i).fCost;
				}
				// If F cost is equal, check the G cost
				else if(openList.get(i) .fCost == bestNodefCost) {
					if(openList.get(i) .gCost < openList.get(bestNodeIndex).gCost) {
						bestNodeIndex = i;
					}
				}
			}


			// After the loop, we get the best node which is our next step
			currentNode = openList.get(bestNodeIndex);
			if(currentNode == goalNode) {
				goalReached = true;
				trackThePath();
			}


		}
		step++;
	}


	private void openNode(Node node) {
		if(node.open == false && node.checked == false && node.solid == false) {
			// If the node is not opened yet, add it to the open list.
			node.setAsOpen();
			node.parent = currentNode;
			openList.add(node);
		}
	}

	private Vector3 trackThePath() {

		// Backtrack and draw the best path
		Node current = goalNode;
		while(current != startNode) {
			current = current.parent;
			if(current.parent == startNode) {
				return new Vector3(current.row,current.col,0);

			}


		}
		return null;
	}












}

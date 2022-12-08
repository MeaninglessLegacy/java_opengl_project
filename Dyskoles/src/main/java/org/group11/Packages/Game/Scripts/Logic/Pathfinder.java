package org.group11.Packages.Game.Scripts.Logic;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Tile;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Tile.tileTypes;

import java.util.ArrayList;
import java.util.Dictionary;
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

		Dictionary<String, Tile> _tileMap = map.get_tileMap();


		Vector3 nextposition = new Vector3(0,0,0);
		nextposition= getNextPosition(_tileMap,pointA,pointB);
		return nextposition;

	}


	//******************************************************************************************************************
	//* methods for Pathfinder. These methods will help convert the map into arrayList of nodes and calculate the next best position
	//*
	//******************************************************************************************************************

	/**
	 *  This method gets the next posiontion of the game
	 * @param pointA the pointA parameter
	 * @param pointB the pointB parameter
	 * @return nextpostion the next position requested
	 */
	public Vector3 getNextPosition(Dictionary<String, Tile> _tileMap,Vector3 pointA, Vector3 pointB){

		System.out.println("here1");


		ArrayList<Node> nodeArrayList = new ArrayList<>();
		ArrayList<Node> openList = new ArrayList<>();
		ArrayList<Node> checkedList = new ArrayList<>();

		boolean goalReached = false;


		nodeArrayList = tilemapConverter(_tileMap);



		// setting the start and goalnode
		int Ax = (int) pointA.x;
		int Ay = (int) pointA.y;
		int Bx = (int) pointB.x;
		int By = (int) pointB.y;

		Node startNode = setStartNode(nodeArrayList,Ax,Ay);
		Node goalNode = setGoalNode(nodeArrayList,Bx,By);

		Node currentNode = startNode;

		setCostOnNodes(nodeArrayList,startNode,goalNode);
		System.out.println("cost done");
		return  autosearch(nodeArrayList,openList,checkedList,currentNode,goalReached,goalNode,startNode);
	}

	/**
	 *  This method converts the dictionary into an arraylist.
	 * @param _tileMap the _tilemap parameter
	 * @return nodeArrayList this returns an Arraylist which contains all the data of the map
	 */

	private ArrayList<Node> tilemapConverter(Dictionary<String, Tile> _tileMap){
		int u =0;
		int t = 0;
		int col = 0;
		int row = 0;

		ArrayList<Node> nodeArrayList = new ArrayList<>();

		// this for loop will loop throug the enumeration and convert all the tiles into arraylist
		for (Enumeration<String> tilePositions = _tileMap.keys(); tilePositions.hasMoreElements();){  // this loop is getting all the tiles in the map
			System.out.println("here2");
			String key = tilePositions.nextElement();

			//this will split the data from a string into node list
			String[] res =(key.split("[.]", 0));
			//this will get me the x and y coordinates
			int i =1;
			for(String myStr: res) {

				if(i<2) {
					row =   Integer.parseInt(myStr);
					int number = Integer.parseInt(myStr);
					t++;
				}

				if(i == 2 ){
					String minus = "-";
					if(myStr.contains(minus)){
						String[] tokens = myStr.split("[-]");
						int tokensNum = 1;

						for(String tokenstr: tokens){
							if(tokensNum != 1){
								col = - Integer.parseInt(tokenstr);
							}
							tokensNum++;
						}
					}else {
						col = Integer.parseInt(myStr);
					}
				}

				if(i==3){
					nodeArrayList.add(new Node(col,row));// this contains all the nodes and positions
				}

				i++;

			}



			//setting the tiles as solid or not solid
			if(_tileMap.get(key).getTileType() == Tile.tileTypes.floor){
				nodeArrayList.get(u).solid = false;
			} else if(_tileMap.get(key).getTileType() == Tile.tileTypes.wall){
				nodeArrayList.get(u).solid = true;
			}
			u++;
		}//end of for loop for the enumeration


		return nodeArrayList;
	}

	/**
	 * this method set a node as start node
	 * @param col the colmun da
	 * @param row
	 * @return
	 */
	private Node setStartNode(ArrayList<Node> nodeArrayList, int row, int col) {
		Node startNode1 = new Node(0,0);
		for (int counter = 0; counter < nodeArrayList.size(); counter++){
			if(nodeArrayList.get(counter).row ==row && nodeArrayList.get(counter).col ==col){
				nodeArrayList.get(counter).setAsStart();
				startNode1 = nodeArrayList.get(counter);
			}
		}
		return startNode1;
	}


	/**
	 * This will set a node as the goal node
	 * @param nodeArrayList
	 * @param row
	 * @param col
	 * @return node
	 */
	private Node setGoalNode(ArrayList<Node> nodeArrayList, int row, int col) {
		Node goalNode= new Node(0,0);
		for (int counter = 0; counter < nodeArrayList.size(); counter++){
			if(nodeArrayList.get(counter).row ==row && nodeArrayList.get(counter).col ==col){
				nodeArrayList.get(counter).setAsStart();
				goalNode = nodeArrayList.get(counter);
			}
		}

		return goalNode;
	}

	/**
	 * This method set the cost of a node: the cost is the axes distance between the goal and start
	 * @param nodeArrayList
	 * @param startNode
	 * @param goalNode
	 */

	private void setCostOnNodes(ArrayList<Node> nodeArrayList, Node startNode, Node goalNode) {

		int col1 = 0;
		int row1 = 0;
		for(int counter = 0; counter < nodeArrayList.size(); counter++) {

			getCost(nodeArrayList.get(counter),startNode,goalNode);
			col1 ++;
			if(col1 == 100) {
				col1 = 0;
				row1 ++;
			}
		}
	}

	/**
	 * this calculates the cost which is the distance between start, current and goal node
	 * @param node
	 * @param startNode
	 * @param goalNode
	 */
	//  calculates the distance between start,current and goal nodes
	private void getCost(Node node, Node startNode, Node goalNode) {
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

	/**
	 * This method will search the closest path and return a vector of the closest path
	 * @param nodeArrayList the nodeArrayList parameter
	 * @param openList the openList parameter( all the tiles that the algorithm hasn't chacked yet)
	 * @param checkedList the checkedList parameter: this is the list of all the tiles that have been checked
	 * @param currentNode
	 * @param goalReached
	 * @param goalNode
	 * @param startNode
	 * @return
	 */
	// this will autosearch the path automaticlay
	public Vector3 autosearch(ArrayList<Node> nodeArrayList, ArrayList<Node> openList, ArrayList<Node> checkedList, Node currentNode, boolean goalReached, Node goalNode, Node startNode) {
		System.out.println("autosearch started");
		int step = 0;

		while(goalReached == false) {
			System.out.println("autosearch started while loop");
			System.out.println(nodeArrayList.size());
			int col = currentNode.col;

			int row = currentNode.row;
			currentNode.setAsChecked(); // this will check each node
			checkedList.add(currentNode); //
			openList.remove(currentNode);


			// OPEN THE UP NODE
			for (int counter = 0; counter < nodeArrayList.size(); counter++){
				if(nodeArrayList.get(counter).row ==row-1 && nodeArrayList.get(counter).col ==col){
					openNode(nodeArrayList.get(counter), currentNode, openList);
				}
			}

			// OPEN THE LEFT NODE

			for (int counter = 0; counter < nodeArrayList.size(); counter++){
				if(nodeArrayList.get(counter).row ==row && nodeArrayList.get(counter).col == col-1){
					openNode(nodeArrayList.get(counter), currentNode,openList);
				}
			}

			// OPEN THE DOWN NODE

			for (int counter = 0; counter < nodeArrayList.size(); counter++){
				if(nodeArrayList.get(counter).row ==row+1 && nodeArrayList.get(counter).col ==col){
					openNode(nodeArrayList.get(counter), currentNode, openList);
				}
			}

			// OPEN THE RIGHT NODE

			for (int counter = 0; counter < nodeArrayList.size(); counter++){
				if(nodeArrayList.get(counter).row ==row && nodeArrayList.get(counter).col ==col+1){
					openNode(nodeArrayList.get(counter),currentNode, openList);
				}
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
			if(!openList.isEmpty()){ currentNode = openList.get(bestNodeIndex);}

			if(currentNode == goalNode) {
				//goalReached = true;
				System.out.println("track path reached");
				return trackThePath(goalNode,startNode);
			}

			step++;
			if(step == nodeArrayList.size()){ goalReached = true;}
		}

		Vector3 vector = new Vector3(startNode.col, startNode.row, 0);
		return vector;
	}

	/**
	 * This method checks if a node has been opened and add it to openlist to the openList in case it's not opened
	 * @param node
	 * @param currentNode
	 * @param openList
	 */
	private void openNode(Node node, Node currentNode, ArrayList<Node> openList) {
		if(node.open == false && node.checked == false && node.solid == false) {
			// If the node is not opened yet, add it to the open list.
			node.setAsOpen();
			node.parent = currentNode;
			openList.add(node);
		}
	}

	/**
	 *  This method backtracks and gives the best path
	 * @param goalNode
	 * @param startNode
	 * @return a vector which is the sext position
	 */
	private Vector3 trackThePath(Node goalNode, Node startNode) {
		System.out.println("here4");
		// Backtrack and give the best path
		Node current = goalNode;
		while(current != startNode) {
			current = current.parent;
			if(current.parent == startNode) {
				Vector3 newvec = new Vector3(current.row,current.col,0);
				return newvec;
			}


		}
		return new Vector3(goalNode.row, goalNode.col,0);

	}
}

package org.group11.Packages.Game.Scripts.Logic;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Tile;

import java.util.*;

/**
 * Map object that keeps track of all tile objects on the map
 */
public class Map {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    private Dictionary<String, Tile> _tileMap = new Hashtable<>();

    private Scene scene;

    //******************************************************************************************************************
    //* setters and getters
    //******************************************************************************************************************
    /**
     * Return this Map's _tileMap
     * @return this Map's _tileMap
     */
    public Dictionary<String, Tile> get_tileMap() {
        return _tileMap;
    }

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    /**
     * Converts Vector3 position to a String key.
     * @param pos Vector3 position to convert to a key.
     * @return The key mapped to by the Vector3 position.
     */
    private String getPosKey(Vector3 pos){
        return pos.x+""+pos.y+""+pos.z;
    }

    /**
     * returns the Tile at the specified Vector3 position, or null if there is no tile
     * @return the Tile at the Vector3 position, or null if there is no tile
     */
    public Tile getTile(Vector3 pos) {
        Tile ret;
        try {
            ret = _tileMap.get(getPosKey(pos));
        }
        catch (Exception e) {
            return null;
        }
        return ret;
    }

    // Eric - I need this to spawn enemies on the floor!!
    /**
     * Returns a list of all Floor tiles within the map.
     * @return Floor tile list.
     */
    public List<Tile> getFloorTiles(){
        List<Tile> floorTiles = new ArrayList<>();
        for (Enumeration<String> tilePositions = _tileMap.keys(); tilePositions.hasMoreElements();) {
            Tile tile = _tileMap.get(tilePositions.nextElement());
            if(tile.getTileType() == Tile.tileTypes.floor) floorTiles.add(tile);
        }
        return floorTiles;
    }

    /**
     * If there is no tile at the Vector3 position, then sets the given tile into that position in the _tileMap
     * @return true if tile was successfully set, false if a tile already exists at the given position
     */
    public boolean setTile(Vector3 pos, Tile tile) {
        if (_tileMap.get(getPosKey(pos)) == null) {
            _tileMap.put(getPosKey(pos), tile);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Clears the _tileMap of all tiles
     */
    public void clearMap() {
        scene = Scene.get_scene();
        if(scene == null) return;
        for (Enumeration<String> tilePositions = _tileMap.keys(); tilePositions.hasMoreElements();) {
            scene.Destroy(_tileMap.get(tilePositions.nextElement()));
        }
        _tileMap = new Hashtable<>();
    }


    //******************************************************************************************************************
    //* methods for Pathfinder. These methods will help convert the map into arrayList of nodes and calculate the next best position
    //******************************************************************************************************************

    public Vector3 getNextPosition(Vector3 pointA, Vector3 pointB){
        int u =0;
        int t = 0;

        int col = 0;
        int row = 0;

        ArrayList<Node> nodeArrayList = new ArrayList<>();
        ArrayList<Node> openList = new ArrayList<>();
        ArrayList<Node> checkedList = new ArrayList<>();

        boolean goalReached = false;



        for (Enumeration<String> tilePositions = _tileMap.keys(); tilePositions.hasMoreElements();){  // this loop is getting all the tiles in the map
            String key = tilePositions.nextElement();
            //System.out.println(key+" num "+u);
           //dSystem.out.println(_tileMap.get(key).getTileType()+" "+u);



            //this will split the data from a string into node list

            String[] res =(key.split("[.]", 0));
            //this can get me the x and y
            int i =1;
            for(String myStr: res) {
                //System.out.println(myStr);
                if(i<2) {
                    row =   Integer.parseInt(myStr);
                    int number = Integer.parseInt(myStr);


                    //System.out.println(row +" row " );
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

                               // System.out.println(col+ " col" );
                            }

                            tokensNum++;
                        }
                    }else {
                        col = Integer.parseInt(myStr);
                        //System.out.println(col+ " col" );
                    }

                }

                if(i==3){
                    //node[col] [row] = new Node(col,row);
                    nodeArrayList.add(new Node(col,row));// this contains all the nodes and positionaw
                }

                i++;

            }



            //setting the tiles as solid or not solid
            if(_tileMap.get(key).getTileType() == Tile.tileTypes.floor){
                //node[col] [row].solid= false;
                nodeArrayList.get(u).solid = false;
            } else if(_tileMap.get(key).getTileType() == Tile.tileTypes.wall){
                // node[col] [row].solid= true;
                nodeArrayList.get(u).solid = true;
            }
            u++;
        }//end of for loop for the enumeration

        //System.out.println(nodeArrayList.get(u-1).row +"  printing the actual data for test "+nodeArrayList.get(u-1).col );

        // setting the origne
        int Ax = (int) pointA.x;
        int Ay = (int) pointA.y;
        int Bx = (int) pointB.x;
        int By = (int) pointB.y;

        Node startNode = setStartNode(nodeArrayList,Ax,Ay);
        Node goalNode = setGoalNode(nodeArrayList,Bx,By);
        //System.out.println("proper goal node row"+pointB.x);
        Node currentNode = startNode;

        setCostOnNodes(nodeArrayList,startNode,goalNode);

        return  autosearch(nodeArrayList,openList,checkedList,currentNode,goalReached,goalNode,startNode);
    }

    /**
     * @param col
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
        //System.out.println("Start node "+ startNode1.col + startNode1.row);

        return startNode1;
    }



    private Node setGoalNode(ArrayList<Node> nodeArrayList, int row, int col) {
        Node goalNode= new Node(0,0);
        for (int counter = 0; counter < nodeArrayList.size(); counter++){
            if(nodeArrayList.get(counter).row ==row && nodeArrayList.get(counter).col ==col){
                nodeArrayList.get(counter).setAsStart();
                goalNode = nodeArrayList.get(counter);
                //System.out.println("goalnode node "+goalNode.row);
            }
        }
        //System.out.println("goalnode node row "+goalNode.row + "col "+goalNode.col);

        return goalNode;
    }


    private void setCostOnNodes(ArrayList<Node> nodeArrayList, Node startNode, Node goalNode) {

        int col1 = 0;
        int row1 = 0;
        for(int counter = 0; counter < nodeArrayList.size(); counter++) {

            //System.out.println(node[col1][row1].col +"  "+node[col1][row1].row );
            getCost(nodeArrayList.get(counter),startNode,goalNode);

            col1 ++;
            if(col1 == 100) {
                col1 = 0;
                row1 ++;
            }
        }
    }


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

        //System.out.println(node.fCost + " G cost "+node.gCost );

    }


    // this will autosearch the path automaticlay
    public Vector3 autosearch(ArrayList<Node> nodeArrayList, ArrayList<Node> openList, ArrayList<Node> checkedList, Node currentNode, boolean goalReached, Node goalNode, Node startNode) {

        while(goalReached == false) {
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
                goalReached = true;
                return trackThePath(goalNode,startNode);
            }


        }
        //step++;
        Vector3 vector = new Vector3(startNode.col, startNode.row, 0);
        return vector;
    }


    private void openNode(Node node, Node currentNode, ArrayList<Node> openList) {
        if(node.open == false && node.checked == false && node.solid == false) {
            // If the node is not opened yet, add it to the open list.
            node.setAsOpen();
            node.parent = currentNode;
            openList.add(node);
        }
    }

    private Vector3 trackThePath(Node goalNode, Node startNode) {

        // Backtrack and draw the best path
        Node current = goalNode;
        while(current != startNode) {
            current = current.parent;
            if(current.parent == startNode) {
                //System.out.println( current.row+" closest "+current.col);
                Vector3 newvec = new Vector3(current.row,current.col,0);
                //losestPosition = newvec;
                return newvec;



            }


        }
        return new Vector3(goalNode.row, goalNode.col,0);
        //return null;
    }



}



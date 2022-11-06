package org.group11.Packages.Game.Scripts.Logic;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Tile;


import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

import static org.group11.Packages.Game.Scripts.Tile_Scripts.Tile.tileTypes.wall;

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
    //* methods
    //******************************************************************************************************************
    /**
     * Converts Vector3 position to a String key.
     * @param pos Vector3 position to convert to a key.
     * @return The key mapped to by the Vector3 psoition.
     */
    public String getPosKey(Vector3 pos){
        return pos.x+""+pos.y+""+pos.z;
    }

    /**
     * returns the Tile at the specified Vector3 position, or null if there is no tile
     * @return the Tile at the Vector3 position, or null if there is no tile
     */
    public Tile getTile(Vector3 pos) {
        Tile ret = null;
        try {
            ret = _tileMap.get(getPosKey(pos));
        }
        catch (Exception e) {
            return null;
        }
        return ret;
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
    }



    //----------------------------Converting map to nodes----------------------------------------------------------------------


    //node fields

    final int maxCol = 100;
    final int maxRow = 100;
    Node [][] node = new Node [1000][1000];
    Node startNode, goalNode, currentNode;
    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> checkedList = new ArrayList<>();
    // OTHERS
    boolean goalReached = false;
    int step = 0;

    Vector3 closestPosition = new Vector3();


    public Vector3 getAlltile(Vector3 pointA, Vector3 pointB){
        scene = Scene.get_scene();
        int u =0;
        int t = 0;
        int[][] twoD_arr = new int[100][100];




        int col = 0;
        int row = 0;





        if(scene == null) return pointA;
        for (Enumeration<String> tilePositions = _tileMap.keys(); tilePositions.hasMoreElements();) {
           // scene.Destroy(_tileMap.get(tilePositions.nextElement()));d
            String key = tilePositions.nextElement();
            System.out.println(key+" "+u);
            System.out.println(_tileMap.get(key).getTileType()+" "+u);



//            u++;
            String[] res =(key.split("[.]", 0));
             //this can get me the x and y
            int i =1;
            for(String myStr: res) {
                //System.out.println(myStr);
                if(i<2) {
                    row =   Integer.parseInt(myStr);
                    int number = Integer.parseInt(myStr);


                   System.out.println(row );
                    t++;

                }

                if(i == 2 ){
                    col = Integer.parseInt(myStr);

                  System.out.println(col );

                }

                if(i==3){
                    node[col] [row] = new Node(col,row);

                }

                i++;

            }

            // here can access the object
            //this is setting up the
            if(_tileMap.get(key).getTileType() == Tile.tileTypes.floor){
            node[col] [row].solid= false;
            } else if(_tileMap.get(key).getTileType() == Tile.tileTypes.wall){
                node[col] [row].solid= true;
            }
            // System.out.println(node[1] [1].col);
            if(node[col] [row].solid == true){
                System.out.println("solid");


            }

            u++;

        }//end of for loop


        // setting the origne
        int Ax = (int) pointA.x;
        int Ay = (int) pointA.y;
        int Bx = (int) pointB.x;
        int By = (int) pointB.y;

        setStartNode(Ax,Ay);
        setGoalNode(Bx,By);

        setCostOnNodes();
        autosearch();



        return closestPosition;





    }// end of getalltile

    // this will set the ending position


    private void setStartNode(int col, int row) {
        node[col][row] .setAsStart();
        startNode = node[col][row];
        currentNode = startNode;
    }
    private void setGoalNode(int col, int row) {
        node[col][row] .setAsGoal();
        goalNode = node[col] [row];

    }



    private void setCostOnNodes() {

        int col1 = 0;
        int row1 = 0;
        while(col1 < 100 && row1 < 100) {
            System.out.println(node[col1][row1].col +"  "+node[col1][row1].row );
            getCost(node[col1][row1]);

            col1 ++;
            if(col1 == 100) {
                col1 = 0;
                row1 ++;
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

        System.out.println(node.fCost + " G cost "+node.gCost );

    }




    private void openNode(Node node) {
        if(node.open == false && node.checked == false && node.solid == false) {
            // If the node is not opened yet, add it to the open list.
            node.setAsOpen();
            node.parent = currentNode;
            openList.add(node);
        }
    }


    // this will autosearch the path automaticlay
    public void autosearch() {

        while(goalReached == false) {
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

    private void trackThePath() {

        // Backtrack and draw the best path
        Node current = goalNode;
        while(current != startNode) {
            current = current.parent;
            if(current.parent == startNode) {
                System.out.println( current.row+" closest "+current.col);
               Vector3 newvec = new Vector3(current.row,current.col,0);
               closestPosition = newvec;



            }


        }
        //return null;
    }







}

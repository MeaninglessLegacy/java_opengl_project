package org.group11.Packages.Game.Scripts.Logic;

import org.group11.Packages.Game.Scripts.Tile_Scripts.Tile;

import java.lang.reflect.Type;

public class Node {

    Node parent;
    int col;
    int row;
    int gCost;
    int hCost;
    int fCost;
    boolean start;
    boolean goal;
    boolean solid;
    boolean open;
    boolean checked;


    //Tile.tileTypes types;





    //node constructor

    public Node(int col, int row) {
        this.col = col;
        this.row =row;

    }

    public void setAsStart() {

        start = true;
    }

    public void setAsGoal() {

        goal = true;
    }

    //this
    public void setAsSolid() {

        solid = true;
    }

    // while checking the node this will go through the node
    public void setAsOpen() {

    }

    public void setAsChecked() {
        if(start == false && goal == false) {

        }
        checked = true;
    }


    public void setAsPath() {

    }







}

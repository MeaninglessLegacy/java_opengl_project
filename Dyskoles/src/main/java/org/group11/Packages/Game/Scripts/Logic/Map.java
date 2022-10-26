package org.group11.Packages.Game.Scripts.Logic;

import org.group11.Packages.Engine.Vector3;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Tile;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Map object that keeps track of all tile objects on the map
 */
public class Map {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    private Dictionary<Vector3, Tile> _tileMap = new Hashtable<>();

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    /**
     *
     * @return
     */
    public Tile getTile(){
        return null;
    }

    /**
     *
     * @return
     */
    public boolean setTile(){
        return false;
    }

    /**
     *
     * @return
     */
    public boolean clearMap(){
        return false;
    }
}

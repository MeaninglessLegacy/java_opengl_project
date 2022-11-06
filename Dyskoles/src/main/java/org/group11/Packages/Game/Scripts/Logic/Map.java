package org.group11.Packages.Game.Scripts.Logic;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Tile;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

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
    private String getPosKey(Vector3 pos){
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

    public void getAlltile(){
        scene = Scene.get_scene();
        int u =0;
        int t = 0;
        if(scene == null) return;
        for (Enumeration<String> tilePositions = _tileMap.keys(); tilePositions.hasMoreElements();) {
           // scene.Destroy(_tileMap.get(tilePositions.nextElement()));d

            System.out.println(_tileMap.get((tilePositions.nextElement())).getTileType() +" "+u);   // this gets the tiles from the map
            //System.out.println((tilePositions.nextElement()).split("[.]", 0));  // this getting all the vectors

           System.out.println();
            u++;
            String[] res =tilePositions.nextElement().split("[.]", 0);
            // this can get me the x and y
            int i =1;
            for(String myStr: res) {
                //System.out.println(myStr);
                if(i<=2) {
                    int number = Integer.parseInt(myStr);
                    System.out.println(number +"     "+t);
                    t++;
                }
                i++;

            }


        }

    }


}

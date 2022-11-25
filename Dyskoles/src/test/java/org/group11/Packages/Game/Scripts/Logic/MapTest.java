package org.group11.Packages.Game.Scripts.Logic;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Engine;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Floor;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Tile;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Wall;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Runs tests on various methods for the Map class
 */
public class MapTest {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    boolean everythingInstantiated = false;
    private Engine engine;
    private Scene scene;

    private Map map;
    private Floor floor;
    private Wall wall;

    //******************************************************************************************************************
    //* setup
    //******************************************************************************************************************
    private class SetupClass extends GameObject {
        @Override
        public void start() {
            // General instantiations
            map = new Map();
            floor = new Floor();
            wall = new Wall();

            scene.Instantiate(floor);
            scene.Instantiate(wall);

            everythingInstantiated = true;
        }
    }

    @Before
    public void setup() {
        engine = new Engine();
        engine.start();
        scene = Scene.get_scene();

        SetupClass setupClass = new SetupClass();
        scene.Instantiate(setupClass);

        while (!everythingInstantiated) {
            System.out.print("");
        }
    }

    //******************************************************************************************************************
    //* tests
    //******************************************************************************************************************
    /**
     * Tests Map's method to return a tile at a specified Vector3 location
     */
    @Test
    public void getTileTest() {
        map.setTile(new Vector3(0, 0, 0), new Floor(new Vector3(0, 0, 0)));
        map.setTile(new Vector3(0, 1, 0), new Wall(new Vector3(0, 1, 0)));

        assert(map.getTile(new Vector3(0, 0, 0)) instanceof Floor);
        assert(map.getTile(new Vector3(0, 1, 0)) instanceof Wall);
        assert(map.getTile(new Vector3(0, 2, 0)) == null);
    }

    /**
     * Tests Map's method to return a list of only floor tiles
     */
    @Test
    public void getFloorTilesTest() {
        for (int i = 0; i < 5; i++) {
            map.setTile(new Vector3(i, 0, 0), new Floor(new Vector3(i, 0, 0)));
        }
        for (int i = 0; i < 10; i++) {
            map.setTile(new Vector3(i, 1, 0), new Wall(new Vector3(i, 1, 0)));
        }

        List<Tile> floorTileList = map.getFloorTiles();
        assert(floorTileList.size() == 5);
    }

    /**
     * Tests Map's method to set tiles at a specified Vector3 location
     */
    @Test
    public void setTileTest() {
        assert(map.setTile(new Vector3(0, 0, 0), new Floor(new Vector3(0, 0, 0))));
        assert(map.setTile(new Vector3(0, 1, 0), new Wall(new Vector3(0, 1, 0))));
        assert(!map.setTile(new Vector3(0, 0, 0), new Floor(new Vector3(0, 0, 0))));
    }

    /**
     * Tests Map's method to clear all tiles and reset the map
     */
    @Test
    public void clearMap() {
        for (int i = 0; i < 5; i++) {
            map.setTile(new Vector3(i, 0, 0), new Floor(new Vector3(i, 0, 0)));
        }
        for (int i = 0; i < 10; i++) {
            map.setTile(new Vector3(i, 1, 0), new Wall(new Vector3(i, 1, 0)));
        }

        map.clearMap();
        assert(map.get_tileMap().size() == 0);
    }
}

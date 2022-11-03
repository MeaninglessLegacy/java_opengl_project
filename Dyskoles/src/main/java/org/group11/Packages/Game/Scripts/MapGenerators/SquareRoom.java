package org.group11.Packages.Game.Scripts.MapGenerators;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Logic.Map;
import org.group11.Packages.Game.Scripts.Logic.MapGenerator;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Floor;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Tile;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Wall;

import java.lang.Math;

/**
 * Creates a 100x100 square of randomly generated floors and tiles to use for testing
 */
public class SquareRoom extends MapGenerator {
    private Scene scene;
    /**
     * Generates a 101x101 Square of floor tiles, and adds a line of wall tiles along the left side
     * @return Map containing a 10x10 map of floor tiles
     */
    @Override
    public Map generateMap() {
        scene = Scene.get_scene();
        if(scene == null) return null;
        Map newMap = new Map();

        for(int y = 4; y <= 6; y++) {
            Vector3 pos = new Vector3(4, y, 0);
            Tile newTile = new Floor();
            newTile.transform.setPosition(pos);
            newMap.setTile(pos, newTile);
            scene.Instantiate(newTile);
        }


        for(int x = 0; x< 100; x++){
            for(int y = 0; y< 100; y++){
                Vector3 pos = new Vector3(x,y,0);
                long randNum = Math.round(Math.random() * 4);
                Tile newTile;
                if (randNum == 0) {
                     newTile = new Wall();
                } else {
                    newTile = new Floor();
                }
                newTile.transform.setPosition(pos);
                if (newMap.setTile(pos, newTile)) {
                    scene.Instantiate(newTile);
                }
            }
        }


        return newMap;
    }
}

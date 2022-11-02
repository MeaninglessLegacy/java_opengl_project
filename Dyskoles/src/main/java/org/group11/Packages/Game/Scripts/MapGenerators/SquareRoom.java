package org.group11.Packages.Game.Scripts.MapGenerators;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Logic.Map;
import org.group11.Packages.Game.Scripts.Logic.MapGenerator;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Floor;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Tile;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Wall;

/**
 *
 */
public class SquareRoom extends MapGenerator {
    private Scene scene;
    /**
     * Generates a 10x10 Square of floor tiles, and adds a line of wall tiles along the left side
     * @return Map containing a 10x10 map of floor tiles
     */
    @Override
    public Map generateMap() {
        scene = Scene.get_scene();
        if(scene == null) return null;
        Map newMap = new Map();
        for(int x = 0; x< 10; x++){
            for(int y = 0; y< 10; y++){
                Vector3 pos = new Vector3(x,y,0);
                Tile newTile = new Floor();
                newTile.transform.setPosition(pos);
                newMap.setTile(pos, newTile);
                scene.Instantiate(newTile);
            }
        }
        for (int y = -1; y < 11; y++) {
            Vector3 pos = new Vector3(-1, y, 0);
            Tile newTile = new Wall();
            newTile.transform.setPosition(pos);
            newMap.setTile(pos, newTile);
            scene.Instantiate(newTile);
        }
        return newMap;
    }
}

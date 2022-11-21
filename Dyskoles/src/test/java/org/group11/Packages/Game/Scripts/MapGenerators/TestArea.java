package org.group11.Packages.Game.Scripts.MapGenerators;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Logic.Map;
import org.group11.Packages.Game.Scripts.Logic.MapGenerator;
import org.group11.Packages.Game.Scripts.Tile_Scripts.CubeWall.CubeWall;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Floor;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Tile;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Wall;

/**
 * Creates a map with a 5x5 area of floor tiles and a line of walls along the left of that square
 */
public class TestArea extends MapGenerator {
    private Scene scene;
    @Override
    public Map generateMap() {
        scene = Scene.get_scene();
        if(scene == null) return null;
        Map newMap = new Map();

        // generates 5x5 floor tiles
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                Vector3 pos = new Vector3(x, y, 0);
                Tile newTile = new Floor(pos);
                newMap.setTile(pos, newTile);
                scene.Instantiate(newTile);
            }
        }

        // generates walls along the left of the 5x5 floor tiles
        for (int y = 0; y < 5; y++) {
            Vector3 pos = new Vector3(-1, y, 0);
            Tile newTile = new Wall(pos);
            newMap.setTile(pos, newTile);
            scene.Instantiate(newTile);
        }

        return newMap;
    }
}

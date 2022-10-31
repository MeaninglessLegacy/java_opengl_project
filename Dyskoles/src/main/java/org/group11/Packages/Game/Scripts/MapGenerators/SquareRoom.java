package org.group11.Packages.Game.Scripts.MapGenerators;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Logic.Map;
import org.group11.Packages.Game.Scripts.Logic.MapGenerator;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Floor;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Tile;

/**
 *
 */
public class SquareRoom extends MapGenerator {
    private Scene scene;
    /**
     *
     * @return
     */
    @Override
    public Map generateMap() {
        scene = Scene.get_scene();
        if(scene == null) return null;
        Map newMap = new Map();
        for(int i = 0; i< 10; i++){
            for(int z = 0; z< 10; z++){
                Vector3 pos = new Vector3(i,z,0);
                Tile newTile = new Floor();
                newTile.transform.setPosition(pos);
                newMap.setTile(pos, newTile);
                scene.Instantiate(newTile);
            }
        }
        return newMap;
    }
}

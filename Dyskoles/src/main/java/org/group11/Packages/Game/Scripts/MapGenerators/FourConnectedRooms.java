package org.group11.Packages.Game.Scripts.MapGenerators;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Logic.Map;
import org.group11.Packages.Game.Scripts.Logic.MapGenerator;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Floor;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Tile;

public class FourConnectedRooms extends MapGenerator {
    private Scene scene;

    @Override
    public Map generateMap() {
        scene = Scene.get_scene();
        if(scene == null) return null;
        Map newMap = new Map();

        Vector3 firstRoomBottomLeft = new Vector3(-12, -12, 0);
        newMap = generateRoom(newMap, firstRoomBottomLeft);

        return newMap;
    }

    private Map generateRoom(Map newMap, Vector3 bottomLeft) {
        for (int x = (int)bottomLeft.x; x < bottomLeft.x + 10; x++) {
            for (int y = (int)bottomLeft.y; y < bottomLeft.y + 10; y++) {
                Vector3 pos = new Vector3(x, y, 0);
                Tile newTile = new Floor(pos);
                newMap.setTile(pos, newTile);
                scene.Instantiate(newTile);
            }
        }

        for (int x = (int)bottomLeft.x - 1; x < bottomLeft.x + 11; x++) {
            Vector3 pos = new Vector3(x, bottomLeft.y + 10, 0);
            Tile newTile = new Floor(pos);
            newMap.setTile(pos, newTile);
            scene.Instantiate(newTile);
        }
        return newMap;
    }
}

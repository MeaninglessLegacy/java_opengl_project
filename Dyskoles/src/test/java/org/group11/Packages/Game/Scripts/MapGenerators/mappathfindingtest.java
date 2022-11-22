package org.group11.Packages.Game.Scripts.MapGenerators;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Logic.Map;
import org.group11.Packages.Game.Scripts.Logic.MapGenerator;
import org.group11.Packages.Game.Scripts.Tile_Scripts.CubeWall.CubeWall;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Floor;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Tile;

public class mappathfindingtest extends MapGenerator {

    private Scene scene;
    @Override
    public Map generateMap() {
        scene = Scene.get_scene();
        if(scene == null) return null;
        Map newMap = new Map();

        // generates 10X10 floor tiles
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                Vector3 pos = new Vector3(x, y, 0);
                Tile newTile = new Floor();
                newTile.transform.setPosition(pos);
                newMap.setTile(pos, newTile);
                scene.Instantiate(newTile);
            }
        }

        // generating walls
        for (int x = 3; x < 5; x++) {
            Vector3 pos = new Vector3(x, 4, 0);
            Tile newTile = new CubeWall();
            newTile.transform.setPosition(pos);
            newMap.setTile(pos, newTile);
            scene.Instantiate(newTile);
        }

        for (int x = 3; x < 5; x++) {
            Vector3 pos = new Vector3(x, 7, 0);
            Tile newTile = new CubeWall();
            newTile.transform.setPosition(pos);
            newMap.setTile(pos, newTile);
            scene.Instantiate(newTile);
        }

        for (int x = 5; x < 7; x++) {
            Vector3 pos = new Vector3(x, 4, 0);
            Tile newTile = new CubeWall();
            newTile.transform.setPosition(pos);
            newMap.setTile(pos, newTile);
            scene.Instantiate(newTile);
        }


        for (int x = 5; x < 7; x++) {
            Vector3 pos = new Vector3(x, 7, 0);
            Tile newTile = new CubeWall();
            newTile.transform.setPosition(pos);
            newMap.setTile(pos, newTile);
            scene.Instantiate(newTile);
        }


        for (int y = 5; y < 7; y++) {
            Vector3 pos = new Vector3(3, y, 0);
            Tile newTile = new CubeWall();
            newTile.transform.setPosition(pos);
            newMap.setTile(pos, newTile);
            scene.Instantiate(newTile);
        }



        for (int y = 5; y < 7; y++) {
            Vector3 pos = new Vector3(7, y, 0);
            Tile newTile = new CubeWall();
            newTile.transform.setPosition(pos);
            newMap.setTile(pos, newTile);
            scene.Instantiate(newTile);
        }

        return newMap;
    }
}

package org.group11.Packages.Game.Scripts.MapGenerators;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Logic.Map;
import org.group11.Packages.Game.Scripts.Logic.MapGenerator;
import org.group11.Packages.Game.Scripts.Tile_Scripts.CubeWall.CubeWall;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Floor;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Tile;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Wall;

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
                if(y==4 && x>=3 && x<5){ // this creates walls
                    Vector3 pos = new Vector3(x, y, 0);
                    Tile newTile = new Wall();
                    newTile.transform.setPosition(pos);
                    newMap.setTile(pos, newTile);
                    scene.Instantiate(newTile);
                } else if (y==7 && x>=3 && x<5) { // this will generate other walls
                    Vector3 pos = new Vector3(x, y, 0);
                    Tile newTile = new Wall();
                    newTile.transform.setPosition(pos);
                    newMap.setTile(pos, newTile);
                    scene.Instantiate(newTile);

                } else if (y==4 && x>=5 && x<7) {
                    Vector3 pos = new Vector3(x, 4, 0);
                    Tile newTile = new Wall();
                    newTile.transform.setPosition(pos);
                    newMap.setTile(pos, newTile);
                    scene.Instantiate(newTile);

                } else if (y==7 && x>=5 && x<7) {
                    Vector3 pos = new Vector3(x, y, 0);
                    Tile newTile = new Wall();
                    newTile.transform.setPosition(pos);
                    newMap.setTile(pos, newTile);
                    scene.Instantiate(newTile);

                } else if (x==3 && y>=5 && y<7) {
                    Vector3 pos = new Vector3(x, y, 0);
                    Tile newTile = new Wall();
                    newTile.transform.setPosition(pos);
                    newMap.setTile(pos, newTile);
                    scene.Instantiate(newTile);

                } else if (x==7 && y>=5 && y<7) {
                    Vector3 pos = new Vector3(x, y, 0);
                    Tile newTile = new CubeWall();
                    newTile.transform.setPosition(pos);
                    newMap.setTile(pos, newTile);
                    scene.Instantiate(newTile);
                } else {
                Vector3 pos = new Vector3(x, y, 0);
                Tile newTile = new Floor();
                newTile.transform.setPosition(pos);
                newMap.setTile(pos, newTile);
                scene.Instantiate(newTile);}
            }
        }



        return newMap;
    }
}

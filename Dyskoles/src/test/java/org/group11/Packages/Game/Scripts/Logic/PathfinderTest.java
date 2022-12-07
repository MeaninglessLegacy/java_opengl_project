package org.group11.Packages.Game.Scripts.Logic;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Engine;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Character_Scripts.Boss;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacterTest;
import org.group11.Packages.Game.Scripts.MapGenerators.SquareRoom;
import org.group11.Packages.Game.Scripts.MapGenerators.mappathfindingtest;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Floor;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Tile;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Wall;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions.*;
import junit.framework.TestCase;


import static org.junit.jupiter.api.Assertions.*;


public class PathfinderTest {

    boolean everythingInstantiated = false;
    private Engine engine;
    private Scene scene;
    private Map map;
    MainCharacter MC;
    Boss boss;
    private Pathfinder pathfinder;
    private Floor floor;
    private Wall wall;

    private class SetupClass extends GameObject {
        @Override
        public void start() {
            // General instantiations

            SquareRoom newroom = new SquareRoom();
           // map = newroom.generateMap();


            //creating a new room for testing properly
            mappathfindingtest newroom2 = new mappathfindingtest();
            map = newroom2.generateMap();
            pathfinder = new Pathfinder();


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

        int i = 0;
        while (!everythingInstantiated) {
//            i++;
//            System.out.println(i);
            System.out.println("");

        }
    }


    /**
     * This will test if pathfinder is not ruturning a null position.
     */
    @Test
    public void pathfinderresultnotnull(){

        Vector3 pointA = new Vector3(4,6,0);
        Vector3 pointB = new Vector3(8,5,0);

        assertNotNull(pathfinder.FindPath(map,pointA,pointB));


    }



    /**
     * PathfinderTestResultNotPointA: this will test if the result is different form the initial position
     */
    @Test
    public void PathfinderTestResultNotPointA(){
        Vector3 pointA = new Vector3(6,6,0);
        Vector3 pointB = new Vector3(8,13,0);

        assertNotEquals(pointA,pathfinder.FindPath(map,pointA,pointB));

    }

    /**
     * PathfinderTestResultNotWall test if the result is not a wall

     */

    @Test
    public void PathfinderTestResultNotWall(){
        Vector3 pointA = new Vector3(6,6,0);
        Vector3 pointB = new Vector3(10,9,0);

        assertNotEquals(Tile.tileTypes.wall,map.getTile(pathfinder.FindPath(map,pointA,pointB)).getTileType());
    }

    /**
     * Test for pathinfinder helper functions that are in the map class
     */
    @Test
    public void getNexPositionTest(){
        Vector3 pointA = new Vector3(5,5,0);
        Vector3 pointB = new Vector3(2,3,0);
       // assertNotNull(map.getNextPosition(pointA,pointB));
    }





}
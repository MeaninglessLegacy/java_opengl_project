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

        Vector3 pointA = new Vector3(5,5,0);
        Vector3 pointB = new Vector3(2,3,0);

        assertNotNull(pathfinder.FindPath(map,pointA,pointB));


    }

    //1 A move to the right or up

    /**
     * PathfinderTest1A: when pointB is on the right and up of pointA and there is wall up and left
     * the xDistance<=yDistance
     * the result should be 5, the right tile
     */
    @Test
    public void PathfinderTest1A(){
        Vector3 pointA = new Vector3(6,6,0);
        Vector3 pointB = new Vector3(8,13,0);
        assertEquals(5 ,pathfinder.FindPath(map,pointA,pointB).x);
        assertEquals(6 ,pathfinder.FindPath(map,pointA,pointB).y);
    }

    /**
     * PathfinderTest1B: when pointB is on the right and up of pointA and there is wall up and left
     * the xDistance>=yDistance
     * the result should be 5, the right tile
     */

    @Test
    public void PathfinderTest1B(){
        Vector3 pointA = new Vector3(6,6,0);
        Vector3 pointB = new Vector3(10,9,0);
        assertEquals(5 ,pathfinder.FindPath(map,pointA,pointB).x);
        assertEquals(6 ,pathfinder.FindPath(map,pointA,pointB).y);
    }

    //2  A move to the right or down
    /**
     * PathfinderTest2A: pointB is on the right and down
     * xDistance<= yDistance
    */
    @Test
    public void PathfinderTest2A(){
    Vector3 pointA = new Vector3(6,5,0);
    Vector3 pointB = new Vector3(8,1,0);
    assertEquals(5 ,pathfinder.FindPath(map,pointA,pointB).x);
    assertEquals(5,pathfinder.FindPath(map,pointA,pointB).y);
    }


    /**
     * PathfinderTest2B: pointB is on the right and down
     * xDistance>= yDistance
     */

    @Test
    public void PathfinderTest2B(){
        Vector3 pointA = new Vector3(6,5,0);
        Vector3 pointB = new Vector3(10,2,0);
        assertEquals(5 ,pathfinder.FindPath(map,pointA,pointB).x);
        assertEquals(5,pathfinder.FindPath(map,pointA,pointB).y);
    }

    /**
     * Pathfinder3A: pointB is on left and up
     * xDistance<=yDistance means goes first left and then up
     */
    @Test
    public void PathfinderTest3A(){
        Vector3 pointA = new Vector3(4,6,0);
        Vector3 pointB = new Vector3(8,11,0);
        assertEquals(5 ,pathfinder.FindPath(map,pointA,pointB).x);
        assertEquals(6,pathfinder.FindPath(map,pointA,pointB).y);
    }

    /**
     * Pathfinder3B: pointB is on left and up
     * xDistance>=yDistance means goes first up and then left
     */
    @Test
    public void PathfinderTest3B(){
        Vector3 pointA = new Vector3(4,6,0);
        Vector3 pointB = new Vector3(1,7,0);
        assertEquals(5 ,pathfinder.FindPath(map,pointA,pointB).x);
        assertEquals(6,pathfinder.FindPath(map,pointA,pointB).y);
    }

    /**
     * Pathfinder4A: pointA is on left and down
     * xDistance<=yDistance means goes first left and  then down
     */
    @Test
    public void PathfinderTest4A(){
        Vector3 pointA = new Vector3(4,5,0);
        Vector3 pointB = new Vector3(2,3,0);
        assertEquals(5 ,pathfinder.FindPath(map,pointA,pointB).x);
        assertEquals(5,pathfinder.FindPath(map,pointA,pointB).y);
    }

    /**
     * Pathfinder4B: pointB is on left and down
     * xDistance>=yDistance means goes first down and then left
     */
    @Test
    public void PathfinderTest4B(){
        Vector3 pointA = new Vector3(4,5,0);
        Vector3 pointB = new Vector3(2,4,0);
        assertEquals(5 ,pathfinder.FindPath(map,pointA,pointB).x);
        assertEquals(5,pathfinder.FindPath(map,pointA,pointB).y);
    }



}
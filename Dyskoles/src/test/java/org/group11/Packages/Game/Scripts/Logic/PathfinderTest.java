package org.group11.Packages.Game.Scripts.Logic;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Engine;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Character_Scripts.Boss;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacterTest;
import org.group11.Packages.Game.Scripts.MapGenerators.SquareRoom;
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

    private class SetupClass extends GameObject {
        @Override
        public void start() {
            // General instantiations

            SquareRoom newroom = new SquareRoom();
            map = newroom.generateMap();
            pathfinder = new Pathfinder();

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


}
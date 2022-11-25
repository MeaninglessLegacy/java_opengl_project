package org.group11.Packages.Game.Scripts;

import org.group11.Packages.Core.Main.Engine;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Character_Scripts.Boss;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;
import org.group11.Packages.Game.Scripts.Character_Scripts.Minion;
import org.group11.Packages.Game.Scripts.Character_Scripts.Runner;
import org.group11.Packages.Game.Scripts.Item_Scripts.*;
import org.group11.Packages.Game.Scripts.Levels.TestLevel;
import org.group11.Packages.Game.Scripts.Logic.GameLogicDriver;
import org.group11.Packages.Game.Scripts.Logic.Map;
import org.group11.Packages.Game.Scripts.Logic.MenuScreen;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Floor;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Wall;
import org.junit.Before;

public abstract class TestSetup {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    protected boolean everythingInstantiated = false;
    protected Engine engine;
    protected Scene scene;

    protected TestLevel testLevel;
    protected MainCharacter MC;
    protected Boss boss;
    protected Minion minion;
    protected Runner runner;
    protected Key key;
    protected RegenHeart regenHeart;
    protected SpikeTrap spikeTrap;
    protected Exit exit;
    protected MenuScreen menuScreen;
    protected Floor floor;
    protected Wall wall;

    protected Backpack backpack;
    protected Map map;

    //******************************************************************************************************************
    //* setup
    //******************************************************************************************************************
    protected class SetupClass extends GameObject {
        @Override
        public void start() {
            // General instantiations
            testLevel = new TestLevel();
            MC = new MainCharacter();
            boss = new Boss();
            minion = new Minion();
            runner = new Runner();
            key = new Key();
            regenHeart = new RegenHeart();
            spikeTrap = new SpikeTrap();
            exit = new Exit();
            menuScreen = new MenuScreen();
            floor = new Floor();
            wall = new Wall();

            backpack = new Backpack();
            map = new Map();

            scene.Instantiate(MC);
            scene.Instantiate(boss);
            scene.Instantiate(minion);
            scene.Instantiate(runner);
            scene.Instantiate(key);
            scene.Instantiate(regenHeart);
            scene.Instantiate(spikeTrap);
            scene.Instantiate(exit);
            scene.Instantiate(menuScreen);
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

        while (!everythingInstantiated) {
            System.out.print("");
        }
    }
}

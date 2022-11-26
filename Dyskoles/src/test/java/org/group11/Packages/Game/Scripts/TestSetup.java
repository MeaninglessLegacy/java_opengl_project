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
import org.group11.Packages.Game.Scripts.Logic.Map;
import org.group11.Packages.Game.Scripts.Logic.MenuScreen;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Floor;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Wall;
import org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdown;
import org.group11.Packages.Game.Scripts.UI_Scripts.StatIncreaseIndicators.AttackIncreaseIndicator;
import org.group11.Packages.Game.Scripts.UI_Scripts.StatIncreaseIndicators.HealthIncreaseIndicator;
import org.junit.Before;

/**
 * Class is used to instantiate all sprites used by subclass tests to make them work
 */
public abstract class TestSetup {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    protected boolean everythingInstantiated = false;
    protected Engine engine;
    protected Scene scene;

    // Levels
    protected TestLevel testLevel;
    // Characters
    protected MainCharacter MC;
    protected Boss boss;
    protected Minion minion;
    protected Runner runner;
    // Items
    protected Key key;
    protected RegenHeart regenHeart;
    protected SpikeTrap spikeTrap;
    protected Exit exit;
    // UI Elements
    protected MenuScreen menuScreen;
    protected MoveCountdown moveCountdown;
    protected AttackIncreaseIndicator attackIncreaseIndicator;
    protected HealthIncreaseIndicator healthIncreaseIndicator;
    // Tiles
    protected Floor floor;
    protected Wall wall;
    // Non scene.Instantiate classes
    protected Backpack backpack;
    protected Map map;

    //******************************************************************************************************************
    //* setup
    //******************************************************************************************************************
    /**
     * Class is used to instantiate all the sprites when the game engine has started
     */
    protected class SetupClass extends GameObject {
        @Override
        public void start() {
            // Levels
            testLevel = new TestLevel();
            // Characters
            MC = new MainCharacter();
            boss = new Boss();
            minion = new Minion();
            runner = new Runner();
            // Items
            key = new Key();
            regenHeart = new RegenHeart();
            spikeTrap = new SpikeTrap();
            exit = new Exit();
            // UI Elements
            menuScreen = new MenuScreen();
            moveCountdown = new MoveCountdown(boss);
            attackIncreaseIndicator = new AttackIncreaseIndicator(MC);
            healthIncreaseIndicator = new HealthIncreaseIndicator(MC);
            // Tiles
            floor = new Floor();
            wall = new Wall();
            // Non scene.Instantiate classes
            backpack = new Backpack();
            map = new Map();

            // Instantiations
            scene.Instantiate(MC);
            scene.Instantiate(boss);
            scene.Instantiate(minion);
            scene.Instantiate(runner);
            scene.Instantiate(key);
            scene.Instantiate(regenHeart);
            scene.Instantiate(spikeTrap);
            scene.Instantiate(exit);
            scene.Instantiate(menuScreen);
            scene.Instantiate(moveCountdown);
            scene.Instantiate(attackIncreaseIndicator);
            scene.Instantiate(healthIncreaseIndicator);
            scene.Instantiate(floor);
            scene.Instantiate(wall);

            everythingInstantiated = true;
        }
    }

    /**
     * Starts the game engine and waits until it is on proceeding to the next step
     */
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

package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Core.Main.Engine;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;
import org.junit.Before;
import org.junit.Test;

/**
 * Runs tests on various methods for the Key class
 */
public class KeyTest {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    boolean everythingInstantiated = false;
    private Engine engine;
    private Scene scene;

    private Key key;
    private MainCharacter MC;

    //******************************************************************************************************************
    //* setup
    //******************************************************************************************************************
    private class SetupClass extends GameObject {
        @Override
        public void start() {
            // General instantiations
            key = new Key();
            scene.Instantiate(key);

            MC = new MainCharacter();
            scene.Instantiate(MC);

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

    //******************************************************************************************************************
    //* tests
    //******************************************************************************************************************
    /**
     * Tests whether the key is picked up when the sprite is disabled and the key should not be obtainable
     */
    @Test
    public void disabledActivateTest() {
        Backpack backpack = new Backpack();

        assert(!key.activate(MC));
        assert(MC.backpack == backpack);
    }

    /**
     * Tests whether the key is picked up when the sprite is enabled and the key should be obtainable
     */
    @Test
    public void enabledActivateTest() {
        Backpack backpack = new Backpack();
        backpack.addItem(key);

        assert(key.activate(MC));
        assert(MC.backpack == backpack);
    }
}

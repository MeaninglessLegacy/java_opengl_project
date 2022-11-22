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
            MC = new MainCharacter();

            scene.Instantiate(key);
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
     * Tests Key's method to activate when its sprite is disabled and should not be obtainable
     */
    @Test
    public void disabledActivateTest() {
        assert(!key.activate(MC));
        assert(MC.backpack._inventory.size() == 0);
    }

    /**
     * Tests Key's method to activate when the sprite is enabled and the key should be obtainable
     */
    @Test
    public void enabledActivateTest() {
        key.setKeyVisibility(true);

        assert(key.activate(MC));
        assert(MC.backpack._inventory.size() == 1);
        assert(MC.backpack._inventory.get(0) instanceof Key);
    }
}

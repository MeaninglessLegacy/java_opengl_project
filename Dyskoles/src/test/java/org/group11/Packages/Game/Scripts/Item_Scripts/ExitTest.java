package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Core.Main.Engine;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;
import org.group11.Packages.Game.Scripts.Logic.GameLogicDriver;
import org.junit.Before;
import org.junit.Test;

/**
 * Runs tests on various methods for the Exit class
 */
public class ExitTest {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    boolean everythingInstantiated = false;
    private Engine engine;
    private Scene scene;

    private Exit exit;
    private Key key;
    private MainCharacter MC;

    //******************************************************************************************************************
    //* setup
    //******************************************************************************************************************
    private class SetupClass extends GameObject {
        @Override
        public void start() {
            // General instantiations
            exit = new Exit();
            MC = new MainCharacter();
            key = new Key();

            scene.Instantiate(exit);
            scene.Instantiate(MC);
            scene.Instantiate(key);

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
     * Tests Exit's activate method when the activating MainCharacter has no key in their Backpack
     */
    @Test
    public void noKeyActivate() {
        assert(!exit.activate(MC));
    }
}

package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Core.Main.Engine;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;
import org.junit.Before;
import org.junit.Test;

public class ExitTest {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    boolean everythingInstantiated = false;
    private Engine engine;
    private Scene scene;

    private Exit exit;
    private MainCharacter MC;

    //******************************************************************************************************************
    //* setup
    //******************************************************************************************************************
    private class SetupClass extends GameObject {
        @Override
        public void start() {
            // General instantiations
            exit = new Exit();
            scene.Instantiate(exit);

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
     * Tests whether if a MainCharacter with no key in their backpack can activate an Exit
     */
    @Test
    public void noKeyActivate() {
        assert(!exit.activate(MC));
    }

    // TODO: figure out what to do when MC has a key
}

package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Core.Main.Engine;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;
import org.junit.Before;
import org.junit.Test;

/**
 * Runs tests on various methods for the SpikeTrap class
 */
public class SpikeTrapTest {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    boolean everythingInstantiated = false;
    private Engine engine;
    private Scene scene;

    private SpikeTrap spikeTrap;
    private MainCharacter MC;

    //******************************************************************************************************************
    //* setup
    //******************************************************************************************************************
    private class SetupClass extends GameObject {
        @Override
        public void start() {
            // General instantiations
            spikeTrap = new SpikeTrap();
            MC = new MainCharacter();

            scene.Instantiate(spikeTrap);
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
     * Tests SpikeTrap's method to activate and damage the MainCharacter that activated it
     */
    @Test
    public void activateTest() {
        int originalHp = MC.getStatBlock().get_hp();

        assert(spikeTrap.activate(MC));
        assert(MC.getStatBlock().get_hp() == originalHp - spikeTrap.get_spikeTrapDamage());
    }
}

package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Core.Main.Engine;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;
import org.junit.Before;
import org.junit.Test;

/**
 * Runs tests on various methods for the RegenHeart class
 */
public class RegenHeartTest {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    boolean everythingInstantiated = false;
    private Engine engine;
    private Scene scene;

    private RegenHeart regenHeart;
    private MainCharacter MC;

    //******************************************************************************************************************
    //* setup
    //******************************************************************************************************************
    private class SetupClass extends GameObject {
        @Override
        public void start() {
            // General instantiations
            regenHeart = new RegenHeart();
            scene.Instantiate(regenHeart);

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
     * Tests if a RegenHeart is not activated when the activating Character has full health
     */
    @Test
    public void fullHpActivate() {
        assert(!regenHeart.activate(MC));
    }

    /**
     * Tests if a RegenHeart is activated when the activating Character doesn't have full health
     */
    @Test
    public void notFullHpActivate() {
        MC.takeDamage(1);

        assert(regenHeart.activate(MC));
    }
}

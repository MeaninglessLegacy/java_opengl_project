package org.group11.Packages.Game.Scripts.UI_Scripts;

import org.group11.Packages.Core.Main.Engine;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;
import org.group11.Packages.Game.Scripts.UI_Scripts.StatIncreaseIndicators.AttackIncreaseIndicator;
import org.group11.Packages.Game.Scripts.UI_Scripts.StatIncreaseIndicators.HealthIncreaseIndicator;
import org.junit.Before;
import org.junit.Test;

/**
 * Runs tests on various methods for the StatIncreaseIndicator class
 */
public class StatIncreaseIndicatorTest {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    boolean everythingInstantiated = false;
    private Engine engine;
    private Scene scene;

    private AttackIncreaseIndicator atkIncreaseIndicator;
    private HealthIncreaseIndicator hpIncreaseIndicator;
    private MainCharacter MC;

    //******************************************************************************************************************
    //* setup
    //******************************************************************************************************************
    private class SetupClass extends GameObject {
        @Override
        public void start() {
            // General instantiations
            MC = new MainCharacter();
            atkIncreaseIndicator = new AttackIncreaseIndicator(MC);
            hpIncreaseIndicator = new HealthIncreaseIndicator(MC);

            scene.Instantiate(MC);
            scene.Instantiate(atkIncreaseIndicator);
            scene.Instantiate(hpIncreaseIndicator);

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
     * Tests AttackIncreaseIndicator's method to activate and display the sprite, then disable the sprite after 2
     * seconds
     */
    @Test
    public void AttackIncreaseIndicatorActivateTest() {
        long curTime = System.currentTimeMillis();
        int timeBeforeDisappear = 2000;

        atkIncreaseIndicator.activate();
        assert(atkIncreaseIndicator.getSprite().enabled);

        boolean spriteShouldBeActivated = true;
        while (spriteShouldBeActivated) {
            if (System.currentTimeMillis() - curTime > timeBeforeDisappear + 100) {
                spriteShouldBeActivated = false;
            }
        }

        assert(!atkIncreaseIndicator.getSprite().enabled);
    }

    /**
     * Tests HealthIncreaseIndicator's method to activate and display the sprite, then disable the sprite after 2
     * seconds
     */
    @Test
    public void HealthIncreaseIndicatorActivateTest() {
        long curTime = System.currentTimeMillis();
        int timeBeforeDisappear = 2000;

        hpIncreaseIndicator.activate();
        assert(hpIncreaseIndicator.getSprite().enabled);

        boolean spriteShouldBeActivated = true;
        while (spriteShouldBeActivated) {
            if (System.currentTimeMillis() - curTime > timeBeforeDisappear + 100) {
                spriteShouldBeActivated = false;
            }
        }

        assert(!hpIncreaseIndicator.getSprite().enabled);
    }
}

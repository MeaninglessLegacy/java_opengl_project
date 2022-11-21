package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Engine;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.junit.Before;
import org.junit.Test;

/**
 * Runs tests on various methods for the Minion class
 */
public class MinionTest {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    boolean everythingInstantiated = false;
    private Engine engine;
    private Scene scene;

    private MainCharacter MC;
    private Minion minion;

    //******************************************************************************************************************
    //* setup
    //******************************************************************************************************************
    private class SetupClass extends GameObject {
        @Override
        public void start() {
            // General instantiations
            MC = new MainCharacter();
            scene.Instantiate(MC);

            // attackEnemyTest() and attackingDirection()
            minion = new Minion(new Vector3(0,1,0));
            scene.Instantiate(minion);

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
     * Tests the function to reduce a Minion's health
     */
    @Test
    public void takeDamageTest() {
        int OriginalHp = minion._statBlock.get_hp();

        minion.takeDamage(1);
        assert(minion._statBlock._hp == OriginalHp - 1);

        // Negative health does not matter for the takeDamage() method
        minion.takeDamage(10);
        assert(minion._statBlock._hp == OriginalHp - 11);
    }

    /**
     * Tests the whether the minion properly attacks a MainCharacter, and if the function properly returns whether the
     * MainCharacter died or not
     */
    @Test
    public void attackCharacterTest() {
        // Should take Minion 3 attacks to kill a MainCharacter with 3 health
        assert(!minion.attackCharacter(MC));
        assert(!minion.attackCharacter(MC));
        assert(minion.attackCharacter(MC));
    }

    /**
     * Tests that the Minion gives the MainCharacter that defeated it the proper amount of exp
     */
    @Test
    public void giveRewardsTest() {
        int OriginalExp = MC._statBlock.get_exp();

        minion.giveRewards(MC);
        assert(MC._statBlock.get_exp() == OriginalExp + minion.expGiven);
    }

    @Test
    public void canEnemyMoveTest() {
        // TODO: implement
    }
}

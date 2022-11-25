package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.group11.Packages.Core.Main.Engine;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.junit.Before;
import org.junit.Test;

/**
 * Runs tests on various methods for the Boss class
 */
public class BossTest {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    boolean everythingInstantiated = false;
    private Engine engine;
    private Scene scene;

    private MainCharacter MC;
    private Boss boss;

    //******************************************************************************************************************
    //* setup
    //******************************************************************************************************************
    private class SetupClass extends GameObject {
        @Override
        public void start() {
            // General instantiations
            MC = new MainCharacter();
            boss = new Boss();

            scene.Instantiate(MC);
            scene.Instantiate(boss);

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
     * Tests Boss's method to take damage and reduce its health
     */
    @Test
    public void takeDamageTest() {
        int OriginalHp = boss._statBlock.get_hp();

        boss.takeDamage(1);
        assert(boss._statBlock._hp == OriginalHp - 1);

        // Negative health does not matter for the takeDamage() method
        boss.takeDamage(10);
        assert(boss._statBlock._hp == OriginalHp - 11);
    }

    /**
     * Tests Boss's method to attack a MainCharacter and see if the MainCharacter died or not
     */
    @Test
    public void attackCharacterTest() {
        MainCharacter MC = new MainCharacter();

        // Should take Boss only 1 attack to kill a MainCharacter with 3 health
        assert(boss.attackCharacter(MC));

        MC = new MainCharacter();
        MC.addMaxHealth(1);
        MC.addHealth(1);

        // Should take Boss 2 attacks to kill a MainCharacter with 4 health
        assert(!boss.attackCharacter(MC));
        assert(boss.attackCharacter(MC));
    }

    /**
     * Tests Boss's method to give appropriate amount of EXP to the MainCharacter that defeated it. Boss gives 5 EXP
     * which should level up the MainCharacter
     */
    @Test
    public void giveRewardsTest() {
        MainCharacter MC = new MainCharacter();

        boss.giveRewards(MC);
        assert(MC._statBlock.get_exp() == 0 && MC.getStatBlock().get_lvl() == 2);
    }

    @Test
    public void canEnemyMoveTest() {
        // TODO: implement
    }
}

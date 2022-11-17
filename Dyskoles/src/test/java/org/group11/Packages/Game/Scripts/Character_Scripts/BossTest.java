package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.junit.Before;
import org.junit.Test;

/**
 * Runs tests on various methods for the Boss class
 */
public class BossTest {
    private Boss boss;

    @Before
    public void setup() {
        boss = new Boss();
    }

    /**
     * Tests the function to reduce a Boss's health
     */
    @Test
    public void takeDamageTest() {
        setup();
        int OriginalHp = boss._statBlock.get_hp();

        boss.takeDamage(1);
        assert(boss._statBlock._hp == OriginalHp - 1);

        // Negative health does not matter for the takeDamage() method
        assert(boss._statBlock._hp == OriginalHp - 11);
    }

    /**
     * Tests the whether the boss properly attacks a MainCharacter, and if the function properly returns whether the
     * MainCharacter died or not
     */
    @Test
    public void attackEnemyTest() {
        setup();
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
     * Tests that the Boss gives the MainCharacter that defeated it the proper amount of exp
     */
    @Test
    public void giveRewardsTest() {
        setup();
        MainCharacter MC = new MainCharacter();
        int OriginalExp = MC._statBlock.get_exp();

        boss.giveRewards(MC);
        assert(MC._statBlock.get_exp() == OriginalExp + boss.expGiven);
    }

    @Test
    public void canEnemyMoveTest() {
        // TODO: implement
    }
}

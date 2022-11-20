package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.junit.Before;
import org.junit.Test;

/**
 * Runs tests on various methods for the Minion class
 */
public class MinionTest {
    private Minion minion;

    @Before
    public void setup() {
        minion = new Minion();
    }

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
        MainCharacter MC = new MainCharacter();

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
        MainCharacter MC = new MainCharacter();
        int OriginalExp = MC._statBlock.get_exp();

        minion.giveRewards(MC);
        assert(MC._statBlock.get_exp() == OriginalExp + minion.expGiven);
    }

    @Test
    public void canEnemyMoveTest() {
        // TODO: implement
    }
}

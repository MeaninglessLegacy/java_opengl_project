package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.group11.Packages.Game.Scripts.TestSetup;
import org.junit.Test;

/**
 * Runs tests on various methods for the Minion class
 */
public class MinionTest extends TestSetup {
    //******************************************************************************************************************
    //* tests
    //******************************************************************************************************************
    /**
     * Tests Minion's method to take damage and reduce its health
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
     * Tests Minion's method to attack a MainCharacter, and whether the method returns if the MainCharacter died or not
     */
    @Test
    public void attackCharacterTest() {
        // Should take Minion 3 attacks to kill a MainCharacter with 3 health
        assert(!minion.attackCharacter(MC));
        assert(!minion.attackCharacter(MC));
        assert(minion.attackCharacter(MC));
    }

    /**
     * Tests Minion's method to give appropriate amount of EXP to the MainCharacter that defeated it.
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

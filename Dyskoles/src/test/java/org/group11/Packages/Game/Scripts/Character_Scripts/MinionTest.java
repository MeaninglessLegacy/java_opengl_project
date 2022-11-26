package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.group11.Packages.Core.DataStructures.Vector3;
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
     * Tests Minion's constructor
     */
    @Test
    public void MinionConstructorTest() {
        Vector3 newPos = new Vector3(1, 1, 0);
        Minion minion1 = new Minion(newPos);

        assert(minion1.transform.position.x == newPos.x && minion1.transform.position.y == newPos.y);
        assert(minion1.characterSprite != null);
        assert(minion1.getStatBlock().get_atk() == 1);
        assert(minion1.getStatBlock().get_hp() == 3);
        assert(minion1.getStatBlock().get_maxHp() == 3);
        assert(minion1._healthBarInside != null);
        assert(minion1._healthBarOutline != null);
    }

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

    /**
     * Tests Minion's method to correctly set the direction its attacking for the attack animation
     */
    @Test
    public void attackingDirection() {
        MC.getStatBlock().set_MaxHp(100);
        MC.getStatBlock().set_hp(100);

        MC.transform.position = new Vector3(0,1,0);
        minion.attackCharacter(MC);
        assert(minion.isAttackingDirection.equals("up"));

        MC.transform.position = new Vector3(1,0,0);
        minion.attackCharacter(MC);
        assert(minion.isAttackingDirection.equals("right"));

        MC.transform.position = new Vector3(0,-1,0);
        minion.attackCharacter(MC);
        assert(minion.isAttackingDirection.equals("down"));

        MC.transform.position = new Vector3(-1,0,0);
        minion.attackCharacter(MC);
        assert(minion.isAttackingDirection.equals("left"));
    }
}

package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Game.Scripts.TestSetup;
import org.junit.Test;

/**
 * Runs tests on various methods for the MainCharacter class
 */
public class MainCharacterTest extends TestSetup {
    //******************************************************************************************************************
    //* tests
    //******************************************************************************************************************
    /**
     * Tests MainCharacter's method to add EXP to itself
     */
    @Test
    public void addExpTest() {
        int OriginalExp = MC._statBlock.get_exp();

        MC.addExp(2);
        assert(MC._statBlock.get_exp() == OriginalExp + 2);
    }

    /**
     * Tests MainCharacter's method to level up when enough exp is added to them
     */
    @Test
    public void levelUpTest () {
        int OriginalExp = MC._statBlock.get_exp();
        int OriginalLvl = MC._statBlock.get_lvl();

        // After this, MC should level up and exp should reset
        MC.addExp(MC.EXPPerLevelMultiplier);
        assert(MC._statBlock.get_exp() == 0 && MC._statBlock.get_lvl() == OriginalLvl + 1);

        // MC should not level after this
        MC.addExp(MC.EXPPerLevelMultiplier);
        assert(MC._statBlock.get_exp() == OriginalExp + MC.EXPPerLevelMultiplier && MC._statBlock.get_lvl() == OriginalLvl + 1);
    }

    /**
     * Tests MainCharacter's method to increase its attack
     */
    @Test
    public void addAttackTest() {
        int OriginalAtk = MC._statBlock.get_atk();

        MC.addAttack(1);
        assert(MC._statBlock.get_atk() == OriginalAtk + 1);
    }

    /**
     * Tests MainCharacter's methods to increase max health and health
     */
    @Test
    public void addHealthTest() {
        int OriginalHp = MC._statBlock.get_hp();
        int OriginalMaxHp = MC._statBlock.get_maxHp();

        // Should not affect health value as max health has not increased
        MC.addHealth(1);
        assert(MC._statBlock.get_hp() == OriginalHp);
        // Should increase health value by 1 as max health has increased
        MC.addMaxHealth(1);
        assert(MC._statBlock.get_maxHp() == OriginalMaxHp + 1);
        MC.addHealth(1);
        assert(MC._statBlock.get_hp() == OriginalHp + 1);
    }

    /**
     * Tests MainCharacter's method to take damage and reduce health
     */
    @Test
    public void takeDamageTest() {
        int OriginalHp = MC._statBlock.get_hp();

        MC.takeDamage(1);
        assert(MC._statBlock._hp == OriginalHp - 1);

        // Negative health does not matter for the takeDamage() method
        MC.takeDamage(10);
        assert(MC._statBlock._hp == OriginalHp - 11);
    }

    /**
     * Tests MainCharacter's method to attack an Enemy, and whether the method returns if the Enemy died or not
     */
    @Test
    public void attackEnemyTest() {

        // Should take MainCharacter 10 attacks to kill Boss with 10 health
        for (int i = 0; i < 9; i++) {
            assert(!MC.attackCharacter(boss));
        }
        assert(MC.attackCharacter(boss));
    }

    /**
     * Tests MainCharacter's method to correctly set the direction its attacking for the attack animation
     */
    @Test
    public void attackingDirection() {
        boss.transform.position = new Vector3(0,1,0);
        MC.attackCharacter(boss);
        assert(MC.isAttackingDirection.equals("up"));

        boss.transform.position = new Vector3(1,0,0);
        MC.attackCharacter(boss);
        assert(MC.isAttackingDirection.equals("right"));

        boss.transform.position = new Vector3(0,-1,0);
        MC.attackCharacter(boss);
        assert(MC.isAttackingDirection.equals("down"));

        boss.transform.position = new Vector3(-1,0,0);
        MC.attackCharacter(boss);
        assert(MC.isAttackingDirection.equals("left"));
    }
}

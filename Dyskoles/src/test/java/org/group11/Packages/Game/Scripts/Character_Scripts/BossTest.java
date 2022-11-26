package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Game.Scripts.TestSetup;
import org.junit.Test;

/**
 * Runs tests on various methods for the Boss class
 */
public class BossTest extends TestSetup {
    //******************************************************************************************************************
    //* tests
    //******************************************************************************************************************
    /**
     * Tests Boss's constructor
     */
    @Test
    public void BossConstructorTest() {
        Vector3 newPos = new Vector3(1, 1, 0);
        Boss boss1 = new Boss(newPos);

        assert(boss1.transform.position.x == newPos.x && boss1.transform.position.y == newPos.y);
        assert(boss1.characterSprite != null);
        assert(boss1.getStatBlock().get_atk() == 3);
        assert(boss1.getStatBlock().get_hp() == 10);
        assert(boss1.getStatBlock().get_maxHp() == 10);
        assert(boss1.get_ticksBeforeNextMove() == 3);
        assert(boss1._healthBarInside != null);
        assert(boss1._healthBarOutline != null);
    }

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

    /**
     * Tests Boss's method to correctly set the direction its attacking for the attack animation
     */
    @Test
    public void attackingDirection() {
        MC.getStatBlock().set_MaxHp(100);
        MC.getStatBlock().set_hp(100);

        MC.transform.position = new Vector3(0,1,0);
        boss.attackCharacter(MC);
        assert(boss.isAttackingDirection.equals("up"));

        MC.transform.position = new Vector3(1,0,0);
        boss.attackCharacter(MC);
        assert(boss.isAttackingDirection.equals("right"));

        MC.transform.position = new Vector3(0,-1,0);
        boss.attackCharacter(MC);
        assert(boss.isAttackingDirection.equals("down"));

        MC.transform.position = new Vector3(-1,0,0);
        boss.attackCharacter(MC);
        assert(boss.isAttackingDirection.equals("left"));
    }
}

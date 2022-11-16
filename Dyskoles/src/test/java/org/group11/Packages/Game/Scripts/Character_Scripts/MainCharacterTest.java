package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.junit.Before;
import org.junit.Test;

public class MainCharacterTest {
    MainCharacter MC;

    @Before
    public void setup() {
        MC = new MainCharacter();
    }

    @Test
    public void addExpTest() {
        setup();
        int OriginalExp = MC._statBlock.get_exp();
        int OriginalLvl = MC._statBlock.get_lvl();

        MC.addExp(2);
        assert(MC._statBlock.get_exp() == OriginalExp + 2);

        // After this, MC should level up and exp should reset
        MC.addExp(3);
        assert(MC._statBlock.get_exp() == 0 && MC._statBlock.get_lvl() == OriginalLvl + 1);

        // MC should not level after this
        MC.addExp(5);
        assert(MC._statBlock.get_exp() == OriginalExp + 5 && MC._statBlock.get_lvl() == OriginalLvl + 1);
    }

    @Test
    public void addAttackTest() {
        setup();
        int OriginalAtk = MC._statBlock.get_atk();

        MC.addAttack(1);
        assert(MC._statBlock.get_atk() == OriginalAtk + 1);
    }

    @Test
    public void addHealthTest() {
        setup();
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

    @Test
    public void takeDamageTest() {
        setup();
        int OriginalHp = MC._statBlock.get_hp();

        MC.takeDamage(1);
        assert(MC._statBlock._hp == OriginalHp - 1);
    }

    @Test
    public void attackEnemyTest() {
        setup();
        Minion minion = new Minion();

        // Should take MainCharacter 3 attacks to kill minion
        assert(!MC.attackCharacter(minion));
        assert(!MC.attackCharacter(minion));
        assert(MC.attackCharacter(minion));
    }

    public void testMainCharacter() {
        addExpTest();
        addAttackTest();
        addHealthTest();
        takeDamageTest();
        attackEnemyTest();
    }
}

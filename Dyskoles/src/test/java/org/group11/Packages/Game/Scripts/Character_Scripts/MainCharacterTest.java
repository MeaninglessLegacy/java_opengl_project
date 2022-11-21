package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Engine;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Logic.GameLogicDriver;
import org.junit.Before;
import org.junit.Test;

/**
 * Runs tests on various methods for the MainCharacter class
 */
public class MainCharacterTest {
    boolean everythingInstantiated = false;
    private Engine engine;
    private Scene scene;
    MainCharacter MC;
    Boss boss;

    private class SetupClass extends GameObject {
        @Override
        public void start() {
            // General instantiations
            MC = new MainCharacter();
            scene.Instantiate(MC);

            // attackEnemyTest() and attackingDirection()
            boss = new Boss(new Vector3(0,1,0));
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

        int i = 0;
        while (!everythingInstantiated) {
            i++;
            System.out.println(i);
        }
    }

    /**
     * Tests the function to add exp for a MainCharacter
     */
    @Test
    public void addExpTest() {
        int OriginalExp = MC._statBlock.get_exp();

        MC.addExp(2);
        assert(MC._statBlock.get_exp() == OriginalExp + 2);
    }

    /**
     * Tests the function that increases a MainCharacter's level when enough exp is added to them
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
     * Tests the function to increase attack for a MainCharacter
     */
    @Test
    public void addAttackTest() {
        int OriginalAtk = MC._statBlock.get_atk();

        MC.addAttack(1);
        assert(MC._statBlock.get_atk() == OriginalAtk + 1);
    }

    /**
     * Tests the functions to increase health and max health for a MainCharacter
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
     * Tests the function to reduce a MainCharacter's health
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
     * Tests the whether the MainCharacter properly attacks an Enemy, and if the function properly returns whether the
     * enemy died or not
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
     * Tests if the direction that the MainCharacter is attacking is correctly set when it attacks
     */
    @Test
    public void attackingDirection() {
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

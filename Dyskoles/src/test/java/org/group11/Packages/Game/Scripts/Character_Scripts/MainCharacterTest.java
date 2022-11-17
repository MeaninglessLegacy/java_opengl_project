package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.group11.Packages.Core.Main.Engine;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Logic.GameLogicDriver;
import org.junit.Before;
import org.junit.Test;

/**
 * Runs tests on various methods for the MainCharacter class
 */
public class MainCharacterTest {
    MainCharacter MC;
    private Engine engine;
    private Scene scene;

    @Before
    public void setup() {
        engine = new Engine();
        engine.start();
        scene = Scene.get_scene();
        GameLogicDriver gameLogicDriver = GameLogicDriver.getInstance();
        scene.Instantiate(gameLogicDriver);

        MC = new MainCharacter();
    }

    /**
     * Tests the function to add exp for a MainCharacter
     */
    @Test
    public void addExpTest() {
        setup();
        int OriginalExp = MC._statBlock.get_exp();

        MC.addExp(2);
        assert(MC._statBlock.get_exp() == OriginalExp + 2);
    }

    /**
     * Tests the function that increases a MainCharacter's level when enough exp is adsded to them
     */
    @Test
    public void levelUpTest () {
        setup();
        int OriginalExp = MC._statBlock.get_exp();
        int OriginalLvl = MC._statBlock.get_lvl();

        // After this, MC should level up and exp should reset
        MC.addExp(5);
        assert(MC._statBlock.get_exp() == 0 && MC._statBlock.get_lvl() == OriginalLvl + 1);

        // MC should not level after this
        MC.addExp(5);
        assert(MC._statBlock.get_exp() == OriginalExp + 5 && MC._statBlock.get_lvl() == OriginalLvl + 1);
    }

    /**
     * Tests the function to increase attack for a MainCharacter
     */
    @Test
    public void addAttackTest() {
        setup();
        int OriginalAtk = MC._statBlock.get_atk();

        MC.addAttack(1);
        assert(MC._statBlock.get_atk() == OriginalAtk + 1);
    }

    /**
     * Tests the functions to increase health and max health for a MainCharacter
     */
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

    /**
     * Tests the function to reduce a MainCharacter's health
     */
    @Test
    public void takeDamageTest() {
        setup();
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
        setup();
        Minion minion = new Minion();

        // Should take MainCharacter 3 attacks to kill Minion with 3 health
        assert(!MC.attackCharacter(minion));
        assert(!MC.attackCharacter(minion));
        assert(MC.attackCharacter(minion));
    }
}

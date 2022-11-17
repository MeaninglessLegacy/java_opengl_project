package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.junit.Before;
import org.junit.Test;

/**
 * Runs tests on various methods for the Runner class
 */
public class RunnerTest {
    private Runner runner;

    @Before
    public void setup() {
        runner = new Runner();
    }

    /**
     * Tests that the Runner's method to decrement the amount of ticks until it vanishes works properly, and returns
     * true once that attribute reaches 0 and the Runner needs to disappear
     */
    @Test
    public void decrementTicksUntilVanishTest() {
        setup();
        int totalTicks = 20;
        runner._enemyActive = true;

        for (int i = 0; i < totalTicks - 1; i++) {
            assert (!runner.decrementTicksUntilVanish());
        }
        assert(runner.decrementTicksUntilVanish());
    }

    /**
     * Tests that the Runner gives the MainCharacter that defeated it the proper rewards (various stat increases)
     */
    @Test
    public void giveRewardsTest() {
        setup();
        MainCharacter MC = new MainCharacter();
        int OriginalExp = MC._statBlock.get_exp();
        int OriginalAtk = MC._statBlock.get_atk();
        int OriginalHp = MC._statBlock.get_hp();
        int OriginalMaxHp = MC._statBlock.get_maxHp();

        runner.giveRewards(MC);
        assert(MC._statBlock.get_exp() == OriginalExp + runner.expGiven);
        assert(MC._statBlock.get_atk() == OriginalAtk + runner.atkGiven);
        assert(MC._statBlock.get_hp() == OriginalHp + runner.maxHpGiven);
        assert(MC._statBlock.get_maxHp() == OriginalMaxHp + runner.maxHpGiven);
    }

    @Test
    public void canEnemyMoveTest() {
        // TODO: implement
    }
}

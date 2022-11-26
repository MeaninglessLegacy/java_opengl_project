package org.group11.Packages.Game.Scripts.UI_Scripts;

import org.group11.Packages.Game.Scripts.TestSetup;
import org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdownSprites.MoveCDSprites;
import org.group11.Packages.Game.Scripts.UI_Scripts.StatIncreaseIndicators.AttackIncreaseIndicator;
import org.group11.Packages.Game.Scripts.UI_Scripts.StatIncreaseIndicators.HealthIncreaseIndicator;
import org.junit.Test;

/**
 * Runs tests on various methods for the StatIncreaseIndicator class
 */
public class StatIncreaseIndicatorTest extends TestSetup {
    //******************************************************************************************************************
    //* tests
    //******************************************************************************************************************
    /**
     * Tests StatIncreaseIndicator's constructor
     */
    @Test
    public void StatIncreaseIndicatorConstructorTest() {
        AttackIncreaseIndicator attackIncreaseIndicator1 = new AttackIncreaseIndicator(MC);
        HealthIncreaseIndicator healthIncreaseIndicator1 = new HealthIncreaseIndicator(MC);

        assert(attackIncreaseIndicator1.getSprite() != null);
        assert(!attackIncreaseIndicator1.getSprite().enabled);
        assert(attackIncreaseIndicator1.transform == MC.transform);
        assert(healthIncreaseIndicator1.getSprite() != null);
        assert(!healthIncreaseIndicator1.getSprite().enabled);
        assert(healthIncreaseIndicator1.transform == MC.transform);
    }

    /**
     * Tests AttackIncreaseIndicator's method to activate and display the sprite, then disable the sprite after 2
     * seconds
     */
    @Test
    public void AttackIncreaseIndicatorActivateTest() {
        long curTime = System.currentTimeMillis();
        int timeBeforeDisappear = 2000;

        attackIncreaseIndicator.activate();
        assert(attackIncreaseIndicator.getSprite().enabled);

        boolean spriteShouldBeActivated = true;
        while (spriteShouldBeActivated) {
            if (System.currentTimeMillis() - curTime > timeBeforeDisappear + 100) {
                spriteShouldBeActivated = false;
            }
        }

        assert(!attackIncreaseIndicator.getSprite().enabled);
    }

    /**
     * Tests HealthIncreaseIndicator's method to activate and display the sprite, then disable the sprite after 2
     * seconds
     */
    @Test
    public void HealthIncreaseIndicatorActivateTest() {
        long curTime = System.currentTimeMillis();
        int timeBeforeDisappear = 2000;

        healthIncreaseIndicator.activate();
        assert(healthIncreaseIndicator.getSprite().enabled);

        boolean spriteShouldBeActivated = true;
        while (spriteShouldBeActivated) {
            if (System.currentTimeMillis() - curTime > timeBeforeDisappear + 100) {
                spriteShouldBeActivated = false;
            }
        }

        assert(!healthIncreaseIndicator.getSprite().enabled);
    }
}

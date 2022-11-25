package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Game.Scripts.TestSetup;
import org.junit.Test;

/**
 * Runs tests on various methods for the RegenHeart class
 */
public class RegenHeartTest extends TestSetup {
    //******************************************************************************************************************
    //* tests
    //******************************************************************************************************************
    /**
     * Tests RegenHeart's method to activate when the activating MainCharacter is at full health
     */
    @Test
    public void fullHpActivate() {
        assert(!regenHeart.activate(MC));
    }

    /**
     * Tests RegenHeart's method to activate when the activating MainCharacter is not at full health
     */
    @Test
    public void notFullHpActivate() {
        MC.takeDamage(1);

        assert(regenHeart.activate(MC));
    }
}

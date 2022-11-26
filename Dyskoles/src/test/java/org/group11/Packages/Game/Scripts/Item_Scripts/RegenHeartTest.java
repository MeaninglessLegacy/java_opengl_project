package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Core.DataStructures.Vector3;
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
     * Tests RegenHeart's constructor
     */
    @Test
    public void RegenHeartConstructorTest() {
        Vector3 newPos = new Vector3(1, 1, 0);
        RegenHeart regenHeart1 = new RegenHeart(newPos);

        assert(regenHeart1.transform.position.x == newPos.x && regenHeart1.transform.position.y == newPos.y);
        assert(regenHeart1.spriteRenderer != null);
    }

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

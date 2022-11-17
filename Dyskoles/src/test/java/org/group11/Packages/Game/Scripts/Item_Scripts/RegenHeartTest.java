package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;
import org.junit.Before;
import org.junit.Test;

/**
 * Runs tests on various methods for the RegenHeart class
 */
public class RegenHeartTest {
    private RegenHeart regenHeart;
    private MainCharacter MC;

    @Before
    public void setup() {
        MC = new MainCharacter();
        regenHeart = new RegenHeart();
    }

    /**
     * Tests if a RegenHeart is not activated when the activating Character has full health
     */
    @Test
    public void fullHpActivate() {
        assert(!regenHeart.activate(MC));
    }

    /**
     * Tests if a RegenHeart is activated when the activating Character doesn't have full health
     */
    @Test
    public void notFullHpActivate() {
        MC.takeDamage(1);

        assert(regenHeart.activate(MC));
    }
}

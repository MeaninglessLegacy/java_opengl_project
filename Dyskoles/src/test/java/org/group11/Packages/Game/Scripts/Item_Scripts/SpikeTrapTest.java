package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Game.Scripts.TestSetup;
import org.junit.Test;

/**
 * Runs tests on various methods for the SpikeTrap class
 */
public class SpikeTrapTest extends TestSetup {
    //******************************************************************************************************************
    //* tests
    //******************************************************************************************************************
    /**
     * Tests SpikeTrap's method to activate and damage the MainCharacter that activated it
     */
    @Test
    public void activateTest() {
        int originalHp = MC.getStatBlock().get_hp();

        assert(spikeTrap.activate(MC));
        assert(MC.getStatBlock().get_hp() == originalHp - spikeTrap.get_spikeTrapDamage());
    }
}

package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;
import org.junit.Before;
import org.junit.Test;

/**
 * Runs tests on various methods for the SpikeTrap class
 */
public class SpikeTrapTest {
    private SpikeTrap spikeTrap;
    private MainCharacter MC;

    @Before
    public void setup() {
        spikeTrap = new SpikeTrap();
        MC = new MainCharacter();
    }

    /**
     * Tests if the Character who activates a SpikeTrap has their health reduced by the amount of damage the SpikeTrap
     * does
     */
    @Test
    public void activateTest() {
        int originalHp = MC.getStatBlock().get_hp();

        assert(spikeTrap.activate(MC));
        assert(MC.getStatBlock().get_hp() == originalHp - spikeTrap.get_spikeTrapDamage());
    }
}

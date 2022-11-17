package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;
import org.junit.Before;
import org.junit.Test;

/**
 * Runs tests on various methods for the Key class
 */
public class KeyTest {
    private Key key;
    private MainCharacter MC;

    @Before
    public void setup() {
        key = new Key();
        MainCharacter MC = new MainCharacter();
    }

    /**
     * Tests whether the key is picked up when the sprite is disabled and the key should not be obtainable
     */
    @Test
    public void disabledActivateTest() {
        Backpack backpack = new Backpack();

        assert(!key.activate(MC));
        assert(MC.backpack == backpack);
    }

    /**
     * Tests whether the key is picked up when the sprite is enabled and the key should be obtainable
     */
    @Test
    public void enabledActivateTest() {
        Backpack backpack = new Backpack();
        backpack.addItem(key);

        assert(key.activate(MC));
        assert(MC.backpack == backpack);
    }
}

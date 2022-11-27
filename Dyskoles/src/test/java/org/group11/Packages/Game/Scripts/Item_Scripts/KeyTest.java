package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Game.Scripts.TestSetup;
import org.junit.Test;

/**
 * Runs tests on various methods for the Key class
 */
public class KeyTest extends TestSetup {
    //******************************************************************************************************************
    //* tests
    //******************************************************************************************************************
    /**
     * Tests Key's constructor
     */
    @Test
    public void KeyConstructorTest() {
        Vector3 newPos = new Vector3(1, 1, 0);
        Key key1 = new Key(newPos);

        assert(key1.transform.position.x == newPos.x && key1.transform.position.y == newPos.y);
        assert(key1.spriteRenderer != null);
    }

    /**
     * Tests Key's method to enable and disable its sprite
     */
    @Test
    public void KeyToggleTest() {
        assert(!key.getKeyVisibility());

        key.setKeyVisibility(true);
        assert(key.getKeyVisibility());
    }

    /**
     * Tests Key's method to activate when its sprite is disabled and should not be obtainable
     */
    @Test
    public void disabledActivateTest() {
        assert(!key.activate(MC));
        assert(MC.backpack._inventory.size() == 0);
    }

    /**
     * Tests Key's method to activate when the sprite is enabled and the key should be obtainable
     */
    @Test
    public void enabledActivateTest() {
        key.setKeyVisibility(true);

        assert(key.activate(MC));
        assert(MC.backpack._inventory.size() == 1);
        assert(MC.backpack._inventory.get(0) instanceof Key);
    }
}

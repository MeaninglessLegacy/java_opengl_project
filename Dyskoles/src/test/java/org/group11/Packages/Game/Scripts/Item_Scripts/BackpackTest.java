package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Game.Scripts.TestSetup;
import org.junit.Test;

/**
 * Runs tests on various methods for the Backpack class
 */
public class BackpackTest extends TestSetup {
    //******************************************************************************************************************
    //* tests
    //******************************************************************************************************************
    /**
     * Tests Backpack's method to add an item to its inventory
     */
    @Test
    public void addItemTest() {
        backpack.addItem(key);

        assert(backpack.getItems().size() == 1);
        assert(backpack.getItems().get(0) == key);
    }

    /**
     * Tests Backpack's method to remove an item to its inventory
     */
    @Test
    public void removeItemTest() {
        backpack.addItem(key);

        assert(backpack.removeItem(key));
        assert(backpack.getItems().size() == 0);
    }
}

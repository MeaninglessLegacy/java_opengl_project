package org.group11.Packages.Game.Scripts.Item_Scripts;

import java.util.ArrayList;

public class Backpack {

    protected ArrayList<Item> _inventory;

    public ArrayList<Item> getItems() { return this._inventory; }

    /**
     * Adds an item to the backpack
     *
     * @param item item to add to backpack
     */
    public void addItem (Item item) { _inventory.add(item); }

    /**
     * Given an item, removes that type of item from the backpack if there is one
     *
     * @param item item to remove from backpack
     * @return true if given item is found and removed, false if given item cannot be found
     */
    public boolean removeItem (Item item) {
        int i = 0;
        while (i < _inventory.size()) {
            if (_inventory.get(i) == item) {
                _inventory.remove(i);
                _inventory.add(_inventory.get(_inventory.size() - 1));
                _inventory.remove(_inventory.size() - 1);
                return true;
            }
        }
        return false;
    }
}

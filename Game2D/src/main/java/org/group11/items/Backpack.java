package org.group11.items;

import java.util.ArrayList;

public class Backpack {

    protected ArrayList<Item> _inventory;

    public ArrayList<Item> getItems() {
        return this._inventory;
    }

    public void addItem (Item item) {
        _inventory.add(item);
    }

    public void removeItem (Item item) {
        boolean searching = true;
        int i = 0;
        while (i < _inventory.size() && searching) {
            if (_inventory.get(i) == item) {
                _inventory.remove(i);
                searching = false;
            }
        }
    }
}

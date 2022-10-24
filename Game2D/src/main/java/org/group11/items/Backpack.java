package org.group11.items;

import java.util.ArrayList;

public class Backpack {

    protected ArrayList<Item> inventory;

    public ArrayList<Item> getItems() {
        return this.inventory;
    }

    public void addItem (Item item) {
        inventory.add(item);
    }

    public void removeItem (Item item) {
        boolean searching = true;
        int i = 0;
        while (i < inventory.size() && searching) {
            if (inventory.get(i) == item) {
                inventory.remove(i);
                searching = false;
            }
        }
    }
}

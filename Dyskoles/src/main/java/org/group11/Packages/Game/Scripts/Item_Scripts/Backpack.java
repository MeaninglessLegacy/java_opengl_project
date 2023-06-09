package org.group11.Packages.Game.Scripts.Item_Scripts;

import java.util.ArrayList;

/**
 * Backpack object for Characters, stores item objects
 */
public class Backpack {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    protected ArrayList<Item> _inventory = new ArrayList<>();

    //******************************************************************************************************************
    //* constructor methods
    //******************************************************************************************************************
    public Backpack() {

    }

    //******************************************************************************************************************
    //* setters and getters
    //******************************************************************************************************************
    public ArrayList<Item> getItems() { return this._inventory; }

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    /**
     * Adds an item to the backpack
     * @param item item to add to backpack
     */
    public void addItem (Item item) { _inventory.add(item); }

    /**
     * Given an item, removes that type of item from the backpack if there is one
     * @param item item to remove from backpack
     * @return true if given item is found and removed, false if given item cannot be found
     */
    public boolean removeItem (Item item) {
        int i = 0;
        while (i < _inventory.size()) {
            if (_inventory.get(i).getClass().getName().equals(item.getClass().getName())) {
                System.out.println("removing item from backpack");
                _inventory.remove(item);
                return true;
            }
            i++;
        }
        return false;
    }
}

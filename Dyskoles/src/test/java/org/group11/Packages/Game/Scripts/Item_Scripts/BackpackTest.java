package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class BackpackTest {
    Backpack backpack;

    @Before
    public void setup() {
       backpack = new Backpack();
    }

    @Test
    public void addItemTest() {
        Key key = new Key();
        backpack.addItem(key);
        ArrayList<Item> itemList = new ArrayList<>();
        itemList.add(key);

        assert(itemList == backpack.getItems());
    }

    @Test
    public void removeItemTest() {
        Key key = new Key();
        backpack.addItem(key);
        ArrayList<Item> itemList = new ArrayList<>();

        assert(backpack.removeItem(key));
        assert(itemList == backpack.getItems());
    }
}

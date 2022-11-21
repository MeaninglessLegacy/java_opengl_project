package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Core.Main.Engine;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class BackpackTest {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    boolean everythingInstantiated = false;
    private Engine engine;
    private Scene scene;

    private Backpack backpack;
    private Key key;

    //******************************************************************************************************************
    //* setup
    //******************************************************************************************************************
    private class SetupClass extends GameObject {
        @Override
        public void start() {
            // General instantiations
            backpack = new Backpack();

            key = new Key();
            scene.Instantiate(key);

            everythingInstantiated = true;
        }
    }

    @Before
    public void setup() {
        engine = new Engine();
        engine.start();
        scene = Scene.get_scene();

        SetupClass setupClass = new SetupClass();
        scene.Instantiate(setupClass);

        while (!everythingInstantiated) {
            System.out.print("");
        }
    }

    //******************************************************************************************************************
    //* tests
    //******************************************************************************************************************
    @Test
    public void addItemTest() {
        backpack.addItem(key);
        ArrayList<Item> itemList = new ArrayList<>();
        itemList.add(key);

        assert(itemList == backpack.getItems());
    }

    @Test
    public void removeItemTest() {
        backpack.addItem(key);
        ArrayList<Item> itemList = new ArrayList<>();

        assert(backpack.removeItem(key));
        assert(itemList == backpack.getItems());
    }
}

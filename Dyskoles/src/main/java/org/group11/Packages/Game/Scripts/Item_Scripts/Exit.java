package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;

import static org.group11.Packages.Game.Scripts.Logic.GameLogicDriver.endGame;

/**
 * Exit object, when the player character runs into this object with a key they complete the level
 */
public class Exit extends Item {
    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    public void start() {
        // TODO: implement method
        // Gets all sprites for object
        // Calls constructor
    }

    public void update() {
        // TODO: implement method
        // changes colour when exit is accessible
    }

    @Override
    public boolean activate(MainCharacter c) {
        // Unsure if this will work
        Key testKey = new Key();
        if (c.backpack.removeItem(testKey)) {
            testKey = null;
            endGame(true);
        }
        return false;
    }
}

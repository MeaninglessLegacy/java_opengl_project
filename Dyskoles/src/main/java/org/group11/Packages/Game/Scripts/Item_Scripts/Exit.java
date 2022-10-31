package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Engine.Vector3;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;

import static org.group11.Packages.Game.Scripts.Logic.GameLogicDriver.endGame;

/**
 * Exit object, when the player character runs into this object with a key they complete the level
 */
public class Exit extends Item {
    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public Exit() {
        _position = new Vector3(400, 400, 0);
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public boolean activate(MainCharacter c) {
        Key testKey = new Key();
        if (c.backpack.removeItem(testKey)) {
            testKey = null;
            endGame(true);
        }
        return false;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void update() {
        super.update();
    }
}

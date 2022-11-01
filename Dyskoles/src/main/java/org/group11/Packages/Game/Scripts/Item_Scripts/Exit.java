package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;

import static org.group11.Packages.Game.Scripts.Logic.GameLogicDriver.endGame;

/**
 * Exit object, when the player character runs into this object with a key they complete the level
 */
public class Exit extends Item {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    private SpriteRenderer spriteRenderer;

    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public Exit() {

    }


    public Exit(Vector3 pos) {
        transform.setPosition(pos);
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public boolean activate(MainCharacter c) {
        Key testKey = new Key(new Vector3(1000, 1000, 0));
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

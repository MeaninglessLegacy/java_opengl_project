package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;

/**
 * Key item, allows players with a key item to proceed to the next level of the game
 */
public class Key extends Item{
    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public Key() {
        transform.position = new Vector3(400, 400, 0);
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public boolean activate(MainCharacter c) {
        Key invKey = new Key();
        c.backpack.addItem(invKey);
        return true;
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

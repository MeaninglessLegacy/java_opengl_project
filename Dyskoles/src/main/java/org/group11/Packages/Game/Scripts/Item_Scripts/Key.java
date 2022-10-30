package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;

/**
 * Key item, allows players with a key item to proceed to the next level of the game
 */
public class Key extends Item{
    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    public boolean activate(MainCharacter c) {
        c.backpack.addItem(this);
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

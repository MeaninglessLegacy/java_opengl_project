package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Engine.GameObject;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;

/**
 * Abstract item class, items can be activated
 */
public abstract class Item extends GameObject {
    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************

    /**
     * When a MainCharacter is on the same position as an item, that character activates the item's effect
     * @param c the MainCharacter who triggered the item
     * @return true if the Item needs to disappear off the map, false if it doesn't
     */
    public boolean activate(MainCharacter c){
        return false;
    }
}

package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Engine.GameObject;
import org.group11.Packages.Engine.Vector3;

/**
 * Abstract item class, items can be activated
 */
public abstract class Item extends GameObject {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    // stores the position of this item
    protected Vector3 _position;
    /**
     * Returns the Vector3 position of this item object
     * @return the Vector3 to return
     */
    public Vector3 get_position() { return this._position; }
    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    public void activate(){
        return;
    }
}

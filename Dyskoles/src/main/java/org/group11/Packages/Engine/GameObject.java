package org.group11.Packages.Engine;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Engine's abstract GameObject class, GameObjects exist within the scene of the game and are operated on by the engine
 */
abstract public class GameObject {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************

    //Transform of this GameObject in it's corresponding scene's world space
    public Transform transform;

    // Components of this GameObject which the engine performs additional operations on
    Dictionary<String, Component> Components = new Hashtable<>();

    //******************************************************************************************************************
    //* engine calls
    //******************************************************************************************************************

    /**
     * Method is called by the game engine on every frame
     */
    public void update() {
        return;
    }

    /**
     * Method is called when the GameObject is first created
     */
    public void start() {
        return;
    }
    //******************************************************************************************************************
    //* event listeners
    //******************************************************************************************************************

    /**
     * Called when event listener records a button being pressed
     * @param key ascii value of the key
     */
    public void onButtonDown(int key) {
        return;
    }

    /**
     * Called when event listener records a button being released
     * @param key ascii value of the key pressed
     */
    public void onButtonUp(int key){
        return;
    }

}

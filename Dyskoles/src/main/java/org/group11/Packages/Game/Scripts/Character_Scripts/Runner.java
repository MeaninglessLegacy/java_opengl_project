package org.group11.Packages.Game.Scripts.Character_Scripts;

/**
 * Special enemy class, Runner runs away from the player when activated
 */
public class Runner extends Enemy{
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    private int _ticksUntilVanish;

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************

    /**
     * Initialize all the values of the Runner character type
     */
    public void start() {
        // TODO: implement method
        _moveTowards = "awayFromPlayer";
        // Gets all sprites for object
        // Calls constructor and sets any necessary attributes
    }

    public void update() {
        // TODO: implement method
        // If character moved, character faces direction they moved
        // Idle animation
    }
}

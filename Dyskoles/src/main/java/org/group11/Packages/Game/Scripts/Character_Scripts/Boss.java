package org.group11.Packages.Game.Scripts.Character_Scripts;

/**
 * Boss enemy type character, chases the player blindly when activated
 */
public class Boss extends Enemy {
    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************

    /**
     * Initialize all the values of the Boss character type
     */
    @Override
    public void start() {
        expGiven = 5;
        // TODO: implement method
        // Gets all sprites for object
        // Calls constructor and sets any necessary attributes
    }

    @Override
    public void update() {
        // TODO: implement method
        // If character moved, character faces direction they moved
        // Idle animation
    }
}
package org.group11.Packages.Game.Scripts.Character_Scripts;

/**
 * Basic enemy type character, Minion chases the player blindly when activated
 */
public class Minion extends Enemy{
    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************

    /**
     * Initialize all the values of the Minion character type
     */
    public void start() {
        _statBlock.set_Atk(1);
        _statBlock.set_MaxHp(3);
        _statBlock.set_hp(3);
        // TODO: implement method
        // Gets all sprites for object
        // Calls constructor and sets any necessary attributes
    }

    public void update() {
        // TODO: implement method
        // If character moved, character faces direction they moved
        // Idle animation
    }
}

package org.group11.Packages.Game.Scripts.Character_Scripts;

/**
 * Special enemy class, Runner runs away from the player when activated
 */
public class Runner extends Enemy{
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    // How many ticks before this Runner will disappear from the game
    private int _ticksUntilVanish;
    // this integer tells the game how much max health will be given to the Character who kills this Enemy
    public int maxHpGiven;
    // this integer tells the game how much attack will be given to the Character who kills this Enemy
    public int atkGiven;

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************

    /**
     * Initialize all the values of the Runner character type
     */
    public void start() {
        // TODO: implement method
        expGiven = 5;
        maxHpGiven = 1;
        atkGiven = 1;
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

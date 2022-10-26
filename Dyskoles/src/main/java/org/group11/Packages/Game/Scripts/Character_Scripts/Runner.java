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
    public void start() {
        // TODO: implement method
        // Gets all sprites for object
        // Calls constructor and sets any necessary attributes
    }

    public void update() {
        // TODO: implement method
        // If character moved, character faces direction they moved
        // Idle animation
    }

    public void doEnemyLogic() {
        // TODO: implement method
        // Runs away from main character
    }

    //******************************************************************************************************************
    //* listeners
    //******************************************************************************************************************
    public void onButtonDown(int key) {
        if (_enemyActive) {
            this.doEnemyLogic();
        }
    }
}

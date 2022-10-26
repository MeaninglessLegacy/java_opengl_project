package org.group11.Packages.Game.Scripts.Character_Scripts;

public class Runner extends Enemy{

    private int _ticksUntilVanish;

    public void initialize() {
        // TODO: implement method
        // Gets all sprites for object
        // Calls constructor and sets any necessary attributes
    }

    public void update() {
        // TODO: implement method
        // If character moved, character faces direction they moved
        // Idle animation
    }

    public void onButtonPressed() {
        if (_enemyActive) { this.doEnemyLogic(); }
    }

    public void doEnemyLogic() {
        // TODO: implement method
        // Runs away from main character
    }
}

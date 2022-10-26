package org.group11.characters;

public class Boss extends Enemy {

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

    public void onButtonPressed(int button) {
        if (_enemyActive) { this.doEnemyLogic(); }
    }
}
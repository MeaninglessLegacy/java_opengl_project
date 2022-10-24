package org.group11.characters;

public class MainCharacter extends Character{

    public MainCharacter() {
        statBlock.setMaxHp(3);
        statBlock.setHp(3);
        statBlock.setAtk(1);
    }

    public void addExp(int exp) {
        statBlock.setExp(statBlock.getExp() + exp);
    }

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
}

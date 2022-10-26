package org.group11.characters;

import org.group11.items.Backpack;

public class MainCharacter extends Character{

    public Backpack backpack;

    public MainCharacter() {
        _statBlock.setMaxHp(3);
        _statBlock.setHp(3);
        _statBlock.setAtk(1);
    }

    public void addExp(int exp) {
        _statBlock.setExp(_statBlock.getExp() + exp);
        // TODO: need to calculate when to add a level and add the appropriate attack/health
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

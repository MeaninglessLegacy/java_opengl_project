package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.group11.Packages.Game.Scripts.Item_Scripts.Backpack;

/**
 * Player character class
 */
public class MainCharacter extends Character{
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    public Backpack backpack;

    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public MainCharacter() {
        _statBlock.setMaxHp(3);
        _statBlock.set_hp(3);
        _statBlock.setAtk(1);
    }

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    public void addExp(int exp) {
        _statBlock.set_exp(_statBlock.get_exp() + exp);
        // TODO: need to calculate when to add a level and add the appropriate attack/health
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    public void start() {
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

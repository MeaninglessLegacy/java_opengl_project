package org.group11.Packages.Game.Scripts.Character_Scripts;

/**
 * Boss enemy type character, chases the player blindly when activated
 */
public class Boss extends Enemy {
    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public Boss() {
        expGiven = 5;
        _statBlock.set_Atk(3);
        _statBlock.set_MaxHp(10);
        _statBlock.set_hp(10);
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void start() {
        super.start();
    }

    @Override
    public void update() {
        // TODO: implement method
        // If character moved, character faces direction they moved
        // Idle animation
        return;
    }

    /**
     * Might change: call logic of enemy
     * @param key ascii value of the key
     */
    @Override
    public void onKeyDown(int key) {
        super.update();
    }
}
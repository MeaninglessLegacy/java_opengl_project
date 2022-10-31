package org.group11.Packages.Game.Scripts.Character_Scripts;

/**
 * Basic enemy type character, Minion chases the player blindly when activated
 */
public class Minion extends Enemy{
    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public Minion() {
        _statBlock.set_Atk(1);
        _statBlock.set_MaxHp(3);
        _statBlock.set_hp(3);
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
        super.update();
    }
}

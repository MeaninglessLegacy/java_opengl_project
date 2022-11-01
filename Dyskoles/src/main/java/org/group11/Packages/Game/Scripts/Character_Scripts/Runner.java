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
    //* constructor
    //******************************************************************************************************************
    public Runner() {
        expGiven = 5;
        maxHpGiven = 1;
        atkGiven = 1;
        _moveTowards = "awayFromPlayer";
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void giveRewards(MainCharacter MC) {
        MC.addExp(expGiven);
        MC.addAttack(atkGiven);
        MC.addMaxHealth(maxHpGiven);
        MC.addHealth(maxHpGiven);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void update() {
        super.update();
    }
}

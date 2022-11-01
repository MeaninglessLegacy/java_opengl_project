package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.group11.Packages.Core.Main.GameObject;

/**
 * Base class for character objects within our game
 */
public abstract class Character extends GameObject {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    // stores all the stats of this character
    protected StatBlock _statBlock = new StatBlock();
    /**
     * Returns the StatBlock of this character object
     * @return the StatBlock to return
     */
    public StatBlock getStatBlock() { return this._statBlock; }

    //******************************************************************************************************************
    //* character methods
    //******************************************************************************************************************
    /**
     * Reduces the hp value of this character by specified amount
     * @param hp value to reduce hp by
     */
    public void takeDamage(int hp) {
        _statBlock.set_hp(_statBlock.get_hp() - hp);
    }

    /**
     * Increases the hp value of this character by specified amount
     * @param hp value to increase hp by
     */
    public void addHealth(int hp) { _statBlock.set_hp(_statBlock.get_hp() + hp); }

    /**
     * Increases the max hp value of this character by specified amount
     * @param maxHp value to increase max hp by
     */
    public void addMaxHealth(int maxHp) { _statBlock.set_MaxHp(_statBlock.get_maxHp() + maxHp); }

    /**
     * Increases the attack value of this character by specified amount
     * @param atk value to increase attack by
     */
    public void addAttack(int atk) { _statBlock.set_Atk(_statBlock.get_atk() + atk); }

    /**
     * Calling Character attacks the parameter Character, reducing the parameter Character's health. Returns true if the
     * parameter Character's health was reduced to 0 or below, false if it hasn't
     * @param defender Character whose health is being reduced
     * @return true if defender's health is reduced to 0 or below, false if not
     */
    public boolean attackCharacter(Character defender) {
        defender.takeDamage(this._statBlock.get_atk());
        if (defender.getStatBlock().get_hp() <= 0) {
            return true;
        }
        return false;
    }
}

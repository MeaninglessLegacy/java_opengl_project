package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.group11.Packages.Engine.GameObject;
import org.group11.Packages.Engine.Vector3;

/**
 * Base class for character objects within our game
 */
public abstract class Character extends GameObject {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    // stores all the stats of this character
    protected StatBlock _statBlock;
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
        if (_statBlock.get_hp() <= 0) {
            // TODO: character dies
        }
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
     * Moves the character to the coordinates specified by the parameter
     * @param nPos the position to which the character moves to
     */
    public void moveTo(Vector3 nPos) {
        // TODO: implement method
    }
}

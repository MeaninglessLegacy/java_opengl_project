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
    protected StatBlock _statBlock = new StatBlock();
    // stores the position of this character
    protected Vector3 _position;
    /**
     * Returns the StatBlock of this character object
     * @return the StatBlock to return
     */
    public StatBlock getStatBlock() { return this._statBlock; }
    /**
     * Returns the Vector3 position of this character object
     * @return the Vector3 to return
     */
    public Vector3 get_position() { return this._position; }
    /**
     * sets _position to pos
     * @param pos the position to set _position to
     */
    public void set_position(Vector3 pos) { this._position = pos;}

    //******************************************************************************************************************
    //* character methods
    //******************************************************************************************************************
    /**
     * Reduces the hp value of this character by specified amount
     * @param hp value to reduce hp by
     */
    public void takeDamage(int hp) { _statBlock.set_hp(_statBlock.get_hp() - hp); }

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
}

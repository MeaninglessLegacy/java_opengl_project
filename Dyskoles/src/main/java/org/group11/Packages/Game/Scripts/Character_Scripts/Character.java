package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.group11.gameEngine.GameObject;
import org.group11.gameEngine.Vector3;

import java.awt.image.BufferedImage;

public abstract class Character extends GameObject {

    protected StatBlock _statBlock;

    public StatBlock getStatBlock() { return this._statBlock; }

    /**
     * Methods to reduce health, add health, and add attack to a character's statBlock
     */
    public void takeDamage(int hp) { _statBlock.setHp(_statBlock.getHp() - hp); }
    public void addHealth(int hp) { _statBlock.setHp(_statBlock.getHp() + hp); }
    public void addAttack(int atk) { _statBlock.setAtk(_statBlock.getAtk() + atk); }

    /**
     * Moves the character to the coordinates specified by the parameter
     *
     * @param nPos the position to which the character moves to
     */
    public void moveTo(Vector3 nPos) {
        // TODO: implement method
    }
}

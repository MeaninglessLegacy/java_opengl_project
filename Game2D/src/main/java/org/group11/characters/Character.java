package org.group11.characters;

import org.group11.gameEngine.GameObject;
import org.group11.gameEngine.Vector3;

import java.awt.image.BufferedImage;

public abstract class Character extends GameObject {

    protected StatBlock statBlock;

    public StatBlock getStatBlock() { return this.statBlock; }

    /**
     * Methods to reduce health, add health, and add attack to a character's statBlock
     */
    public void takeDamage(int hp) { statBlock.setHp(statBlock.getHp() - hp); }
    public void addHealth(int hp) { statBlock.setHp(statBlock.getHp() + hp); }
    public void addAttack(int atk) { statBlock.setAtk(statBlock.getAtk() + atk); }

    /**
     * Moves the character to the coordinates specified by the parameter
     *
     * @param nPos the position to which the character moves to
     */
    public void moveTo(Vector3 nPos) {
        // TODO: implement method
    }
}

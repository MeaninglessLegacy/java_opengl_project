package org.group11.characters;

import org.group11.gameEngine.GameObject;
import org.group11.gameEngine.Vector3;

import java.awt.image.BufferedImage;

public abstract class Character extends GameObject {

    protected StatBlock statBlock;
    public BufferedImage up, down, left, right;

    public StatBlock getStatBlock() { return this.statBlock; }

    public void takeDamage(int hp) {
        statBlock.setHp(statBlock.getHp() - hp);
    }

    public void moveTo(Vector3 nPos) {
        // TODO implement method
    }
}

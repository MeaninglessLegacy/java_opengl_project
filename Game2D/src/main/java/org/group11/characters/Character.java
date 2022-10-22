package org.group11.characters;

import org.group11.gameEngine.GameObject;

public abstract class Character extends GameObject {
    StatBlock statBlock;

    public StatBlock getStatBlock() { return this.statBlock; }
}

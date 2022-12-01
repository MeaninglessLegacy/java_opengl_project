package org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdownSprites;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Game.Scripts.Character_Scripts.Enemy;

/**
 * Sprite that displays the moveCDSprite 0 (enemy will move on the next tick)
 */
public class MoveCD0Sprite extends MoveCDSprites {
    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public MoveCD0Sprite(Enemy enemy) {
        MoveCDSprite = new SpriteRenderer(this, "./Resources/countdown0.png");
        this.addComponent(MoveCDSprite);

        // Binds this MoveCountdown's position to the enemy it's for
        this.transform = enemy.transform;
    }
}


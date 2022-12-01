package org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdownSprites;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Game.Scripts.Character_Scripts.Enemy;

/**
 * Sprite that displays the moveCDSprite 2 (enemy will move in 3 more ticks)
 */
public class MoveCD2Sprite extends MoveCDSprites {
    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public MoveCD2Sprite(Enemy enemy) {
        MoveCDSprite = new SpriteRenderer(this, "./Resources/countdown2.png");
        this.addComponent(MoveCDSprite);

        // Binds this MoveCountdown's position to the enemy it's for
        this.transform = enemy.transform;
    }
}

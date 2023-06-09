package org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdownSprites;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Game.Scripts.Character_Scripts.Enemy;

/**
 * Sprite that displays the moveCDSprite 3 (enemy will move in 4 more tick)
 */
public class MoveCD3Sprite extends MoveCDSprites {
    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public MoveCD3Sprite(Enemy enemy) {
        MoveCDSprite = new SpriteRenderer(this, "./Resources/countdown3.png");
        this.addComponent(MoveCDSprite);

        // Binds this MoveCountdown's position to the enemy it's for
        this.transform = enemy.transform;
    }
}

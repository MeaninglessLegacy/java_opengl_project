package org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdownSprites;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Game.Scripts.Character_Scripts.Enemy;

public class MoveCD1Sprite extends MoveCDSprites {
    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public MoveCD1Sprite(Enemy enemy) {
        MoveCDSprite = new SpriteRenderer(this, "./Resources/countdown1.png");
        this.addComponent(MoveCDSprite);

        // Binds this MoveCountdown's position to the enemy it's for
        this.transform = enemy.transform;
    }
}


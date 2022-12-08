package org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.PressSpaceSprites;

import org.group11.Packages.Core.Components.SpriteRenderer;

public class SpaceRetry extends BlinkingPressSpaceSprites {
    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public SpaceRetry() {
        spriteRenderer = new SpriteRenderer(this, "./resources/SpaceRetry.png");
        this.addComponent(spriteRenderer);

        setupSprite();
    }
}

package org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.PressSpaceSprites;

import org.group11.Packages.Core.Components.SpriteRenderer;

public class SpaceStartGame extends BlinkingPressSpaceSprites {
    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public SpaceStartGame() {
        spriteRenderer = new SpriteRenderer(this, "./resources/SpaceStartGame.png");
        this.addComponent(spriteRenderer);

        setupSprite();
    }
}

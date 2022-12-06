package org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.PressSpaceSprites;

import org.group11.Packages.Core.Components.SpriteRenderer;

public class SpaceNextLevel extends BlinkingPressSpaceSprites{
    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public SpaceNextLevel() {
        spriteRenderer = new SpriteRenderer(this, "./resources/SpaceNextLevel.png");
        this.addComponent(spriteRenderer);

        setupSprite();
    }
}

package org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.PressSpaceSprites;

import org.group11.Packages.Core.Components.SpriteRenderer;

public class SpaceStartOver extends BlinkingPressSpaceSprites {
    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public SpaceStartOver() {
        spriteRenderer = new SpriteRenderer(this, "./resources/SpaceStartOver.png");
        this.addComponent(spriteRenderer);

        setupSprite();
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void start() { super.start(); }

    @Override
    public void update() { super.update(); }
}

package org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.PressSpaceSprites;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;

public class SpaceStartGame extends BlinkingPressSpaceSprites {
    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public SpaceStartGame() {
        spriteRenderer = new SpriteRenderer(this, "./resources/SpaceStartGame.png");
        this.addComponent(spriteRenderer);

        // Sets the sprite at a specified position on the menu
        this.transform.position = new Vector3(0, 0, 0);

        spriteRenderer.get_sprite().set_scale(10, 8,0);
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void start() { super.start(); }

    @Override
    public void update() { super.update(); }
}

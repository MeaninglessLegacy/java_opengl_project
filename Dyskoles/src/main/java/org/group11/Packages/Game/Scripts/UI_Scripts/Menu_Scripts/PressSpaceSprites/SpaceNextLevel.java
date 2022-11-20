package org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.PressSpaceSprites;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;

public class SpaceNextLevel extends BlinkingPressSpaceSprites{
    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public SpaceNextLevel() {
        spriteRenderer = new SpriteRenderer(this, "./resources/SpaceNextLevel.png");
        this.addComponent(spriteRenderer);

        // Sets the sprite at a specified position on the menu
        this.transform.position = new Vector3(0, 3, 0);

        spriteRenderer.get_sprite().set_scale(8, 6,0);
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void start() { super.start(); }

    @Override
    public void update() { super.update(); }
}

package org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;

public class Instructions extends MenuElement{
    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public Instructions() {
        spriteRenderer = new SpriteRenderer(this, "./resources/InstructionsMenu.png");
        this.addComponent(spriteRenderer);

        // Sets the sprite at a specified position on the menu
        this.transform.position = new Vector3(0, 1, 20);

        spriteRenderer.get_sprite().set_scale(30, 20,0);
    }
}

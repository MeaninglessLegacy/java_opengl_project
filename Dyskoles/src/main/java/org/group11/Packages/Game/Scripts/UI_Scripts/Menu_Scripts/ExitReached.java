package org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;

public class ExitReached extends MenuElement {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    SpriteRenderer spriteRenderer;

    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public ExitReached() {
        spriteRenderer = new SpriteRenderer(this, "./resources/ExitReached.png");
        this.addComponent(spriteRenderer);

        // Sets the sprite at a specified position on the menu
        this.transform.position = new Vector3(0, -0.25f, 20);

        spriteRenderer.get_sprite().set_scale(28, 10,0);
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void start() { super.start(); }

    @Override
    public void update() { super.update(); }
}

package org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.GameObject;

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
        this.transform.position = new Vector3(0, 0, 0);

        spriteRenderer.get_sprite().set_scale(10, 8,0);
    }

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void start() { super.start(); }

    @Override
    public void update() { super.update(); }
}

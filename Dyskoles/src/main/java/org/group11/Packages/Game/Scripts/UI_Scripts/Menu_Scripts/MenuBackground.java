package org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;

public class MenuBackground extends MenuElement {
    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public MenuBackground() {
        spriteRenderer = new SpriteRenderer(this, "./resources/MenuBackground.png");
        this.addComponent(spriteRenderer);

        // Sets the sprite at a specified position on the menu
        this.transform.position = new Vector3(0, 0, 21);

        spriteRenderer.get_sprite().set_scale(40, 40,0);
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void start() { super.start(); }

    @Override
    public void update() { super.update(); }
}

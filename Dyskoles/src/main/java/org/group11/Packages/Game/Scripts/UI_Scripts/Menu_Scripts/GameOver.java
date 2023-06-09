package org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;

public class GameOver extends MenuElement {
    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public GameOver() {
        spriteRenderer = new SpriteRenderer(this, "./resources/GameOver.png");
        this.addComponent(spriteRenderer);

        // Sets the sprite at a specified position on the menu
        this.transform.position = new Vector3(0, 8.5f, 20);

        spriteRenderer.get_sprite().set_scale(32, 16,1);
    }
}

package org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.GameObject;

public class GameOver extends GameObject {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    SpriteRenderer gameOverSprite;

    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public GameOver() {
        gameOverSprite = new SpriteRenderer(this, "./resources/GameOver.png");
        this.addComponent(gameOverSprite);

        // Sets the sprite at a specified position on the menu
        this.transform.position = new Vector3(0, 8, 0);

        gameOverSprite.get_sprite().set_scale(10, 8,0);
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

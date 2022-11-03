package org.group11.Packages.Game.Scripts.UI_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Transform;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;

/**
 * Displays a bar on the bottom right of the screen to keep track of the EXP the player has
 */
public class EXPBar extends GameObject {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    // Sprite that renders the outline of the EXP bar
    SpriteRenderer outsideBarSprite;
    // Sprite that renders how much of the EXP bar that is full
    SpriteRenderer insideBarSprite;

    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public EXPBar(MainCharacter MC) {
        outsideBarSprite = new SpriteRenderer(this, "./resources/EXPBarOutline.png");
        this.addComponent(outsideBarSprite);
        insideBarSprite = new SpriteRenderer(this, "./resources/EXPBarInside.png");
        this.addComponent(insideBarSprite);
        this.transform.position.x = MC.transform.position.x;
        this.transform.position.y = MC.transform.position.y;
        this.transform.position.z = MC.transform.position.z;
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

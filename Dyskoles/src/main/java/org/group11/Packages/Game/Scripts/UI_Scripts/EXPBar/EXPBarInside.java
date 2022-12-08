package org.group11.Packages.Game.Scripts.UI_Scripts.EXPBar;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;

/**
 * Displays a bar underneath the character to keep track of the EXP the player has
 * This class displays the outline of the EXP bar
 */
public class EXPBarInside extends GameObject {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    // Sprite that renders how much of the EXP bar that is full
    SpriteRenderer insideBarSprite;

    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public EXPBarInside(MainCharacter MC) {
        insideBarSprite = new SpriteRenderer(this, "./resources/EXPBarInside.png");
        this.addComponent(insideBarSprite);

        // Binds this HealthBar's position to the MainCharacter it's for
        this.transform = MC.transform;

        insideBarSprite.shiftSprite('y', (float) -0.5);
        insideBarSprite.shiftSprite('z', (float) -0.6);
        insideBarSprite.get_sprite().set_scale(0, (float) 0.1, 0);
    }

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    public void changeEXPBar(float exp, float expNeeded) {
        float scale = exp / expNeeded;
        insideBarSprite.get_sprite().set_scale(scale, (float) 0.1, 0);
        insideBarSprite.get_sprite().transform.position.x = -(1 - scale) / 2;
    }
}

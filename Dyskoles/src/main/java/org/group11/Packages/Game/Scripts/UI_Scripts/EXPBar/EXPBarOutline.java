package org.group11.Packages.Game.Scripts.UI_Scripts.EXPBar;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;

/**
 * Displays a bar underneath the character to keep track of the EXP the player has
 * This class displays the outline of the EXP bar
 */
public class EXPBarOutline extends GameObject {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    // Sprite that renders the outline of the EXP bar
    SpriteRenderer outsideBarSprite;

    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public EXPBarOutline(MainCharacter MC) {
        outsideBarSprite = new SpriteRenderer(this, "./resources/EXPBarOutline.png");
        this.addComponent(outsideBarSprite);

        // Binds this HealthBar's position to the MainCharacter it's for
        this.transform = MC.transform;

        outsideBarSprite.shiftSprite('y', (float)-0.5);
        outsideBarSprite.shiftSprite('z', (float)-0.6);
        outsideBarSprite.get_sprite().set_scale(1, (float)0.12,0);
    }
}

package org.group11.Packages.Game.Scripts.UI_Scripts.HealthBar;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Game.Scripts.Character_Scripts.Character;

/**
 * Displays a bar over each Character sprite to keep track of how much health they have
 * This class displays the outline of the health bar
 */
public class HealthBarOutline extends GameObject {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    // Sprite that renders the outline of the health bar
    SpriteRenderer outsideBarSprite;

    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public HealthBarOutline(Character character) {
        outsideBarSprite = new SpriteRenderer(this, "./resources/HealthBarOutline.png");
        this.addComponent(outsideBarSprite);

        // Binds this HealthBar's position to the character it's for
        this.transform = character.transform;

        outsideBarSprite.shiftSprite('y', (float)0.5);
        outsideBarSprite.shiftSprite('z', (float)-0.6);
        outsideBarSprite.get_sprite().set_scale(1, (float)0.17, 0);
    }
}

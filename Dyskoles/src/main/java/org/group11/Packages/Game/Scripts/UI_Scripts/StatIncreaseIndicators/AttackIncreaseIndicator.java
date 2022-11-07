package org.group11.Packages.Game.Scripts.UI_Scripts.StatIncreaseIndicators;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;

/**
 * Sprite displays beside MainCharacter when they gain an additional attack stat
 */
public class AttackIncreaseIndicator extends StatIncreaseIndicator {
    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public AttackIncreaseIndicator(MainCharacter MC) {
        statIncrease = new SpriteRenderer(this, "./resources/AttackIncrease.png");
        this.addComponent(statIncrease);

        // Binds this HealthBar's position to the MainCharacter it's for
        this.transform = MC.transform;

        statIncrease.shiftSprite('x', (float)0.55);
        statIncrease.shiftSprite('y', (float)-0.2);
        statIncrease.shiftSprite('z', (float)-0.5);
        statIncrease.get_sprite().set_scale((float)0.45, (float)0.3, 0);
        statIncrease.enabled = false;
    }
}

package org.group11.Packages.Game.Scripts.UI_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Game.Scripts.Character_Scripts.Character;

public class HealthBarInside extends GameObject {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    // Sprite that renders how much of the health bar that is full
    SpriteRenderer insideBarSprite;

    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public HealthBarInside(Character character) {
        insideBarSprite = new SpriteRenderer(this, "./resources/HealthBarInside.png");
        this.addComponent(insideBarSprite);
        // Binds this HealthBar's position to the character it's for
        this.transform = character.transform;

        // TODO: make this cleaner
        insideBarSprite.get_sprite().transform.position.y = insideBarSprite.get_sprite().transform.position.y + (float)0.5;
        insideBarSprite.get_sprite().set_scale(1, (float)0.2);
    }

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    public void changeHealthBar(float hp, float maxHp) {
        insideBarSprite.get_sprite().set_scale(hp/maxHp, (float)0.2);
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void start() { super.start(); }

    @Override
    public void update() { super.update(); }
}

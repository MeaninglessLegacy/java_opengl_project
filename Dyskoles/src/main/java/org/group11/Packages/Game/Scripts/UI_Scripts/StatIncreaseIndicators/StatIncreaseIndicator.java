package org.group11.Packages.Game.Scripts.UI_Scripts.StatIncreaseIndicators;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.Main.GameObject;

/**
 * Abstract class for any sprites that indicate stat increases for a character
 */
public abstract class StatIncreaseIndicator extends GameObject {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    // Sprite that renders when the MainCharacter gains an additional stat
    protected SpriteRenderer statIncrease;
    // Used to calculate the time to until the sprite disappears
    protected long timeWhenActivated = 0;
    protected int timeBeforeDisappear = 2000;

    //******************************************************************************************************************
    //* setters and getters
    //******************************************************************************************************************
    /**
     * Returns this object's sprite
     * @return this object's sprite
     */
    public SpriteRenderer getSprite() { return statIncrease; }

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    /**
     * Activates the object and enables the sprite
     */
    public void activate() {
        statIncrease.enabled = true;
        timeWhenActivated = System.currentTimeMillis();
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void update() {
        if(System.currentTimeMillis()-timeWhenActivated > timeBeforeDisappear) {
            statIncrease.enabled = false;
        }
    }
}

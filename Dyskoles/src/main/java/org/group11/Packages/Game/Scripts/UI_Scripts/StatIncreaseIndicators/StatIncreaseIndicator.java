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
    private long timeWhenActivated = 0;
    private int timeBeforeDisappear = 2000;

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    public void activate() {
        statIncrease.enabled = true;
        timeWhenActivated = System.currentTimeMillis();
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void start() { super.start(); }

    @Override
    public void update() {
        if(System.currentTimeMillis()-timeWhenActivated > timeBeforeDisappear) {
            statIncrease.enabled = false;
        }
    }
}
package org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdownSprites;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.Main.GameObject;

/**
 * Abstract class for any sprites that display the number of ticks before an enemy moves
 */
public abstract class MoveCDSprites extends GameObject {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    protected SpriteRenderer MoveCDSprite;

    //******************************************************************************************************************
    //* getters and setters
    //******************************************************************************************************************
    public SpriteRenderer getCDSprite() { return MoveCDSprite; }

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    /**
     * Disables the associated sprite
     */
    public void disableSprite() {
        MoveCDSprite.enabled = false;
    }

    /**
     * Enables the associated sprite
     */
    public void enableSprite() {
        MoveCDSprite.enabled = true;
    }
}

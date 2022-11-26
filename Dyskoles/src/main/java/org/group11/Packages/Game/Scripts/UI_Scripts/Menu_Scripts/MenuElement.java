package org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.Main.GameObject;

public abstract class MenuElement extends GameObject {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    protected SpriteRenderer spriteRenderer;

    //******************************************************************************************************************
    //* getters and setters
    //******************************************************************************************************************
    /**
     * Returns this object's spriteRenderer
     * @return this object's spriteRenderer
     */
    public SpriteRenderer getSprite() {
        return spriteRenderer;
    }
}

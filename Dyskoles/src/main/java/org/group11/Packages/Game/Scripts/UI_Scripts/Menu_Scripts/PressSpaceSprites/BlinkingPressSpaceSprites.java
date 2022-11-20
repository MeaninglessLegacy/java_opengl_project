package org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.PressSpaceSprites;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.MenuElement;

public abstract class BlinkingPressSpaceSprites extends MenuElement {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    SpriteRenderer spriteRenderer;

    // Used to calculate the time to until the sprite disappears/reappears
    protected long timeWhenActivated = 0;
    protected int timeBeforeSpriteToggle = 1000;

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void start() {
        timeWhenActivated = System.currentTimeMillis();
    }

    @Override
    public void update() {
        if(System.currentTimeMillis()-timeWhenActivated > timeBeforeSpriteToggle) {
            spriteRenderer.enabled = !spriteRenderer.enabled;
            timeWhenActivated = System.currentTimeMillis();
        }
    }
}

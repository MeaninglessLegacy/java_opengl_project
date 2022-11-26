package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;

/**
 * Key item, allows players with a key item to proceed to the next level of the game
 */
public class Key extends Item{
    //******************************************************************************************************************
    //* constructor  methods
    //******************************************************************************************************************
    public Key () {
        setupKey();
    }

    public Key(Vector3 pos) {
        transform.setPosition(pos);
        setupKey();
    }

    private void setupKey() {
        spriteRenderer = new SpriteRenderer(this, "./Resources/key.png");
        this.addComponent(spriteRenderer);
        spriteRenderer.shiftSprite('z', (float)-0.1);
        spriteRenderer.enabled = false;
    }

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    /**
     * Makes this Key visible or invisible according to the parameter
     * @param state true to make the key visible, false to make the key invisible
     */
    public void setKeyVisibility(boolean state) { spriteRenderer.enabled = state; }

    /**
     * Returns whether this Key is visible
     * @return true if Key is visible, false if not
     */
    public boolean getKeyVisibility() { return spriteRenderer.enabled; }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    /**
     * Adds a Key to the MainCharacter's backpack if the Key is obtainable
     * @param c the MainCharacter who triggered the item
     * @return true if the Item needs to disappear off the map (the Key was obtainable), false if it doesn't
     */
    @Override
    public boolean activate(MainCharacter c) {
        if (spriteRenderer.enabled) {
            Key invKey = new Key();
            c.backpack.addItem(invKey);
            spriteRenderer.enabled = false;
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void start() { super.start(); }

    @Override
    public void update() { super.update(); }
}

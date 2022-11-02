package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;

/**
 * Key item, allows players with a key item to proceed to the next level of the game
 */
public class Key extends Item{
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    private SpriteRenderer spriteRenderer;

    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public Key () {
        spriteRenderer = new SpriteRenderer(this, "./Resources/key.png");
        this.addComponent(spriteRenderer);
        spriteRenderer.enabled = false;
    }

    public Key(Vector3 pos) {
        transform.setPosition(pos);
        spriteRenderer = new SpriteRenderer(this, "./Resources/key.png");
        this.addComponent(spriteRenderer);
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
     *
     * @return
     */
    public boolean getKeyVisibility() { return spriteRenderer.enabled; }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
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
    public void start() {
        super.start();
    }

    @Override
    public void update() {
        super.update();
    }
}

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
        // TODO: implement no key texture
    }

    public Key(Vector3 pos) {
        transform.setPosition(pos);
        spriteRenderer = new SpriteRenderer(this, "./Resources/key.png");
        this.addComponent(spriteRenderer);
        // TODO: implement no key texture
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public boolean activate(MainCharacter c) {
        Key invKey = new Key();
        c.backpack.addItem(invKey);
        return true;
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

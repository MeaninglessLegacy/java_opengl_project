package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;

/**
 * Spike trap object, only affects player character and hurts the player character when the player character touches
 * this object
 */
public class RegenHeart extends Item {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    private SpriteRenderer spriteRenderer;
    private int _regenAmount = 1;

    /**
     * Returns how much health this heart regenerates
     * @return integer _regenAmount
     */
    public int get_regenAmount() { return _regenAmount; }

    //******************************************************************************************************************
    //* constructor methods
    //******************************************************************************************************************
    public RegenHeart() {
        setupRegenHeart();
    }

    public RegenHeart(Vector3 pos) {
        transform.setPosition(pos);
        setupRegenHeart();
    }

    private void setupRegenHeart() {
        spriteRenderer = new SpriteRenderer(this, "./Resources/Heart.png");
        this.addComponent(spriteRenderer);
        spriteRenderer.get_sprite().transform.position.z -= 0.1;
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public boolean activate(MainCharacter c) {
        if (c.getStatBlock().get_hp() < c.getStatBlock().get_maxHp()) {
            c.addHealth(_regenAmount);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void update() { super.update(); }

    @Override
    public void start() { super.start(); }
}

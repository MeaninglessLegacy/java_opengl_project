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

    //******************************************************************************************************************
    //* getters and setters
    //******************************************************************************************************************
    /**
     * Returns how much health this heart regenerates
     * @return integer _regenAmount
     */
    public int get_regenAmount() { return _regenAmount; }
    /**
     * sets the amount of health that this RegenHeart gives
     * @param _regenAmount the value to set the amount of health this RegenHeart gives
     */
    public void set_regenAmount(int _regenAmount) { this._regenAmount = _regenAmount; }

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
        spriteRenderer.shiftSprite('z', (float)-0.5);
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

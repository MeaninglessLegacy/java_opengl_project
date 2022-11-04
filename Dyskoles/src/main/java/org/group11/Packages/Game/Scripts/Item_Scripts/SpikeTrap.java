package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;

/**
 * Spike trap object, only affects player character and hurts the player character when the player character touches
 * this object
 */
public class SpikeTrap extends Item {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    private SpriteRenderer spriteRenderer;
    private int _spikeTrapDamage = 1;

    //******************************************************************************************************************
    //* setters and getters
    //******************************************************************************************************************
    /**
     * Returns how much damage this SpikeTrap does
     * @return integer _spikeTrapDamage
     */
    public int get_spikeTrapDamage() { return _spikeTrapDamage; }

    //******************************************************************************************************************
    //* constructor methods
    //******************************************************************************************************************
    public SpikeTrap() {
        setupSpikeTrap();
    }

    public SpikeTrap(Vector3 pos) {
        transform.setPosition(pos);
        setupSpikeTrap();
    }

    private void setupSpikeTrap() {
        spriteRenderer = new SpriteRenderer(this, "./Resources/SpikeTrap.png");
        this.addComponent(spriteRenderer);
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public boolean activate(MainCharacter c) {
        c.takeDamage(_spikeTrapDamage);
        return true;
    }

    @Override
    public void update() { super.update(); }

    @Override
    public void start() { super.start(); }
}

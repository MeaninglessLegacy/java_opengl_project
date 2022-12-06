package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Transform;
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
    private int _spikeTrapDamage = 1;

    //******************************************************************************************************************
    //* setters and getters
    //******************************************************************************************************************
    /**
     * Returns how much damage this SpikeTrap does
     * @return integer _spikeTrapDamage
     */
    public int get_spikeTrapDamage() { return _spikeTrapDamage; }
    /**
     * Sets the amount of damage this SpikeTrap does
     * @param _spikeTrapDamage the amount of damage this SpikeTrap does
     */
    public void set_spikeTrapDamage(int _spikeTrapDamage) { this._spikeTrapDamage = _spikeTrapDamage; }

    //******************************************************************************************************************
    //* constructor methods
    //******************************************************************************************************************
    public SpikeTrap() {
        this.transform = new Transform();
        setupSpikeTrap();
    }

    public SpikeTrap(Vector3 pos) {
        transform.setPosition(pos);
        setupSpikeTrap();
    }

    private void setupSpikeTrap() {
        spriteRenderer = new SpriteRenderer(this, "./Resources/SpikeTrap.png");
        this.addComponent(spriteRenderer);
        //spriteRenderer.get_sprite().transform.position.z -= 0.1; // if texture is flat must be elevated from floor
        // create new quads to make spike trap vertical
        Vector3[] quadCords = new Vector3[] {
                new Vector3(0.5f, 0.5f, -0.5f),
                new Vector3(0.5f, 0f, 0f),
                new Vector3(-0.5f, 0f, 0f),
                new Vector3(-0.5f, 0.5f, -0.5f)
        };
        spriteRenderer.get_sprite().renderComponent.set_quadCords(quadCords);
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public boolean activate(MainCharacter c) {
        c.takeDamage(_spikeTrapDamage);
        return true;
    }
}

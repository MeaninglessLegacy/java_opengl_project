package org.group11.Packages.Game.Scripts.Tile_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;

/**
 * Floor tile, characters can stand on this
 */
public class Floor extends Tile {
    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public Floor() {
        setupFloor();
    }

    public Floor(Vector3 pos) {
        setupFloor();
        transform.setPosition(pos);
    }

    private void setupFloor() {
        _tileType = tileTypes.floor;
        this.spriteRenderer = new SpriteRenderer(this,"./Resources/GrassTile1.png");
        this.addComponent(this.spriteRenderer);
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void update() { super.update(); }

    @Override
    public void start() { super.start(); }
}

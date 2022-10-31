package org.group11.Packages.Game.Scripts.Tile_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;

/**
 * Floor tile, characters can stand on this
 */
public class Floor extends Tile {
    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public Floor() {
        _tileType = tileTypes.floor;
        this.spriteRenderer = new SpriteRenderer(this,"./Resources/BWTileFloor.png");
        this.addComponent(this.spriteRenderer);
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void update() {
        super.update();
    }

    @Override
    public void start() {
        super.start();
    }
}

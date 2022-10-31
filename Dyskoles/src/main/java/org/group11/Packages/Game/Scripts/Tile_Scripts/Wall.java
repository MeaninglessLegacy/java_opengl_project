package org.group11.Packages.Game.Scripts.Tile_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;

/**
 * Wall object, characters cannot move through a wall
 */
public class Wall extends Tile {
    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public Wall () {
        _tileType = tileTypes.wall;
        this.spriteRenderer = new SpriteRenderer(this,"./Resources/GreyWall.png");
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

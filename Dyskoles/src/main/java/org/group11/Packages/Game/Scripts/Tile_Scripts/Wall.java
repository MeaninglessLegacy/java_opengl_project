package org.group11.Packages.Game.Scripts.Tile_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;

/**
 * Wall object, characters cannot move through a wall
 */
public class Wall extends Tile {
    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public Wall () {
        setupWall();
    }

    public Wall (Vector3 pos) {
        setupWall();
        transform.setPosition(pos);
    }

    private void setupWall() {
        _tileType = tileTypes.wall;
        this.spriteRenderer = new SpriteRenderer(this,"./Resources/GrassWall.png");
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

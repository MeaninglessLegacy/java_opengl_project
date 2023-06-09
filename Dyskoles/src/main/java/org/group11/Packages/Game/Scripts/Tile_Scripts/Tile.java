package org.group11.Packages.Game.Scripts.Tile_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.Main.GameObject;

/**
 * Abstract tile object, defines what kind of tiles exist. Used to create the map
 */
public abstract class Tile extends GameObject {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    public enum tileTypes { floor, wall }

    protected tileTypes _tileType;
    protected SpriteRenderer spriteRenderer;

    //******************************************************************************************************************
    //* setters and getters
    //******************************************************************************************************************
    /**
     * Returns this tile's _tileType
     * @return this tile's tile type
     */
    public tileTypes getTileType() { return _tileType; }
}

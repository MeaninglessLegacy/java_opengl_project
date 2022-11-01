package org.group11.Packages.Game.Scripts.Tile_Scripts;

import org.group11.Packages.Engine.GameObject;

import java.util.ArrayList;

/*
We can make Tile into an iterator to iterate over over its objectsOnTile array
 */
/**
 * Abstract tile object, defines what kind of tiles exist. Used to create the map
 */
public abstract class Tile extends GameObject {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    public enum tileTypes { floor, wall }

    protected tileTypes _tileType;
    /**
     * Returns this tile's _tileType
     * @return this tile's tile type
     */
    public tileTypes getTileType() { return _tileType; }
}
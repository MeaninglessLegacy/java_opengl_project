package org.group11.Packages.Game.Scripts.Tile_Scripts;

import org.group11.gameEngine.GameObject;

public class Tile extends GameObject {
    public enum tileTypes { floor, wall, spawn, exit }

    protected tileTypes _tileType;

    public tileTypes getTileType() { return _tileType; }
}

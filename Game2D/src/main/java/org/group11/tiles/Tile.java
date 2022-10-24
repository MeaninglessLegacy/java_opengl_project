package org.group11.tiles;

import org.group11.gameEngine.GameObject;

public class Tile extends GameObject {
    public enum tileTypes { floor, wall, spawn, exit }

    protected tileTypes tileType;

    public tileTypes getTileType() { return tileType; }
}

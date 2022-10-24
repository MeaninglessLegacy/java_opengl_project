package org.group11.tilesAndItems;

import org.group11.gameEngine.GameObject;

public class Tile extends GameObject {
    public enum tileTypes { floor, wall, spawn, exit }

    protected tileTypes tileType;

    public tileTypes getTileTypes() {
        // Returns a list of tile types? or returns what tile type this object is?
        return null;
    }
}

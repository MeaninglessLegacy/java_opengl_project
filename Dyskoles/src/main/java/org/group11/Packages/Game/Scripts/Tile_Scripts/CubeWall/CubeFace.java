package org.group11.Packages.Game.Scripts.Tile_Scripts.CubeWall;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.Main.GameObject;

/**
 * A Sprite that is a face of a cube.
 */
public class CubeFace extends GameObject {
    //******************************************************************************************************************
    //* spriteRenderer
    //******************************************************************************************************************
    private SpriteRenderer _spriteRenderer;
    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public CubeFace(String textureFile){
        this._spriteRenderer = new SpriteRenderer(this,textureFile);
        this.addComponent(this._spriteRenderer);
    }
    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    /**
     * Returns the spriteRenderer of this face.
     * @return SpriteRenderer object.
     */
    public SpriteRenderer get_spriteRenderer() {
        return _spriteRenderer;
    }
}

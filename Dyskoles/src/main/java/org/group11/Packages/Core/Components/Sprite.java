package org.group11.Packages.Core.Components;

import org.group11.Packages.Core.DataStructures.AssetPool;
import org.group11.Packages.Core.DataStructures.Transform;
import org.group11.Packages.Core.DataStructures.Vector2;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Renderer.RenderComponent;
import org.group11.Packages.Core.Renderer.Texture;

/**
 * A Sprite that has its own transform and renderComponent.
 */
public class Sprite {
    public Transform transform;
    private Vector3 _size; // size of this sprite
    private Vector3 _scale; // scales off the _size of this sprite
    public RenderComponent renderComponent; // rendering part of the Sprite

    /**
     * Constructs a new Sprite with given texture file path.
     * @param textureFile Path to texture file.
     */
    public Sprite(String textureFile){
        this.transform = new Transform();
        this._size = new Vector3(1,1,1);
        this._scale = new Vector3(1,1,0);
        this.renderComponent = new RenderComponent(textureFile);
        set_scale(this._scale);
    }

    //******************************************************************************************************************
    //* setters
    //******************************************************************************************************************
    /**
     * Sets the scale of the Sprite. Changes the size of the quad in the renderComponent that represents the Sprite.
     * Since a Sprite is 2-dimensional it will stretch diagonally if an axis is not set to 0.
     * @param scale Vector3 that gives the x-scale, y-scale, and z-scale of the Sprite.
     */
    public void set_scale(Vector3 scale) {
        this._scale = scale;
        Vector3[] quadCords = new Vector3[]{
                new Vector3( this._scale.x/2*this._size.x,this._scale.y/2*this._size.y,this._scale.z/2*this._size.z),
                new Vector3( this._scale.x/2*this._size.x,-this._scale.y/2*this._size.y,-this._scale.z/2*this._size.z),
                new Vector3( -this._scale.x/2*this._size.x,-this._scale.y/2*this._size.y,-this._scale.z/2*this._size.z),
                new Vector3( -this._scale.x/2*this._size.x,this._scale.y/2*this._size.y,this._scale.z/2*this._size.z)
        };
        assert quadCords.length == 4;
        renderComponent.set_quadCords(quadCords);
    }

    /**
     * Sets the scale of the Sprite. Changes the size of the quad in the renderComponent that represents the Sprite.
     * Since a Sprite is 2-dimensional it will stretch diagonally if an axis is not set to 0.
     * @param x Float of the x-scale.
     * @param y Float of the y-scale.
     */
    public void set_scale(float x, float y, float z) {
        this._scale.x = x;
        this._scale.y = y;
        this._scale.z = z;
        set_scale(_scale);
    }

    /**
     * Sets the texture file of the Sprite. Changes the texture linked
     * @param textureFile String path to the texture file.
     */
    public void set_texture(String textureFile) {
        renderComponent.set_texture(AssetPool.getTexture(textureFile));
    }

    //******************************************************************************************************************
    //* getters
    //******************************************************************************************************************
    /**
     * Returns the Texture object of the Sprite's renderComponent.
     * @return Texture object of the Sprite's renderComponent.
     */
    public Texture get_texture(){
        return renderComponent.get_texture();
    }

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    /**
     * Test method for flipping the Sprite's texture along the x-axis.
     */
    public void flipX(){
        Vector3[] oldTex = renderComponent.get_texCords();
        Vector3[] texCords = new Vector3[]{
                oldTex[3],
                oldTex[2],
                oldTex[1],
                oldTex[0]
        };
        renderComponent.set_texCords(texCords);
    }
}

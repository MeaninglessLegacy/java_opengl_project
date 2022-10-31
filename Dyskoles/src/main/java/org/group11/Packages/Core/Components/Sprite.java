package org.group11.Packages.Core.Components;

import org.group11.Packages.Core.DataStructures.AssetPool;
import org.group11.Packages.Core.DataStructures.Transform;
import org.group11.Packages.Core.DataStructures.Vector2;
import org.group11.Packages.Core.Renderer.RenderComponent;
import org.group11.Packages.Core.Renderer.Texture;

/**
 * A Sprite that has its own transform and renderComponent.
 */
public class Sprite {
    public Transform transform;
    private Vector2 _scale;
    public RenderComponent renderComponent; // rendering part of the Sprite

    /**
     * Constructs a new Sprite with given texture file path.
     * @param textureFile Path to texture file.
     */
    public Sprite(String textureFile){
        this.transform = new Transform();
        this._scale = new Vector2(1,1);
        this.renderComponent = new RenderComponent(textureFile);
        set_scale(this._scale);
    }

    //******************************************************************************************************************
    //* setters
    //******************************************************************************************************************
    /**
     * Sets the scale of the Sprite. Changes the size of the quad in the renderComponent that represents the Sprite.
     * @param scale Vector2 that gives the x-scale and y-scale of the Sprite.
     */
    public void set_scale(Vector2 scale) {
        this._scale = scale;

        Vector2[] quadCords = new Vector2[]{
                new Vector2( this._scale.x/2,this._scale.y/2),
                new Vector2(this._scale.x/2,-this._scale.y/2),
                new Vector2(-this._scale.x/2,-this._scale.y/2),
                new Vector2(-this._scale.x/2,this._scale.y/2)
        };

        renderComponent.set_quadCords(quadCords);
    }

    /**
     * Sets the scale of the Sprite. Changes the size of the quad in the renderComponent that represents the Sprite.
     * @param x Float of the x-scale.
     * @param y Float of the y-scale.
     */
    public void set_scale(float x, float y) {
        this._scale.x = x;
        this._scale.y = y;
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
        Vector2[] oldTex = renderComponent.get_texCords();
        Vector2[] texCords = new Vector2[]{
                oldTex[3],
                oldTex[2],
                oldTex[1],
                oldTex[0]
        };

        renderComponent.set_texCords(texCords);
    }
}

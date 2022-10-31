package org.group11.Packages.Core.Renderer;

import org.group11.Packages.Core.DataStructures.AssetPool;
import org.group11.Packages.Core.DataStructures.Vector2;

/**
 * Something that can be rendered by the Renderer, contains a Texture, the bounding vertices of the Texture, and
 * the bounding vertices of the Quad to draw the texture on.
 */
public class RenderComponent {
    private Texture _texture; // texture to render onto the quad
    // position of each vertex of the texture
    private Vector2[] _texCords = new Vector2[] {
            new Vector2(1.0f, 0.0f),
            new Vector2(1.0f, 1.0f),
            new Vector2(0.0f, 1.0f),
            new Vector2(0.0f, 0.0f)
    };
    // the position of each vertex of the quad that will be used to display the texture
    private Vector2[] _quadCords = new Vector2[] {
            new Vector2(1.0f, 1.0f),
            new Vector2(1.0f, 0.0f),
            new Vector2(0.0f, 0.0f),
            new Vector2(0.0f, 1.0f)
    };

    /**
     * Construct a new RenderComponent with given file path to texture file.
     * @param textureFile String path to texture file.
     */
    public RenderComponent(String textureFile){
        _texture = AssetPool.getTexture(textureFile);
    }

    //******************************************************************************************************************
    //* setters
    //******************************************************************************************************************
    /**
     *
     * @param _texture
     */
    public void set_texture(Texture _texture) {this._texture = _texture;}

    /**
     *
     * @param _quadCords
     */
    public void set_quadCords(Vector2[] _quadCords) {
        this._quadCords = _quadCords;
    }

    /**
     *
     * @param _texCords
     */
    public void set_texCords(Vector2[] _texCords) {
        this._texCords = _texCords;
    }


    //******************************************************************************************************************
    //* getters
    //******************************************************************************************************************
    /**
     *
     * @return
     */
    public Vector2[] get_quadCords() {
        return _quadCords;
    }

    /**
     *
     * @return
     */
    public Vector2[] get_texCords() {
        return _texCords;
    }

    /**
     *
     * @return
     */
    public Texture get_texture() {
        return _texture;
    }
}

package org.group11.Packages.Core.Components;

import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Renderer.Renderer;

/**
 * Component for a GameObject, specifies that a GameObject should render a 2-dimensional sprite.
 */
public class SpriteRenderer extends Component {
    private Sprite _sprite; // sprite to be rendered
    private GameObject _gameObject; // should be set to the game object that this is going to be attached too
    private int _sortingOrder = 0; // which layer this is sorted on
    private boolean _useWorldSpace = true; // use world space or screen space coordinates

    /**
     *
     * @param gameObject
     * @param textureFile
     */
    public SpriteRenderer(GameObject gameObject, String textureFile){
        this._gameObject = gameObject;
        this._sprite = new Sprite(textureFile);
    }

    //******************************************************************************************************************
    //* getters
    //******************************************************************************************************************
    /**
     *
     * @return
     */
    public Sprite get_sprite() {
        return _sprite;
    }

    /**
     *
     * @return
     */
    public GameObject get_gameObject() {
        return _gameObject;
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void update() {
        super.update();
    }
}
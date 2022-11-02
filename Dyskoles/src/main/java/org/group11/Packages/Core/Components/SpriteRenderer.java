package org.group11.Packages.Core.Components;

import org.group11.Packages.Core.Main.GameObject;

/**
 * Component for a GameObject, specifies that a GameObject should render a 2-dimensional sprite.
 */
public class SpriteRenderer extends Component {
    private Sprite _sprite; // sprite to be rendered
    private GameObject _gameObject; // should be set to the game object that this is going to be attached too
    private int _sortingOrder = 0; // which layer this is sorted on
    private boolean _useWorldSpace = true; // use world space or screen space coordinates

    /**
     * Constructor for SpriteRenderer
     * @param gameObject specific GameObject object to assign this SpriteRenderer to
     * @param textureFile the sprite that SpriteRenderer is rendering
     */
    public SpriteRenderer(GameObject gameObject, String textureFile){
        this._gameObject = gameObject;
        this._sprite = new Sprite(textureFile);
    }

    //******************************************************************************************************************
    //* getters
    //******************************************************************************************************************
    /**
     * Returns the Sprite that is assigned to this SpriteRenderer
     * @return this SpriteRenderer's Sprite attribute
     */
    public Sprite get_sprite() {
        return _sprite;
    }

    /**
     * Returns the GameObject that is assigned to this SpriteRenderer
     * @return this SpriteRenderer's GameObject attribute
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
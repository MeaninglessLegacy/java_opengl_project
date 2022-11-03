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
    //* methods
    //******************************************************************************************************************
    /**
     * Shifts the sprite in a certain direction by a certain factor
     * @param direction the plane on which to shift the sprite (x, y, or z)
     * @param scale how much to shift the sprite
     */
    public void shiftSprite(char direction, float scale) {
        if (direction == 'x') {
            _sprite.transform.position.x += scale;
        }
        else if (direction == 'y') {
            _sprite.transform.position.y += scale;
        }
        else if (direction == 'z') {
            _sprite.transform.position.z += scale;
        }
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void update() {
        super.update();
    }
}
package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Item_Scripts.Backpack;
import org.group11.Packages.Game.Scripts.UI_Scripts.*;
import org.group11.Packages.Game.Scripts.UI_Scripts.StatIncreaseIndicators.AttackIncreaseIndicator;
import org.group11.Packages.Game.Scripts.UI_Scripts.StatIncreaseIndicators.HealthIncreaseIndicator;

import static org.group11.Packages.Game.Scripts.Logic.GameLogicDriver.*;

/**
 * Player Character class
 */
public class MainCharacter extends Character{
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    // Used to store items this MainCharacter picks up
    public Backpack backpack = new Backpack();

    // Displays the exp of the MainCharacter below their sprite
    protected EXPBarOutline _EXPBarOutline;
    protected EXPBarInside _EXPBarInside;

    // Displays when this MainCharacter levels up and gains attack and/or health
    protected AttackIncreaseIndicator _attackIncreaseIndicator;
    protected HealthIncreaseIndicator _healthIncreaseIndicator;

    // Used to help render and control the sprite
    protected SpriteRenderer characterSprite;
    protected boolean _chrFacingRight = true;
    protected double _lastUpdateTime; // time since last update

    // smoothly move character during update() calls
    private Vector3 _moveVector = new Vector3(); // direction to move the character in

    // animate character
    private char _aniState = 0;// animation state
    private int _aniFrame = 1; // current animation frame
    private double _timeSinceLastFrame = 0; // time since last frame in MS
    private double _breathingScale; // character scaling parameter for breathing effect

    //******************************************************************************************************************
    //* setters and getters
    //******************************************************************************************************************
    /**
     * Return the EXPBarOutline of this character object
     * @return the EXPBarOutline to return
     */
    public EXPBarOutline get_EXPBarOutline() { return this._EXPBarOutline; }
    /**
     * Return the EXPBarInside of this character object
     * @return the EXPBarInside to return
     */
    public EXPBarInside get_EXPBarInside() { return this._EXPBarInside; }

    //******************************************************************************************************************
    //* constructor methods
    //******************************************************************************************************************
    public MainCharacter() {
        setupMainCharacter();
    }

    public MainCharacter(Vector3 pos) {
        transform.setPosition(pos);
        setupMainCharacter();
    }

    private void setupMainCharacter() {
        _statBlock.set_MaxHp(3);
        _statBlock.set_hp(3);
        _statBlock.set_Atk(1);
        characterSprite = new SpriteRenderer(this, "./Resources/ump45.png");
        this.addComponent(characterSprite);
        // display sprite on top of other sprites with small z translation
        characterSprite.get_sprite().transform.position.z -= 0.4;
        _healthBarOutline = new HealthBarOutline(this);
        _healthBarInside = new HealthBarInside(this);
        _EXPBarOutline = new EXPBarOutline(this);
        _EXPBarInside = new EXPBarInside(this);
        _healthIncreaseIndicator = new HealthIncreaseIndicator(this);
        _attackIncreaseIndicator = new AttackIncreaseIndicator(this);
    }

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    /**
     * Increases the exp value of this character by specified amount. If exp reaches a certain value, increases level
     * then decreases exp by the amount needed to level up
     * @param exp value to increase exp by
     */
    public void addExp(int exp) {
        _statBlock.set_exp(_statBlock.get_exp() + exp);
        if (_statBlock.get_exp() >= 5 * _statBlock.get_lvl()) {
            _statBlock.set_exp(_statBlock.get_exp() - (5 * _statBlock.get_lvl()));
            addLevel();
        }
        _EXPBarInside.changeEXPBar((float)_statBlock.get_exp(), ((float)5 * _statBlock.get_lvl()));
    }

    /**
     * Increases the level value of this character by 1 and increases other stats depending on what the new level is
     */
    public void addLevel() {
        // Adjust the values being added to stats of MainCharacter as needed to balance
        _statBlock.set_lvl(_statBlock.get_lvl() + 1);
        if (_statBlock.get_lvl() % 2 == 0) {
            this.addAttack(1);
        }
        if (_statBlock.get_lvl() % 2 == 1) {
            this.addMaxHealth(1);
            this.addHealth(1);
        }
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************

    @Override
    public void addMaxHealth(int maxHp) {
        super.addMaxHealth(maxHp);
        _healthIncreaseIndicator.activate();
    }

    @Override
    public void addAttack(int atk) {
        super.addAttack(atk);
        _attackIncreaseIndicator.activate();
    }

    @Override
    public void instantiateRelatedSprites(Scene scene) {
        scene.Instantiate(_healthBarInside);
        scene.Instantiate(_healthBarOutline);
        scene.Instantiate(_EXPBarInside);
        scene.Instantiate(_EXPBarOutline);
        scene.Instantiate(_attackIncreaseIndicator);
        scene.Instantiate(_healthIncreaseIndicator);
    }

    @Override
    public void destroyRelatedSprites(Scene scene) {
        scene.Destroy(_healthBarInside);
        scene.Destroy(_healthBarOutline);
        scene.Destroy(_EXPBarInside);
        scene.Destroy(_EXPBarOutline);
        scene.Destroy(_attackIncreaseIndicator);
        scene.Destroy(_healthIncreaseIndicator);
    }

    @Override
    public void Delete() {
        destroyRelatedSprites(Scene.get_scene());
    }

    @Override
    public void start() { instantiateRelatedSprites(Scene.get_scene()); }

    /**
     * Override default attackCharacter to include setting the character animation state to attack.
     */
    @Override
    public boolean attackCharacter(Character defender) {
        // set animation parameters
        _aniState = 2;
        _aniFrame = 1;
        return super.attackCharacter(defender);
    }

    /**
     * Smoothly moves character to next position based on the _moveVector. And animates the character based on the
     * character _animState.
     */
    @Override
    public void update() {
        // all animations are tied to time passed
        double timePassed = System.currentTimeMillis() - _lastUpdateTime;
        _timeSinceLastFrame += timePassed;
        // smoothly move character based on remaining _moveVector
        double moveVectorSum = Math.abs(_moveVector.x) + Math.abs(_moveVector.y);
        if(moveVectorSum > 0.1){
            if(_aniState != 2) _aniState = 1;
            this.transform.position.x += _moveVector.x/10;
            this.transform.position.y += _moveVector.y/10;
            _moveVector.x -= _moveVector.x/10;
            _moveVector.y -= _moveVector.y/10;
        }else{
            if(_aniState != 2) _aniState = 0;
            // since game logic is on a integer grid we need to snap back to the grid when done
            this.transform.position.x = Math.round(this.transform.position.x);
            this.transform.position.y = Math.round(this.transform.position.y);
        }
        // animate character bobbing
        if(_breathingScale < 2) {
            _breathingScale += timePassed / 500;
        }else{
            _breathingScale = 0;
        }
        double yScale = -Math.pow((_breathingScale -1),4)+1;
        characterSprite.get_sprite().set_scale(1, (float)(1+0.05*yScale), 0);
        _lastUpdateTime = System.currentTimeMillis();
        // animate based on state
        // animation is bad, takes a lot of memory need to implement sprite sheets
        if(_aniState == 0){
            characterSprite.get_sprite().set_texture("./Resources/ump45.png");
        }else if(_aniState == 1){
            if(_timeSinceLastFrame > 50){
                if(_aniFrame > 18) _aniFrame = 1;
                characterSprite.get_sprite().set_texture("./Resources/ump45/ump45 ("+ _aniFrame +").png");
                _aniFrame++;
                _timeSinceLastFrame = 0;
            }
        }else if(_aniState == 2){
            if(_timeSinceLastFrame > 20){
                if(_aniFrame > 10) {
                    _aniFrame = 1;
                    _aniState = 0;
                }
                characterSprite.get_sprite().set_scale(0.9f, (float)(0.9+0.05*yScale), 0); // image scale is off
                characterSprite.get_sprite().set_texture("./Resources/ump45/ump45-gun ("+ _aniFrame +").png");
                _aniFrame++;
                _timeSinceLastFrame = 0;
            }
        }
        super.update();
    }

    private long _lastButtonPressTime = 0; // debounce key press
    /**
     * When a movement key is pressed, if there was sufficient time since the last movement key press, then gets the
     * direction of where the MainCharacter is attempting to move and asks GameLogicDriver if it can move to that
     * position
     * @param key Ascii value of the key.
     */
    @Override
    public void onKeyDown(int key) {
        int timeBeforeNextRead = 200;
        if(System.currentTimeMillis()- _lastButtonPressTime > timeBeforeNextRead &&
           (key == 'W' || key == 'A' || key == 'S' || key == 'D')) {
            _lastButtonPressTime = System.currentTimeMillis();

            // Gets the position of where this MainCharacter is moving to next
            float playerX = transform.position.x;
            float playerY = transform.position.y;
            float playerZ = transform.position.z;
            Vector3 nextMove = null;
            if (key == 'W') {
                nextMove = new Vector3(playerX, playerY + 1, playerZ);
            } else if (key == 'A') {
                nextMove = new Vector3(playerX - 1, playerY, playerZ);
                if(_chrFacingRight){
                    _chrFacingRight = false;
                    characterSprite.get_sprite().flipX();
                }
            } else if (key == 'S') {
                nextMove = new Vector3(playerX, playerY - 1, playerZ);
            } else if (key == 'D') {
                nextMove = new Vector3(playerX + 1, playerY, playerZ);
                if(!_chrFacingRight){
                    _chrFacingRight = true;
                    characterSprite.get_sprite().flipX();
                }
            }
            // Create move animation start position and check if the character successfully moved
            Vector3 chrStartPos = new Vector3();
            boolean chrMoved = false;
            // MainCharacter attempts to move
            if (nextMove != null) {
                boolean canMove = MCCheckMove(this, nextMove);
                if (canMove) {
                    // Setup move animation start position
                    chrStartPos.x = this.transform.position.x;
                    chrStartPos.y = this.transform.position.y;
                    chrStartPos.z = this.transform.position.z;
                    chrMoved = true;
                    // Need to instantaneously move character so game logic can process the turn
                    this.transform.setPosition(nextMove);
                    // Set the "velocity" vector of character
                    _moveVector.x = nextMove.x-playerX;
                    _moveVector.y = nextMove.y-playerY;
                    _moveVector.z = nextMove.z-playerZ;
                }
            }
            // Perform logic after the character moves
            // Checks for items
            MCCheckItem(this);
            afterMCMoveLogic(this);
            // Shift character back to start position after logic processing so the movement is animated correctly
            if(chrMoved)this.transform.setPosition(chrStartPos);
        }
    }
}

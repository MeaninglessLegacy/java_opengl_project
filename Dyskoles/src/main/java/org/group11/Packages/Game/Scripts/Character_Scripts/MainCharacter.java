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
    protected boolean facingRight = true;
    double time; // time since last update
    double x; // character scaling parameter for breathing effect

    // used to animate running from previous tile to new tile
    private Vector3 moveVector = new Vector3(); // direction to move the character in
    private int frame = 1; // current animation frame
    private double lastFrameTime = 0; // time since last frame in MS

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
        characterSprite.get_sprite().transform.position.z -= 0.5;
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
     * Creates a 'breathing' effect by scaling the y component the sprite of this Character down and up over time. Also
     * moves this object in the direction of the
     */
    @Override
    public void update() {
        // animate bob
        double timePassed = System.currentTimeMillis() - time;
        if(x < 2) {
            x += timePassed / 500;
        }else{
            x = 0;
        }
        double yScale = -Math.pow((x-1),4)+1;
        characterSprite.get_sprite().set_scale(1, (float)(1+0.05*yScale), 0);
        time = System.currentTimeMillis();
        // move character and animate run
        // animation is bad, takes a lot of memory need to implement sprite sheets
        lastFrameTime += timePassed;
        // calculate how much remaining distance to move
        double moveVectorSum = Math.abs(moveVector.x) + Math.abs(moveVector.y);
        // move towards position
        if(moveVectorSum > 0.1){
            if(lastFrameTime > 50){
                if(frame > 18) frame = 1;
                characterSprite.get_sprite().set_texture("./Resources/ump45/ump45 ("+frame+").png");
                frame++;
                lastFrameTime = 0;
            }
            Vector3 spritePos = characterSprite.get_sprite().transform.position;
            spritePos.x = moveVector.x - moveVector.x/10;
            spritePos.y = moveVector.y - moveVector.y/10;
            moveVector.x -= moveVector.x/10;
            moveVector.y -= moveVector.y/10;
        }else{
            // since game logic is on a integer grid we need to snap back to the grid when done
            Vector3 spritePos = characterSprite.get_sprite().transform.position;
            spritePos.x = 0;
            spritePos.y = 0;
            characterSprite.get_sprite().set_texture("./Resources/ump45.png");
        }
        super.update();
    }

    private long lastTime = 0;
    /**
     * When a movement key is pressed, if there was sufficient time since the last movement key press, then gets the
     * direction of where the MainCharacter is attempting to move and asks GameLogicDriver if it can move to that
     * position
     * @param key Ascii value of the key.
     */
    @Override
    public void onKeyDown(int key) {
        int timeBeforeNextRead = 200;
        if(System.currentTimeMillis()-lastTime > timeBeforeNextRead &&
           (key == 'W' || key == 'A' || key == 'S' || key == 'D')) {
            lastTime = System.currentTimeMillis();

            // Gets the position of where this MainCharacter is moving to next
            float playerX = transform.position.x;
            float playerY = transform.position.y;
            float playerZ = transform.position.z;
            Vector3 nextMove = null;
            if (key == 'W') {
                nextMove = new Vector3(playerX, playerY + 1, playerZ);
            } else if (key == 'A') {
                nextMove = new Vector3(playerX - 1, playerY, playerZ);
                if(facingRight){
                    facingRight = false;
                    characterSprite.get_sprite().flipX();
                }
            } else if (key == 'S') {
                nextMove = new Vector3(playerX, playerY - 1, playerZ);
            } else if (key == 'D') {
                nextMove = new Vector3(playerX + 1, playerY, playerZ);
                if(!facingRight){
                    facingRight = true;
                    characterSprite.get_sprite().flipX();
                }
            }

            // MainCharacter attempts to move
            if (nextMove != null) {
                boolean canMove = MCCheckMove(this, nextMove);
                if (canMove) {
                    this.transform.setPosition(nextMove);
                    //set the "velocity" vector of character sprite
                    moveVector.x = -(nextMove.x-playerX);
                    moveVector.y = -(nextMove.y-playerY);
                    moveVector.z = -(nextMove.z-playerZ);
                }
            }

            // Checks for items
            MCCheckItem(this);
            afterMCMoveLogic(this);
        }
    }
}

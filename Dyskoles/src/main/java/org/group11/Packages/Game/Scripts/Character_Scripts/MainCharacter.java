package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Game.Scripts.Item_Scripts.Backpack;

import static org.group11.Packages.Game.Scripts.Logic.GameLogicDriver.*;

/**
 * Player character class
 */
public class MainCharacter extends Character{
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    // Used to store items this MainCharacter picks up
    public Backpack backpack = new Backpack();

    // Variables used to help control the sprite
    private SpriteRenderer spriteRenderer;
    private boolean facingRight = true;
    double time;
    double x;

    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public MainCharacter() {
        _statBlock.set_MaxHp(3);
        _statBlock.set_hp(3);
        _statBlock.set_Atk(1);
        // set position
        this.transform.setPosition(new Vector3(1,1,0));
        // create sprite renderer
        spriteRenderer = new SpriteRenderer(this, "./Resources/ump45.png");
        this.addComponent(spriteRenderer);
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
        if (exp >= 5 * _statBlock.get_lvl()) {
            addLevel();
            _statBlock.set_exp(_statBlock.get_exp() - 5 * _statBlock.get_lvl());
        }
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
    public void start() {
        super.start();
    }

    @Override
    public void update() {
        double timePassed = System.currentTimeMillis() - time;
        if(x < 2) {
            x += timePassed / 500;
        }else{
            x = 0;
        }
        double yScale = -Math.pow((x-1),4)+1;
        spriteRenderer.get_sprite().set_scale(1, (float)(1+0.05*yScale));
        time = System.currentTimeMillis();
        super.update();
    }

    private long lastTime = 0;
    private int timeBeforeNextRead = 200;
    @Override
    public void onKeyDown(int key) {
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
                    spriteRenderer.get_sprite().flipX();
                }
            } else if (key == 'S') {
                nextMove = new Vector3(playerX, playerY - 1, playerZ);
            } else if (key == 'D') {
                nextMove = new Vector3(playerX + 1, playerY, playerZ);
                if(!facingRight){
                    facingRight = true;
                    spriteRenderer.get_sprite().flipX();
                }
            }

            // MainCharacter attempts to move
            if (nextMove != null) {
                boolean canMove = MCCheckMove(this, nextMove);
                if (canMove) {
                    this.transform.setPosition(nextMove);
                }
            }

            // Checks for items
            MCCheckItem(this, this.transform.position);
            afterMCMoveLogic(this);
        }
    }
}

package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Game.Scripts.Item_Scripts.Backpack;

/**
 * Player character class
 */
public class MainCharacter extends Character{
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    public Backpack backpack = new Backpack();

    private SpriteRenderer spriteRenderer;

    private boolean facingRight = true;

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
        spriteRenderer.get_sprite().set_scale(1.5f, 1.5f);
        spriteRenderer.get_sprite().transform.position.y += 0.5;
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

    private double time = 0;
    private double x = 0;
    
    @Override
    public void update() {
        double timepassed = System.currentTimeMillis() - time;
        if(x < 2) {
            x += timepassed / 500;
        }else{
            x = 0;
        }
        double yScale = -Math.pow((x-1),4)+1;
        spriteRenderer.get_sprite().set_scale(1.5f, (float)(1.5+0.05*yScale));
        time = System.currentTimeMillis();
    }

    @Override
    public void onKeyDown(int key) {
        if (key == 'A') {
            if(facingRight){
                facingRight = false;
                spriteRenderer.get_sprite().flipX();
            }
        } else if (key == 'D') {
            if(!facingRight){
                facingRight = true;
                spriteRenderer.get_sprite().flipX();
            }
        }
    }
}

package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Transform;
import org.group11.Packages.Core.DataStructures.Vector3;

/**
 * Boss enemy type character, chases the player blindly when activated
 */
public class Boss extends Enemy {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    private SpriteRenderer spriteRenderer;

    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public Boss() {
        expGiven = 5;
        _statBlock.set_Atk(3);
        _statBlock.set_MaxHp(10);
        _statBlock.set_hp(10);
        spriteRenderer = new SpriteRenderer(this, "./Resources/ump45.png");
        this.addComponent(spriteRenderer);
    }

    public Boss(Vector3 pos) {
        expGiven = 5;
        _statBlock.set_Atk(3);
        _statBlock.set_MaxHp(10);
        _statBlock.set_hp(10);
        transform.setPosition(pos);
        spriteRenderer = new SpriteRenderer(this, "./Resources/ump9.png");
        this.addComponent(spriteRenderer);
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
        super.update();
    }

    /**
     * Might change: call logic of enemy
     * @param key ascii value of the key
     */
    @Override
    public void onKeyDown(int key) {
        super.update();
    }
}
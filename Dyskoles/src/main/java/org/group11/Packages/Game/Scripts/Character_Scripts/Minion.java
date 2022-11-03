package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Game.Scripts.UI_Scripts.HealthBarInside;
import org.group11.Packages.Game.Scripts.UI_Scripts.HealthBarOutline;

/**
 * Basic enemy type character, Minion chases the player blindly when activated
 */
public class Minion extends Enemy{
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    private SpriteRenderer spriteRenderer;

    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public Minion() {
        _statBlock.set_Atk(1);
        _statBlock.set_MaxHp(3);
        _statBlock.set_hp(3);

        _healthBarOutline = new HealthBarOutline(this);
        _healthBarInside = new HealthBarInside(this);
        spriteRenderer = new SpriteRenderer(this, "./Resources/chibi_screenshot.png");
        this.addComponent(spriteRenderer);
    }

    public Minion(Vector3 pos) {
        _statBlock.set_Atk(1);
        _statBlock.set_MaxHp(3);
        _statBlock.set_hp(3);
        transform.setPosition(pos);

        _healthBarOutline = new HealthBarOutline(this);
        _healthBarInside = new HealthBarInside(this);
        spriteRenderer = new SpriteRenderer(this, "./Resources/chibi_screenshot.png");
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
}

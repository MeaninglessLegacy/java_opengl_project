package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.UI_Scripts.HealthBarInside;
import org.group11.Packages.Game.Scripts.UI_Scripts.HealthBarOutline;
import org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdown;

/**
 * Basic enemy type character, Minion chases the player blindly when activated
 */
public class Minion extends Enemy{
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    private SpriteRenderer characterSprite;

    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public Minion() {
        setupMinion();
    }

    public Minion(Vector3 pos) {
        transform.setPosition(pos);
        setupMinion();
    }

    private void setupMinion() {
        _statBlock.set_Atk(1);
        _statBlock.set_MaxHp(3);
        _statBlock.set_hp(3);

        characterSprite = new SpriteRenderer(this, "./Resources/chibi_screenshot.png");
        this.addComponent(characterSprite);
        _healthBarInside = new HealthBarInside(this);
        _healthBarOutline = new HealthBarOutline(this);
        _moveCountdown = new MoveCountdown(this);
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void instantiateRelatedSprites(Scene scene) {
        scene.Instantiate(this);
        scene.Instantiate(_healthBarInside);
        scene.Instantiate(_healthBarOutline);
        scene.Instantiate(_moveCountdown);
    }

    @Override
    public void destroyRelatedSprites(Scene scene) {
        scene.Destroy(this);
        scene.Destroy(_healthBarInside);
        scene.Destroy(_healthBarOutline);
        scene.Destroy(_moveCountdown);
    }

    @Override
    public void start() { super.start(); }

    @Override
    public void update() { super.update(); }
}

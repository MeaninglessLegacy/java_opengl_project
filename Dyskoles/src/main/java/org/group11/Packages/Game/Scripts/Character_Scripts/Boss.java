package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.UI_Scripts.HealthBarInside;
import org.group11.Packages.Game.Scripts.UI_Scripts.HealthBarOutline;
import org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdown;

/**
 * Boss enemy type character, chases the player blindly when activated
 */
public class Boss extends Enemy {
    //******************************************************************************************************************
    //* constructor methods
    //******************************************************************************************************************
    public Boss() {
        setupBoss();
    }

    public Boss(Vector3 pos) {
        transform.setPosition(pos);
        setupBoss();
    }

    private void setupBoss() {
        expGiven = 5;
        _statBlock.set_Atk(3);
        _statBlock.set_MaxHp(10);
        _statBlock.set_hp(10);
        _ticksBeforeNextMove = 3;
        _ticksPerMove = 3;

        characterSprite = new SpriteRenderer(this, "./Resources/ump9.png");
        this.addComponent(characterSprite);
        characterSprite.get_sprite().transform.position.z -= 0.2; // place above tiles
        _healthBarInside = new HealthBarInside(this);
        _healthBarOutline = new HealthBarOutline(this);
        _moveCountdown = new MoveCountdown(this);
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void instantiateRelatedSprites(Scene scene) {
        scene.Instantiate(_healthBarInside);
        scene.Instantiate(_healthBarOutline);
        scene.Instantiate(_moveCountdown);
        _moveCountdown.instantiateCDSprites(scene);
    }

    @Override
    public void destroyRelatedSprites(Scene scene) {
        scene.Destroy(_healthBarInside);
        scene.Destroy(_healthBarOutline);
        scene.Destroy(_moveCountdown);
        _moveCountdown.destroyCDSprites(scene);
    }

    @Override
    public void Delete() {
        destroyRelatedSprites(Scene.get_scene());
    }

    @Override
    public void start() { instantiateRelatedSprites(Scene.get_scene()); }

    @Override
    public void update() { super.update(); }

    @Override
    public void onKeyDown(int key) { super.update(); }
}
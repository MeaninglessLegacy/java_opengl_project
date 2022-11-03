package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.UI_Scripts.HealthBarInside;
import org.group11.Packages.Game.Scripts.UI_Scripts.HealthBarOutline;
import org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdown;

/**
 * Special enemy class, Runner runs away from the player when activated
 */
public class Runner extends Enemy{
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    // How many ticks before this Runner will disappear from the game
    private int _ticksUntilVanish;
    // this integer tells the game how much max health will be given to the Character who kills this Enemy
    public int maxHpGiven;
    // this integer tells the game how much attack will be given to the Character who kills this Enemy
    public int atkGiven;

    private SpriteRenderer characterSprite;

    //******************************************************************************************************************
    //* constructor methods
    //******************************************************************************************************************
    public Runner() {
        setupRunner();
    }

    public Runner(Vector3 pos) {
        transform.setPosition(pos);
        setupRunner();
    }

    private void setupRunner() {
        expGiven = 5;
        maxHpGiven = 1;
        atkGiven = 1;
        _moveTowards = "awayFromPlayer";

        // TODO: implement runner sprite
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
        _moveCountdown.instantiateCDSprites(scene);
    }

    @Override
    public void destroyRelatedSprites(Scene scene) {
        scene.Destroy(this);
        scene.Destroy(_healthBarInside);
        scene.Destroy(_healthBarOutline);
        scene.Destroy(_moveCountdown);
        _moveCountdown.destroyCDSprites(scene);
    }

    @Override
    public void giveRewards(MainCharacter MC) {
        MC.addExp(expGiven);
        MC.addAttack(atkGiven);
        MC.addMaxHealth(maxHpGiven);
        MC.addHealth(maxHpGiven);
    }

    @Override
    public void start() { super.start(); }

    @Override
    public void update() { super.update(); }
}

package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Logic.Map;
import org.group11.Packages.Game.Scripts.Logic.Pathfinder;
import org.group11.Packages.Game.Scripts.UI_Scripts.HealthBarInside;
import org.group11.Packages.Game.Scripts.UI_Scripts.HealthBarOutline;
import org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdown;

import static org.group11.Packages.Game.Scripts.Logic.GameLogicDriver.removeEnemy;

/**
 * Special enemy class, Runner runs away from the player when activated
 */
public class Runner extends Enemy{
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    // How many ticks before this Runner will disappear from the game
    protected int _ticksUntilVanish = 10;
    // this integer tells the game how much max health will be given to the Character who kills this Enemy
    protected int maxHpGiven;
    // this integer tells the game how much attack will be given to the Character who kills this Enemy
    protected int atkGiven;

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

        characterSprite = new SpriteRenderer(this, "./Resources/m1911.png");
        this.addComponent(characterSprite);
        characterSprite.get_sprite().transform.position.z -= 0.1; // place above tiles
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
    public void giveRewards(MainCharacter MC) {
        MC.addExp(expGiven);
        MC.addAttack(atkGiven);
        MC.addMaxHealth(maxHpGiven);
        MC.addHealth(maxHpGiven);
    }

    @Override
    public void canEnemyMove(Pathfinder _pathfinder, Map _gameMap, MainCharacter MC) {
        if (_enemyActive) {
            super.canEnemyMove(_pathfinder, _gameMap, MC);
            if (--_ticksUntilVanish == 0) {
                removeEnemy(this);
            }
        }
    }

    @Override
    public void start() { instantiateRelatedSprites(Scene.get_scene()); }

    @Override
    public void update() { super.update(); }
}

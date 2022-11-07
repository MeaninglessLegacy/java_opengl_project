package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Logic.Map;
import org.group11.Packages.Game.Scripts.Logic.Pathfinder;
import org.group11.Packages.Game.Scripts.UI_Scripts.HealthBarInside;
import org.group11.Packages.Game.Scripts.UI_Scripts.HealthBarOutline;
import org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdown;

import static org.group11.Packages.Game.Scripts.Logic.GameLogicDriver.enemyCheckMove;

/**
 * Special enemy class, Runner runs away from the player when activated
 */
public class Runner extends Enemy{
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    // How many ticks before this Runner will disappear from the game
    protected int _ticksUntilVanish = 20;
    // this integer tells the game how much max health will be given to the Character who kills this Enemy
    protected int maxHpGiven;
    // this integer tells the game how much attack will be given to the Character who kills this Enemy
    protected int atkGiven;

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
        expGiven = 0;
        maxHpGiven = 1;
        atkGiven = 1;

        characterSprite = new SpriteRenderer(this, "./Resources/m1911.png");
        this.addComponent(characterSprite);
        characterSprite.get_sprite().transform.position.z -= 0.1; // place above tiles
        _healthBarInside = new HealthBarInside(this);
        _healthBarOutline = new HealthBarOutline(this);
        _moveCountdown = new MoveCountdown(this);
    }

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************

    /**
     * Decrements this Runner's _ticksUntilVanish attribute
     * @return true if _ticksUntilVanish reaches 0, false if it hasn't yet
     */
    public boolean decrementTicksUntilVanish() {
        if (_enemyActive) {
            return --_ticksUntilVanish == 0;
        }
        return false;
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void canEnemyMove(Pathfinder _pathfinder, Map _gameMap, MainCharacter MC) {
        if (_enemyActive) {
            if (--_ticksBeforeNextMove == 0) {
                // Getting the next Tile the Runner will move on to
                System.out.println("Runner is getting nextMove");
                // TODO: figure out how to get a random point in opposite direction of player
                Vector3 farAwayPosition = new Vector3(30, 2, 0);
                Vector3 nextMove = _pathfinder.FindPath(_gameMap, this.transform.position, farAwayPosition);

                // Checking if the enemy can move onto that Tile enemy can move
                System.out.println("Checking if enemy can move");
                if (nextMove != null) {
                    Character characterInNextSpace = enemyCheckMove(this, nextMove);
                    /* After interacting with any character in the next tile, moves this Enemy if it can move or keeps
                       it in place if it can't */
                    if (characterInNextSpace == null) {
                        System.out.println("Enemy is moving");
                        this.transform.setPosition(nextMove);
                        _ticksBeforeNextMove = _ticksPerMove;
                    }
                    else if (characterInNextSpace instanceof MainCharacter) {
                        _ticksBeforeNextMove = _ticksPerMove;
                    }
                    else {
                        _ticksBeforeNextMove = 1;
                    }
                }
                else {
                    _ticksBeforeNextMove = 1;
                    System.out.println("Next move for enemy is null");
                }
            }
            // Changes the countdown sprite above the Enemy to reflect how many ticks until it moves
            _moveCountdown.changeCountdown(_ticksBeforeNextMove);
        }
    }

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
    public void start() { super.start(); }

    @Override
    public void update() { super.update(); }
}

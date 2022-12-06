package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Logic.GameLogicDriver;
import org.group11.Packages.Game.Scripts.Logic.Map;
import org.group11.Packages.Game.Scripts.Logic.Pathfinder;
import org.group11.Packages.Game.Scripts.UI_Scripts.HealthBar.HealthBarInside;
import org.group11.Packages.Game.Scripts.UI_Scripts.HealthBar.HealthBarOutline;
import org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdown;

import java.util.ArrayList;

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
        _ticksPerMove = 1;
        _ticksBeforeNextMove = 1;

        characterSprite = new SpriteRenderer(this, "./Resources/m1911.png");
        setupEnemySprites();
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
    /**
     * If the Runner is active, decrements their _ticksBeforeNextMove. If it decrements to 0, uses _pathfinder to find
     * the next tile on the path towards either the boss if it is alive, or the player if it is not,
     * then asks gameLogicDriver if they can move to it. Enemy reacts accordingly depending on what is on the next tile
     * @param _pathfinder used to find a path from the Enemy to their destination
     * @param _gameMap the map used by _pathfinder to find a path
     * @param MC the MainCharacter object, used to determine where the MainCharacter is for pathfinding
     */
    @Override
    public void canEnemyMove(Pathfinder _pathfinder, Map _gameMap, MainCharacter MC) {
        if (_enemyActive) {
            if (--_ticksBeforeNextMove == 0) {
                // Getting the next Tile the Runner will move on to
                System.out.println("Runner is getting nextMove");
                ArrayList<Enemy> enemyList = GameLogicDriver.getEnemyList();
                Vector3 positionToGoTo = null;
                for (Enemy e : enemyList) {
                    if (e instanceof Boss) {
                        positionToGoTo = e.transform.position;
                    }
                }
                if (positionToGoTo == null) {
                    positionToGoTo = MC.transform.position;
                }
                Vector3 nextMove = _pathfinder.FindPath(_gameMap, this.transform.position, positionToGoTo);

                // Checking if the enemy can move onto that Tile enemy can move
                System.out.println("Checking if runner can move");
                if (nextMove != null) {
                    Character characterInNextSpace = enemyCheckMove(this, nextMove);
                    /* After interacting with any character in the next tile, moves this Enemy if it can move or keeps
                       it in place if it can't */
                    if (characterInNextSpace == null) {
                        System.out.println("Enemy is moving");
                        this.transform.setPosition(nextMove);
                        _ticksBeforeNextMove = _ticksPerMove;
                    }
                    else if (characterInNextSpace instanceof MainCharacter ||
                             characterInNextSpace instanceof Enemy) {
                        _ticksBeforeNextMove = 1;
                    }
                    else {
                        System.out.println("Undefined movement behaviour, check Runner class");
                    }
                }
                else {
                    _ticksBeforeNextMove = 1;
                    System.out.println("Next move for runner is null");
                }
            }
            // Changes the countdown sprite above the Enemy to reflect how many ticks until it moves
            _moveCountdown.changeCountdown(_ticksBeforeNextMove);
        }
    }

    @Override
    public void giveRewards(MainCharacter MC) {
        MC.addExp(expGiven);
        MC.addAttack(atkGiven);
        MC.addMaxHealth(maxHpGiven);
        MC.addHealth(maxHpGiven);
    }
}

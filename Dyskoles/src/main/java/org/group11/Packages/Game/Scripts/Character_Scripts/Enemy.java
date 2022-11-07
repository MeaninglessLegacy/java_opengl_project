package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Game.Scripts.Logic.Map;
import org.group11.Packages.Game.Scripts.Logic.Pathfinder;
import org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdown;

import static org.group11.Packages.Game.Scripts.Logic.GameLogicDriver.enemyCheckMove;

/**
 * Abstract Character class, adds methods for Enemy character types
 */
public abstract class Enemy extends Character{
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    // used to help render and control the sprite
    protected SpriteRenderer characterSprite;
    protected boolean facingRight = true;
    double time; // time since last update
    double x; // character scaling parameter for breathing effect
    // counter, when this counter reaches 0 it means that this character can make another move
    protected int _ticksBeforeNextMove = 2;
    /* counter reset value, when this character makes a move, this character's _ticksBeforeNextMove counter should be
    set to this value*/
    protected int _ticksPerMove = 2;
    // this boolean tracks if this character should perform enemy logic
    protected boolean _enemyActive = false;
    // this integer tells the game how much exp will be given to the Character who kills this Enemy
    public int expGiven = 1;
    // displays how many ticks until this enemy moves above their sprite
    protected MoveCountdown _moveCountdown;

    //******************************************************************************************************************
    //* setters and getters
    //******************************************************************************************************************
    /**
     * Sets the _enemyActive state of this character to specified value
     * @param state the value to set _enemyActive to
     */
    public void set_enemyActiveState(boolean state) { _enemyActive = state; }

    /**
     * Returns whether the enemy is active and is moving around
     * @return true if enemy is active, false if not
     */
    public boolean get_enemyActiveState() { return _enemyActive; }

    /**
     * Sets the _ticksBeforeNextMove value of this character to specified value
     * @param ticks the value to set _ticksBeforeNextMove to
     */
    public void set_ticksBeforeNextMove(int ticks) { _ticksBeforeNextMove = ticks; }

    /**
     * Returns the _ticksBeforeNextMove value of this Enemy
     * @return the _ticksBeforeNextMOve value of this Enemy
     */
    public int get_ticksBeforeNextMove() {
        return _ticksBeforeNextMove;
    }

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    /**
     * Sets the sprite displaying the number of moves before
     */
    public void set_moveCountdownNumber(int num) {
        _moveCountdown.changeCountdown(num);
    }

    /**
     * If an enemy is active, decrements their _ticksBeforeNextMove. If it decrements to 0, uses _pathfinder to find
     * the next tile to go to then asks gameLogicDriver if they can move to it. Enemy reacts accordingly depending on
     * what is on the next tile
     * @param _pathfinder used to find a path from the Enemy to their destination
     * @param _gameMap the map used by _pathfinder to find a path
     * @param MC the MainCharacter object, used to determine where the MainCharacter is for pathfinding
     */
    public void canEnemyMove(Pathfinder _pathfinder, Map _gameMap, MainCharacter MC) {
        if (_enemyActive) {
            if (--_ticksBeforeNextMove == 0) {
                // Getting the next Tile the Enemy will move on to
                System.out.println("Regular Enemy is getting nextMove");
                Vector3 nextMove = _pathfinder.FindPath(_gameMap, this.transform.position, MC.transform.position);

                // Checking if the enemy can move onto that Tile
                System.out.println("Checking if enemy can move");
                if (nextMove != null) {
                    Character characterInNextSpace = enemyCheckMove(this, nextMove);
                    /* After interacting with any character in the next tile, moves this Enemy if it can move or keeps
                       it in place if it can't */
                    if (characterInNextSpace == null) {
                        System.out.println("Enemy is moving");
                        if (nextMove.x < this.transform.position.x && facingRight) {
                            facingRight = false;
                            characterSprite.get_sprite().flipX();
                        }
                        else if (nextMove.x > this.transform.position.x && !facingRight) {
                            facingRight = true;
                            characterSprite.get_sprite().flipX();
                        }
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

    /**
     * When this Enemy is defeated calling this method gives the MainCharacter who defeated this Enemy certain rewards
     * @param MC the MainCharacter that defeated this Enemy
     */
    public void giveRewards(MainCharacter MC) { MC.addExp(expGiven); }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void update() {
        super.update();
        double timePassed = System.currentTimeMillis() - time;
        if(x < 2) {
            x += timePassed / 500;
        }else{
            x = 0;
        }
        double yScale = -Math.pow((x-1),4)+1;
        characterSprite.get_sprite().set_scale(1, (float)(1+0.05*yScale));
        time = System.currentTimeMillis();
        super.update();
    }
}

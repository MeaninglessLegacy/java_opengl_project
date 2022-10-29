package org.group11.Packages.Game.Scripts.Character_Scripts;

/**
 * Abstract character class, add methods for enemy character types
 */
public abstract class Enemy extends Character{
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    // counter, when this counter reaches 0 it means that this character can make another move
    protected int _ticksBeforeNextMove;
    /* counter reset value, when this character makes a move, this character's _ticksBeforeNextMove counter should be
    set to this value*/
    protected int _ticksPerMove;
    // this boolean tracks if this character should perform enemy logic
    protected boolean _enemyActive = false;
    // this string tells the game logic where this character moves towards when activated
    protected String _moveTowards = "player";
    // this integer tells the game how much exp will be given to the Character who kills this Enemy
    public int expGiven = 1;

    /**
     * Sets the _enemyActive state of this character to specified value
     * @param state the value to set _enemyActive to
     */
    public void set_enemyActiveState(boolean state) {
        _enemyActive = state;
    }

    /**
     * Returns whether the enemy is active and is moving around
     * @return true if enemy is active, false if not
     */
    public boolean get_enemyActiveState() { return _enemyActive; }

    /**
     * Returns the String containing where the Enemy moves towards
     * @return String that describes where the Enemy moves towards
     */
    public String get_moveTowards() { return _moveTowards; }

    /**
     * if the enemy is active, subtracts 1 from _ticksBeforeNextMove and if the values becomes 0, resets
     * _ticksBeforeNextMove and returns true, signalling this Enemy will move
     * @return true if the Enemy can move a tile, false if not
     */
    public boolean canEnemyMove() {
        if (_enemyActive) {
            if (--_ticksBeforeNextMove == 0) {
                _ticksBeforeNextMove = _ticksPerMove;
                return true;
            }
        }
        return false;
    }
}

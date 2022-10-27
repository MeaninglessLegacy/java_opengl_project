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
    public boolean enemyActive = false;
    /**
     * Sets the _enemyActive state of this character to specified value
     * @param state the value to set _enemyActive to
     */
    public void setEnemyActiveState(boolean state) {
        enemyActive = state;
    }

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    /**
     * Tells this character to perform enemy logic, can be overridden by children
     */
    public void doEnemyLogic() {
        _ticksBeforeNextMove--;
        if (_ticksBeforeNextMove == 0) {
            // TODO: BFS then move one tile towards main character
            _ticksBeforeNextMove = _ticksPerMove;
        }
    }
}

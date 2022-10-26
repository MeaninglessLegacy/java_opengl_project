package org.group11.characters;

public abstract class Enemy extends Character{

    protected int _ticksBeforeNextMove, _ticksPerMove;
    protected boolean _enemyActive = false;

    public void setEnemyActiveState(boolean state) {
        _enemyActive = state;
    }

    /**
     *
     */
    public void doEnemyLogic() {
        _ticksBeforeNextMove--;
        if (_ticksBeforeNextMove == 0) {
            // TODO: BFS then move one tile towards main character
            _ticksBeforeNextMove = _ticksPerMove;
        }
    }
}

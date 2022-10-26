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
        // TODO: implement method
        if (--_ticksBeforeNextMove == 0);
        // BFS then move one tile towards main character
    }
}

package org.group11.characters;

public abstract class Enemy extends Character{

    protected int _ticksBeforeNextMove, _ticksPerMove;
    protected boolean _enemyActive;

    public void setEnemyActiveState(boolean state) {
        _enemyActive = state;
    }

    /**
     *
     */
    public void doEnemyLogic() {
        // TODO: implement method
        // BFS then move one tile towards main character
    }
}

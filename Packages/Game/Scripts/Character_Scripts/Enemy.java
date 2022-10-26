package org.group11.characters;

public abstract class Enemy extends Character{

    protected int ticksBeforeNextMove, ticksPerMove;
    protected boolean enemyActive;

    public void setEnemyActiveState(boolean state) {
        enemyActive = state;
    }

    public void doEnemyLogic() {
        // TODO: implement method
    }
}

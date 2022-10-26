package org.group11.characters;

public class StatBlock {

    protected int lvl = 0;
    protected int exp = 0;

    protected int hp = 1;
    protected int maxHp = 1;

    protected int atk = 0;

    public int getLvl() { return this.lvl; }
    public void setLvl(int lvl) { this.lvl = lvl; }
    public int getExp() { return this.exp; }
    public void setExp(int exp) { this.exp = exp; }

    public int getHp() { return this.hp; }
    public void setHp(int Hp) { this.hp = Hp; }
    public int getMaxHp() { return this.maxHp; }
    public void setMaxHp(int maxHp) { this.maxHp = maxHp; }

    public int getAtk() { return this.atk; }
    public void setAtk(int atk) { this.atk = atk; }
}

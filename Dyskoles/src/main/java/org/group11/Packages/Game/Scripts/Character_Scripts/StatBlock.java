package org.group11.characters;

public class StatBlock {

    protected int _lvl = 0;
    protected int _exp = 0;

    protected int _hp = 1;
    protected int _maxHp = 1;

    protected int _atk = 0;

    public int getLvl() { return this._lvl; }
    public void setLvl(int lvl) { this._lvl = lvl; }
    public int getExp() { return this._exp; }
    public void setExp(int exp) { this._exp = exp; }

    public int getHp() { return this._hp; }
    public void setHp(int Hp) { this._hp = Hp; }
    public int getMaxHp() { return this._maxHp; }
    public void setMaxHp(int maxHp) { this._maxHp = maxHp; }

    public int getAtk() { return this._atk; }
    public void setAtk(int atk) { this._atk = atk; }
}

package org.group11.Packages.Game.Scripts.Character_Scripts;

/**
 * A list of stat values for Character objects
 */
public class StatBlock {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    protected int _lvl = 0;
    protected int _exp = 0;

    protected int _hp = 1;
    protected int _maxHp = 1;

    protected int _atk = 0;

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    public int get_lvl() { return this._lvl; }
    public void set_lvl(int lvl) { this._lvl = lvl; }
    public int get_exp() { return this._exp; }
    public void set_exp(int exp) { this._exp = exp; }

    public int get_hp() { return this._hp; }
    public void set_hp(int Hp) { this._hp = Hp; }
    public int get_maxHp() { return this._maxHp; }
    public void set_MaxHp(int maxHp) { this._maxHp = maxHp; }

    public int get_atk() { return this._atk; }
    public void set_Atk(int atk) { this._atk = atk; }
}
